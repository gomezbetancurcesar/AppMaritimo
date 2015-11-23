package WebService;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import Data.*;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

public class ObjectToXml extends SoapClient{
    private final Maritimos transacciones = new Maritimos();
    private final String filename;
    private final String url;
    private String msgBody;
    
    public ObjectToXml(){
        transacciones.setEmployees(new ArrayList<Maritimo>());
        this.url = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
        this.filename = "/home/cesar-gomez/xml/file.xml";
    }
    
    public void agregarNave(Maritimo maritimo){
        transacciones.getEmployees().add(maritimo);
    }
    
    public String generateXML() throws JAXBException{
        String output;
        
        String soapEnvelope =
        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soapenv:Header /><soapenv:Body>%s</soapenv:Body></soapenv:Envelope>";
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Maritimos.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        
        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty("jaxb.fragment", Boolean.TRUE);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(transacciones, outputStream);
        
        String transacciones = new String(outputStream.toByteArray());
        
        output = transacciones;
        this.msgBody = transacciones;
        //output = String.format(soapEnvelope, transacciones);
        
        this.crearArchivo(output);
        
        return output;
    }
    
    private void crearArchivo(String text){
        BufferedWriter output = null;
        try {
            File file = new File(this.filename);
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
    public void llamar(){
        //this.conectar();
    }
}
