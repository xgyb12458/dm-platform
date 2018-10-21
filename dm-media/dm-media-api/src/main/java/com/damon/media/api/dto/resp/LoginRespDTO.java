package com.damon.media.api.dto.resp;

import com.damon.shared.enums.ResponseCodeEnum;
import com.damon.shared.exception.SystemException;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Damon S.
 */
@ToString
@Data
@NoArgsConstructor
@ApiModel(value = "用户登录结果")
public final class LoginRespDTO implements Serializable {
    private static final long serialVersionUID = 9615776L;

    private String someProperty;


    /**
     * 在请求对象里好像不需要加密
     * */
    private void writeObject(ObjectOutputStream output) {
        // TODO: 加密密码，使用SealedObject或SignedObject加解密
        this.setSomeProperty("");
        try {
            output.defaultWriteObject();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemException(ResponseCodeEnum.BAD_REQUEST.getCode(), "密码加密失败");
        }
    }
}
