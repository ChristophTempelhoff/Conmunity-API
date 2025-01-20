package at.conmunity.API.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ObjectJSON")
public class ObjectJSONService<T> {
    private Logger log = LoggerFactory.getLogger(ObjectJSONService.class);
    public String ObjectToJson(T object){
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(object);
        } catch (JsonProcessingException ex){
            log.error(ex.toString());
            throw new RuntimeException(ex);
        }
    }
}
