package com.example.test.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RestController
@Api
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<TestObj> test() throws Exception {
        TestObj testObj = new TestObj();
        testObj.setMessage("ok");
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        testObj.setDate(date2);
        return ResponseEntity.ok(testObj);
    }

    @PostMapping("/test")
    public ResponseEntity<TestObj> test(@RequestBody TestObj testObj) throws Exception {
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(XMLGregorianCalendar.class, new XGCalConverter.Serializer())
//                .registerTypeAdapter(XMLGregorianCalendar.class, new XGCalConverter.Deserializer())
//                .create();
//        String jsonStr = gson.toJson(testObj);
//        System.out.println(jsonStr);
        return ResponseEntity.ok(testObj);
    }
}