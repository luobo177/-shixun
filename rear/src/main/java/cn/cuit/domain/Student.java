package cn.cuit.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student {
    /**
     * 
     */
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    /**
     * 
     */
    @TableField(value = "photo")
    private String photo;

    /**
     * 
     */
    @TableField(value = "on_campus")
    private Integer onCampus;

    /**
     * 
     */
    @TableField(value = "student_no")
    private String studentNo;

    /**
     * 
     */
    @TableField(value = "registration_status")
    private Integer registrationStatus;

    /**
     * 
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "birth_date")
    private Date birthDate;
}