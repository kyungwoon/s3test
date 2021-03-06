package com.example.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class AwsController {

    private final S3Uploader s3Uploader;

    @PostMapping("/images/upload")
    public HashMap<String,String> upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        System.out.println("이미지 전송 중");

        String imgUrl = "";
        String fileName = "";
        HashMap<String,String> imgInfo = s3Uploader.upload(multipartFile, "static");
        imgUrl = imgInfo.get("img");

        System.out.println("이미지 url : " + imgUrl );

        fileName = imgInfo.get("fileName");

        HashMap<String,String> imageUrl = new HashMap<>();
        imageUrl.put("url",imgUrl);

        return imageUrl;
    }

    // 사진 삭제 테스트
    @DeleteMapping("/image/delete")
    public String deleteimage(@RequestParam("path") String fileName) {
        s3Uploader.deleteFile(fileName);
        return "삭제완료";
    }
}



