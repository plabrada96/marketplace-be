/**
 * 
 */
package com.test.marketplace.common.data.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ FIELD })

public @interface FilterUtil {
	String field();
	Conditional condition();
	String join() default "";
	String concat() default "";
	String operador() default "AND";
}
