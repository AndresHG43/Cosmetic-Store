package com.cosmetics.store.utils;

import com.cosmetics.store.constans.Errors;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.stereotype.Service;

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

    public ResponseBody ok(JsonObject body) throws IOException {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setStatus(ResponseBody.status_ok);

        String json = body.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonParser jsonParser = jsonFactory.createParser(json);
        JsonNode jsonNode = objectMapper.readTree(jsonParser);

        responseBody.setBody(jsonNode);
        return responseBody;
    }

    public ResponseBody ok(JsonArray body) throws IOException {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setStatus(ResponseBody.status_ok);

        String json = body.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonParser jsonParser = jsonFactory.createParser(json);
        JsonNode jsonNode = objectMapper.readTree(jsonParser);

        responseBody.setBody(jsonNode);
        return responseBody;
    }

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

    public ResponseBody error(JsonObject body) throws IOException {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setStatus(ResponseBody.status_error);

        String json = body.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonParser jsonParser = jsonFactory.createParser(json);
        JsonNode jsonNode = objectMapper.readTree(jsonParser);
        responseBody.setBody(body);

        return responseBody;
    }

    public ResponseBody error(JsonArray body) throws IOException {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setStatus(ResponseBody.status_error);

        String json = body.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonParser jsonParser = jsonFactory.createParser(json);
        JsonNode jsonNode = objectMapper.readTree(jsonParser);

        responseBody.setBody(jsonNode);
        return responseBody;
    }

    public ResponseBody error(Errors error) {
        ResponseBody responseBody = new ResponseBody();
        ResponseBodyError body = new ResponseBodyError(error.getCode(), error.getMessage());
        responseBody.setStatus(ResponseBody.status_error);
        responseBody.setBody(body);
        return responseBody;
    }
}
