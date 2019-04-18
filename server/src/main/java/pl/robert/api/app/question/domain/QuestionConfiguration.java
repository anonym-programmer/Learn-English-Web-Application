package pl.robert.api.app.question.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class QuestionConfiguration {

    @Bean
    QuestionFacade questionFacade(QuestionRepository questionRepository) {
        return new QuestionFacade(new QuestionService(questionRepository));
    }
}
