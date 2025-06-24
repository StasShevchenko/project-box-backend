package com.example.project_box_backend.validators;

import com.example.project_box_backend.annotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // At least 8 characters
                new LengthRule(8, 30),

                // At least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 2),

                // At least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 3),

                // At least one digit
                new CharacterRule(EnglishCharacterData.Digit, 2),

                // At least one special character
                new CharacterRule(EnglishCharacterData.Special, 1),

                // No whitespace
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        "Пароль должен иметь длину не менее 8 символов и содержать как минимум: 2 заглавные буквы, 1 спец символ (!@#$&*), 2 цифры, 3 строчные буквы")
                .addConstraintViolation();
        return false;
    }
}