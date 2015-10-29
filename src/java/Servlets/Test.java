package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Data.*;
import WebService.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Maritimo maritimo = new Maritimo();
            ObjectToXml xml = new ObjectToXml();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");
            
            Long idCliente = Long.parseLong(request.getParameter("idCliente"));
            Date fechaTransporte = null;
            Integer catidadTransporte = Integer.parseInt(request.getParameter("cantidadTransporte"));
            String Origen = request.getParameter("Origen");
            String Destino = request.getParameter("Destino");
            Integer idTransporte = Integer.parseInt(request.getParameter("idTransporte"));
            
            maritimo.setCantidadTransporte(catidadTransporte);
            maritimo.setOrigen(Origen);
            maritimo.setDestino(Destino);
            maritimo.setFechaTransporte(fechaTransporte);
            maritimo.setIdCliente(idCliente);
            maritimo.setIdTransporte(idTransporte);
            
            xml.agregarNave(maritimo);
            xml.agregarNave(maritimo);
            xml.agregarNave(maritimo);
            
            String xmlText = "";
            try {
                xmlText = xml.generateXML();
            } catch (JAXBException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<h1>" + maritimo.getDestino() + "</h1>");
            out.println("<h1>ahi mostre el destino</h1>");
            out.println("<h1>" + request.getParameter("idCliente") + "</h1>");
            out.println("<h1>" + request.getParameter("fechaTransporte") + "</h1>");
            out.println("<h1>" + request.getParameter("cantidadTransporte") + "</h1>");    
            out.println("<h1>" + request.getParameter("Origen") + "</h1>");    
            out.println("<h1>" + request.getParameter("Destino") + "</h1>");    
            out.println("<h1>" + request.getParameter("idTransporte") + "</h1>");    
            out.println("<h1> XML" + xmlText + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
