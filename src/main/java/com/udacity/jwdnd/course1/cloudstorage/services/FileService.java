package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.exception.StorageException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {
    private FileMapper fileMapper;
    private  UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }


    public File getFile(String filename){
        return fileMapper.getFile(filename);
    }

    public String[] getFiles(Integer userID){
        return fileMapper.getFiles(userID);
    }

    public int inserFile(MultipartFile multipartFile,String userName){
        try{
            if(multipartFile.isEmpty()){
                throw new StorageException("Failed to store empty file.");
            }
            byte[] fileData;
            try(InputStream inputStream = multipartFile.getInputStream()){
                fileData = inputStream.readAllBytes();
            }
            String fileName = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            String fileSize = String.valueOf(multipartFile.getSize());
            Integer userId = userService.getUser(userName).getUserid();
            File file = new File(null, fileName, contentType, fileSize, userId, fileData);
            return fileMapper.insertFile(file);

        }catch (IOException e){
            throw  new StorageException("Failed to store file.", e);
        }
    }

    public void deleteFile(String filename){
        fileMapper.deleteFile(filename);
    }


}
