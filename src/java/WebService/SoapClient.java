package WebService;

import Data.Maritimo;
import java.util.ArrayList;
import javax.xml.soap.*;

public class SoapClient{
    private SOAPConnectionFactory soapConnectionFactory;
    private SOAPConnection soapConnection;
    private String url = "http://localhost:8080/TestWebservice/TestWebservice?WSDL";
    private String action = "TEST";
    private String tipo = "?";
    
    public SoapClient(){
        try {
            this.soapConnectionFactory = SOAPConnectionFactory.newInstance();
            this.soapConnection = soapConnectionFactory.createConnection();
        }catch(SOAPException | UnsupportedOperationException e){
            this.soapConnectionFactory = null;
            this.soapConnectionFactory = null;
        }
    }
    
    public Boolean enviar(ArrayList<Maritimo> data){
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();

            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("car", "http://www.duoc.cl/integracion/cargaMaritimaService");
            envelope.addNamespaceDeclaration("typ", "http://www.duoc.cl/integracion/cargaMaritimaService/types");

            SOAPBody soapBody = envelope.getBody();
            SOAPElement soapBodyElem = soapBody.addChildElement(this.action, "car");
            //SOAPElement soapBodyElem = soapBody.addChildElement("insertarCargaMaritimaRequestDocumen", "car");
            SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("trx", "typ");
            soapBodyElem1.addTextNode(this.tipo);
            
            for(Maritimo maritimo : data){
                SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("transacciones", "typ");
                SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("codigo", "typ");
                soapBodyElem3.addTextNode( maritimo.getIdCliente().toString() );
                
                SOAPElement soapBodyElem4 = soapBodyElem2.addChildElement("monto", "typ");
                //cambiar esto...a la estructura de los campos del carlos
                soapBodyElem4.addTextNode( maritimo.getCantidadTransporte().toString() );
                
                SOAPElement soapBodyElem5 = soapBodyElem2.addChildElement("fecha", "typ");
                soapBodyElem5.addTextNode( maritimo.getFechaTransporte().toString() );
                
                SOAPElement soapBodyElem6 = soapBodyElem2.addChildElement("rut", "typ");
                soapBodyElem6.addTextNode(maritimo.getIdTransporte().toString());
            }
            MimeHeaders headers = soapMessage.getMimeHeaders();
            headers.setHeader("Content-Type", "application/soap+xml; charset=utf-8");
            headers.addHeader(this.action, this.url);
            
            soapMessage.saveChanges();
            soapMessage.writeTo(System.out);
            System.out.println();

            // call() throws a SOAPException 
            SOAPMessage soapResponse = soapConnection.call(soapMessage, this.url);
            soapConnection.close();
            return true;
        } catch(Exception e){
            System.err.println("Error mientras se enviaba el dato al servidor!!!!");
            e.printStackTrace();
            return false;
        }
    }
}