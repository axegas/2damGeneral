package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

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
        String op = request.getParameter("op");
        String url = "";
        switch (op) {
            case "doAlta":
                url = "AltaAction";
                break;
            case "doEliminar":
                url = "EliminarAction";
                break;
            case "doRecuperar":
                url = "RecuperarAction";
                break;
            case "toNuevo":
                url = "nuevo.html";
                break;
            case "toIndex":
                url = "index.html";
                break;

        }
        request.getRequestDispatcher(url).forward(request, response);
    }

}
