package com.example.demo.ServiceImpl;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.constants.ConstantsData;
import com.example.demo.constants.message.ConstantsMsg;
import com.example.demo.formDetail.UserInfoUpdateFormDetail;
import com.example.demo.formDetail.UserInfoUpdateListFormDetail;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoUpdateService;
import com.example.demo.utils.ImageUtils;
import com.example.demo.utils.StringUtils;

@Service
public class UserInfoUpdateServiceImpl implements UserInfoUpdateService {

	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	SalaryInfoMapper salaryInfoMapper;
	@Autowired
	StringUtils stringUtils;
	@Autowired
	ImageUtils imageUtils;
	@Autowired
	UserInfoUpdateListServiceImpl userInfoUpdateListServiceImpl;

	@Override
	public UserInfoUpdateFormDetail UserInfoUpdateInit(String id) {

		//データの取得
		UserInfoUpdateFormDetail userInfoUpdateFormDetail = userInfoMapper.selectUserInfoForUpdate(id);

		File file = new File(ConstantsData.FILE_UPLOAD_PATH);
		if (!(file.exists())) {
			file.mkdir();
		}

		imageUtils.DeleteUploadFile();

		//画像表示用変換＋uploadFileに画像をダウンロード
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getImageName()))) {
			try {
				userInfoUpdateFormDetail.setImageOutput(imageUtils.GetImageOutputString(
						userInfoUpdateFormDetail.getImageName(), userInfoUpdateFormDetail.getImageData()));
			} catch (UnsupportedEncodingException e) {
				userInfoUpdateFormDetail.setImageErrMessage("エンコードに失敗しました。");
				e.printStackTrace();
			}

			Path uploadfilePath = Paths
					.get(ConstantsData.FILE_UPLOAD_PATH + "\\" + userInfoUpdateFormDetail.getImageName());
			try (OutputStream os = Files.newOutputStream(uploadfilePath, StandardOpenOption.CREATE)) {

				os.write(userInfoUpdateFormDetail.getImageData());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//基本給の型変換
		userInfoUpdateFormDetail.setBasicSalaryStr(String.valueOf(userInfoUpdateFormDetail.getBasicSalary()));

		//役職のプルダウンを設定
		userInfoUpdateFormDetail.setSelectPosition(itemReturn());

		return userInfoUpdateFormDetail;
	}

	@Override
	public UserInfoUpdateListFormDetail UserInfoUpdateBack(UserInfoUpdateFormDetail userInfoUpdateFormDetail) {

		//一覧に戻るために再検索
		UserInfoUpdateListFormDetail userInfoUpdateListFormDetail = new UserInfoUpdateListFormDetail();
		userInfoUpdateListFormDetail
				.setUserInfoUpdateListFormList(userInfoUpdateListServiceImpl.UserInfoSelectForUpdateList());

		imageUtils.DeleteUploadFile();

		return userInfoUpdateListFormDetail;

	}

	@Override
	public UserInfoUpdateFormDetail imageUpload(UserInfoUpdateFormDetail form) {

		//０バイト、未選択チェック
		if (form.getImage().isEmpty()) {
			form.setImageErrMessage("選択された画像が不正、または選択されていません。");
			return form;
		}

		//拡張子チェック（JPEG/PNG/GIF許容）
		String extension = form.getImage().getOriginalFilename()
				.substring(form.getImage().getOriginalFilename().lastIndexOf(".") + 1);
		if (!(extension.equalsIgnoreCase("JPEG") || extension.equalsIgnoreCase("JPG")
				|| extension.equalsIgnoreCase("PNG"))) {
			form.setImageErrMessage(
					"選択された画像の拡張子が不正です。許容されている拡張子は「JPEG(JPG)」「PNG」です。");
			return form;
		}

		//チェックOK＋アップロード
		form.setImageName(form.getImage().getOriginalFilename());
		Path uploadfilePath = Paths.get(ConstantsData.FILE_UPLOAD_PATH + "\\" + form.getImageName());
		try (OutputStream os = Files.newOutputStream(uploadfilePath, StandardOpenOption.CREATE)) {
			byte[] bytes = form.getImage().getBytes();
			os.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//画像表示用変換
		if (!(stringUtils.isEmpty(form.getImageName()))) {
			try {
				form.setImageOutput(imageUtils.GetImageOutputString(
						form.getImageName(), form.getImage().getBytes()));
			} catch (IOException e) {
				form.setImageErrMessage("エンコードに失敗しました。");
				e.printStackTrace();
			}
		}

		//MultipartFile型 image はもう不要なので削除
		form.setImage(null);

		return form;

	}

	@Override
	public UserInfoUpdateFormDetail imageDelete(UserInfoUpdateFormDetail userInfoUpdateFormDetail) {

		//画像関連のデータをすべて削除
		userInfoUpdateFormDetail.setImage(null);
		userInfoUpdateFormDetail.setImageName(null);
		userInfoUpdateFormDetail.setImageOutput(null);
		userInfoUpdateFormDetail.setImageErrMessage(null);
		userInfoUpdateFormDetail.setImageData(null);

		//uploadFileにデータがある場合、削除
		imageUtils.DeleteUploadFile();

		return userInfoUpdateFormDetail;
	}

	@Override
	public UserInfoUpdateFormDetail UserInfoUpdate(UserInfoUpdateFormDetail userInfoUpdateFormDetail) {

		//いろいろ初期化
		userInfoUpdateFormDetail = formReset(userInfoUpdateFormDetail);

		//入力データのトリム
		userInfoUpdateFormDetail = UserInfoUpdateFormDetailAllTrim(userInfoUpdateFormDetail);

		//更新者の設定
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userInfoUpdateFormDetail.setUpdater(auth.getName());
		//更新日の設定
		userInfoUpdateFormDetail.setUpdatedDate(Date.valueOf(stringUtils.getNowDate()));
		//基本給の設定
		userInfoUpdateFormDetail.setBasicSalary(Integer.parseInt(userInfoUpdateFormDetail.getBasicSalaryStr()));
		//画像データを設定
		try {
			String s = ConstantsData.FILE_UPLOAD_PATH + "\\" + userInfoUpdateFormDetail.getImageName();
			BufferedImage bImage = ImageIO
					.read(new File(s));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			BufferedOutputStream os = new BufferedOutputStream(bos);
			bImage.flush();
			//拡張子の取得
			String extension = userInfoUpdateFormDetail.getImageName()
					.substring(userInfoUpdateFormDetail.getImageName().lastIndexOf(".") + 1);
			ImageIO.write(bImage, extension, os); //. png 型
			userInfoUpdateFormDetail.setImageData(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

		//更新
		userInfoMapper.updateUserInfo(userInfoUpdateFormDetail);
		salaryInfoMapper.updateSalaryInfo(userInfoUpdateFormDetail);

		//画像データは重いので削除
		userInfoUpdateFormDetail.setImageData(null);

		userInfoUpdateFormDetail.setMessage(ConstantsMsg.MSG_UPDATE_OK);

		return userInfoUpdateFormDetail;
	}

	@Override
	public UserInfoUpdateFormDetail UserInfoUpdateFormDetailAllTrim(UserInfoUpdateFormDetail userInfoUpdateFormDetail) {
		//トリム
		if (!(stringUtils.isEmpty(stringUtils.trim(userInfoUpdateFormDetail.getId())))) {
			userInfoUpdateFormDetail.setId(userInfoUpdateFormDetail.getId());
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getNameReading()))) {
			userInfoUpdateFormDetail.setNameReading(stringUtils.trim(userInfoUpdateFormDetail.getNameReading()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getName()))) {
			userInfoUpdateFormDetail.setName(stringUtils.trim(userInfoUpdateFormDetail.getName()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getMailAddress()))) {
			userInfoUpdateFormDetail.setMailAddress(stringUtils.trim(userInfoUpdateFormDetail.getMailAddress()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getPassword()))) {
			userInfoUpdateFormDetail.setPassword(stringUtils.trim(userInfoUpdateFormDetail.getPassword()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getPosition()))) {
			userInfoUpdateFormDetail.setPosition(stringUtils.trim(userInfoUpdateFormDetail.getPosition()));
		}
		if (!(stringUtils.isEmpty(userInfoUpdateFormDetail.getUpdater()))) {
			userInfoUpdateFormDetail.setUpdater(stringUtils.trim(userInfoUpdateFormDetail.getUpdater()));
		}
		return userInfoUpdateFormDetail;
	}

	@Override
	public List<String> itemReturn() {
		List<String> tmpList = new ArrayList<String>();

		tmpList.add("Administrater");
		tmpList.add("Member");

		return tmpList;
	}

	public UserInfoUpdateFormDetail formReset(UserInfoUpdateFormDetail form) {

		form.setMessage("");
		form.setSelectPosition(stringUtils.itemColumnsShaping(form.getSelectPosition()));
		return form;
	}
}
