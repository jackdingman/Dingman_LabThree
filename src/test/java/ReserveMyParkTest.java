//Jack Dingman
//ReserveMyParkTesting

import org.example.IllegalAgeException;
import org.example.NightReservationException;
import org.example.ReserveMyPark;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest; //run same test multiple times with many inputs
import org.junit.jupiter.params.provider.CsvFileSource; //CSV file data source for parameterized testing

import static org.junit.jupiter.api.Assertions.*; //assertEquals and assertThrows

public class ReserveMyParkTest {

    private ReserveMyPark reserveMyPark; //create object

    @BeforeEach //runs before every single test. Will run once per row. Runs a fresh object so tests don't share state
    void setUp() {
        reserveMyPark = new ReserveMyPark();
    }

    @ParameterizedTest
    @CsvFileSource(resources="/ageTest.csv", numLinesToSkip=1)
    void testAgeValid(String testCaseID, int age, double expected) throws Exception {

        double price = reserveMyPark.calculateStayPrice( //fix all parameters except the one we are testing, age.
                1,
                age,
                false,
                false
        );

        assertEquals(expected, price); //verification that the returned price matches the expected in the csv.
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ageTestInvalid.csv", numLinesToSkip = 1)
    void testAgeInvalid(String tcId,
                            int age) {

        assertThrows( // verifies that the code inside the lambda throws exception type, since we have an invalid input
                IllegalAgeException.class, // IllegalAgeException is the exception that I created that disables inputs of age under 0
                () -> reserveMyPark.calculateStayPrice(
                        1,
                        age,
                        false,
                        false
                )
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources="/nightsTest.csv", numLinesToSkip = 1)
    void testNightsValid(String testCaseID, int nights, double expected) //works similarly to the age test, nights is the only variable that varies for testing
        throws Exception {
        double price = reserveMyPark.calculateStayPrice(
                nights,
                50,
                false,
                false
        );
        assertEquals(expected, price);

    }
    @ParameterizedTest
    @CsvFileSource(resources = "/nightsTestInvalid.csv", numLinesToSkip = 1)
    void testNightsInvalid(String tcId,
                               int nights) {

        assertThrows(
                NightReservationException.class,
                () -> reserveMyPark.calculateStayPrice(
                        nights,
                        50,
                        false,
                        false
                )
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources="/DecisionTable.csv", numLinesToSkip = 1)
    void decisionTableTest(String testCaseID, boolean resident, boolean veteran, double expected )
        throws Exception { //nights and guest age are fixed to test resident and veteran booleans
        double price = reserveMyPark.calculateStayPrice(1,50,resident, veteran);
        assertEquals(expected, price);
    }

}
