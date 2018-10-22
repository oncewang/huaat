/**
 * 
 */
package net.huaat.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * @Description:
 * @author meisong ke
 * @date 2014-9-16 下午1:40:49
 * @version V1.0  
 */
public class DateTimeSerializer  extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = datetimeFormat.format(value);
		jgen.writeString(formattedDate);
	}
}
