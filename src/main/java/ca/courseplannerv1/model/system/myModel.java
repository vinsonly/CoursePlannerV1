package ca.courseplannerv1.model.system;

import ca.courseplannerv1.controllers.CoursePlannerController;
import ca.courseplannerv1.model.list.DepartmentList;
import ca.courseplannerv1.model.list.WatcherList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

//has many departments
public class myModel {
    public static int insertions = 0;

    //ArrayList of departments, in sorted ascending alphabetical order of deptName
//    public static ArrayList<Department> departments = new ArrayList<>();

    public static DepartmentList departmentList = new DepartmentList();

    //list of observers
    public static WatcherList watcherList = new WatcherList();

    //insert new department into departments, in sorted ascending alphabetical order of deptName
    private static void insertNewDepartment(Department department) {
        departmentList.insertSorted(department);
    }

    //precondition: line contains 8 strings
    //postcondition: saves the 8 elements of line into course object and saves the newly created course object into
    //classList
    //returns true if action successful, otherwise false
    public static boolean saveLineIntoSystem(String[] line) {

        //if the line is not complete, don't save into our system
        if(line.length != 8) {
            return false;
        }

        int semCode = Integer.valueOf(line[0]);
        String deptName = line[1].trim();
        String catalogNumber = line[2].trim();
        String location = line[3].trim();
        int enrolCap = Integer.valueOf(line[4]);
        int enrolTot = Integer.valueOf(line[5]);
        ArrayList<String> instructors = splitString(line[6]);
        String type = line[7].trim();

        Department thisDept = findDep(deptName);
        Course thisCourse;
        CourseOffering thisOffering;
        CourseSection thisSection;

        //new CourseSubSection
        CourseSubSection subSection = new CourseSubSection(enrolCap, enrolTot, instructors, type);
        //create new Semester
        Semester newSem = new Semester(semCode);

        //check if department exists
        if(thisDept != null) {
            //check if the course exists
            thisCourse = findCourse(thisDept, catalogNumber);
            if(thisCourse != null) {
                //check if the offering exists
                thisOffering = findCourseOffering(thisCourse, location, semCode);

                if(thisOffering != null) {
                    //check if the section exists
                    thisSection = findCourseSection(thisOffering, type);

                    if(thisSection != null) {
                        //insert subSection into thisSection
                        thisSection.insertNewCourseSubSection(subSection);
                    }
                    else {
                        //create a new section, insert subsection, insert section into offering
                        thisSection = new CourseSection(enrolCap, enrolTot, instructors, type);
                        thisOffering.insertCourseSection(thisSection);
                        thisSection.getSubSections().insert(subSection);
                    }
                }
                else {
                    //create a new CourseOffering, CourseSection, link all together
                    thisSection = new CourseSection(enrolCap, enrolTot, instructors, type);
                    thisOffering = new CourseOffering(newSem, location, instructors, thisSection);
                    thisCourse.insertNewCourseOffering(thisOffering);
                    thisSection.getSubSections().insert(subSection);
                }
            }
            else {
                //create a new Course, CourseOffering, Course Section, link all together
                thisSection = new CourseSection(enrolCap, enrolTot, instructors, type);
                thisOffering = new CourseOffering(newSem, location, instructors, thisSection);
                thisCourse = new Course(deptName, catalogNumber, location, thisOffering);
                thisDept.insertNewCourse(thisCourse);
                thisSection.getSubSections().insert(subSection);
            }
        }
        else {
            //create a new Department, Course, CourseOffering, Course Section, link all together
            thisSection = new CourseSection(enrolCap, enrolTot, instructors, type);
            thisOffering = new CourseOffering(newSem, location, instructors, thisSection);
            thisCourse = new Course(deptName, catalogNumber, location, thisOffering);
            thisDept = new Department(deptName, thisCourse);
            myModel.insertNewDepartment(thisDept);
            thisSection.getSubSections().insert(subSection);
        }

        myModel.insertions++;
        return true;
    }

    //return the Department if a department with the deptName already exists, else returns false.
    private static Department findDep(String deptName) {
        for(Department dept : departmentList) {
            if(dept.getDeptName().equals(deptName)) {
                return dept;
            }
        }
        return null;
    }

    //return the Course if a course with the given catalogNumber exists in the indicated department, else return false
    private static Course findCourse(Department dept, String catalogNumber) {
        for(Course course : dept.getCourses()) {
            if(course.getCatalogNumber().equals(catalogNumber)) {
                return course;
            }
        }
        return null;
    }

    //return the CourseOffering if a course offering with the given location exists in the indicated department, else return false
    private static CourseOffering findCourseOffering(Course course, String location, int semCode) {
        for(CourseOffering offering : course.getCourseOfferings()) {
            if(offering.getLocation().equals(location) && offering.getSem().getSemCode() == semCode) {
                return offering;
            }
        }
        return null;
    }

    //return the CourseSection if a course section with the given type exists in the indicated course offering, else return false
    private static CourseSection findCourseSection(CourseOffering offering, String type) {
        for(CourseSection section : offering.getCourseSections()){
            if(section.getType().equals(type)) {
                return section;
            }
        }
        return null;
    }

    //convert a single string of instructors, divided by comma into a string array
    private static ArrayList<String> splitString(String longString) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        String[] stringArray = longString.split(",");

        for(String str : stringArray) {
            str = str.trim();
            stringArrayList.add(str);
        }

        return stringArrayList;
    }

    //prints random information from the model to test the data that is being inputted
    public static void printModelInfo() {
//        System.out.println("myModel.insertions = " + myModel.insertions);
//        System.out.println("CourseSubSection.subSectionCount = " + CourseSubSection.subSectionCount);
//        System.out.println("CourseSection.sectionCount = " + CourseSection.sectionCount);
//        System.out.println("CourseOffering.courseOfferingCount = " + CourseOffering.courseOfferingCount);
//        System.out.println("Course.courseCount = " + Course.courseCount);
//        System.out.println("Department.departmentCount = " + Department.departmentCount);
//        System.out.println("myModel.departments.size() = " + myModel.departments.size());
//        System.out.println("myModel.departments.get(1).getDeptName() = " + myModel.departments.get(1).getDeptName());
//        System.out.println("myModel.departments.get(1).getCourses().size() = " + myModel.departments.get(1).getCourses().size());
//        System.out.println("myModel.departments.get(1).getCourses().get(1).getCatalogNumber() = " + myModel.departments.get(1).getCourses().get(1).getCatalogNumber());
//        System.out.println("myModel.departments.get(1).getCourses().get(1).getCourseOfferings().size() = " + myModel.departments.get(1).getCourses().get(1).getCourseOfferings().size());
//        System.out.println("myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).getLocation() = " + myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).getLocation());
//        System.out.println("myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(0).getLocation() = " + myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(0).getLocation());
//        System.out.println("myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).getCourseSections().size() = " + myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).getCourseSections().size());
    }

    //prints all the departments in the model
    public static void printDepartments() {
        for(Department department : departmentList) {
            System.out.println(department.getDeptName());
        }
    }

    //compares str1 to str2 and returns true if str 1 is less than str 2, false otherwise.
    public static boolean compareString(String str1, String str2) {
        //if a string is empty, it is lessThan the other string.
        if(str1.length() == 0 && str2.length() == 0) {
            return false;
        }
        if(str2.length() == 0) {
            return false;
        }
        if(str1.length() == 0) {
            return true;
        }

        //find which deptName is shorter.
        int shortestStrLength;

        if(str1.length() < str2.length()) {
            shortestStrLength = str1.length();
        }
        else {
            shortestStrLength = str2.length();
        }

        //loop through each of the strings and compare each of the characters
        for(int i = 0; i < shortestStrLength; i++) {

            //convert the ith character in each string to integer value and compare.
            if((int)str1.charAt(i) == (int)str2.charAt(i)) {
                continue;
            }
            else if((int)str1.charAt(i) < (int)str2.charAt(i)) {
                return true;
            }
            else {
                return false;
            }
        }

        //if we are at the last letter and so far the strings are the same,
        // then return true if str1 is shorter than str2
        // else return false
        if(str1.length() < str2.length()) {
            return true;
        }
        else {
            return false;
        }
    }

    //find department given deptID
    public static Department findDepartmentById(long deptID) {
        for(Department thisDept : departmentList) {
            if(thisDept.getDeptId() == deptID) {
                return thisDept;
            }
        }

        throw new CoursePlannerController.DepartmentNotFoundException(deptID);
    }

    //dumps the model into console
    public static void dumpModel() {

        String tab = "      ";

        //loop through each department
        for(Department department : departmentList){

            //loop through each course
            for(Course course : department.getCourses()) {

                System.out.println(department.getDeptName() + " " + course.getCatalogNumber());

                //loop through each CourseOffering
                for(CourseOffering offering : course.getCourseOfferings()) {
                    System.out.print(tab + offering.getSem().getSemCode() + " in " + offering.getLocation() + " by ");

                    //print out each instructor for offering
                    int instructorCount = 0;
                    for(String instructor : offering.getInstructors()) {
                        System.out.print(instructor);
                        instructorCount++;
                        if(instructorCount != offering.getInstructors().size()) {
                            System.out.print(", ");
                        }
                    }

                    System.out.println();

                    //loop through each CourseSection in each CourseOffering
                    for(CourseSection section : offering.getCourseSections()) {
                        //print out the info for each section
                        System.out.println(tab + tab + "Type=" + section.getType() + ", Enrollment=" + section.getEnrolmentTotal()+ "/" + section.getEnrolmentCapacity());
                    }

                }
            }
        }

    }

    //dumps the model into console
    public static void dumpModelToFile() {

        try {

            PrintStream stdout = System.out;

            System.setOut(new PrintStream(new FileOutputStream("data/output_dump.txt")));

            dumpModel();

            System.setOut(stdout);

        } catch (FileNotFoundException exception) {
            System.out.println("Error, file not found.");
            exception.printStackTrace();
        }
    }


}
