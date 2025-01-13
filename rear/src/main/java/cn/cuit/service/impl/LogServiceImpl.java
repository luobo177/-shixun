package cn.cuit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cuit.domain.Log;
import cn.cuit.service.LogService;
import cn.cuit.mapper.LogMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuki
 * @description 针对表【log】的数据库操作Service实现
 * @createDate 2025-01-12 10:14:53
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
        implements LogService {

    public List<Log> getLogs() {
        return list(); // 查询所有日志
    }

    public List<Log> filterLogs(String query) {
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        if (query != null && !query.trim().isEmpty()) {
            wrapper.like("operation_status", query)
                    .or()
                    .like("operation_type", query);
        }
        return list(wrapper);
    }
}
