package cn.cuit.controller;

import cn.cuit.domain.Admin;
import cn.cuit.mapper.AdminMapper;
import cn.cuit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin")
@RestController
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;  // 注入LogMapper，处理与数据库的交互

    /**
     * 查询所有日志
     * @return
     */
    @GetMapping
    public List<Admin> getadmins() {
        return adminMapper.selectList(null); // 查询所有日志
    }

    /**
     * 查询单个日志
     * @param id 日志ID
     * @return
     */
    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable("id") int id) {
        return adminMapper.selectById(id); // 根据ID查询日志
    }

    /**
     * 添加日志
     * @param admin
     * @return
     */
    @PostMapping
    public AjaxResult addAdmin(@RequestBody Admin admin) {
        if (adminMapper.insert(admin) > 0) {
            return AjaxResult.me(); // 插入成功
        }
        return AjaxResult.me().setSuccess(false).setMessage("程序错误，请联系管理员！"); // 插入失败
    }

    /**
     * 更新日志
     * @param admin 日志对象
     * @return
     */
    @PutMapping
    public AjaxResult updateAdmin(@RequestBody Admin admin) {
        if (adminMapper.updateById(admin) > 0) {
            return AjaxResult.me(); // 更新成功
        }
        return AjaxResult.me().setSuccess(false).setMessage("程序错误，请联系管理员！"); // 更新失败
    }

    /**
     * 删除日志
     * @param id 日志ID
     * @return
     */
    @DeleteMapping("/{id}")
    public AjaxResult deleteAdmin(@PathVariable("id") int id) {
        if (adminMapper.deleteById(id) > 0) {
            return AjaxResult.me(); // 删除成功
        }
        return AjaxResult.me().setSuccess(false).setMessage("删除失败，请联系管理员！"); // 删除失败
    }
}
