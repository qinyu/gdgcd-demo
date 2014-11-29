Feature: Books feature

Scenario: I can see first book
When I start the app
Then I see "Programming Google App engine"


Scenario: I can see last book
When I start the app
Then I scroll down to the list bottom
Then I see "Google Maps API, 2nd Edition"