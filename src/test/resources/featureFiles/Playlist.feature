@api
Feature: Playlist Service

  As a dev/tester
  I want to execute playlist service
  So that i can create a playlist.

  Scenario: Create a video
    When I execute POST request on endpoint "/api/playlist" with payload "playlist"
    Then I should expected status code as 201
    Then I should validate response as below
      | title | My List            |
      | desc  | My first playlist. |

  Scenario: Returns a playlist
    When I execute GET request on endpoint "/api/playlist/{id}"
    Then I should expected status code as 200
    Then I should validate response as below
      | desc | My first playlist.  |
      | title | My List            |

  Scenario: Returns list of playlist
    When I execute GET request on endpoint "/api/playlist"
    Then I should expected status code as 200
    Then I should expected response contains more than 1 object

  Scenario: Patch add song playlist
    When I execute Patch request on endpoint "/api/playlist/{id}" with payload "playlist-add-viedo"
    Then I should expected status code as 204
    Then I should expected response body returns ""


  Scenario: Patch Remove song playlist
    When I execute Patch request on endpoint "/api/playlist/{id}" with payload "playlist-remove-viedo"
    Then I should expected status code as 501
    Then I should expected response body returns "Not implemented."


  Scenario: Delete a playlist
    When I execute DELETE request on endpoint "/api/playlist/{id}"
    Then I should expected status code as 204
    Then I should expected response body returns ""
