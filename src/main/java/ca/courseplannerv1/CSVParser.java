package ca.courseplannerv1;

//responsible for parsing a CSV file into our system

import ca.courseplannerv1.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
    //reads \data\course_data_2018.csv and saves its data into the system.
    //returns an arraylist of courses if successful, null otherwise and throws an exception
    public static void readCSV() {

        String csv = System.getProperty("user.dir");
//        csv = csv + "\\data\\course_data_2018.csv";
//        csv = csv + "\\data\\test.csv";
        csv = csv + "\\data\\course_data_2016.csv";
//        System.out.println("csv = " + csv);

        File csvFile = new File(csv);

        ArrayList<CourseSection> classList = new ArrayList<>();


        String currentLine;
        int wordCount = 0;
        int lineCounter = 0;

        //boolean that determines whether there are multiple strings that need to be divided in this field
        //multistrings are indicated by "
        boolean isMultiString = false;

        try {

            Scanner scanner = new Scanner(csvFile);
            int i;

            while(scanner.hasNextLine()){

                //skip the first line
                if(lineCounter == 0) {
                    lineCounter++;
                    currentLine = scanner.nextLine();
                    continue;
                }

                lineCounter++;
                currentLine = scanner.nextLine();

                String text = new String();
                String[] line = new String[8];

//                split the string up divided by ","
                for(i = 0; i < currentLine.length(); i++) {

                    if(currentLine.charAt(i) == ',' && (isMultiString == false)) {

                        text = text.trim();

                        //save the text into line
                        line[wordCount] = text;

                        //increment wordCount
                        wordCount++;

                        //reset text
                        text = new String();

                    //check if this is a start or end of a multi string
                    } else if(currentLine.charAt(i) == '"') {

                        isMultiString = !isMultiString;

                    //check if this character is the last character in the line
                    } else if(i == (currentLine.length() - 1)) {
                        //save the character into text
                        text = text + currentLine.charAt(i);

                        text = text.trim();

                        //save the text into line
                        line[wordCount] = text;

                        //reset wordCount
                        wordCount = 0;

                        //reset text
                        text = new String();
                    }
                    else {
                        //save the character into text
                        text = text + currentLine.charAt(i);
                    }

                }


                //save the current line into myModel
                myModel.saveLineIntoSystem(line);

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println();
            e.printStackTrace();
        }

    }




}
