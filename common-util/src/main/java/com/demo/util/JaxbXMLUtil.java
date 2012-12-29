package com.demo.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * java与xml之间转换
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-29
 * Time: 下午8:17
 * To change this template use File | Settings | File Templates.
 */
public class JaxbXMLUtil {
    private JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public JaxbXMLUtil(Class<?> clazz){
        try {
            this.context = JAXBContext.newInstance(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String java2Xml(Object obj){
        StringWriter sw = new StringWriter();
        try {
            this.marshaller = context.createMarshaller();
            this.marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            this.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return  sw.toString().replace(" standalone=\"yes\"", "");
    }

    public Object xml2Java(String xml){
        Object obj = null;
        try {
            this.unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            obj = unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
