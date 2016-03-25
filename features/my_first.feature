Feature: Books feature

    Scenario: I can see first book
        Given I start the app
        And I wait the book list loading finishes
        Then I see "Programming Google App Engine"

    Scenario: I can see last book
        Given I start the app
        And I wait the book list loading finishes
        When I scroll down to the list bottom
        Then I see "Google Maps API, 2nd Edition"
