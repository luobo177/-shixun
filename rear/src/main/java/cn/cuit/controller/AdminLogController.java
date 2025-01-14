package cn.cuit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminLogController {
    
    @Autowired
    private LogService logService;
    
    @GetMapping("/getLogs")
    public Result getLogs(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String type
    ) {
        try {
            PageHelper.startPage(page, pageSize);
            List<Log> logs = logService.getLogList(startDate, endDate, type);
            PageInfo<Log> pageInfo = new PageInfo<>(logs);
            
            return Result.success(pageInfo);
        } catch (Exception e) {
            return Result.error("获取日志失败：" + e.getMessage());
        }
    }
    
    @DeleteMapping("/clearLogs")
    public Result clearLogs(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        try {
            logService.clearLogs(startDate, endDate);
            return Result.success("清除成功");
        } catch (Exception e) {
            return Result.error("清除失败：" + e.getMessage());
        }
    }
} 