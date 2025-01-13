package cn.cuit.constants;

public class SystemConstants {
    
    // 用户状态
    public static final Integer USER_ENABLE = 1;
    public static final Integer USER_DISABLE = 0;
    
    // 学生报到状态
    public static final Integer STUDENT_REPORTED = 1;
    public static final Integer STUDENT_NOT_REPORTED = 0;
    
    // 通知类型
    public static final Integer NOTIFICATION_TYPE_SYSTEM = 1;
    public static final Integer NOTIFICATION_TYPE_NORMAL = 0;
    
    // 日志类型
    public static final String LOG_TYPE_LOGIN = "登录";
    public static final String LOG_TYPE_LOGOUT = "登出";
    public static final String LOG_TYPE_ADD = "新增";
    public static final String LOG_TYPE_DELETE = "删除";
    public static final String LOG_TYPE_UPDATE = "修改";
    public static final String LOG_TYPE_QUERY = "查询";
} 