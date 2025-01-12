package cn.cuit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cuit.domain.Notification;
import cn.cuit.service.NotificationService;
import cn.cuit.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

/**
* @author yuki
* @description 针对表【notification】的数据库操作Service实现
* @createDate 2025-01-12 10:14:53
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{

}



