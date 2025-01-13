package cn.cuit.controller;

import cn.cuit.domain.Log;
import cn.cuit.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/getLogs")
    public List<Map<String, Object>> getLogs() {
        // 获取所有日志
        List<Log> logs = logService.getLogs();
        // 进行字段映射
        return logs.stream()
                .map(log -> {
                    Map<String, Object> logMap = Map.of(
                            "id", log.getLogId(),
                            "content", log.getOperationStatus(),
                            "timestamp", log.getTimestamp(),
                            "adminId", log.getAdminId(),
                            "operationType", log.getOperationType()
                    );
                    return logMap;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/filterLogs")
    public List<Map<String, Object>> filterLogs(@RequestParam(required = false) String query) {
        // 根据查询参数筛选日志
        List<Log> logs = logService.filterLogs(query);
        // 进行字段映射
        return logs.stream()
                .map(log -> {
                    Map<String, Object> logMap = Map.of(
                            "id", log.getLogId(),
                            "content", log.getOperationStatus(),
                            "timestamp", log.getTimestamp(),
                            "adminId", log.getAdminId(),
                            "operationType", log.getOperationType()
                    );
                    return logMap;
                })
                .collect(Collectors.toList());
    }
}
