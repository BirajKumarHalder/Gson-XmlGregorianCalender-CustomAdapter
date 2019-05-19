package com.example.client;

import com.google.gson.*;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class XGCalConverter {

    public static class Serializer implements JsonSerializer {
        public Serializer() {
            super();
        }

        public JsonElement serialize(Object t, Type type,
                                     JsonSerializationContext jsonSerializationContext) {
            XMLGregorianCalendar xgcal = (XMLGregorianCalendar) t;
            return new JsonPrimitive(xgcal.toXMLFormat());
        }
    }

    public static class Deserializer implements JsonDeserializer {

        public Object deserialize(JsonElement jsonElement, Type type,
                                  JsonDeserializationContext jsonDeserializationContext) {
            try {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(jsonElement.getAsString().split("T")[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

}