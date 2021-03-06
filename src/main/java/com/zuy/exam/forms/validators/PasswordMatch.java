package com.zuy.exam.forms.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class)
public @interface PasswordMatch {

    String violationReportProperty() default "passwordConfirm";

    String message() default "{user.data.form.passwords.match}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
