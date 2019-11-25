package com.jiebai.framework.common.exception;

import com.jiebai.framework.common.exception.base.BusinessException;

/**
 * 条件匹配异常
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class NotMatchException extends BusinessException {
    public NotMatchException() {
        super();
    }

    public NotMatchException(String message) {
        super(message);
    }

    public NotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
