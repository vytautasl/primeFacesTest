/**
 * @author      Vytautas Lesciauskas <vlesciauskas@gmail.com>
 * @version     1.0
 * @since       2013-12-29
 */
package lt.agmis.testproject.faces.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class CallUtils {

    /**
     * Performs GET call and returns the object of the supplied class
     *
     * @param url target url call
     * @param sampleClass the class of object to be returned
     * @param params map of the params to be supplied for the call
     * @return
     */
    public static Object getCall(String url, Class sampleClass, HashMap params)
    {
        RestTemplate template = new RestTemplate();
        ResponseEntity schemaListEntity = template.getForEntity(url, sampleClass, params);
        return schemaListEntity.getBody();
    }

    /**
     * Performs POST call and returns the object of the supplied class
     *
     * @param url target url call
     * @param postingObject the object to be posted to the client
     * @param sampleClass the class of object to be returned
     * @param params map of the params to be supplied for the call
     * @return
     */
    public static Object postCall(String url, Object postingObject, Class sampleClass, HashMap params)
    {
        RestTemplate template = new RestTemplate();
        ResponseEntity schemaListEntity = template.postForEntity(url, postingObject, sampleClass, params);
        return schemaListEntity.getBody();
    }
}
