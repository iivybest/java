package edu.hit.adv.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        String xml = readContent("src/main/java/edu/hit/guide/java/adv/jaxb/1.xml");
        Object obj = xmlToObjectXXE(xml, Student.class);
        System.out.println(objectToXML(obj, Student.class));
    }

    public static String readContent(String file) throws Exception {
        FileInputStream input = new FileInputStream(file);
        byte[] content = new byte[2 * 1024];
        int realBytes = input.read(content);
        input.close();
        return new String(content, 0, realBytes, "UTF-8");
    }

    public static Object xmlToObjectXXE(String xml, Class<?> klass) throws Exception {
        JAXBContext context = JAXBContext.newInstance(klass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(new StringReader(xml));
    }

    public static Object xmlToObjectSafe(String xml, Class<?> klass) throws Exception {
        JAXBContext context = JAXBContext.newInstance(klass);

        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        xif.setProperty(XMLInputFactory.SUPPORT_DTD, true);
        XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(xml));

        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(xsr);
    }

    public static String objectToXML(Object obj, Class<?> klass) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(klass);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);

        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        writer.close();

        return writer.toString();
    }
}
