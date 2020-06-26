package com.example.demo.ServiceImpl;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
import com.example.demo.domain.SalaryInfo;
import com.example.demo.domain.UserInfo;
import com.example.demo.formDetail.UserInfoRegisterFormDetail;
import com.example.demo.mapper.SalaryInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoRegisterService;
import com.example.demo.utils.ImageUtils;
import com.example.demo.utils.StringUtils;

@Service
public class UserInfoRegisterServiceImpl implements UserInfoRegisterService {

	@Autowired
	StringUtils stringUtils;
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	SalaryInfoMapper salaryInfoMapper;
	@Autowired
	ImageUtils imageUtils;

	@Override
	public UserInfoRegisterFormDetail UserInfoRegisterInit() {
		UserInfoRegisterFormDetail userInfoRegisterFormDetail = new UserInfoRegisterFormDetail();
		File file = new File(ConstantsData.FILE_UPLOAD_PATH);
		if (!(file.exists())) {
			file.mkdir();
		}
		userInfoRegisterFormDetail.setSelectPosition(itemReturn());
		return userInfoRegisterFormDetail;

	}

	@Override
	public UserInfoRegisterFormDetail imageUpload(UserInfoRegisterFormDetail form) {

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
		//tipartFile型 image はもう不要なので削除
		form.setImage(null);

		return form;

	}

	@Override
	public UserInfoRegisterFormDetail imageDelete(UserInfoRegisterFormDetail userInfoRegisterFormDetail) {

		//画像関連のデータをすべて削除
		userInfoRegisterFormDetail.setImage(null);
		userInfoRegisterFormDetail.setImageName(null);
		userInfoRegisterFormDetail.setImageOutput(null);
		userInfoRegisterFormDetail.setImageErrMessage(null);
		userInfoRegisterFormDetail.setImageData(null);

		imageUtils.DeleteUploadFile();

		return userInfoRegisterFormDetail;
	}

	@Override
	public UserInfoRegisterFormDetail UserInfoRegister(UserInfoRegisterFormDetail form) {

		/*エラーフラグ　エラーメッセージ　メッセージ　プルダウン　初期化*/
		form = FormReset(form);

		/*入力データのトリム*/
		form.setInputName(stringUtils.trim(form.getInputName()));
		form.setInputPosition(stringUtils.trim(form.getInputPosition()));
		form.setInputBasicSalary(stringUtils.trim(form.getInputBasicSalary()));

		//登録
		boolean result = true;
		try {

			//userinfo準備
			UserInfo userInfoToInsert = new UserInfo();
			//社員番号（自動採番）
			String strId = userInfoMapper.selectMaxId();
			int id = 0;
			if (stringUtils.isEmpty(strId)) {
				id = 1;
			} else {
				id = Integer.parseInt(strId) + 1;
			}
			userInfoToInsert.setId(String.valueOf(String.format("%04d", id)));
			//フリガナ
			userInfoToInsert.setNameReading(form.getInputNameReading());
			//氏名
			userInfoToInsert.setName(form.getInputName());
			//メールアドレス
			userInfoToInsert.setMailAddress(form.getInputMailAddress());
			//パスワード
			userInfoToInsert.setPassword(form.getInputPassword());
			//認証失敗回数
			userInfoToInsert.setFailureTimes("0");
			//登録者
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			userInfoToInsert.setRegistrant(auth.getName());
			//登録日
			userInfoToInsert.setRegistrationDate(Date.valueOf(stringUtils.getNowDate()));
			//画像名
			userInfoToInsert.setImageName(form.getImageName());
			//画像
			try {
				String s = ConstantsData.FILE_UPLOAD_PATH + "\\" + form.getImageName();
				BufferedImage bImage = ImageIO
						.read(new File(s));
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				BufferedOutputStream os = new BufferedOutputStream(bos);
				bImage.flush();
				//拡張子の取得
				String extension = form.getImageName()
						.substring(form.getImageName().lastIndexOf(".") + 1);
				ImageIO.write(bImage, extension, os); //. png 型
				userInfoToInsert.setImageData(bos.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}

			//salaryInfo準備
			SalaryInfo salaryInfoToInsert = new SalaryInfo();
			//社員番号
			salaryInfoToInsert.setId(userInfoToInsert.getId());
			//役職
			salaryInfoToInsert.setPosition(form.getInputPosition());
			//基本給
			salaryInfoToInsert.setBasicSalary(Integer.parseInt(form.getInputBasicSalary()));

			//userInfoのインサート
			userInfoMapper.insertUserInfo(userInfoToInsert);

			//salaryInfoのインサート
			salaryInfoMapper.insertSalaryInfo(salaryInfoToInsert);

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		if (result == true) {
			form.setInputNameReading(null);
			form.setInputName(null);
			form.setInputMailAddress(null);
			form.setInputPassword(null);
			form.setInputPosition(null);
			form.setInputBasicSalary(null);
			form.setImageName(null);
			form.setImageData(null);
			form.setImage(null);
			//uploadFileにデータがある場合、削除
			File file = new File(ConstantsData.FILE_UPLOAD_PATH);
			File[] files = file.listFiles();
			if (files.length != 0) {
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			form.setMessage(ConstantsMsg.MSG_REGISTER_OK);
		} else {
			form.setMessage(ConstantsMsg.MSG_REGISTER_NG);
		}

		return form;
	}

	/**
	 * itemReturnメソッド
	 * プルダウンの値を返却する.
	 * @return
	 */
	public List<String> itemReturn() {
		List<String> tmpList = new ArrayList<String>();

		tmpList.add("Administrater");
		tmpList.add("Member");

		return tmpList;
	}

	/**
	 * FormResetメソッド
	 * UserInfoRegisterFormDetailの下記の値を初期化する。
	 * 各項目のエラーフラグ
	 * 各項目のエラーメッセージ
	 * DB登録の結果メッセージ.
	 * @param form UserInfoRegisterFormDetail
	 * @return 初期化後の UserInfoRegisterFormDetail
	 */
	public UserInfoRegisterFormDetail FormReset(UserInfoRegisterFormDetail form) {

		form.setMessage("");
		form.setSelectPosition(stringUtils.itemColumnsShaping(form.getSelectPosition()));
		return form;

	}

}
