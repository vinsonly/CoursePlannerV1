package ca.courseplannerv1.controllers;

import ca.courseplannerv1.CSVParser;
import ca.courseplannerv1.model.*;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CoursePlannerController {

    //General


    @GetMapping("/api/about")
    public String getInfo() {
        return "This is an awesome Course Planner written by Vinson Ly.";
    }

    @GetMapping("/api/dump-model")
    public void dumpModel() {
        myModel.dumpModel();
        myModel.dumpModelToFile();
    }

    //Access Departments, Courses, Offerings, and Sections
    @GetMapping("/api/departments")
    //list of all departments
    public List<DepartmentLite> getDepartments() {

        ArrayList<DepartmentLite> deptLites = new ArrayList<>();

        for(Department savedDepartment : myModel.departments) {
            DepartmentLite newDeptLite = new DepartmentLite(savedDepartment.getDeptId(), savedDepartment.getDeptName());
            deptLites.add(newDeptLite);
        }
        return deptLites;
    }

    //List all courses with the given deptId
    @GetMapping("/api/departments/{deptId}/courses")
    public List<CourseLite> getCourses(@PathVariable("deptId") long deptId) {

        Department foundDepartment = myModel.findDepartment(deptId);

        ArrayList<CourseLite> courseLites = new ArrayList<>();

        for(Course savedCourse : foundDepartment.getCourses()) {
            CourseLite newCourseLite = new CourseLite(savedCourse.getCourseId(), savedCourse.getCatalogNumber());
            courseLites.add(newCourseLite);
        }

        return courseLites;

    }

    //List the offerings of the course with courseID inside department with deptId
    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings")
    public List<CourseOfferingLite> getOfferings(@PathVariable("deptId") long deptId, @PathVariable("courseId") long courseId) {

        Department foundDepartment = myModel.findDepartment(deptId);

        Course foundCourse = foundDepartment.findCourse(courseId);

        CourseLite thisCourseLite = new CourseLite(foundCourse.getCourseId(), foundCourse.getCatalogNumber());

        ArrayList<CourseOfferingLite> courseOfferingLites = new ArrayList<>();

        for(CourseOffering courseOffering : foundCourse.getCourseOfferings()) {
            CourseOfferingLite thisCourseOfferingLite = new CourseOfferingLite(courseOffering, thisCourseLite);
            courseOfferingLites.add(thisCourseOfferingLite);
        }

        return courseOfferingLites;
    }

    //Return the list of sections for offering with courseOfferingID, in course with courseId, in department with deptId
    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings/{courseOfferingId}")
    public List<CourseSectionLite> getSections(@PathVariable("deptId") long deptId, @PathVariable("courseId") long courseId, @PathVariable("courseOfferingId") long courseOfferingId) {
        Department foundDepartment = myModel.findDepartment(deptId);

        Course foundCourse = foundDepartment.findCourse(courseId);

        CourseOffering foundCourseOffering = foundCourse.findCourseOffering(courseOfferingId);

        ArrayList<CourseSectionLite> sectionLites = new ArrayList<>();

        for(CourseSection savedSection : foundCourseOffering.getCourseSections()) {
            CourseSectionLite newCourseSectionLite = new CourseSectionLite(savedSection);
            sectionLites.add(newCourseSectionLite);
        }

        return sectionLites;

    }

    //Add a new section to the data stored by the system.
    @PostMapping("/api/addoffering")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseSectionLite addOffering(@RequestBody String lineJson){

        Gson gson = new Gson();

        Line newLine = gson.fromJson(lineJson, Line.class);

        String[] lineArray = newLine.toStringArray();

        myModel.saveLineIntoSystem(lineArray);

        CourseSectionLite newCourseSectionLite = new CourseSectionLite(newLine.getComponent(), Integer.valueOf(newLine.getEnrollmentTotal()), Integer.valueOf(newLine.getEnrollmentCap()));

        return newCourseSectionLite;
    }


    //Exception Handlers
    public static class DepartmentNotFoundException extends RuntimeException {
        private long deptId;
        public DepartmentNotFoundException(long deptId) {
            super();
            this.deptId = deptId;
        }

        public long getDeptId() {
            return this.deptId;
        }
    }

    public static class CourseNotFoundException extends RuntimeException {
        private long courseId;
        public CourseNotFoundException(long courseId) {
            super();
            this.courseId = courseId;
        }

        public long getCourseId() {
            return this.courseId;
        }
    }

    public static class CourseOfferingNotFoundException extends RuntimeException {
        private long courseOfferingId;
        public CourseOfferingNotFoundException(long courseOfferingId) {
            super();
            this.courseOfferingId = courseOfferingId;
        }

        public long getCourseOfferingId() {
            return this.courseOfferingId;
        }
    }


    //If deptID is invalid
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(DepartmentNotFoundException.class)
    public void badIdExceptionHandler(DepartmentNotFoundException e, HttpServletResponse response) throws IOException {
        String message = "Department of ID " + e.getDeptId() + " not found.";
        response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseNotFoundException.class)
    public void badIdExceptionHandler(CourseNotFoundException e, HttpServletResponse response) throws IOException {
        String message = "Course of ID " + e.getCourseId() + " not found.";
        response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseOfferingNotFoundException.class)
    public void badIdExceptionHandler(CourseOfferingNotFoundException e, HttpServletResponse response) throws IOException {
        String message = "Course offering of ID " + e.getCourseOfferingId() + " not found.";
        response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    }




}
