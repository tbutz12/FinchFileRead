
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import static java.awt.Color.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author tristin butz
 * Lab Assignment 6
 * This program reads in a text field and finds whether it is a number or a
 * string, and then classifies the numbers into smallest and largest
 */
public class LabAssign6 {

    public static void main(String[] args) {
        //initiate variables
        int text, largest = 0, smallest = 0;
        boolean firstInt = true;
        String line;
        //initiate Finch Robot
        Finch martyMcFinch = new Finch();
        //initiate read in text
        String fileName = "Lab6Data.txt";
        try {
            //read in text
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            //find out in text is an integer
            while (scan.hasNext()) {
                if (scan.hasNextInt()) {
                    text = scan.nextInt();
                    System.out.println(text);
                    //find if integer is first integer
                    if (firstInt == true) {
                        martyMcFinch.saySomething("The first integer is"
                                + text, 2000);
                    }
                    //find if integer is the largest integer
                    if (firstInt || text > largest) {
                        largest = text;
                        //see if number is negative or postitive
                        if (largest > 0) {
                            martyMcFinch.setLED(yellow);
                            martyMcFinch.saySomething("The largest integer is"
                                    + largest, 2600);
                            martyMcFinch.setLED(black);
                        } else {
                            largest = text;
                            martyMcFinch.setLED(yellow);
                            martyMcFinch.saySomething("The largest integer is negative"
                                    + largest, 2600);
                            martyMcFinch.setLED(black);
                        }
                    }
                    //see if integer is the smallest integer
                    if (firstInt || text < smallest) {
                        smallest = text;
                        //see if integer is negative or positive
                        if (smallest > 0) {
                            martyMcFinch.setLED(blue);
                            martyMcFinch.saySomething("The smallest integer is"
                                    + smallest, 2600);
                            martyMcFinch.setLED(black);
                        } else {
                            smallest = text;
                            martyMcFinch.setLED(blue);
                            martyMcFinch.saySomething("The smallest integer is negative"
                                    + smallest, 2600);
                            martyMcFinch.setLED(black);
                        }
                    }
                    //reclassify the boolean to false after loop
                    firstInt = false;
                    //see if integer is a string
                } else {
                    line = scan.nextLine();
                    System.out.println(line);
                    martyMcFinch.setLED(red);
                    martyMcFinch.saySomething("Non integer value found!", 2600);
                    martyMcFinch.setLED(black);
                }
            }
            //catch any possible thrown exceptions
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
            //declare final results
        } finally {
            if (largest > 0) {
                martyMcFinch.saySomething("The largest value is" + largest, 4000);
            } else {
                martyMcFinch.saySomething("The largest value is negative" + largest, 4000);
            }
            if (smallest > 0) {
                martyMcFinch.saySomething("The smallest value is" + smallest, 4000);
            } else {
                martyMcFinch.saySomething("The smallest value is negative" + smallest, 4000);

            }
            
        }
        //turn robot off
          martyMcFinch.quit();
    }
}
