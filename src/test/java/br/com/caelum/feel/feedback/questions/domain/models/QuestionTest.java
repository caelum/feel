package br.com.caelum.feel.feedback.questions.domain.models;

import br.com.caelum.feel.feedback.questions.domain.models.vo.Affirmation;
import br.com.caelum.feel.feedback.questions.domain.models.vo.QuestionState;
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
        @DisplayName("A question can't be instantiate with a null explanation")
        void explanationCantBeNull(){
            var nullExplanation = assertThrows(IllegalArgumentException.class, () -> new Question(null, defaultAffirmation, tomorrow, QuestionState.OPEN));

            assertEquals("Explanation required", nullExplanation.getMessage());
        }

        @Test
        @DisplayName("A question can't be instantiate with a empty explanation")
        void explanationCantBeEmpty(){
            var emptyExplanation = assertThrows(IllegalArgumentException.class, () -> new Question("", defaultAffirmation, tomorrow, QuestionState.OPEN));

            assertEquals("Explanation required", emptyExplanation.getMessage());
        }

        @Test
        @DisplayName("A question can't be instantiate with a null affirmation")
        void affirmationCantBeNull(){
            var nullAffirmation = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", null, tomorrow, QuestionState.OPEN));

            assertEquals("Affirmation required", nullAffirmation.getMessage());
        }
        @Test
        @DisplayName("A question can't be instantiate with a null due date")
        void dueDateCantBeNull(){
            var nullDueDate = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", defaultAffirmation, null, QuestionState.OPEN));

            assertEquals("Due date required", nullDueDate.getMessage());
        }

        @Test
        @DisplayName("A question can't be instantiate with a due date in the present")
        void dueDateCantBeInThePresent(){
            var today = LocalDate.now();
            var dueDateInThePresent = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", defaultAffirmation, today, QuestionState.OPEN));

            assertEquals("Due date should be in the future", dueDateInThePresent.getMessage());

        }

        @Test
        @DisplayName("A question can't be instantiate with a due date in the past")
        void dueDateCantBeInThePast(){
            var yesterday = LocalDate.now().minusDays(1);

            var dueDateInThePast = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", defaultAffirmation, yesterday, QuestionState.OPEN));

            assertEquals("Due date should be in the future", dueDateInThePast.getMessage());
        }

        @Test
        @DisplayName("A question can't be instantiate with a null state")
        void initialStateCantBeNull(){
            var nullState = assertThrows(IllegalArgumentException.class, () -> new Question("Some explanation", defaultAffirmation,tomorrow, null));

            assertEquals("Initial state required", nullState.getMessage());
        }

        @Test
        @DisplayName("A question should have a explanation, statement, description for lower and highest value and initial state")
        void shouldHaveAExplanationAffirmationAndInitialState(){
            var question = new Question("Some explanation", defaultAffirmation,tomorrow, QuestionState.OPEN);

            assertEquals("Some explanation", question.getExplanation());
            assertEquals("Some statement", question.getStatement());
            assertEquals("Description of lower value", question.getDescriptionOfLowerValue());
            assertEquals("Description of highest value", question.getDescriptionOfHighestValue());
            assertEquals(tomorrow, question.getDueDate());
            assertEquals(QuestionState.OPEN, question.getCurrentState());
        }
    }



    @Nested
    @DisplayName("Equality tests")
    class Equality {
        @Test
        @DisplayName("Two questions are equals when both affirmation are equal")
        void shouldBeEqualsByAffirmation(){
            var questionA = new Question("Explanation A", defaultAffirmation,tomorrow, QuestionState.OPEN);
            var questionB = new Question("Explanation B", defaultAffirmation,tomorrow, QuestionState.CLOSE);

            assertEquals(questionA, questionB);
            assertEquals(questionA.hashCode(), questionB.hashCode());
        }

        @Test
        @DisplayName("Two questions are not equals when both affirmation are different")
        void shouldBeDifferentByAffirmation(){
            var affirmationA = new Affirmation("Statement A", "Lower A", "Highest A");
            var affirmationB = new Affirmation("Statemetn B", "Lower B", "Highest B");

            var questionA = new Question("Explanation A", affirmationA,tomorrow, QuestionState.OPEN);
            var questionB = new Question("Explanation A", affirmationB,tomorrow, QuestionState.OPEN);

            assertNotEquals(questionA, questionB);
            assertNotEquals(questionA.hashCode(), questionB.hashCode());
        }
    }




    @Test
    @DisplayName("A question in close state should not transit to close state")
    void shouldNotTransitFromCloseToClose(){
        var question = new Question("Some explanation", defaultAffirmation,tomorrow, QuestionState.CLOSE);

        var transitionFail = assertThrows(IllegalStateException.class, question::close);

        assertEquals("Question is already closed", transitionFail.getMessage());
    }

    @Test
    @DisplayName("A question in close state should transit to open state")
    void shouldTransitFromCloseToOpen(){
        var question = new Question("Some explanation", defaultAffirmation,tomorrow, QuestionState.CLOSE);

        question.open();

        assertEquals(QuestionState.OPEN, question.getCurrentState());
    }

    @Test
    @DisplayName("A question in open state should not transit to open state")
    void shouldNotTransitFromOpenToOpen(){
        var question = new Question("Some explanation", defaultAffirmation,tomorrow, QuestionState.OPEN);

        var transitionFail = assertThrows(IllegalStateException.class, question::open);

        assertEquals("Question is already open", transitionFail.getMessage());
    }

    @Test
    @DisplayName("A question in close state should transit to open state")
    void shouldTransitFromOpenToClose(){
        var question = new Question("Some explanation", defaultAffirmation,tomorrow, QuestionState.OPEN);

        question.close();

        assertEquals(QuestionState.CLOSE, question.getCurrentState());
    }


}