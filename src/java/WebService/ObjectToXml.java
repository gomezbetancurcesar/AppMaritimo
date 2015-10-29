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

public class ObjectToXml{
    private final Maritimos maritimos = new Maritimos();
    private final String filename;
    private final String url;
    
    public ObjectToXml(){
        maritimos.setEmployees(new ArrayList<Maritimo>());
        this.url = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
        this.filename = "/home/cesar-gomez/xml/file.xml";
    }
    
    public void agregarNave(Maritimo maritimo){
        maritimos.getEmployees().add(maritimo);
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
        jaxbMarshaller.marshal(maritimos, outputStream);
        
        String payload = new String(outputStream.toByteArray());
        
        output = String.format(soapEnvelope, payload);
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
    
    public void conectar(String data){
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            String url = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
            
            InputStream is = new ByteArrayInputStream(data.getBytes());
            SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
            SOAPMessage soapResponse = soapConnection.call(request, url);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }
}
