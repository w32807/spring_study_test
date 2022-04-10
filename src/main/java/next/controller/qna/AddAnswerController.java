package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

    private AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        long questionId = Long.parseLong(req.getParameter("questionId"));
        Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents"),
                questionId);
        log.debug("answer : {}", answer);

        Answer savedAnswer = answerDao.insert(answer);
        Long countOfComment = answerDao.count(questionId);
        log.debug("countOfComment : {}", countOfComment);

        return jsonView()
                .addObject("questionId", questionId)
                .addObject("answer", savedAnswer)
                .addObject("countOfComment", countOfComment);
    }
}
