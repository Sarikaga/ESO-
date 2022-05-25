#Author: sarika.satyajeet.gaikwad@gmail.com
#Feature:Dummy Test cases for UI application PropertyPal

Feature: Testing of Web Page Challenging Dom
  I want to use this template for my feature file

 
  #Scenario: Verify Web page can be opened and the page header matches the expected name "ProprtyPal.com"
    #Given I have open the browser
    #When I open PropertyPal.com website
    #When Verify Web Page Title as PropertyPal.com is dispalyed on UI
    #Then Close browser   
 
   #Scenario: Search properties in BT6 area for Page1 and Page2
    #Given I have open the browser
    #When I open PropertyPal.com website
    #When Search for properties in BT6 area
    #Then Validate that properties from BT6 are displayed on Page
    #Then Validate that properties from BT6 are displayed on Page2
    #Then Validate that properties from BT6 are displayed on Page
    #Then Close browser
    
   #Scenario: Search properties in BT6 and BT5
    #Given I have open the browser
    #When I open PropertyPal.com website
    #When Search for properties in BT6 and BT5 area
    #Then Validate that properties from multiple postcodes BT6 and BT5 are displayed on Page
    #Then Close browser
   
  Scenario: Sort Searched properties in BT6
    Given I have open the browser
    When I open PropertyPal.com website
    When Search for properties in BT6 area
    Then Sort the proprties for any postcode
    Then Close browser 

       
 #Scenario: Vefify Link name from Web Page
    #Given I have open the browser
    #When I open Challenging Dom website
    #Then Verify Link name at the bottom of the Page
    #Then Close browser
#
 #Scenario: Verify buttons on Web page changing Id's on click event
    #Given I have open the browser
    #When I open Challenging Dom website
    #Then Verify three button on UI in blue Red and Green
    #Then Close browser
    #
 #Scenario: Verify table header  on Web Page
    #Given I have open the browser
    #When I open Challenging Dom website
    #Then Verify table header on webPage
    #Then Close browser
#
 #Scenario: Verify table Edit and Delete button  on Web Page
    #Given I have open the browser
    #When I open Challenging Dom website
    #Then Verify edit and delete button in table
    #Then Close browser
