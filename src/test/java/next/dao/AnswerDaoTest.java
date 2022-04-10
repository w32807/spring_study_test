package next.dao;

import next.model.Answer;

import next.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerDaoTest {
    @BeforeEach
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void addAnswer() throws Exception {
        long questionId = 1L;
        Answer expected = new Answer("javajigi", "answer contents", questionId);
        AnswerDao dut = new AnswerDao();
        Answer answer = dut.insert(expected);
        System.out.println("Answer : " + answer);
    }

    @Test
    @DisplayName("댓글 1개 조회")
    public void findById() throws Exception {
        // given
        Long answerId = 1L;
        AnswerDao answerDao = new AnswerDao();
        // when
        Answer answer = answerDao.findById(answerId);
        // then
        assertThat(answer).isNotNull();
    }

    @Test
    @DisplayName("질문 1개의 댓글 조회")
    public void findAllByQuestionId() throws Exception {
        // given
        Long questionId = 8L;
        AnswerDao answerDao = new AnswerDao();
        // when
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
        // then
        assertThat(answers.size()).isGreaterThan(0);
    }
}
