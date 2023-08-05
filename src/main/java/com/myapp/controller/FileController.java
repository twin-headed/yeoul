package com.myapp.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileController {

	@Autowired
	private AmazonS3Client amazonS3Client;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload (@RequestParam("file") MultipartFile multipartFile) throws IOException{
		System.out.println(multipartFile);
		String originalName = multipartFile.getOriginalFilename(); // 파일 이름
		String fileName = UUID.randomUUID() + originalName;
		long size = multipartFile.getSize(); // 파일 크기

		ObjectMetadata objectMetaData = new ObjectMetadata();
		objectMetaData.setContentType(multipartFile.getContentType());
		objectMetaData.setContentLength(size);

		// S3에 업로드
		amazonS3Client.putObject(
				new PutObjectRequest("yeoulserver", fileName, multipartFile.getInputStream(), objectMetaData)
						.withCannedAcl(CannedAccessControlList.PublicRead)
		);

		String imagePath = amazonS3Client.getUrl("yeoulserver", originalName).toString(); // 접근가능한 URL 가져오기
        return imagePath;
	}

}
