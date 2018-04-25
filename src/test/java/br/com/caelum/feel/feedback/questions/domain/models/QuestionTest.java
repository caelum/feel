package br.com.caelum.feel.feedback.questions.domain.models;

import br.com.caelum.feel.feedback.questions.domain.models.vo.Affirmation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Question tests")
class QuestionTest {

    private Affirmation defaultAffirmation = new Affirmation("Some statement", "Description of lower value", "Description of highest value");
    private LocalDate tomorrow = LocalDate.now().plusDays(1);

    @Nested
    @DisplayName("Creation tests")
    class Creation {

        @Test
        @DisplayName("A question can't instantiate with a null explanation")
        void explanationCantBeNull(){
            var nullExplanation = assertThrows(IllegalArgumentException.class, () -> new Question(null, defaultAffirmation, tomorrow));

            assertEquals("Explanation required", nullExplanation.getMessage());
        }

        @Test
        @DisplayName("A question can't instantiate with a empty explanation")
        void explanationCantBeEmpty(){
            var emptyExplanation = assertThrows(IllegalArgumentException.class, () -> new Question("", defaultAffirmation, tomorrow));

            assertEquals("Explanation required", emptyExplanation.getMessage());
        }

        @Test
        @DisplayName("A question can't instantiate with a null affirmation")
        void affirmationCantBeNull(){
            var nullAffirmation = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", null, tomorrow));

            assertEquals("Affirmation required", nullAffirmation.getMessage());
        }


        @Test
        @DisplayName("A question can't be instantiate with a null due date")
        void dueDateCantBeNull(){
            var nullDueDate = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", defaultAffirmation, null));

            assertEquals("Due date required", nullDueDate.getMessage());
        }

        @Test
        @DisplayName("A question can't be instantiate with a due date in the present")
        void dueDateCantBeInThePresent(){
            var today = LocalDate.now();
            var dueDateInThePresent = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", defaultAffirmation, today));

            assertEquals("Due date should be in the future", dueDateInThePresent.getMessage());

        }

        @Test
        @DisplayName("A question can't be instantiate with a due date in the past")
        void dueDateCantBeInThePast(){
            var yesterday = LocalDate.now().minusDays(1);

            var dueDateInThePast = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", defaultAffirmation, yesterday));

            assertEquals("Due date should be in the future", dueDateInThePast.getMessage());
        }

        @Test
        @DisplayName("A question should have a explanation, statement, description for lower and highest value and due date")
        void shouldHaveAExplanation_Affirmation_And_DueDate(){
            var question = new Question("Some explanation", defaultAffirmation, tomorrow);

            assertEquals("Some explanation", question.getExplanation());
            assertEquals("Some statement", question.getStatement());
            assertEquals("Description of lower value", question.getDescriptionOfLowerValue());
            assertEquals("Description of highest value", question.getDescriptionOfHighestValue());
            assertEquals(tomorrow, question.getDueDate());
        }
    }



    @Nested
    @DisplayName("Equality tests")
    class Equality {
        @Test
        @DisplayName("Two questions are equals when both affirmation are equal")
        void shouldBeEqualsByAffirmation(){
            var questionA = new Question("Explanation A", defaultAffirmation, tomorrow);
            var questionB = new Question("Explanation B", defaultAffirmation, tomorrow.plusDays(1));

            assertEquals(questionA, questionB);
            assertEquals(questionA.hashCode(), questionB.hashCode());
        }

        @Test
        @DisplayName("Two questions are not equals when both affirmation are different")
        void shouldBeDifferentByAffirmation(){
            var affirmationA = new Affirmation("Statement A", "Lower A", "Highest A");
            var affirmationB = new Affirmation("Statemetn B", "Lower B", "Highest B");

            var questionA = new Question("Explanation A", affirmationA, tomorrow);
            var questionB = new Question("Explanation B", affirmationB, tomorrow.plusDays(1));

            assertNotEquals(questionA, questionB);
            assertNotEquals(questionA.hashCode(), questionB.hashCode());
        }
    }

}