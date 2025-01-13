package cn.cuit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cuit.domain.Student;
import cn.cuit.service.StudentService;
import cn.cuit.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author yuki
* @description 针对表【student】的数据库操作Service实现
* @createDate 2025-01-12 10:14:53
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




