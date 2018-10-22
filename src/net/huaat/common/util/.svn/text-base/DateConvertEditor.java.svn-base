package net.huaat.common.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

/**
 * @Description: TODO
 * @author zhiqiang yang
 * @date 2012-10-10 下午5:06:00
 * @version V1.0
 */
public class DateConvertEditor extends PropertyEditorSupport {
	private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text))
			try {
				if ((text.indexOf(":") == -1) && (text.length() == 10)) {
					setValue(this.dateFormat.parse(text));
					return;
				}
				if ((text.indexOf(":") > 0) && (text.length() == 19)) {
					setValue(this.datetimeFormat.parse(text));
					return;
				}
				throw new IllegalArgumentException(
						"Could not parse date, date format is error ");
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		else
			setValue(null);
	}
}
