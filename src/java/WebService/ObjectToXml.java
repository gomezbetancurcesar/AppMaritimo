package WebService;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import Data.*;
import java.util.ArrayList;

public class ObjectToXml{
    private final Maritimos maritimos = new Maritimos();
    public ObjectToXml(){
        maritimos.setEmployees(new ArrayList<Maritimo>());
    }
    
    public void agregarNave(Maritimo maritimo){
        maritimos.getEmployees().add(maritimo);
        System.out.print(maritimos);
    }
    
    public void generateXML() throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Maritimos.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        
        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        //Marshal the employees list in console
        jaxbMarshaller.marshal(maritimos, System.out);
     
        //Marshal the employees list in file
        jaxbMarshaller.marshal(maritimos, new File("/home/cesar-gomez/xml/file.xml"));
    }
    
    public static void main(String[] args) throws JAXBException{
       //generateXML();
    }
}
