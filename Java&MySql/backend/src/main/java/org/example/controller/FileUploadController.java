package org.example.controller;

import org.apache.logging.log4j.message.ReusableMessage;
import org.example.pojo.Result;
import org.example.utls.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //store the content of the file to local disk.
        String  originalfilename = file.getOriginalFilename();

        //ensure the filename is unique to prevent file from overwriting.
        String filename = UUID.randomUUID().toString() + originalfilename.substring(originalfilename.lastIndexOf("."));
        //file.transferTo(new File("G:\\MyProjects\\mycode\\JAVA\\test\\big-event\\uploadfiles\\"+filename));
        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }
}
