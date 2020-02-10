package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class AuthServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        UserService.instance.addUser("Dima","12345",24L,"admin");
        UserService.instance.addUser("Den","12345",24L);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/auth.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String pass = req.getParameter("pass");

            switch (UserService.instance.getUserRole(name,pass)){
                case "admin":
                    req.getSession().setAttribute("role","admin");
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    break;
                case "user":
                    req.getSession().setAttribute("role","user");
                    resp.sendRedirect(req.getContextPath() + "/user/home");
                    break;
                default:
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    break;
            }
        } catch (Exception ignored){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
