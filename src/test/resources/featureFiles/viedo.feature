@api
Feature: Video Service

  As a dev/tester
  I want to execute video service
  So that i can use music videos.

  Scenario: Create a video
    When I execute POST request on endpoint "/api/video/" with payload "video-post"
    Then I should expected status code as 201
    Then I should validate response as below
      | artist | Lady Gaga  |
      | song   | Poker face |

  Scenario: Returns data about a single video
    When I execute GET request on endpoint "/api/video/{id}"
    Then I should expected status code as 200
    Then I should validate response as below
      | artist | Lady Gaga  |
      | song   | Poker face |

  Scenario: Returns all data
    When I execute GET request on endpoint "/api/video"
    Then I should expected status code as 200
    Then I should expected response contains more than 1 object

  Scenario: Patch a video
    When I execute Patch request on endpoint "/api/video/{id}"
    Then I should expected status code as 501
    Then I should expected response body returns "Not implemented."

  Scenario: Delete a video
    When I execute DELETE request on endpoint "/api/video/{id}"
    Then I should expected status code as 204
    When I execute GET request on endpoint "/api/video/{id}"
    Then I should expected response body returns "null"



