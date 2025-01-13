package cn.cuit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName notification
 */
@TableName(value ="notification")
@Data
public class Notification {
    /**
     * 
     */
    @TableId(value = "notification_id", type = IdType.AUTO)
    private Integer notificationId;

    /**
     * 
     */
    @TableField(value = "read_status")
    private Integer readStatus;

    /**
     * 
     */
    @TableField(value = "receiver")
    private Integer receiver;

    /**
     * 
     */
    @TableField(value = "content")
    private String content;

    /**
     * 
     */
    @TableField(value = "title")
    private String title;
}