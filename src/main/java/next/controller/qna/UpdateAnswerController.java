package next.controller.qna;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 아이디 가져오기
        // 세션 가져오기
        // 맞으면 redirect로 forwardController 태우기




        Long answerId = Long.parseLong(request.getParameter("answerId"));
        Long questionId = Long.parseLong(request.getParameter("questionId"));

        ModelAndView mav = jsonView();
        try {
            answerDao.delete(answerId);
            mav.addObject("result", Result.ok())
                .addObject("questionId", questionId);
        } catch (DataAccessException e) {
            mav.addObject("result", Result.fail(e.getMessage()));
        }
        return mav;
    }
}
