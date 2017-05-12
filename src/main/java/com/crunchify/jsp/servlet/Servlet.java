package com.crunchify.jsp.servlet;

//import ModeloWeb.EscrituraAccesoAleatorio;
import edu.co.sergio.mundo.dao.Conexion;
import edu.co.sergio.mundo.dao.DepartamentoDAO;
import edu.co.sergio.mundo.vo.Obra_De_Arte;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet_Menu", urlPatterns = {"/Servlet_Menu"})
public class Servlet extends HttpServlet {

    Conexion conexion = new Conexion();
    Connection connection = null;
    ResultSet rs = null;
    Boolean b;
    DepartamentoDAO dao = new DepartamentoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, URISyntaxException {
        response.setContentType("text/html;charset=UTF-8");
    }
    //}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            connection = conexion.getConnection();

            int opcion = Integer.parseInt(request.getParameter("opcion"));

            switch (opcion) {
                case 1:
                    int Valor = Integer.parseInt(request.getParameter("Valor"));
                    String NombreObra = request.getParameter("NombreObra");
                    String Descripcion = request.getParameter("Descripcion");
                    String Estilo = request.getParameter("Estilo");
                    int IdArtista = Integer.parseInt(request.getParameter("IdArtista"));
                    Obra_De_Arte oa = new Obra_De_Arte();
                    oa.setNombre(NombreObra);
                    oa.setDescripcion(Descripcion);
                    oa.setEstilo(Estilo);
                    oa.setValor(Valor);
                    b = dao.insert(oa);

                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet Servlet_Menu</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>El Usuario Se Agrego Satisfactoriamente...</h1>");
                        out.println("<h1>" + NombreObra + "</h1>");
                        out.println("<h1>" + Descripcion + "</h1>");
                        out.println("<h1>" + Estilo + "</h1>");
                        out.println("<h1>" + Valor + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet Servlet_Menu</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "InsertarObra.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>No fue posible registrar la obra</h1>");
                        out.println("<p>Seras dirigido automaticamente en cinco segundos al menu principal. En caso contrario, puedes acceder registrar otro Lote, haciendo click <a href=" + "CrearUser.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    ;
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
