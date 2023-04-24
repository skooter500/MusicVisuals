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

        String payload = String.format("{\"prompt\": \"%s\",\"n\": 1,\"size\": \"512x512\"}", prompt);

        System.out.println(payload);

        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost("https://api.openai.com/v1/images/generations");
            StringEntity params = new StringEntity(payload, ContentType.APPLICATION_JSON);
            request.addHeader("content-type", "application/json");
            request.addHeader("authorization", "Bearer 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");

            request.setEntity(params);
            ClassicHttpResponse response = (ClassicHttpResponse) httpClient.execute(request);
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            JSONObject responseJson = new JSONObject(json);
            JSONArray data = responseJson.getJSONArray("data");
            JSONObject url = data.getJSONObject(0);
            link = url.getString("url");
        } catch(Exception e) {

        }

        return link;
    } // End void postOpenAPI

}
