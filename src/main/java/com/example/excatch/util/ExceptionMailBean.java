package com.example.excatch.util;
/**
 * @Author songqd
 * @Date 2019/9/3
 * @Description
 */
public class ExceptionMailBean {
    public String getExceptionClassName() {
        return exceptionClassName;
    }

    public void setExceptionClassName(String exceptionClassName) {
        this.exceptionClassName = exceptionClassName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    private String exceptionClassName;

    private String exceptionMessage;

    private String exceptionDetail;
}
