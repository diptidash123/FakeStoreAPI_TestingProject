package utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertionsReader
{
  private static SoftAssert softAssert;

  //Default constructor
  /*public SoftAssertionsReader()
  {
      softAssert=new SoftAssert();
  }*/
  //Another way to initialize
  public static void initialize()
  {
      softAssert=new SoftAssert();
  }
  //assertEquals validation
  public static void assertEquals(Object actual,Object expected,String message)
  {
      softAssert.assertEquals(actual,expected,message);
  }

  //assertNotEquals validation
    public static void assertNotEquals(Object actual,Object expected,String message)
    {
        softAssert.assertNotEquals(actual,expected,message);
    }
  //assertNotNull validation
   public static void assertNotNull(Object obj,String message)
   {
       softAssert.assertNotNull(obj,message);
   }
   //assertTrue validation
    public static void assertTrue(boolean condition,String message)
    {
        softAssert.assertTrue(condition,message);
    }
    //assertFalse validation
    public static void assertFalse(boolean condition,String message)
    {
        softAssert.assertFalse(condition,message);
    }
    // Final assertAll (MANDATORY)
    public static void assertAll()
    {
        softAssert.assertAll();
    }


}