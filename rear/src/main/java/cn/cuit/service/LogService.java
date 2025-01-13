package cn.cuit.service;

import cn.cuit.domain.Log;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yuki
* @description 针对表【log】的数据库操作Service
* @createDate 2025-01-12 10:14:53
*/
public interface LogService extends IService<Log> {
    List<Log> getLogs();
    List<Log> filterLogs(String query);
}
