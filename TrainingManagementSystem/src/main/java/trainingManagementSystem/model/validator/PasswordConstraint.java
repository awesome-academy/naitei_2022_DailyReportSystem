package trainingManagementSystem.model.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Mật khẩu phải có ít nhất 4 loại ký tự: chữ thường, viết hoa, số, ký tự đặc biệt.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}