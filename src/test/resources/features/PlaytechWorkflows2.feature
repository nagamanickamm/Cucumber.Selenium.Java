Feature: Validate given tests on Playtech.com

  @NoSmoke
  Scenario: Customers below 18 years are not allowed to enter Playtech.com
    Given Im on Playtech homepage
    When I see Age Verification Alert with heading as "Welcome to the Playtech website"
    And I see message on the Alert "Please confirm you are of legal age before entering this site"
    And I see content on the Alert "AgeRestriction_Content1"
    And I see responsible Gambling message on the Alert "AgeRestriction_Content2"
    And I enter my age and submit
      | Day | Month | Year |
      | 01  | 05    | 2008 |
    Then I see the error message "Sorry you must be over 18 to enter."

  Scenario Outline: Customers with 18 or above are allowed to enter Playtech.com
    Given Im on Playtech homepage
    When I see Age Verification Alert with heading as "Welcome to the Playtech website"
    And I see message on the Alert "Please confirm you are of legal age before entering this site"
    And I see content on the Alert "AgeRestriction_Content1"
    And I see responsible Gambling message on the Alert "AgeRestriction_Content2"
    When I enter my age and submit
      | Day   | Month   | Year   |
      | <day> | <month> | <year> |
    Then I can enter into the homepage now

    Examples:
      | scenario         | day | month | year |
      | "on 18 years"    | 01  | 03    | 2003 |
      | "above 18 years" | 01  | 05    | 2000 |

  @Smoke
  Scenario: Customers checks the overview of playtech
    Given Im on Playtech homepage
    When I enter my age and submit
      | Day | Month | Year |
      | 01  | 05    | 2000 |
    Then I can enter into the homepage now
    When I go to AboutUs page
    Then Iam at AboutUs page
    When I go to the company stats section
    Then I see "Employee stats" i.e "Number of Employees" as 5900
    And I see "Location stats" i.e "Number of countries Playtech has offices" as 19
    And I see "Global stats" i.e "Global licensees" as 140
    And I see "Regulated stats" i.e "Regulated Jurisdictions" as 20