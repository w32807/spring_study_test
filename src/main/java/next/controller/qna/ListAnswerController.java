package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.*;

public class ListAnswerController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ListAnswerController.class);
    private QuestionDao questionDao = new QuestionDao();
    private AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = jsonView();
        long questionId = parseLong(request.getParameter("questionId"));
        return mav.addObject("answers", answerDao.findAllByQuestionId(questionId))
                .addObject("countOfComment", answerDao.count(questionId));
    }
}
