package com.ai.service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {

    private String courseFilePath = new File("src\\main\\resources\\static\\courses\\").getAbsolutePath();

    public void createFile(MultipartFile file, String folderName) throws IllegalStateException, IOException {
        file.transferTo(
                new File(courseFilePath + folderName + "\\" + file.getOriginalFilename()));
    }

    public boolean deleteFile(String path) {
        return new File(path).delete();
    }

    public void createFolderForModule(String courseName) {
        var theDir = new File(courseFilePath + "\\"+courseName);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        var videoFolder = new File(courseFilePath + "\\"+courseName + "\\videos");
        if (!videoFolder.exists()) {
            videoFolder.mkdirs();
        }
        var resourceFolder = new File(courseFilePath + "\\"+courseName + "\\resources");
        if (!resourceFolder.exists()) {
            resourceFolder.mkdirs();
        }
    }

    public void renameFolder(String oldFolder, String newFolder) throws IOException {
        var data = Paths.get(courseFilePath, oldFolder);
        Files.move(data, data.resolveSibling(newFolder));
    }

    public void deleteFolder(String folderName) throws IOException {
        var folder = new File(courseFilePath.concat("\\").concat(folderName));
        FileUtils.deleteDirectory(folder);
    }
}
