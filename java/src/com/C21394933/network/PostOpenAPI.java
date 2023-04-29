package com.C21394933.network;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

public class PostOpenAPI {
    static public String run(String prompt) {
        String link= "";

        String payload = String.format("{\"prompt\": \"%s\"}", prompt);

        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost("https://api.ernestjohndecina.com/api/dalle/image");
            StringEntity params = new StringEntity(payload, ContentType.APPLICATION_JSON);
            request.addHeader("content-type", "application/json");
        

            request.setEntity(params);
            ClassicHttpResponse response = (ClassicHttpResponse) httpClient.execute(request);
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            JSONObject responseJson = new JSONObject(json);
            String data = responseJson.getString("link");
            link = data;
        } catch(Exception e) {
            return null;
        }

        return link;
    } // End void postOpenAPI

}
