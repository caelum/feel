package br.com.caelum.feel.feedback.questions.domain.models.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Affirmation tests")
class AffirmationTest {


    @Nested
    @DisplayName("Creation Tests")
    class Creation {



        @Test
        @DisplayName("An affirmation can't be instantiate with a null statement")
        void statementCantBeNull(){
            var nullStatement = assertThrows(IllegalArgumentException.class, () -> new Affirmation(null, "Lower", "Highest"));

            assertEquals("Statement required", nullStatement.getMessage());
        }

        @Test
        @DisplayName("An affirmation can't be instantiate with a empty statement")
        void statementCantBeEmpty(){
            var emptyStatement = assertThrows(IllegalArgumentException.class, () -> new Affirmation("", "Lower", "Highest"));

            assertEquals("Statement required", emptyStatement.getMessage());
        }


        @Test
        @DisplayName("An affirmation can't be instantiate with a null description of lower value")
        void descriptionOfLowerValueCantBeNull(){
            var nullDescriptionOfLowerValue = assertThrows(IllegalArgumentException.class, () -> new Affirmation("Statement", null, "Highest"));

            assertEquals("Description of lower value required", nullDescriptionOfLowerValue.getMessage());
        }

        @Test
        @DisplayName("An affirmation can't be instantiate with a empty description of lower value")
        void descriptionOfLowerValueCantBeEmpty(){
            var emptyDescriptionOfLowerValue = assertThrows(IllegalArgumentException.class, () -> new Affirmation("Statement", "", "Highest"));

            assertEquals("Description of lower value required", emptyDescriptionOfLowerValue.getMessage());
        }

        @Test
        @DisplayName("An affirmation can't be instantiate with a null description of highest value")
        void descriptionOfHighestValueCantBeNull(){
            var nullDescriptionOfHighestValue = assertThrows(IllegalArgumentException.class, () -> new Affirmation("Statement", "Lower", null));

            assertEquals("Description of highest value required", nullDescriptionOfHighestValue.getMessage());
        }

        @Test
        @DisplayName("An affirmation can't be instantiate with a empty description of highest value")
        void descriptionOfHighestValueCantBeEmpty(){
            var emptyDescriptionOfHighestValue = assertThrows(IllegalArgumentException.class, () -> new Affirmation("Statement", "Lower", ""));

            assertEquals("Description of highest value required", emptyDescriptionOfHighestValue.getMessage());
        }

        @Test
        @DisplayName("An affirmation should have a statement and description of lower and highest value")
        void shouldHaveStatement_DescriptionOf_Lower_And_Highest_Value(){

            var affirmation = new Affirmation("Some statement", "Some description of lower value", "Some description of highest value");

            assertEquals("Some statement", affirmation.getStatement());
            assertEquals("Some description of lower value", affirmation.getDescriptionOfLowerValue());
            assertEquals("Some description of highest value", affirmation.getDescriptionOfHighestValue());

        }
    }

    @Nested
    @DisplayName("Equality test")
    class Equality {


        @Test
        @DisplayName("Two affirmation are equals when both statement are equals")
        void shouldBeEqualsByStatement() {
            var affirmationA = new Affirmation("Statement", "Lower A", "Highest A");
            var affirmationB = new Affirmation("Statement", "Lower B", "Highest B");

            assertEquals(affirmationA, affirmationB);
            assertEquals(affirmationA.hashCode(), affirmationB.hashCode());
        }

        @Test
        @DisplayName("Two affirmation are equals when both statement are different")
        void shouldBeEqualsByDifferent() {
            var affirmationA = new Affirmation("Statement A", "Lower A", "Highest A");
            var affirmationB = new Affirmation("Statement B", "Lower B", "Highest B");

            assertNotEquals(affirmationA, affirmationB);
            assertNotEquals(affirmationA.hashCode(), affirmationB.hashCode());
        }
    }

}