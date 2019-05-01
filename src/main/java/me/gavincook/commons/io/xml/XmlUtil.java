package me.gavincook.commons.io.xml;

import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象与xml转化工具类
 * @author Hinsteny
 * @version $ID: XmlUtil 2019-05-01 14:46 All rights reserved.$
 */
public final class XmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    private static final String XML_DEFAULT_ENCODING = "UTF-8";

    /**
     *
     * 将javaBean转换为xml对象
     *
     * @param pojo 要被转化的对象
     * @param hasHeader 最后生成的xml是否包含头, <?xml version="1.0" encoding="utf-8"?>
     * @return
     * @throws JAXBException
     * @throws XMLStreamException
     */
    public static String transformPojoToXml(Object pojo, boolean hasHeader, boolean hasCData) throws JAXBException, XMLStreamException {
        if (null == pojo) {
            return null;
        }
        ByteArrayOutputStream op = new ByteArrayOutputStream();
        CustomXMLStreamWriter customXMLStreamWriter = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(pojo.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            XMLOutputFactory xof = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = xof.createXMLStreamWriter(op);

            customXMLStreamWriter = new CustomXMLStreamWriter(streamWriter);
            customXMLStreamWriter.setcData(hasCData);

            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, !hasHeader);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, XML_DEFAULT_ENCODING);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(pojo, customXMLStreamWriter);
        } finally {
            if (null != customXMLStreamWriter) {
                try {
                    customXMLStreamWriter.flush();
                    customXMLStreamWriter.close();
                } catch (Exception e) {
                    logger.warn("close stream Exception", e);
                }
            }
        }
        return op.toString();
    }


}