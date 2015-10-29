package WebService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.xml.soap.*;

public class SoapClient{
    public void conectar(String data){
        try {
            // Create SOAP Connection
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