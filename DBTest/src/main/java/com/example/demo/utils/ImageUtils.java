package com.example.demo.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.constants.ConstantsData;

@Component
public class ImageUtils {

	@Autowired
	StringUtils stringUtils;

	public String GetImageOutputString(String imageName, byte[] imageData) throws UnsupportedEncodingException {

		String result = null;

		if (!(stringUtils.isEmpty(imageName)) && imageData != null) {

			//拡張子の取得
			String extension = imageName.substring(imageName.lastIndexOf(".") + 1);

			String base64;
			base64 = new String(Base64.encodeBase64(imageData), "ASCII");
			StringBuffer data = new StringBuffer();
			data.append("data:image/");
			data.append(extension);
			data.append(";base64,");
			data.append(base64);
			result = data.toString();

		}

		return result;
	}

	public void DeleteUploadFile() {
		//uploadFileにデータがある場合、削除
		File file = new File(ConstantsData.FILE_UPLOAD_PATH);
		File[] files = file.listFiles();
		if (files.length != 0) {
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
			}
		}
	}
}
