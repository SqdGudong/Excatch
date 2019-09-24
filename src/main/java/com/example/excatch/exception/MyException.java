package com.example.excatch.exception;

/**
 * @Author
 * @Date 2019/8/12 13:34
 * @Version 1.0
 * Description:
 */
public class MyException extends RuntimeException{
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }
}
