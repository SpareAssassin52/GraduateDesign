package org.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.RoleValidation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented     //元注解
@Target({ FIELD})   //元注解
@Retention(RUNTIME) //元注解, 运行时会保留
@Constraint(validatedBy = {RoleValidation.class})  //指定提供校验规则的类
public @interface Role {
    //提供校验失败时的信息
    String message() default "role参数的数值只能是user、admin、expert";
    //指定分组
    Class<?>[] groups() default { };
    //负载    获取到State注解的附加信息
    Class<? extends Payload>[] payload() default { };
}
