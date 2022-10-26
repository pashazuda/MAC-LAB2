package pr4.serialization;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public class JsonHelper {
	private static final ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	
	public static String toJson(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("can not serialize object {}",o);
			return "";
		}
	}

	public static <T>  T fromJson(String strObj, Class<T> clazz){
		try{
			return mapper.readValue(strObj, clazz);
		} catch (JsonProcessingException ex) {
			ex.printStackTrace();
			log.error("can not deserialize msg {} for class {}", strObj, clazz.getName());
			return null;
		}
	}
}
