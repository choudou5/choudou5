package com.choudou5.base.exception;

/**
 * @Name：系统异常
 * @Author：xuhaowen
 * @Date：2018-03-17
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 6362782762086492531L;

    private Integer errorCode;

    private String message;

    private Throwable throwable;

    public SysException() {
        super();
    }

    public SysException(String message) {
        super();
        this.message = message;
    }

    public SysException(int errorCode, Throwable throwable) {
        super();
        this.errorCode = errorCode;
        this.throwable = throwable;
    }

    public SysException(String message, Throwable throwable) {
        super();
        this.message = message;
        this.throwable = throwable;
    }

    public SysException(int errorCode, String message) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}