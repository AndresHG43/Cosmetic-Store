package com.store.cosmetics.utils;

import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.store.cosmetics.constants.Errors;
import org.springframework.stereotype.Service;
import tools.jackson.core.JsonParser;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;

@Service
public class OutMessage {
    //Success messages
    public ResponseBody ok(Object body) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setStatus(ResponseBody.status_ok);
        responseBody.setBody(body);
        return responseBody;
    }

//    public ResponseBody ok(JsonObject body) throws IOException {
//        ResponseBody responseBody = new ResponseBody();
//        responseBody.setStatus(ResponseBody.status_ok);
//
//        String json = body.toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonFactory jsonFactory = objectMapper.getFactory();
//        JsonParser jsonParser = jsonFactory.createParser(json);
//        JsonNode jsonNode = objectMapper.readTree(jsonParser);
//
//        responseBody.setBody(jsonNode);
//        return responseBody;
//    }
//
//    public ResponseBody ok(JsonArray body) throws IOException {
//        ResponseBody responseBody = new ResponseBody();
//        responseBody.setStatus(ResponseBody.status_ok);
//
//        String json = body.toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonFactory jsonFactory = objectMapper.getFactory();
//        JsonParser jsonParser = jsonFactory.createParser(json);
//        JsonNode jsonNode = objectMapper.readTree(jsonParser);
//
//        responseBody.setBody(jsonNode);
//        return responseBody;
//    }

    //Error messages
    public ResponseBody error(String code, String message) {
        ResponseBody responseBody = new ResponseBody();
        ResponseBodyError body =new ResponseBodyError(code, message);
        responseBody.setStatus(ResponseBody.status_error);
        responseBody.setBody(body);
        return responseBody;
    }

    public ResponseBody error(String code, Object message) {
        ResponseBody responseBody = new ResponseBody();
        ResponseBodyError body = new ResponseBodyError(code, message);
        responseBody.setStatus(ResponseBody.status_error);
        responseBody.setBody(body);
        return responseBody;
    }

    public ResponseBody error(Object body) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setStatus(ResponseBody.status_error);
        responseBody.setBody(body);
        return responseBody;
    }

//    public ResponseBody error(JsonObject body) throws IOException {
//        ResponseBody responseBody = new ResponseBody();
//        responseBody.setStatus(ResponseBody.status_error);
//
//        String json = body.toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonFactory jsonFactory = objectMapper.getFactory();
//        JsonParser jsonParser = jsonFactory.createParser(json);
//        JsonNode jsonNode = objectMapper.readTree(jsonParser);
//        responseBody.setBody(jsonNode);
//
//        return responseBody;
//    }
//
//    public ResponseBody error(JsonArray body) throws IOException {
//        ResponseBody responseBody = new ResponseBody();
//        responseBody.setStatus(ResponseBody.status_error);
//
//        String json = body.toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonFactory jsonFactory = objectMapper.getFactory();
//        JsonParser jsonParser = jsonFactory.createParser(json);
//        JsonNode jsonNode = objectMapper.readTree(jsonParser);
//
//        responseBody.setBody(jsonNode);
//        return responseBody;
//    }

    public ResponseBody error(Errors error) {
        ResponseBody responseBody = new ResponseBody();
        ResponseBodyError body = new ResponseBodyError(error.getCode(), error.getMessage());
        responseBody.setStatus(ResponseBody.status_error);
        responseBody.setBody(body);
        return responseBody;
    }
}
