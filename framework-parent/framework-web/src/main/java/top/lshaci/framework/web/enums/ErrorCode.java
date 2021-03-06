package top.lshaci.framework.web.enums;

import lombok.Getter;

/**
 * Error code enum
 * 
 * @author lshaci
 * @since 0.0.3
 */
@Getter
public enum ErrorCode {
	
	/**
	 * top.lshaci.framework.common.exception.BaseException
	 */
	INTERNAL_PROGRAM_ERROR(50000, "程序内部错误，操作失败", "top.lshaci.framework.common.exception.BaseException"),
	/**
	 * org.springframework.dao.DataAccessException
	 */
    DATA_ACCESS_EXCEPTION(50001, "数据库操作失败", "org.springframework.dao.DataAccessException"),
    /**
     * com.mysql.jdbc.CommunicationsException
     */
    COMMUNICATIONS_EXCEPTION(50002, "数据库连接中断", "com.mysql.jdbc.CommunicationsException"),
    /**
     * org.springframework.dao.DataIntegrityViolationException
     */
    DATA_INTEGRITY_VIOLATION_EXCEPTION(50003, "对象已经存在，请勿重复操作", "org.springframework.dao.DataIntegrityViolationException"),
    /**
     * com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException
     */
    MYSQL_INTEGRITY_CONSTRAINT_VIOLATION_EXCEPTION(50004, "对象已经存在，请勿重复操作", "com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException"),
    /**
     * java.lang.NullPointerException
     */
    NULL_POINTER_EXCEPTION(50005, "调用了未经初始化的对象或者是不存在的对象", "java.lang.NullPointerException"),
    /**
     * java.io.IOException
     */
    IO_EXCEPTION(50006, "IO异常", "java.io.IOException"),
    /**
     * java.lang.ClassNotFoundException
     */
    CLASS_NOT_FOUND_EXCEPTION(50007, "指定的类不存在", "java.lang.ClassNotFoundException"),
    /**
     * java.lang.ArithmeticException
     */
    ARITHMETIC_EXCEPTION(50008, "数学运算异常", "java.lang.ArithmeticException"),
    /**
     * java.lang.ArrayIndexOutOfBoundsException
     */
    ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION(50009, "数组下标越界", "java.lang.ArrayIndexOutOfBoundsException"),
    /**
     * java.lang.IllegalArgumentException
     */
    ILLEGAL_ARGUMENT_EXCEPTION(50010, "参数错误或非法", "java.lang.IllegalArgumentException"),
    /**
     * java.lang.ClassCastException
     */
    CLASS_CAST_EXCEPTION(50011, "类型强制转换错误", "java.lang.ClassCastException"),
    /**
     * java.sql.SQLException
     */
    SQL_EXCEPTION(50013, "操作数据库异常", "java.sql.SQLException"),
    /**
     * java.lang.SecurityException
     */
    SECURITY_EXCEPTION(50012, "违背安全原则异常", "java.lang.SecurityException"),
    /**
     * java.lang.NoSuchMethodException
     */
    NOSUCH_METHOD_EXCEPTION(50014, "方法未找到异常", "java.lang.NoSuchMethodException"),
    /**
     * java.net.ConnectException
     */
    CONNECT_EXCEPTION(50016, "服务器连接异常", "java.net.ConnectException"),
    /**
     * java.util.concurrent.CancellationException
     */
    CANCELLATION_EXCEPTION(50017, "任务已被取消的异常", "java.util.concurrent.CancellationException"),
    /**
     * java.util.concurrent.CancellationException
     */
    PARSE_EXCEPTION(50019, "日期格式错误", "java.text.ParseException"),
    ;
	
	private int code;
	private String msg;
	private String exceptionClass;
	
	private ErrorCode(int code, String msg, String exceptionClass) {
		this.code = code;
		this.msg = msg;
		this.exceptionClass = exceptionClass;
	}
	
	/**
	 * Get error code by exception
	 * 
	 * @param exception the exception
	 * @return the error code
	 */
	public static ErrorCode getByException(Exception exception) {
		if (exception != null) {
			ErrorCode[] errorCodes = ErrorCode.values();
			for (ErrorCode errorCode : errorCodes) {
				if (exception.getClass().getName().equals(errorCode.exceptionClass)) {
					return errorCode;
				}
			}
		}
		return INTERNAL_PROGRAM_ERROR;
	}
	
}
