package top.lshaci.framework.excel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The title name of the excel corresponding to the entity field
 * 
 * @author lshaci
 * @since 0.0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelTitle {

	/**
	 * Get the value(excel title name)
	 * 
	 * @return the excel title name
	 */
	String value();
}
