package ca.courseplannerv1.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

//has many courses
public class Department {

    private static AtomicLong nextDeptId = new AtomicLong();

    //data members
//  --------------------------------------------------------

    //unique deptId
    private long deptId;

    //the department name
    private String deptName;

    //the courses in this department
    //stored in sorted order, by catalogNumber
    private ArrayList<Course> courses;

    //class functions
//  --------------------------------------------------------

    //default constructor
    public Department() {
        this.deptId = incrementAndGetDeptId();
        this.deptName = new String();
        this.courses = new ArrayList<>();
    }

    //parametrized constructor
    public Department(String deptName, ArrayList<Course> courses) {
        this.deptId = incrementAndGetDeptId();
        this.deptName = deptName;
        this.courses = courses;
    }

    //insert course into courses, in sorted order
    //returns true if successful, false otherwise.
    private boolean insertNewCourse(Course course) {
        courses.add(course);
        return true;
    }

    //search for a course
    private Course findCourse(long courseId) {
        for(Course course : courses) {
            if(course.getCourseId() == courseId) {
                return course;
            }
        }

        throw new RuntimeException("Course with courseID: " + courseId + " not found.");
    }

    //getters and setters
//  --------------------------------------------------------

    public long getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public long incrementAndGetDeptId() {
        this.deptId = Department.nextDeptId.incrementAndGet();
        return deptId;
    }
}
