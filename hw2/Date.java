/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
  private int month;
  private int day;
  private int year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
    if(this.isValidDate(month, day, year)) {
      this.month = month;
      this.day = day;
      this.year = year;
    }
    else {
      System.out.println("ERROR: Invalid date detected!");
      System.exit(0);
    }
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
  	// Examine the format of the input string by Regular Expression.
    if(s.matches("(\\d{1,2})/(\\d{1,2})/(\\d{1,4})")){
      String[] ss = s.split("/");
      //this.Date(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));     // WHY CAN'T THIS LINE WORK?
      //this(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));          // WHY CAN'T THIS LINE WORK?
      if(Date.isValidDate(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]))) {
      month = Integer.parseInt(ss[0]);
      day = Integer.parseInt(ss[1]);
      year = Integer.parseInt(ss[2]);
      }
      else {
        System.out.println("ERROR: Invalid date detected!");
        System.exit(0);
      }

    }
    else {
      System.out.println("ERROR: Invalid date string detected! Correct example: 12\\21\\2017");
    }
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
    //return true;                        // replace this line with your solution
    if(year % 4 == 0) {
      if(year % 100 ==0) {
        if(year % 400 ==0) {
          return true;
        }
        else {
          return false;
        }
      }
      else {
        return true;
      }
    }
    else {
      return false;
    }
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    //return 0;                           // replace this line with your solution
    /*switch(month){
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
          return 31;
      case 4:
      case 6:
      case 9:
      case 11:
          return 30;
      case 2:
          if(Date.isLeapYear(year)){
            return 29;
          }
          else{
            return 28;
          }
    }*/
    //WHY CAN'T THE LINES ABOVE WORK?
    int i = 0;
    switch(month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
          i = 31;
          break;
      case 4:
      case 6:
      case 9:
      case 11:
          i = 30;
          break;
      case 2:
          if(Date.isLeapYear(year)) {
            i = 29;
          }
          else {
            i = 28;
          }
    }
    return i;
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    //return true;                        // replace this line with your solution
    if(year >=0 && year <=9999) {
      if(month >=1 && month <= 12) {
        if(day >=1 && day <= Date.daysInMonth(month, year)) {
          return true;
        }
        else {
          return false;
        }
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    //return "stuff";                     // replace this line with your solution
    return month + "/" + day + "/" + year; 
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
    //return true;                        // replace this line with your solution
    if(this.year < d.year){
      return true;
    }
    else if(this.year == d.year) {
      if(this.month < d.month) {
        return true;
      }
      else if(this.month == d.month) {
        if(this.day < d.day) {
          return true;
        }
        else {
          return false;
        }
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
  	//return true;                        // replace this line with your solution
  	// If this is not before or equal to d, return true, else false.
    return !this.isBefore(d) && this != d;
    
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
    //return 0;                           // replace this line with your solution
    int num = 0;
    for(int i = 1; i < this.month; i++) {
      num = num + Date.daysInMonth(i, this.year);
    }
    num = num + this.day;
    return num;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
    //return 0;                           // replace this line with your solution
    // If this and d are the same date, then return 0.
    if(this == d) {
      return 0;
    }
    // Else if this is before d, the result should be negative.
    else if(this.isBefore(d)) {
      return -d.difference(this);      
    }
    // Define the value of parameter diffYear
    int diffYear = 0;
    if(this.year - d.year > 0) {
    	diffYear= 1;
    }
    // Add up the number of days from d's next year to this's last year.
    int diff = 0;
    for(int i = 1; i < this.year - d.year; i++) {
    	Date lastDayOfYear = new Date(12, 31, d.year + i);
    	diff = diff + lastDayOfYear.dayInYear();
    }
    // Add up the difference and return the result.
    Date lastDayOfYear = new Date(12, 31, d.year);
    diff = diff + this.dayInYear() + lastDayOfYear.dayInYear() * diffYear - d.dayInYear();
    return diff;

    /*
    // Define the former and latter date object;
    Date earlierDate = d;
    Date laterDate = this;
    int orderOfDates = 1;       // Define a parameter that tells the order of this and d.
    // If this and d are the same date, then return 0.
    if(this == d) {
      return 0;
    }
    // Else if this is before d, switch them so that this is always after d.
    else if(this.isBefore(d)) {
      earlierDate = this;
      laterDate = d;
      orderOfDates = -1;           // If this is before d, the result should be negative.
    }
    // Define the value of parameter diffYear
    int diffYear = 0;
    if(laterDate.year - earlierDate.year > 0) {
    	diffYear= 1;
    }
    // Add up the number of days from d's next year to this's last year.
    int diff = 0;
    for(int i = 1; i < laterDate.year - earlierDate.year; i++) {
    	Date lastDayOfYear = new Date(12, 31, earlierDate.year + i);
    	diff = diff + lastDayOfYear.dayInYear();
    }
    // Add up the difference and return the result.
    Date lastDayOfYear = new Date(12, 31, d.year);
    diff = diff + laterDate.dayInYear() + lastDayOfYear.dayInYear() * diffYear - earlierDate.dayInYear();
    return orderOfDates * diff;
    */    // ???WHY DO THE LINES ABOVE RETURNS THE WRONG RESULT IF D IS BEFORE THIS???    
  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */ // As below:
    System.out.println("\nTesting isLeapYear function.");
    Date d6 = new Date("1/5/484");
    System.out.println(d6 + " is a leap year (should be ture): " + Date.isLeapYear(d6.year));
    d6 = new Date("1/5/2004");
    System.out.println(d6 + " is a leap year (should be ture): " + Date.isLeapYear(d6.year));
    d6 = new Date("1/5/8");
    System.out.println(d6 + " is a leap year (should be ture): " + Date.isLeapYear(d6.year));
    d6 = new Date("1/5/1800");
    System.out.println(d6 + " is a leap year (should be false): " + Date.isLeapYear(d6.year));
    d6 = new Date("1/5/1900");
    System.out.println(d6 + " is a leap year (should be false): " + Date.isLeapYear(d6.year));
    d6 = new Date("1/5/1600");
    System.out.println(d6 + " is a leap year (should be ture): " + Date.isLeapYear(d6.year));
    d6 = new Date("1/5/2000");
    System.out.println(d6 + " is a leap year (should be ture): " + Date.isLeapYear(d6.year));

    //Test daysInMonth and isValidDate functions.
    System.out.println("\nTesting daysInMonth function.");
    d6 = new Date("5/5/2000");
    System.out.println(d6 + " has 31 days in its month: " + Date.daysInMonth(d6.month, d6.year));
    d6 = new Date("4/4/2000");
    System.out.println(d6 + " has 30 days in its month: " + Date.daysInMonth(d6.month, d6.year));
    d6 = new Date("2/2/2000");
    System.out.println(d6 + " has 29 days in its month: " + Date.daysInMonth(d6.month, d6.year));
    d6 = new Date("2/2/1800");
    System.out.println(d6 + " has 28 days in its month: " + Date.daysInMonth(d6.month, d6.year));
    d6 = new Date("2/2/2011");
    System.out.println(d6 + " has 28 days in its month: " + Date.daysInMonth(d6.month, d6.year));
    /*System.out.println("\nTesting isValidDate function.");
    d6 = new Date("1/5/2004");
    d6 = new Date("1/5/1900");
    d6 = new Date("2/27/1800");
    d6 = new Date("2/29/2000");
    d6 = new Date("9/31/1987");*/ // WHY CAN'T THE LINES ABOVE WORK?

    // Test isBefore and isAfter functions.
    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    // Test dayInYear function.
    System.out.println("\nTesting dayInYear function.");
    d6 = new Date("1/8/2000");
    System.out.println(d6 + " should be the 8th day of the year: " + d6.dayInYear());
    d6 = new Date("2/1/2000");
    System.out.println(d6 + " should be the 32th day of the year: " + d6.dayInYear());
    d6 = new Date("12/31/2000");
    System.out.println(d6 + " should be the 366th day of the year: " + d6.dayInYear());
    d6 = new Date("12/31/2011");
    System.out.println(d6 + " should be the 365th day of the year: " + d6.dayInYear());

    // Test difference function.
    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d1 + " - " + d2  + " should be -1: " + 
                       d1.difference(d2));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d1 + " - " + d3  + " should be -2: " + 
                       d1.difference(d3));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d4 + " - " + d3  + " should be 422: " + 
                       d4.difference(d3));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
    System.out.println(d4 + " - " + d5  + " should be -48762: " + 
                       d4.difference(d5));
  }
}
