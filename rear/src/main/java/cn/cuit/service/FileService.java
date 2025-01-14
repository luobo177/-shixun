package cn.cuit.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    
    // 上传文件
    String uploadFile(MultipartFile file);
    
    // 删除文件
    void deleteFile(String fileUrl);
} 