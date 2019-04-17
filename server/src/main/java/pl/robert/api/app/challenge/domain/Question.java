package pl.robert.api.app.challenge.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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

    String question;

    String answers;

    String correctAnswer;
}
