package pl.robert.api.app.challenge.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findById(long id);
}
