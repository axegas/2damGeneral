package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.GestionContactos;
import mx.com.gm.sga.domain.Contacto;

/**
 * Servlet implementation class RecuperarAction
 */
@WebServlet("/RecuperarAction")
public class RecuperarAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GestionContactos gcontacto = new GestionContactos();
        List<Contacto> contactos = gcontacto.recuperarContactos();
        //guardamos contactos en un atributo de petici�n
        request.setAttribute("contactos", contactos);
        //trasnferencia de la petici�n
        request.getRequestDispatcher("contactos.jsp").forward(request, response);
    }

}
