package cn.com.sharinglife.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by hell on 2018/2/11
 */
public class JsonMapperUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String objectToJsonString(Object object){
        if(Objects.nonNull(object)){
            try {
               return mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static <T> T jsonStringToObject(String jsonString, Class<T> valueType){
        if(Objects.nonNull(jsonString)) {
            try {
                return mapper.readValue(jsonString, valueType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
