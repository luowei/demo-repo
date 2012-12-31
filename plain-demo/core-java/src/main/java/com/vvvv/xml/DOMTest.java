package com.vvvv.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class DOMTest
{
    private static final String PLANT="PLANT";
    private static final String COMMON="COMMON";
    private static final String BOTANIC="BOTANICAL";
    private static final String PRICE="PRICE";
    private static final String LIGHT="LIGHT";
    private static final String ZONE="ZONE";
    private static final String AVAILABILITY="AVAILABILITY";

    public static void main(String[] args) throws Exception
    {

        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        FileInputStream fis=new FileInputStream("./xml/plant_catalog.xml");
        Document doc=db.parse(fis);
        Element root=doc.getDocumentElement();
        NodeList plantList=root.getElementsByTagName(PLANT);

        NodeList common=root.getElementsByTagName(COMMON);
        NodeList botanic=root.getElementsByTagName(BOTANIC);
        NodeList price=root.getElementsByTagName(PRICE);
        NodeList light=root.getElementsByTagName(LIGHT);
        NodeList zone=root.getElementsByTagName(ZONE);
        NodeList availability=root.getElementsByTagName(AVAILABILITY);
        Double sum=new Double(0.0);

        for(int i=0;i<plantList.getLength();i++)
        {
            Node plant=plantList.item(i);
            Plant p=new Plant();
            p.setAvailability(availability.item(i)
                    .getFirstChild().getTextContent());
            p.setZone(zone.item(i)
                    .getFirstChild().getTextContent());
            p.setLight(light.item(i)
                    .getFirstChild().getTextContent());
            p.setPrice(price.item(i)
                    .getFirstChild().getTextContent());
            p.setBotanincal(botanic.item(i)
                    .getFirstChild().getTextContent());
            p.setCommon(common.item(i)
                    .getFirstChild().getTextContent());
            System.out.println(p);

            System.out.println("p.getPrice():"+p.getPrice());
            String strPric=p.getPrice();
            Double pric=new Double((strPric.substring(1,strPric.length())));
            sum=sum.doubleValue()+pric.doubleValue();
        }
        System.out.println("------------总价格--------------");
        System.out.println("sum price:"+sum);

        write();
        System.out.println("----------------------my_plant.xml write success");
    }

    //使用dom生成xml
    public static void write() throws Exception
    {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        //在内存中创建了dom模型
        Document doc=db.newDocument();
        //创建元素
        Element root=doc.createElement("CATALOG");
        Element p=doc.createElement("PLANT");
        Plant plant=new Plant();
        plant.setAvailability("p1-boni");
        plant.setCommon("p1-common");
        plant.setBotanincal("p1-botanincal");
        plant.setLight("p1-light");
        plant.setPrice("p1-price");
        plant.setZone("p1-zone");

        Element common=doc.createElement("COMMON");
        Text commonText=(Text)doc.createTextNode(plant.getCommon());
        common.appendChild(commonText);

        Element botanical=doc.createElement("BOTANICAL");
        Text botanicalText=(Text)doc.createTextNode(plant.getBotanincal());
        botanical.appendChild(botanicalText);

        Element price=doc.createElement("PRICE");
        Text priceText=(Text)doc.createTextNode(plant.getPrice());
        price.appendChild(priceText);

        Element light=doc.createElement("LIGHT");
        Text lightText=(Text)doc.createTextNode(plant.getLight());
        light.appendChild(lightText);

        Element zone=doc.createElement("ZONE");
        Text zoneText=(Text)doc.createTextNode(plant.getZone());
        zone.appendChild(zoneText);

        Element availability=doc.createElement("AVAILABILITY");
        Text availabilityText=(Text)doc.createTextNode(plant.getAvailability());
        availability.appendChild(availabilityText);

        p.appendChild(common);
        p.appendChild(botanical);
        p.appendChild(price);
        p.appendChild(light);
        p.appendChild(zone);
        p.appendChild(availability);
        root.appendChild(p);
        doc.appendChild(root);

        TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        DOMSource ds=new DOMSource(doc);
        FileOutputStream fos=new FileOutputStream("./xml/my_plant.xml");

        StreamResult sr=new StreamResult(fos);
        //System.out.println(sr);
        tf.transform(ds,sr);
        fos.flush();
        fos.close();
    }
}

class Plant
{
    private String common;
    private String botanincal;
    private String zone;
    private String light;
    private String price;
    private String availability;
    public String getAvailability()
    {
        return availability;
    }
    public void setAvailability(String availability)
    {
        this.availability = availability;
    }
    public String getBotanincal()
    {
        return botanincal;
    }
    public void setBotanincal(String botanincal)
    {
        this.botanincal = botanincal;
    }
    public String getCommon()
    {
        return common;
    }
    public void setCommon(String common)
    {
        this.common = common;
    }
    public String getLight()
    {
        return light;
    }
    public void setLight(String light)
    {
        this.light = light;
    }
    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price = price;
    }
    public String getZone()
    {
        return zone;
    }
    public void setZone(String zone)
    {
        this.zone = zone;
    }
    @Override
    public String toString()
    {
        return "Plant is [commont="+common+",botanincal="+botanincal+",zone="
                +zone+",light="+light+",price="+price+",availability="
                +availability+"]";
    }
}
