package next.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;
import next.model.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionDaoTest {
    private static final Logger log = LoggerFactory.getLogger(QuestionDaoTest.class);
    
    @BeforeEach
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }
    
    @Test
    public void crud() {
        Question question = new Question("writer", "title", "contents");
        QuestionDao questionDao = new QuestionDao();
        Question savedQuestion = questionDao.insert(question);
        log.debug("question : {}", savedQuestion);
    }

    @Test
    @DisplayName("전체 리스트 조회")
    public void findAll() throws Exception {
        // given
        QuestionDao questionDao = new QuestionDao();
        // when
        List<Question> list = questionDao.findAll();
        // then
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("질문 1개 조회")
    public void findById() throws Exception {
        // given
        Long questionId = 1L;
        QuestionDao questionDao = new QuestionDao();
        // when
        Question question = questionDao.findById(questionId);
        // then
        assertThat(question).isNotNull();
    }



}
