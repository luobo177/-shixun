package cn.cuit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminOverviewController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/getTotalData")
    public Result getTotalData() {
        try {
            Map<String, Object> data = new HashMap<>();
            
            // 获取总人数
            data.put("totalStudent", studentService.getTotalCount());
            
            // 获取性别统计
            data.put("boyCount", studentService.getCountByGender("男"));
            data.put("girlCount", studentService.getCountByGender("女"));
            
            // 获取报到人数
            data.put("reportedStudent", studentService.getReportedCount());
            
            // 获取专业统计
            List<Map<String, Object>> majorList = studentService.getMajorStatistics();
            data.put("majorList", majorList);
            
            // 获取入学年份统计
            List<Map<String, Object>> enrollmentYearData = studentService.getEnrollmentYearStatistics();
            data.put("enrollmentYearData", enrollmentYearData);
            
            return Result.success(data);
        } catch (Exception e) {
            return Result.error("获取数据失败：" + e.getMessage());
        }
    }
} 