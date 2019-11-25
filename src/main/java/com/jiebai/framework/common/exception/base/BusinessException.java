package com.jiebai.framework.common.exception.base;

import java.io.Serializable;

/**
 * 业务异常
 *
 * @author lizhihui
 * @version 1.0.0
 */
public class BusinessException extends Exception implements Serializable {

    private static final long serialVersionUID = -1623944262806869461L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
