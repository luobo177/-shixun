package cn.cuit.service.impl;

import cn.cuit.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    
    @Value("${file.upload-path}")
    private String uploadPath;
    
    @Value("${file.access-path}")
    private String accessPath;
    
    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // 生成文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + extension;
            
            // 确保目录存在
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // 保存文件
            File destFile = new File(uploadPath + fileName);
            file.transferTo(destFile);
            
            // 返回访问路径
            return accessPath + fileName;
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败：" + e.getMessage());
        }
    }
    
    @Override
    public void deleteFile(String fileUrl) {
        try {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            File file = new File(uploadPath + fileName);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            throw new RuntimeException("文件删除失败：" + e.getMessage());
        }
    }
} 