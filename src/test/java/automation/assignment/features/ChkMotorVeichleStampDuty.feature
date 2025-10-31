Feature:Validate Motor Veichle Stamp Duty

  Scenario Outline:Validate motor vehicle registration duty calculator
    Given User access "<Motor Vehicle Stamp Duty>" URL
    When  User clicks Online Button
    Then  open page "<page>" showing "<calculator>"
    When Select "<option>"
    And Enter "<vehicle amount>"
    And Click Calculate
   Then Pop up window renders the Calculated Duty as per the "<option>" and "<vehicle amount>"



    Examples:
      | Motor Vehicle Stamp Duty                                                |page                   |calculator                                |option      |vehicle amount|
      |https://www.service.nsw.gov.au/transaction/check-motor-vehicle-stamp-duty|Revenue NSW calculators|Motor vehicle registration duty calculator|Yes         |90000      |

