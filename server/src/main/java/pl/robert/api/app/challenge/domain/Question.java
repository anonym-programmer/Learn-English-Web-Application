package pl.robert.api.app.challenge.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static pl.robert.api.app.shared.Constants.*;

@Entity
@Table(name = "questions")
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = COL_LENGTH_QUESTION, nullable = false)
    String question;

    @Column(length = COL_LENGTH_ANSWERS, nullable = false)
    String answers;

    @Column(name = "correct_answer_full_form", length = COL_LENGTH_CORRECT_ANSWER_FULL_FORM, nullable = false)
    String correctAnswerFullForm;

    @Enumerated(EnumType.STRING)
    @Column(name = "correct_answer_short_form", length = COL_LENGTH_CORRECT_ANSWER_SHORT_FORM, nullable = false)
    QuestionAnswerOptions correctAnswerShortForm;
}
