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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            HttpSession sesion = request.getSession();
            Maritimo maritimo = new Maritimo();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy");
            ObjectToXml xml = new ObjectToXml();
            
            if(request.getParameter("btnEnviar") != null){
                ArrayList<Maritimo> maritimos = (ArrayList<Maritimo>)sesion.getAttribute("datos");
                SoapClient soap = new SoapClient();
                Boolean enviado = soap.enviar(maritimos);
                sesion.setAttribute("enviado", enviado);
                if(enviado){
                    sesion.removeAttribute("datos");
                }
                response.sendRedirect("/maritimo/");
            }else if(request.getParameter("btnAgregar") != null){
                Long idCliente = Long.parseLong(request.getParameter("idCliente"));
                Date fechaTransporte = new Date();

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

                if(sesion.getAttribute("datos") != null){
                    ArrayList<Maritimo> maritimos = (ArrayList<Maritimo>)sesion.getAttribute("datos");
                    maritimos.add(maritimo);
                    sesion.setAttribute("datos", maritimos);
                }else{
                    ArrayList<Maritimo> maritimos = new ArrayList<Maritimo>();
                    maritimos.add(maritimo);
                    sesion.setAttribute("datos", maritimos);
                }

                response.sendRedirect("/maritimo/"); 
            }
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
