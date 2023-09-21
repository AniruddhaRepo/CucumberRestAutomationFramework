Feature: Validating Add Place and Delete APIs are working fine or not

  @AddPlace @Regression
  Scenario Outline: Add Place API Validation
    Given Add Place Payload with "<name>" "<address>" and "<language>"
    When user calls "<API>" with "Post" HTTP request
    Then the API call should be success with "<success_code>"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_id created maps to "<name>" using "getPlaceAPI"
    Examples:
      | API         | success_code | name      | address           | language |
      | addPlaceAPI | 200          | Aniruddha | 20 Downing Street | English  |
#      | getPlaceAPI | 200          | Robin | 11 Downing Street | Spanish  |

  @DeletePlace @Regression
  Scenario Outline: Verify if delete functionality is working fine
    Given Delete Place Payload
    When user calls "<API>" with "Post" HTTP request
    Then the API call should be success with "<success_code>"
    And "status" in response body is "OK"
    Examples:
      | API            | success_code |
      | deletePlaceAPI | 200          |