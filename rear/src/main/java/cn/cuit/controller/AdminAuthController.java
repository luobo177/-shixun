package cn.cuit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {
    
    @Autowired
    private AdminService adminService;
    
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        try {
            String token = adminService.login(loginDTO.getUsername(), loginDTO.getPassword());
            return Result.success(token);
        } catch (Exception e) {
            return Result.error("登录失败：" + e.getMessage());
        }
    }
    
    @PostMapping("/logout")
    public Result logout() {
        try {
            adminService.logout();
            return Result.success("退出成功");
        } catch (Exception e) {
            return Result.error("退出失败：" + e.getMessage());
        }
    }
    
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        try {
            String url = fileService.uploadFile(file);
            return Result.success(url);
        } catch (Exception e) {
            return Result.error("上传失败：" + e.getMessage());
        }
    }
} 