package cn.cuit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName log
 */
@TableName(value ="log")
@Data
public class Log {
    /**
     * 
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 
     */
    @TableField(value = "operation_status")
    private String operationStatus;

    /**
     * 
     */
    @TableField(value = "timestamp")
    private Date timestamp;

    /**
     * 
     */
    @TableField(value = "admin_id")
    private Integer adminId;

    /**
     * 
     */
    @TableField(value = "operation_type")
    private String operationType;
}