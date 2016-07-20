package com.zuy.exam.forms.validators;

import com.zuy.exam.forms.UserDataForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordMatch, UserDataForm> {

    private PasswordMatch passwordMatch;

    @Override
    public void initialize(final PasswordMatch passwordMatch) {
        this.passwordMatch = passwordMatch;
    }

    @Override
    public boolean isValid(final UserDataForm form, final ConstraintValidatorContext ctx) {
        if(form.getPassword() == null || form.getPasswordConfirm() == null) {
            return true;
        }
        else if(form.getPassword().equals(form.getPasswordConfirm())) {
            return true;
        }
        else {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                .addPropertyNode(passwordMatch.violationReportProperty())
                .addConstraintViolation();
            return false;
        }
    }
}
