package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.anno.Role;

public class RoleValidation implements ConstraintValidator<Role,String> {     //ConstraintValidator<给哪个注解提供校验规则，校验的数据类型>

    /**
     * @param value 要校验的数据
     * @param context context in which the constraint is evaluated
     *
     * @return  return false means failed.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //提供校验规则
        if(value == null){
            return false;
        }
        if(value.equals("user") || value.equals("expert") || value.equals("admin")){
            return true;
        }
        return false;
    }
}
