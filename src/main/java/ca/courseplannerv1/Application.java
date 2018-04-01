package ca.courseplannerv1;

import ca.courseplannerv1.model.system.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

//tells spring that this is where it all starts and adds in a bunch of magic for us behind our backs
@SpringBootApplication

public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//
//        //parse \data\course_data_2018.csv into our system.
        CSVParser.readCSV();
        myModel.dumpModel();
        myModel.dumpModelToFile();
        myModel.departmentList.printItems();
        myModel.departmentList.get(1).getCourses().printItems();
        myModel.departmentList.get(1).getCourses().get(1).getCourseOfferings().printItems();


//            int enrolmentCapacity = 5;
//            int enrolmentTotal = 2;
//            ArrayList<String> instructors = new ArrayList<>();
//            instructors.add("Bob");
//            String type = "LEC";
//            Semester newSem = new Semester(1187);
//            String location = "SURREY";
//            String deptName = "CMPT";
//            String catalogNumber = "300";
//
//            CourseSubSection newSS = new CourseSubSection(enrolmentCapacity, enrolmentTotal, instructors, type);
//            CourseSection newSection = new CourseSection(newSS);
//            CourseOffering newOffering = new CourseOffering(newSem, location, instructors, newSection);
//            Course newCourse =  new Course(deptName, catalogNumber, location, newOffering);
//            Department newDept = new Department(deptName, newCourse);
//            Department newDept2 = new Department("CMNS", newCourse);
//
//            DepartmentList newDepartmentList = new DepartmentList();
//
//            newDepartmentList.insert(newDept);
//            newDepartmentList.insert(newDept2);
//
//            for(Department dept : newDepartmentList) {
//                System.out.println(dept.getDeptName());
//            }
//
//            System.out.println("newDepartmentList.size() = " + newDepartmentList.size());


    }
}
