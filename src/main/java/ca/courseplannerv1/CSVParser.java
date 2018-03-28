package ca.courseplannerv1;

//responsible for parsing a CSV file into our system

import ca.courseplannerv1.model.CourseSection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
    //reads \data\course_data_2018.csv and saves its data into the system.
    //returns an arraylist of courses if successful, null otherwise and throws an exception
    public static ArrayList<CourseSection> readCSV() {

        String csv = System.getProperty("user.dir");
        csv = csv + "\\data\\course_data_2018.csv";
        System.out.println("csv = " + csv);

        File csvFile = new File(csv);

        ArrayList<CourseSection> classList = new ArrayList<>();


        String currentLine;
        int wordCount = 0;
        int lineCounter = 0;

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
                System.out.println("Read: " + currentLine);
                System.out.println("currentLine.length() = " + currentLine.length());

                String text = new String();
                String[] line = new String[8];

//                split the string up divided by ","
                for(i = 0; i < currentLine.length(); i++) {

                    if(currentLine.charAt(i) == ','&& (isMultiString == false)) {

                        text = text.trim();

                        //save the text into line
                        line[wordCount] = text;

                        //increment wordCount
                        wordCount++;

                        //reset text
                        text = new String();
                    } else if(currentLine.charAt(i) == '"') {

                        isMultiString = !isMultiString;

                    } else if(i == (currentLine.length() - 1)) {
                        //save the character into text
                        text = text + currentLine.charAt(i);

                        text = text.trim();

                        //save the text into line
                        line[wordCount] = text;

                        for(int j = 0; j < line.length; j++) {
                            System.out.print("|" + line[j] + "|");
                        }

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
                System.out.println("i = " + i);

                //print out the current line
                System.out.print("line: ");
                for(int j = 0; j < line.length; j++) {
                    System.out.print("|" + line[j] + "|");
                }
                System.out.println("");
                System.out.println("");

                //instantiate a new course object
                //add the newly created course object into classList
                saveCourse(line, classList);
                classList.get(classList.size() - 1).printCourseOffering();



            }
            System.out.println("lineCounter = " + lineCounter);
            System.out.println("classList.size()" + classList.size());
            System.out.println(scanner.hasNextLine());
            System.out.println(scanner.hasNext());


            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println();
            e.printStackTrace();
        }


        return classList;
    }

    //precondition: line contains 8 strings
    //postcondition: saves the 8 elements of line into course object and saves the newly created course object into
    //classList
    //returns true if action successful, otherwise false
    public static boolean saveCourse(String[] line, ArrayList<CourseSection> classList) {
        int sizeBefore = classList.size();

        //instantiate a new course object
        CourseSection newClass = new CourseSection(
                Integer.valueOf(line[0]),   //semester code
                line[1],                    //subject
                line[2],                    //catalog number
                line[3],                    //location
                Integer.valueOf(line[4]),   //enrolment capacity
                Integer.valueOf(line[5]),   //enrolment total
                splitString(line[6]),//instructors
                line[7]                     //component code
        );

        //add the newly created course object into fileList
        classList.add(newClass);

        int sizeAfter = classList.size();

        if(sizeAfter > sizeBefore) {
            return true;
        }
        else {
            return false;
        }

    }

    //convert a single string of instructors, divided by comma into a string array
    public static ArrayList<String> splitString(String longString) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        String[] stringArray = longString.split(",");

        for(String str : stringArray) {
            stringArrayList.add(str);
        }

        return stringArrayList;
    }



}
