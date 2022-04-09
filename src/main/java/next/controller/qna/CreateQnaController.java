package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQnaController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateQnaController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question question = new Question(request.getParameter("writer")
                , request.getParameter("title")
                , request.getParameter("contents"));

        log.info("question {}", question);
        questionDao.insert(question);
        return jspView("redirect:/");
    }
}
