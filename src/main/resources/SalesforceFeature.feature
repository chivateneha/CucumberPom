Feature: Login into Salesforce web application

  Scenario: LoginErrorMessage
    Given User open salesforce application "https://login.salesforce.com/"
    When user on "LoginPage"
    And User enters value into text box username as "chirag@home.com"
    And User enters value into text box password as ""
    And Click on Login button
    Then verify we can see "Please enter your password." error message
    
  Scenario: LogintoSalesfore
  
    Given User open salesforce application "https://login.salesforce.com/"
    When user on "LoginPage"
    And User enters value into text box username as "chirag@home.com"
    And User enters value into text box password as "belgaum84"
    And Click on Login button
    And user on "homepage"
    Then verify we can see "Home" page
    
  Scenario: Remember me
  
    Given User open salesforce application "https://login.salesforce.com/"
    When user on "LoginPage"
    And User enters value into text box username as "chirag@home.com"
    And User enters value into text box password as "belgaum84"
    And User selects remember me check box
    And Click on Login button
    And user on "homepage"
    Then verify we can see "Home" page
    When User clicks on NehaABCD tab
    And User clicks Logout button
    And user on "LoginPage"
    Then verify we can see "Username" populated with the username chirag@home.com
    
  Scenario: Forgot Password A
  
    Given User open salesforce application "https://login.salesforce.com/"
    When user on "LoginPage"
    And Click on forgot password link
    And user on "ForgotpasswordPage"
    Then verify that we can see "Forgot Your Password" on forgot password page
    And User enters value into text box username as "chirag@home.com" on forgot password page
    And User clicks the continue button
    
  Scenario: Forgot Password B
  
    Given User open salesforce application "https://login.salesforce.com/"
    When user on "LoginPage"
    And User enters value into text box username as "123"
    And User enters value into text box password as "22131"
    And Click on Login button
    Then verify we can see Error message2 "Please check your username and password. If you still can't log in, contact your Salesforce administrator." 
    
