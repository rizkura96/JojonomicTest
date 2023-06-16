Feature: API Automation

  Background:
    Given I have registered and obtained the API token key

  Scenario: Get current weather state code
    When I send a GET request to "current" with lat "40.730610" and lon "-73.935242"
    Then the response should have status code 200
    And the response should have property "data.state_code"

  Scenario: Get weather forecast data
    When I send a GET request to "forecast/3hourly" with postal_code "{postal_code}"
    Then the response should have status code 200
    And the response should have properties "timestamp_utc" and "weather" for all data entries
