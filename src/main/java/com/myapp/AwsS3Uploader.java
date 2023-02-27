package com.myapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;

@Component
public class AwsS3Uploader {
		
		private static final Logger logger = LoggerFactory.getLogger(AwsS3Uploader.class);
		
		@Autowired
		private AmazonS3Client amazonS3Client;

	    @Value("yeoulserver")
	    public String bucket; 

	    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
	        File uploadFile = convert(multipartFile)        // 파일 생성
	                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File convert fail"));

	        return upload(uploadFile, dirName);
	    }

	    private String upload(File uploadFile, String dirName) {
	        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();
	        String uploadImageUrl = putS3(uploadFile, fileName);    // s3로 업로드
	        removeNewFile(uploadFile);
	        return uploadImageUrl;
	    }

	    // 1. 로컬에 파일생성
	    private Optional<File> convert(MultipartFile file) throws IOException {
	        File convertFile = new File(file.getOriginalFilename());
	        if (convertFile.createNewFile()) {
	            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
	                fos.write(file.getBytes());
	            }
	            return Optional.of(convertFile);
	        }

	        return Optional.empty();
	    }

	    // 2. S3에 파일업로드
	    private String putS3(File uploadFile, String fileName) {
	        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
	        logger.info("File Upload : " + fileName);
	        return amazonS3Client.getUrl(bucket, fileName).toString();
	    }

	    // 3. 로컬에 생성된 파일삭제
	    private void removeNewFile(File targetFile) {
	        if (targetFile.delete()) {
	            logger.info("File delete success");
	            return;
	        }
	        logger.info("File delete fail");
	    }


	    public void delete(String fileName) {
	        logger.info("File Delete : " + fileName);
	        amazonS3Client.deleteObject(bucket, fileName);
	    }
}
