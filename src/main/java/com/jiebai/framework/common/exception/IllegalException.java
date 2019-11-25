package com.jiebai.framework.common.exception;

import com.jiebai.framework.common.exception.base.BusinessException;

/**
 * 参数非法异常
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class IllegalException extends BusinessException {
    public IllegalException() {
        super();
    }

    public IllegalException(String message) {
        super(message);
    }

    public IllegalException(String message, Throwable cause) {
        super(message, cause);
    }
}
