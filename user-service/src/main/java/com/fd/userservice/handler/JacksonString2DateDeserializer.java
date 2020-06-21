package com.fd.userservice.handler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JacksonString2DateDeserializer extends JsonDeserializer<Date> {

        private SimpleDateFormat primary = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private SimpleDateFormat seconds = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            Date date = null;
            try {
                date = primary.parse(jp.getText());
            } catch (ParseException e) {
                try {
                    seconds.parse(jp.getText());
                    return process(jp);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
            return date;
        }

        private Date process(JsonParser jp){
            JsonStreamContext parsingContext = jp.getParsingContext();
            String currentName = parsingContext.getCurrentName();
            try {
                if("startTime".equals(currentName)){
                    return primary.parse(jp.getText()+" 00:00:00");
                }

                if("endTime".equals(currentName)){
                    return primary.parse(jp.getText()+" 23:59:59");
                }
            }catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
}
