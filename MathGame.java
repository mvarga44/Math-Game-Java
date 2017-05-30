
//MidTerm
//Maria Vargas Caraballo
package javaapplication;

import java.util.Scanner;   // Needed for Scanner class
import java.io.*;           // Needed for File I/O classes
import java.util.Random; // Needed for Random Numbers

public class MathGameMariaVargas
{
   public static void main(String[] args) throws IOException
   {
     // String userName;    // user's name
      String userInput;
      char proceed = 'y'; // for continuing program
      PrintWriter outputFile = null;
      UpdateStats myUserStats = new UpdateStats();//used to wrap userStat variables and pass them around from function to function

      // Create a Scanner object for keyboard input.
      Scanner keyboard = new Scanner(System.in);

      credits();//name of creator and game

      System.out.println();
      System.out.println("y/Y to continue, any other char to exit");
      userInput = keyboard.nextLine();

      int userAnswerLength = userInput.length();

      if(userAnswerLength == 0)//so if userInput is <ENTER> it closes and does not crash
	  {
	    System.out.println("Goodbye!");
        System.exit(0);
       }

      proceed = userInput.charAt(0);

      if(!(proceed == 'y' || proceed == 'Y'))// so that if anything besides y/Y closes
      {
		System.out.println("Goodbye!");
        System.exit(0);
      }

	  for (int counter = 0; counter < 3; counter++)
        {
         System.out.println();
        }

      System.out.print("Enter your name: ");
      myUserStats.userName = keyboard.nextLine();
      myUserStats.userName = checkUserName (myUserStats.userName);//Validate userName (for Strings)
      openStats(myUserStats, outputFile);//

      for (int counter = 0; counter < 3; counter++)
	        {
	           System.out.println();
        	}

      menu(myUserStats, outputFile);//display menu
      

   }// end main

 public static void credits () //to display creator name and name of game
    {
        System.out.println("***********************");
        System.out.println("***********************");
        System.out.println("***********************");
        System.out.println("******           ******");
        System.out.println("******TheMathGame******");
        System.out.println("******  ByMaria  ******");
        System.out.println("******  Vargas   ******");
        System.out.println("******           ******");
        System.out.println("***********************");
        System.out.println("***********************");
        System.out.println("***********************");

    } // end credits

 public static void menu (UpdateStats myUserStats, PrintWriter outputFile) throws IOException // to display menu
    {
     boolean bool = true; //to continue loop
     
     do
     {
        System.out.println("******CHOOSE A PROBLEM******");
        System.out.println("****************************");
        System.out.println("****************************");
        System.out.println("******                ******");
        System.out.println("****** 1. ADD         ******");
        System.out.println("****** 2. SUBTRACT    ******");
        System.out.println("****** 3. MULTIPLY    ******");
        System.out.println("****** 4. DIVIDE      ******");
        System.out.println("****** 5. STATS       ******");
        System.out.println("****** n/N to QUIT    ******");
        System.out.println("******                ******");
        System.out.println("****************************");
        System.out.println("****************************");
        System.out.println("****************************");
        
        
	  for (int counter = 0; counter < 3; counter++)
        {
            System.out.println();
        }

        String userInput;
        //char quit;
        String number;
        Scanner keyboard = new Scanner (System.in);

        number = keyboard.nextLine();//for user nubmber

        switch (number)
        {
            case "1": generateAddition(myUserStats);
            break;
            case "2": generateSubtraction(myUserStats);
            break;
            case "3":generateMultiplication(myUserStats);
            break;
            case "4":generateDivision(myUserStats);
            break;
            case "5":displayStats(myUserStats);
            break;
            case "n":displayStats(myUserStats);
            System.out.println ("Goodbye");
            saveStats(outputFile,myUserStats); 
            System.exit(0);
            break;
            case "N":displayStats(myUserStats);
            System.out.println ("Goodbye");
            saveStats(outputFile,myUserStats);
            System.exit(0);
            default:
            System.out.println("That is not a valid entry.\nPlease try again.");
        }
     }
        while (bool == true);

    } // end menu

 public static String checkUserResponse (String userAnswer) // to validate user number response
    {
        int userAnswerLength = userAnswer.length();
        int counter = 0;
        Scanner keyboard = new Scanner(System.in);

        while(userAnswerLength==0)
        {
            System.out.println("That is a blank.\nTry again.");
            userAnswer = keyboard.nextLine();
            userAnswerLength = userAnswer.length();
        }

        while(counter < userAnswerLength)
        {
            if(!Character.isDigit(userAnswer.charAt(counter)))
            {
                System.out.println("That is not a number.\nTry again.");
                userAnswer = keyboard.nextLine();
                userAnswerLength = userAnswer.length();
                counter = 0;
            }
            else
            {
                counter++;
            }
            while(userAnswerLength==0)
            {
                System.out.println("That is a blank, again.\nTry again.");
                userAnswer = keyboard.nextLine();
                userAnswerLength = userAnswer.length();
            }
        }
        
        return userAnswer;
        
        
    } //end checkUserResponse

  public static String checkUserName (String userAnswer) //to validate username
    {

        int userAnswerLength = userAnswer.length();
        int counter = 0;
        Scanner keyboard = new Scanner(System.in);

        while(userAnswerLength==0)
        {
            System.out.println("That is a blank.\nTry again.");
            userAnswer = keyboard.nextLine();
            userAnswerLength = userAnswer.length();
        }

        while(counter < userAnswerLength)
        {
            if(!Character.isLetter(userAnswer.charAt(counter)))
            {
                System.out.println("That is not a valid entry.\nTry again.");
                userAnswer = keyboard.nextLine();
                userAnswerLength = userAnswer.length();
                counter = 0;
            }
            else
            {
                counter++;
            }
            while(userAnswerLength==0)
            {
                System.out.println("That is a blank, again.\nTry again.");
                userAnswer = keyboard.nextLine();
                userAnswerLength = userAnswer.length();
            }
        }

        return userAnswer;
    }//end checkUserName

 public static class UpdateStats // to keep a running total of game statistics
    {
    int totalRight;
    int totalWrong;
    double totalWages;
    String userName;
    } //end updateStats

 public static void displayStats(UpdateStats myUserStats) // display statistics on screen
    {
        System.out.println(myUserStats.userName);
        System.out.println("Your score is:" );
        System.out.println("Total Earnings " + myUserStats.totalWages);
        System.out.println("Total Right " + myUserStats.totalRight);
        System.out.println("Total Wrong " + myUserStats.totalWrong);
    } //end displayStats

 public static void openStats(UpdateStats myUserStats, PrintWriter outputFile) throws IOException// to retrieve player's statistics from external txt file
    {
     //String fileName = myUserStats.userName + ".txt";

     File file = new File(myUserStats.userName + ".txt");//create a file object

            if (file.exists())
            {
                Scanner inputFile = new Scanner(file);

                 myUserStats.userName = inputFile.nextLine();
                 myUserStats.totalRight = inputFile.nextInt();
                 myUserStats.totalWrong = inputFile.nextInt();
                 myUserStats.totalWages = inputFile.nextDouble();
            }

            else
            {
                outputFile = new PrintWriter(file);//create a file for writing or saving

		outputFile.println(myUserStats.userName);
                outputFile.println(myUserStats.totalRight);
                outputFile.println(myUserStats.totalWrong);
                outputFile.println(myUserStats.totalWages);
                outputFile.close();

            }
    } // end retrieveStats

 public static void saveStats(PrintWriter outputFile, UpdateStats myUserStats) throws IOException // to save player's statistics on external txt file
    {
     	File file = new File(myUserStats.userName + ".txt");
     	outputFile = new PrintWriter(file);

        outputFile.println(myUserStats.userName);
	outputFile.println(myUserStats.totalRight);
	outputFile.println(myUserStats.totalWrong);
        outputFile.println(myUserStats.totalWages);
        outputFile.close();


    } //end saveStats

 public static void generateAddition(UpdateStats myuserStats) //to generate addition
   {
        Random someRandomInt = new Random();
        int number1 = (someRandomInt.nextInt(9)) + 1;
	int number2 = (someRandomInt.nextInt(9)) + 1;
	int theAnswer = number1 + number2;
	int userInputInteger;

	// Create a Scanner object to read input.
	Scanner keyboard = new Scanner(System.in);
        
        for (int counter = 0; counter < 3; counter++)
        {
         System.out.println();
        }
        

	System.out.println("******ADDITION******");
        System.out.println("********************");
        System.out.println("********************");
	System.out.println("***** " + number1 + " + " + number2 + " =?" + " *****");
        System.out.println("********************");
        System.out.println("********************");
        
	  for (int counter = 0; counter < 3; counter++)
        {
         System.out.println();
        }
        
	String userAnswer = keyboard.nextLine();
        userAnswer = checkUserResponse(userAnswer);//Validate userAnswer (for int)

        userInputInteger = Integer.parseInt(userAnswer);

	//userInputInteger = checkUserAnswer(userInputInteger);//check userAnswer for correctness
	if(userInputInteger == theAnswer)
	{
            System.out.println("CORRECT ");
            myuserStats.totalRight += 1;
            myuserStats.totalWages += 0.05;
	}
	else
	{
            System.out.println("INCORRECT ");
            myuserStats.totalWrong += 1;
            myuserStats.totalWages -= 0.03;
	}

    }//end generateAddition

public static void generateSubtraction(UpdateStats myuserStats) // to generate subtraction
    {
        Random someRandomInt = new Random();
            int number1 = (someRandomInt.nextInt(9)) + 1;
            int number2 = (someRandomInt.nextInt(9)) + 1;
            int theAnswer = number1 - number2;
            int userInputInteger;

            // Create a Scanner object to read input.
            Scanner keyboard = new Scanner(System.in);
            
            for (int counter = 0; counter < 3; counter++)
            {
                System.out.println();
            }

            System.out.println("****SUBTRACTION*****");
            System.out.println("********************");
            System.out.println("********************");
            System.out.println("***** "+ number1 + " - " + number2 + " =?"+" *****");
            System.out.println("********************");
            System.out.println("********************");
            String userAnswer = keyboard.nextLine();
            userAnswer = checkUserResponse(userAnswer);//Validate userAnswer (for int)
            
            for (int counter = 0; counter < 3; counter++)
            {
                System.out.println();
            }

            userInputInteger = Integer.parseInt(userAnswer);

            //check userAnswer for correctness
            if(userInputInteger == theAnswer)
            {
                System.out.println("CORRECT ");
                myuserStats.totalRight += 1;
                myuserStats.totalWages += 0.05;
            }
            else
            {
                System.out.println("INCORRECT ");
                myuserStats.totalWrong += 1;
                myuserStats.totalWages -= 0.03;
            }

    }//end generateSubtraction

public static void generateMultiplication(UpdateStats myuserStats) // to generate subtraction
    {
            Random someRandomInt = new Random();
            int number1 = (someRandomInt.nextInt(9)) + 1;
            int number2 = (someRandomInt.nextInt(9)) + 1;
            int theAnswer = number1 * number2;
            int userInputInteger;

            // Create a Scanner object to read input.
            Scanner keyboard = new Scanner(System.in);
            
            for (int counter = 0; counter < 3; counter++)
            {
                System.out.println();
            }

            System.out.println("***MULTIPLICATION***");
            System.out.println("********************");
            System.out.println("********************");
            System.out.println("***** "+ number1 + " * " + number2 + " =?"+" *****");
            System.out.println("********************");
            System.out.println("********************");
            String userAnswer = keyboard.nextLine();
            userAnswer = checkUserResponse(userAnswer);//Validate userAnswer (for int)
            
            for (int counter = 0; counter < 3; counter++)
            {
                System.out.println();
            }

            userInputInteger = Integer.parseInt(userAnswer);

            //check userAnswer for correctness
            if(userInputInteger == theAnswer)
            {
                System.out.println("CORRECT ");
                myuserStats.totalRight += 1;
                myuserStats.totalWages += 0.05;
            }
            else
            {
                System.out.println("INCORRECT ");
                myuserStats.totalWrong += 1;
                myuserStats.totalWages -= 0.03;
            }

    }//end generateMultiplication

public static void generateDivision(UpdateStats myuserStats) // to generate division
    {
    Random someRandomInt = new Random();
            int number1 = (someRandomInt.nextInt(9)) + 1;
            int number2 = (someRandomInt.nextInt(9)) + 1;
            int theAnswer = (number1 *number2) / number2;
            int userInputInteger;

            // Create a Scanner object to read input.
            Scanner keyboard = new Scanner(System.in);
            
            for (int counter = 0; counter < 3; counter++)
            {
                System.out.println();
            }

            System.out.println("******DIVISION******");
            System.out.println("********************");
            System.out.println("********************");
            System.out.println("***** "+ (number1 * number2) + " / " + number2 + " =?"+" *****");
            System.out.println("********************");
            System.out.println("********************");
            String userAnswer = keyboard.nextLine();
            userAnswer = checkUserResponse(userAnswer);//Validate userAnswer (for int)
            
            for (int counter = 0; counter < 3; counter++)
            {
                System.out.println();
            }

            userInputInteger = Integer.parseInt(userAnswer);

            //check userAnswer for correctness
            if(userInputInteger == theAnswer)
            {
                System.out.println("CORRECT ");
                myuserStats.totalRight += 1;
                myuserStats.totalWages += 0.05;
            }
            else
            {
                System.out.println("INCORRECT ");
                myuserStats.totalWrong += 1;
                myuserStats.totalWages -= 0.03;
            }

    }// end generateDivision

} //end MidTerm