package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceConsumer {
    public static void main(String[] args) throws Exception {
        String responseBody = null;
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("http://localhost:8080/test");
        String JSON_STRING = "{\"date\":\"2019-05-19\",\"message\":\"ok\"}";
        StringRequestEntity stringRequestEntity = new StringRequestEntity(
                JSON_STRING,
                "application/json",
                "UTF-8");
        method.setRequestEntity(stringRequestEntity);
        BufferedReader br = null;
        try {
            int returnCode = client.executeMethod(method);
            if (returnCode != HttpStatus.SC_NOT_IMPLEMENTED) {
                responseBody = "";
                br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
                String readLine;
                while (((readLine = br.readLine()) != null)) {
                    responseBody = responseBody + readLine;
                }
            }
            System.out.println(responseBody);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(XMLGregorianCalendar.class, new XGCalConverter.Serializer())
                    .registerTypeAdapter(XMLGregorianCalendar.class, new XGCalConverter.Deserializer())
                    .create();
            TestObj obj = gson.fromJson(responseBody, TestObj.class);
            System.out.println(obj.getDate());
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
            if (br != null) try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
