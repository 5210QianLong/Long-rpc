package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 响应数据
     */
    private Object result;
    /**
     * 响应数据类型
     */
    private Class<?> resultType;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 异常信息
     */
    private Exception exception;
}
