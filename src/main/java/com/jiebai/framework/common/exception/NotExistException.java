package com.jiebai.framework.common.exception;

import com.jiebai.framework.common.exception.base.BusinessException;

/**
 * 不存在异常
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class NotExistException extends BusinessException {

    public NotExistException() {
        super();
    }

    public NotExistException(String message) {
        super(message);
    }

    public NotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
