package com.jiebai.framework.common.exception;

import com.jiebai.framework.common.exception.base.BusinessException;

/**
 * 已存在异常
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class ExistException extends BusinessException {
    public ExistException(String message) {
        super(message);
    }

    public ExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistException(Throwable cause) {
        super(cause);
    }
}
