package cn.cuit.controller;

import cn.cuit.domain.Admin;
import cn.cuit.mapper.AdminMapper;
import cn.cuit.utils.AjaxResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/login")
@RestController
public class AdminLoginController {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 查询所有管理员
     * @return
     */
    @GetMapping("/admins")
    public List<Admin> getAdmins() {
        return adminMapper.selectList(null); // 查询所有管理员
    }

    /**
     * 查询单个管理员
     * @param id 管理员ID
     * @return
     */
    @GetMapping("/admins/{id}")
    public Admin getAdmin(@PathVariable("id") int id) {
        return adminMapper.selectById(id); // 根据ID查询管理员
    }

    /**
     * 添加或更新管理员
     * @param admin 管理员对象
     * @return
     */
    @PutMapping("/admins")
    public AjaxResult addOrUpdateAdmin(@RequestBody Admin admin) {
        if (adminMapper.insert(admin) > 0) {
            return AjaxResult.me(); // 插入成功
        } else if (adminMapper.updateById(admin) > 0) {
            return AjaxResult.me(); // 更新成功
        }
        return AjaxResult.me().setSuccess(false).setMessage("程序错误，请联系管理员！"); // 插入更新失败
    }

    /**
     * 删除管理员
     * @param id 管理员ID
     * @return
     */
    @DeleteMapping("/admins/{id}")
    public AjaxResult deleteAdmin(@PathVariable("id") int id) {
        if (adminMapper.deleteById(id) > 0) {
            return AjaxResult.me(); // 删除成功
        }
        return AjaxResult.me().setSuccess(false).setMessage("删除失败，请联系管理员！"); // 删除失败
    }

    /**
     * 登录
     * @param account 账户
     * @param password 密码
     * @param type 角色类型
     * @return
     */
    @PostMapping
    public AjaxResult login(@RequestParam("account") String account,
                            @RequestParam("password") String password,
                            @RequestParam("type") String type) {
        // 根据账户查询管理员
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", account));

        if (admin == null) {
            return AjaxResult.me().setSuccess(false).setMessage("账户或密码错误");
        }

        // 检查密码是否正确
        if (!admin.getPassword().equals(password)) {
            return AjaxResult.me().setSuccess(false).setMessage("账户或密码错误");
        }

        // 检查角色是否匹配
//        if (!admin.getType().equals(type)) {
//            return AjaxResult.me().setSuccess(false).setMessage("账户或密码错误");
//        }

        // 登录成功，保存token和角色到localStorage
        String token = "generated_token_here"; // 这里应该生成一个有效的token
        AjaxResult result = AjaxResult.me();
        result.setSuccess(true);
        result.setMessage("登录成功");
        result.setResultObj(new LoginResponse(token, type));
        return result;
    }

    @Data
    public static class LoginResponse {
        private String token;
        private String role;

        public LoginResponse(String token, String role) {
            this.token = token;
            this.role = role;
        }
    }
}
