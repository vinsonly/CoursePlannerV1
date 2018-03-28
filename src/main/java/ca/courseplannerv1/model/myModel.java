package ca.courseplannerv1.model;

import ca.courseplannerv1.CSVParser;

import javax.validation.constraints.Null;
import java.util.ArrayList;

//has many departments
public class myModel {
    public static int insertions = 0;

    //ArrayList of departments, in sorted order, by alphabetical order of deptName
    public static ArrayList<Department> departments = new ArrayList<>();

    //insert new department into departments, in sorted order.
    //returns true if successful, false otherwise.
    private static boolean insertNewDepartment(Department department) {
        myModel.departments.add(department);
        return true;
    }

    //precondition: line contains 8 strings
    //postcondition: saves the 8 elements of line into course object and saves the newly created course object into
    //classList
    //returns true if action successful, otherwise false
    public static boolean saveLineIntoSystem(String[] line) {

        if(line.length != 8) {
            return false;
        }

        boolean isInserted = false;

        int semCode = Integer.valueOf(line[0]);
        String deptName = line[1];
        String catalogNumber = line[2];
        String location = line[3];
        int enrolCap = Integer.valueOf(line[4]);
        int enrolTot = Integer.valueOf(line[5]);
        ArrayList<String> instructors = splitString(line[6]);
        String type = line[7];

        boolean deptExists = true;
        boolean courseExists = true;
        boolean offeringExists = true;

        Department thisDept = findDep(deptName);
        Course thisCourse;
        CourseOffering thisOffering;

        //new CourseSection
        CourseSection section = new CourseSection(enrolCap, enrolTot, instructors, type);

        //create new Semester
        Semester newSem = new Semester(semCode);

        //check if a department with the deptName already exists, create a new department with deptName if false
        if(thisDept == null) {
            deptExists = false;
            courseExists = false;
            offeringExists = false;

            //create new CourseOffering
            thisOffering = new CourseOffering(newSem, location, instructors, enrolCap, enrolTot, section);

            //create new Course
            thisCourse = new Course(deptName, catalogNumber, location, thisOffering);

            //create new Department
            thisDept = new Department(deptName, thisCourse);

            myModel.insertNewDepartment(thisDept);
            isInserted = true;
        }
        //if department exists, check if course exists
        //if course doesnt not exist, create a new course
        else{
            thisCourse = findCourse(thisDept, catalogNumber);

            if(thisCourse == null) {
                courseExists = false;
                offeringExists = false;

                //create new CourseOffering
                thisOffering = new CourseOffering(newSem, location, instructors, enrolCap, enrolTot, section);

                //create new Course
                thisCourse = new Course(deptName, catalogNumber, location, thisOffering);

                thisDept.insertNewCourse(thisCourse);

                isInserted = true;
            }
        }

        //if department and course exists, check if offering exists
        //if thisOffering is null, create a new offering
        if(deptExists && courseExists) {
            thisOffering = findCourseOffering(thisCourse, location);

            if(thisOffering == null) {
                offeringExists = false;

                //create new CourseOffering
                thisOffering = new CourseOffering(newSem, location, instructors, enrolCap, enrolTot, section);

                thisCourse.insertNewCourseOffering(thisOffering);

                isInserted = true;
            }
            //if department, course and offering exists, insert section into offering
            else {
                thisOffering.insertCourseSection(section);
                isInserted = true;
            }


        }

        if(isInserted) {
            insertions++;
        }
        return isInserted;

    }

    //return true if a department with the deptName already exists, else returns false.
    private static Department findDep(String deptName) {
        for(Department dep : departments) {
            if(dep.getDeptName().equals(deptName)) {
                return dep;
            }
        }
        return null;
    }

    //return true if a course with the given catalogNumber exists in the indicated department, else return false
    private static Course findCourse(Department dept, String catalogNumber) {
        for(Course course : dept.getCourses()) {
            if(course.getCatalogNumber().equals(catalogNumber)) {
                return course;
            }
        }
        return null;
    }

    //return true if a course offering with the given  exists in the indicated department, else return false
    private static CourseOffering findCourseOffering(Course course, String location) {
        for(CourseOffering offering : course.getCourseOfferings()) {
            if(offering.getLocation().equals(location)) {
                return offering;
            }
        }
        return null;
    }



    //convert a single string of instructors, divided by comma into a string array
    private static ArrayList<String> splitString(String longString) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        String[] stringArray = longString.split(",");

        for(String str : stringArray) {
            stringArrayList.add(str);
        }

        return stringArrayList;
    }

    //dumps the model into console
    public static void dumpModel() {

        String tab = "      ";

        //loop through each department
        for(Department department : myModel.departments){

            //loop through each course
            for(Course course : department.getCourses()) {

                System.out.println(department.getDeptName() + course.getCatalogNumber());

                //loop through each CourseOffering
                for(CourseOffering offering : course.getCourseOfferings()) {
                    System.out.println(tab + offering.getSem().getSemCode() + " in " + offering.getLocation() + " by ");
                    //print out all instructors for given course offering
                    int instructorCount = 0;
                    for(String instructor : offering.getAggregatedInstructors()) {
                        System.out.print(instructor);
                        instructorCount++;
                        if(instructorCount != offering.getAggregatedInstructors().size()) {
                            System.out.print(", "); //CONTINUE HERE
                        }
                    }



                }


            }



        }


    }


}
