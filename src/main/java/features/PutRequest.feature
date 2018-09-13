@FunctionalTest
Feature: put request using resreq site

Scenario: put a rest request api

Given put url request
When user put request
Then put request status code is 200
And put response body contains
		|name|morpheus|
		|job |zion resident |
And put response headers contains
		|Connection  |keep-alive|
		|Content-Type|application/json; charset=utf-8|