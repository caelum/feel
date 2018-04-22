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
        @DisplayName("A company name should not instantiate with a null name")
        void nameCantBeNull(){
            var nullName = assertThrows(IllegalArgumentException.class, () -> new CompanyTeam(1L, null));

            assertEquals("Name required", nullName.getMessage());
        }

        @Test
        @DisplayName("A company name should not instantiate with a empty name")
        void nameCantBeEmpty(){
            var nullName = assertThrows(IllegalArgumentException.class, () -> new CompanyTeam(1L, ""));

            assertEquals("Name required", nullName.getMessage());
        }

        @Test
        @DisplayName("A company team should have a name")
        void shouldHaveAName(){
           var team = new CompanyTeam(1L, "Caelum");

           assertEquals("Caelum", team.getName());
        }

    }


    @Nested
    @DisplayName("Equality tests")
    class Equality {

        @Test
        @DisplayName("Two company team are equals when both name  are equal")
        void checkIfAreEqualsByName(){
            var firstTeam = new CompanyTeam(1L, "Caelum");
            var secondTeam = new CompanyTeam(2L, "Caelum");


            assertEquals(firstTeam, secondTeam);
            assertEquals(firstTeam.hashCode(), secondTeam.hashCode());
        }

        @Test
        @DisplayName("Two company team are different when both name are different")
        void checkIfAreDifferentByName(){
            var firstTeam = new CompanyTeam(1L, "Caelum");
            var secondTeam = new CompanyTeam(2L, "CaelumWeb");


            assertNotEquals(firstTeam, secondTeam);
            assertNotEquals(firstTeam.hashCode(), secondTeam.hashCode());
        }

    }


}