package cn.cuit.controller;

import cn.cuit.domain.Student;
import cn.cuit.service.StudentService;
import cn.cuit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public List<Student> getStudent() {
        List<Student> list =  studentService.list();
        return list;
    }

    /**
     * 查询单个
     * @param id
     * @return
     */
    @GetMapping("/{id}")//http://localhost:8080/employee/2
    public Student getStudent(@PathVariable("id") int id) {
        Student byId =  studentService.getById(id);
        return byId;
    }


    @PutMapping//http://localhost:8080/employee
    public AjaxResult addOrUpdateStudent(@RequestBody Student student) {//@RequestBody 将前端的json对象转化为实体对象
        if ( studentService.saveOrUpdate(student)){
            return AjaxResult.me();
        }
        return AjaxResult.me().setSuccess(false).setMessage("程序错误，请联系管理员！");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public AjaxResult deleteEmployee(@PathVariable("id") int id) {
        if ( studentService.removeById(id)){
            return AjaxResult.me();
        }
        return AjaxResult.me().setSuccess(false).setMessage("删除失败，请联系管理员！");
    }

}

