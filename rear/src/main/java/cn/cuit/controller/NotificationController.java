package cn.cuit.controller;

import cn.cuit.domain.Notification;
import cn.cuit.mapper.NotificationMapper;
import cn.cuit.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("notification")
@RestController
public class NotificationController {
    @Autowired
    private NotificationMapper notificationMapper;  // 注入NotificationMapper，处理与数据库的交互

    /**
     * 查询所有通知
     * @return
     */
    @GetMapping
    public List<Notification> getNotifications() {
        return notificationMapper.selectList(null); // 查询所有通知
    }

    /**
     * 查询单个通知
     * @param id 通知ID
     * @return
     */
    @GetMapping("/{id}")
    public Notification getNotification(@PathVariable("id") int id) {
        return notificationMapper.selectById(id); // 根据ID查询通知
    }

    /**
     * 添加通知
     * @param notification 通知对象
     * @return
     */
    @PostMapping
    public AjaxResult addNotification(@RequestBody Notification notification) {
        if (notificationMapper.insert(notification) > 0) {
            return AjaxResult.me(); // 插入成功
        }
        return AjaxResult.me().setSuccess(false).setMessage("程序错误，请联系管理员！"); // 插入失败
    }

    /**
     * 更新通知
     * @param notification 通知对象
     * @return
     */
    @PutMapping
    public AjaxResult updateNotification(@RequestBody Notification notification) {
        if (notificationMapper.updateById(notification) > 0) {
            return AjaxResult.me(); // 更新成功
        }
        return AjaxResult.me().setSuccess(false).setMessage("程序错误，请联系管理员！"); // 更新失败
    }

    /**
     * 删除通知
     * @param id 通知ID
     * @return
     */
    @DeleteMapping("/{id}")
    public AjaxResult deleteNotification(@PathVariable("id") int id) {
        if (notificationMapper.deleteById(id) > 0) {
            return AjaxResult.me(); // 删除成功
        }
        return AjaxResult.me().setSuccess(false).setMessage("删除失败，请联系管理员！"); // 删除失败
    }
}
