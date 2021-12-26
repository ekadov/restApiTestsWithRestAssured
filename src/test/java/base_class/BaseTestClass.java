package base_class;

import com.restfulbooker.pojos.request.create_booking.BookingRequestPojo;
import com.restfulbooker.utils.ConfProperties;
import com.restfulbooker.utils.GenerateData;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

public class BaseTestClass {

    protected BookingRequestPojo generatedBooking = GenerateData.generateNewBooking();
    protected BookingRequestPojo generatedWrongBooking = GenerateData.generateWrongBooking();

    @BeforeAll
    static void globalConfig() {
        RestAssured.baseURI = ConfProperties.getBaseUrl();
        RestAssured.port = ConfProperties.getPort();
        RestAssured.defaultParser = Parser.JSON;
    }

}
