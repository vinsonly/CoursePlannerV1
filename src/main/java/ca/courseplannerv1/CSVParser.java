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
        csv = csv + "\\data\\course_data_2018.csv";
//        csv = csv + "\\data\\test.csv";
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
                System.out.print("current line: ");
                for(int j = 0; j < line.length; j++) {
                    System.out.print("|" + line[j] + "|");
                }
                System.out.println("");
                System.out.println("");

                //instantiate a new course object
                //add the newly created course object into classList
                myModel.saveLineIntoSystem(line);

            }
            System.out.println("myModel.insertions = " + myModel.insertions);
            System.out.println("CourseSection.sectionCount = " + CourseSection.sectionCount);
            System.out.println("CourseOffering.courseOfferingCount = " + CourseOffering.courseOfferingCount);
            System.out.println("Course.courseCount = " + Course.courseCount);
            System.out.println("Department.departmentCount = " + Department.departmentCount);
            System.out.println("myModel.departments.size() = " + myModel.departments.size());
            System.out.println("myModel.departments.get(1).getDeptName() = " + myModel.departments.get(1).getDeptName());
            System.out.println("myModel.departments.get(1).getCourses().size() = " + myModel.departments.get(1).getCourses().size());
            System.out.println("myModel.departments.get(1).getCourses().get(1).getCatalogNumber() = " + myModel.departments.get(1).getCourses().get(1).getCatalogNumber());
            System.out.println("myModel.departments.get(1).getCourses().get(1).getCourseOfferings().size() = " + myModel.departments.get(1).getCourses().get(1).getCourseOfferings().size());
            System.out.println("myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).getLocation() = " + myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).getLocation());
            System.out.println("myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(0).getLocation() = " + myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(0).getLocation());
            System.out.println("myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).getCourseSections().size() = " + myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).getCourseSections().size());

            System.out.println(scanner.hasNextLine());
            System.out.println(scanner.hasNext());


            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println();
            e.printStackTrace();
        }

    }




}
