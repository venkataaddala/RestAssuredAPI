@smokeTest
Feature: Delete request using resreq site

Scenario: Delete rest request api

Given Delete url request
When user Delete request
Then Delete request status code is 204
And Delete response headers contains
		|Connection  |keep-alive|