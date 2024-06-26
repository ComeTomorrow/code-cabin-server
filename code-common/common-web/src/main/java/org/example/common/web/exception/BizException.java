package org.example.common.web.exception;

import lombok.Getter;
import org.example.common.core.result.IResultCode;

/**
 * 自定义业务异常
 *
 * @author ComeTomorrow
 * @since 2024/4/10
 */
@Getter
public class BizException extends RuntimeException {

    public IResultCode resultCode;

    public BizException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.resultCode = errorCode;
    }

    public BizException(String message){
        super(message);
    }

    public BizException(String message, Throwable cause){
        super(message, cause);
    }

    public BizException(Throwable cause){
        super(cause);
    }


}