package cn.cuit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminStudentController {
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/getStudents")
    public Result getStudents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String searchKey,
            @RequestParam(required = false) String major,
            @RequestParam(required = false) Integer status
    ) {
        try {
            PageHelper.startPage(page, pageSize);
            List<Student> students = studentService.getStudentList(searchKey, major, status);
            PageInfo<Student> pageInfo = new PageInfo<>(students);
            
            return Result.success(pageInfo);
        } catch (Exception e) {
            return Result.error("获取学生列表失败：" + e.getMessage());
        }
    }
    
    @DeleteMapping("/deleteStudent/{id}")
    public Result deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("删除失败：" + e.getMessage());
        }
    }
    
    @PutMapping("/updateStudent/{id}")
    public Result updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            student.setId(id);
            studentService.updateStudent(student);
            return Result.success("更新成功");
        } catch (Exception e) {
            return Result.error("更新失败：" + e.getMessage());
        }
    }
    
    @PostMapping("/importStudents")
    public Result importStudents(@RequestParam("file") MultipartFile file) {
        try {
            studentService.importStudents(file);
            return Result.success("导入成功");
        } catch (Exception e) {
            return Result.error("导入失败：" + e.getMessage());
        }
    }
    
    @GetMapping("/exportStudents")
    public void exportStudents(HttpServletResponse response) {
        try {
            studentService.exportStudents(response);
        } catch (Exception e) {
            throw new RuntimeException("导出失败：" + e.getMessage());
        }
    }
} 