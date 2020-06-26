package com.example.demo.myValidation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { MyNotBlankValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface MyNotBlank {

	String message() default "空白は許可されていません";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ FIELD })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		MyNotBlank[] value();
	}
}