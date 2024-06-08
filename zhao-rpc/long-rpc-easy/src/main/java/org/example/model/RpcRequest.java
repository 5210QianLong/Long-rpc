package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 在类上添加@Builder注解,可以自动生成一个建造者模式相关的代码
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数类型列表
     */
    private Class<?>[] paramsTypes;
    /**
     * 参数列表
     */
    private Object[] params;
}
