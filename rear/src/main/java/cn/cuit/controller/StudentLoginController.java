package cn.cuit.controller;

import cn.cuit.domain.Student;
import cn.cuit.mapper.StudentMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class StudentLoginController {

    @Autowired
    private StudentMapper studentMapper;  // 注入学生信息的 Mapper，处理与数据库的交互

    // 显示学生登录页面
    @GetMapping("/student/login")
    public String toLogin(){
        return "student-login";  // 返回学生登录页面（student-login.html）
    }

    // 处理学生登录请求
    @PostMapping("/student/login")
    public String login(Student student, HttpSession httpSession, Model model){
        log.info("Student Login Attempt: " + student);

        // 校验学号与身份证号是否匹配
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("student_id", student.getStudentId());  // 使用studentId作为查询条件
        Student dbStudent = studentMapper.selectOne(studentQueryWrapper);  // 查询数据库，返回匹配的学生
        log.info("Student from DB: " + dbStudent);

        if (dbStudent != null){
            // 如果学生存在，验证身份证是否匹配
            if (dbStudent.getIdCard().equals(student.getIdCard())){
                if (dbStudent.getRegistrationStatus() == 1){  // 假设 1 为注册状态，0 为未注册
                    model.addAttribute("info", "登录成功！！！");  // 登录成功提示
                    httpSession.setAttribute("STUDENT", dbStudent);  // 将学生信息存入 session 中
                    return "redirect:/student/index";  // 登录成功后重定向到学生首页
                } else {
                    model.addAttribute("info", "该学生未注册，请先注册！！！");  // 提示学生未注册
                    return "student-login";  // 返回登录页面
                }
            } else {
                model.addAttribute("info", "身份证号码错误，请重新输入！！！");  // 身份证号码错误，提示信息
                return "student-login";  // 返回登录页面
            }
        } else {
            model.addAttribute("info", "学号错误，请重新输入！！！");  // 学号错误，提示信息
            return "student-login";  // 返回登录页面
        }
    }
}
