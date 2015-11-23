<%@page import="java.util.ArrayList"%>
<%@page import="Data.Maritimo"%>
<%
    HttpSession sesion = request.getSession();
%>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Aplicación Marítimos</h1>
        <form action="/maritimo/Test" method="POST" novalidate="true">
        <table>
            <%
                if(sesion.getAttribute("enviado") != null){
                    Boolean envio = (Boolean) sesion.getAttribute("enviado");
                    sesion.removeAttribute("enviado");
                    %>
                    <tr>
                        <td colspan="2">
                            <h1>
                            <%
                                if(envio){
                                    out.print("OK, los datos se han enviado correctamente");
                                }else{
                                    out.print("Error, los datos no se han podido enviar... :( ");
                                }
                            %>
                            </h1>
                        </td>
                    </tr>
                    <%
                }
            %>
            <tr>
                <td>
                    <label for="idCliente">Id Cliente</label>
                </td>
                <td>
                    <input type="text" name="idCliente" id="idCliente" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="cantidadTransporte">Cantidad</label>
                </td>
                <td>
                    <input type="text" name="cantidadTransporte" id="cantidadTransporte">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Origen">Origen</label>
                </td>
                <td>
                    <input type="text" name="Origen" id="Origen">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Destino">Destino</label>
                </td>
                <td>
                    <input type="text" name="Destino" id="Destino">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="idTransporte">Id Transforte</label>
                </td>
                <td>
                    <input type="text" name="idTransporte" id="idTransporte">
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <input type="submit" value="Agregar" name="btnAgregar">
                </td>
            </tr>
        </table>
        <%
            if(sesion.getAttribute("datos") != null){
                ArrayList<Maritimo> maritimos = (ArrayList<Maritimo>)sesion.getAttribute("datos");
                %>
                <table>
                    <tr>
                        <th>Id</th>
                        <th>Fecha</th>
                        <th>Cantidad</th>
                        <th>Origen</th>
                        <th>Destino</th>
                        <th>Transporte</th>
                    </tr>
                <%
                for(Maritimo maritimo : maritimos){
                    %>
                    <tr>
                        <td><% out.print(maritimo.getIdCliente().toString());%></td>
                        <td><% out.print(maritimo.getFechaTransporte().toString());%></td>
                        <td><% out.print(maritimo.getCantidadTransporte().toString());%></td>
                        <td><% out.print(maritimo.getOrigen());%></td>
                        <td><% out.print(maritimo.getDestino());%></td>
                        <td><% out.print(maritimo.getIdTransporte().toString());%></td>
                    </tr>
                    <%
                }
                %>
                    <tr>
                        <td colspan="5">
                            <input type="submit" value="Enviar" name="btnEnviar">
                        </td>
                    </tr>
                </table>
                <%
            }
        %>
        </form>
    </body>
</html>
