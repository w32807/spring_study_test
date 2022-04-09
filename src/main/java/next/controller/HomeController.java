package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.controller.user.UpdateUserController;
import next.dao.QuestionDao;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        return jspView("home.jsp")
                .addObject("questions", questionDao.findAll())
                .addObject("isLogin", (user == null) ? false : true);
    }
}
