package steps;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before
    public void setup() throws IOException {
        StepDefinitions stepDefinitions = new StepDefinitions();

        if(StepDefinitions.place_ID==null){
            stepDefinitions.addPlacePayloadWithAnd("Rahul", "USA", "English");
            stepDefinitions.userCallsWithHTTPRequest("addPlaceAPI", "POST");
            stepDefinitions.in_response_body_is("status", "OK");
        }
    }
}
