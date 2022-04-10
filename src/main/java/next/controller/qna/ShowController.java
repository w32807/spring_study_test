package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.model.User;

public class ShowController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();
    private AnswerDao answerDao = new AnswerDao();
    private Question question;
    private List<Answer> answers;

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        HttpSession session = req.getSession();
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        User user = (User) session.getAttribute("user");
        boolean isWriter = false;

        question = questionDao.findById(questionId);
        answers = answerDao.findAllByQuestionId(questionId);

        if(user != null){
            isWriter = (question.getWriterId().equals(user.getUserId())) ? true : false;
            System.out.println("question.getWriterId() = " + question.getWriterId());
            System.out.println("user.getUserId() = " + user.getUserId());
            System.out.println("isWriter = " + isWriter);
        }

        ModelAndView mav = jspView("/qna/show.jsp");
        mav.addObject("question", question);
        mav.addObject("answers", answers);
        mav.addObject("isWriter", isWriter);
        return mav;
    }
}
