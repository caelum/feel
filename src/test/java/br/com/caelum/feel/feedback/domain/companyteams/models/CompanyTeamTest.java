package br.com.caelum.feel.feedback.domain.companyteams.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Company Team tests")
class CompanyTeamTest {


    @Nested
    @DisplayName("Creation tests")
    class Creation {

        @Test
        @DisplayName("A company team should not instantiate with a null name")
        void nameCantBeNull(){
            var nullName = assertThrows(IllegalArgumentException.class, () -> new CompanyTeam(1L, null, 1));

            assertEquals("Name required", nullName.getMessage());
        }

        @Test
        @DisplayName("A company team should not instantiate with a empty name")
        void nameCantBeEmpty(){
            var nullName = assertThrows(IllegalArgumentException.class, () -> new CompanyTeam(1L, "", 1));

            assertEquals("Name required", nullName.getMessage());
        }

        @Test
        @DisplayName("A company team should not instantiate if total expected people is null")
        void totalExpectedPeopleCantBeNull(){
            var nullTotal = assertThrows(IllegalArgumentException.class, () ->  new CompanyTeam(1L, "Caelum", null));

            assertEquals("Total expected people required", nullTotal.getMessage());
        }

        @Test
        @DisplayName("A company team should not instantiate if total expected people is negative")
        void totalExpectedPeopleCantBeNegative(){
            var negativeTotal = assertThrows(IllegalArgumentException.class, () -> new CompanyTeam(1L, "Caelum", -1));


            assertEquals("Total expected people should be positive", negativeTotal.getMessage());
        }

        @Test
        @DisplayName("A company team should have a name and total expected people and maybe an id")
        void nameAndTotalExpectedPeopleRequired(){
           var team = new CompanyTeam(1L, "Caelum", 25);


           assertEquals(Long.valueOf(1L), team.getId());
           assertEquals("Caelum", team.getName());
           assertEquals(Integer.valueOf(25), team.getTotalExpectedPeople());


           team = new CompanyTeam(null, "Caelum", 25);

            assertNull(team.getId());
            assertEquals("Caelum", team.getName());
            assertEquals(Integer.valueOf(25), team.getTotalExpectedPeople());

        }

    }


    @Nested
    @DisplayName("Equality tests")
    class Equality {

        @Test
        @DisplayName("Two company team are equals only when both name  are equal")
        void checkIfAreEqualsByName(){
            var firstTeam = new CompanyTeam(1L, "Caelum", 25);
            var secondTeam = new CompanyTeam(2L, "Caelum", 32);


            assertEquals(firstTeam, secondTeam);
            assertEquals(firstTeam.hashCode(), secondTeam.hashCode());
        }

        @Test
        @DisplayName("Two company team are different only when both name are different")
        void checkIfAreDifferentByName(){
            var firstTeam = new CompanyTeam(1L, "Caelum", 25);
            var secondTeam = new CompanyTeam(2L, "CaelumWeb", 25);


            assertNotEquals(firstTeam, secondTeam);
            assertNotEquals(firstTeam.hashCode(), secondTeam.hashCode());
        }

    }


}