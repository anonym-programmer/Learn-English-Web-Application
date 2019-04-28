import {AnsweredQuestionQuery} from './answered-question-query.model';

export class CompletedChallengeDetailsCorrectAnswersQuery {
  questions: AnsweredQuestionQuery[];
  areCorrect: string[];
  myAnswers: string[];
}
