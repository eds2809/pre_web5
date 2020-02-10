package servlet;

import model.User;
import org.hibernate.Session;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        UserService.instance.addUser("Dima","12345",24L,"admin");
        UserService.instance.addUser("Den","12345",24L);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String pass = req.getParameter("pass");
            User user = UserService.instance.getUser(name,pass);

            if (!user.getRole().isEmpty()){
                req.getSession().setAttribute("user",user);
                resp.sendRedirect(req.getContextPath() + "/user/home");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendRedirect(req.getContextPath());
            }
        } catch (Exception ignored){
            resp.sendRedirect(req.getContextPath());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
