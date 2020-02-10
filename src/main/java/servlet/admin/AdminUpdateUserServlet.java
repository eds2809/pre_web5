package servlet.admin;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/update/*")
public class AdminUpdateUserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String[] path = req.getPathInfo().split("/");
            long id = Long.parseLong(path[path.length - 1]);
            User user = userService.getUser(id);

            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/adminUpdateUser.jsp").forward(req, resp);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ignored) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
