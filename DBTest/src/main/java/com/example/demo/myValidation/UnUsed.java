package com.example.demo.myValidation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { UnUsedValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UnUsed {

	String message() default "すでに登録済みのメールアドレスです";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ FIELD })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		UnUsed[] value();
	}
}