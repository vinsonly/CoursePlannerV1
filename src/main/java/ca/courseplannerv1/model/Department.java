package ca.courseplannerv1.model;

import ca.courseplannerv1.controllers.CoursePlannerController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

//has many courses
public class Department {
    public static int departmentCount = 0;


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
        this.deptId = getAndIncrementDeptId();
        this.deptName = new String();
        this.courses = new ArrayList<>();
        departmentCount++;
    }

    //parametrized constructor
    public Department(String deptName, ArrayList<Course> courses) {
        this.deptId = getAndIncrementDeptId();
        this.deptName = deptName;
        this.courses = courses;
        departmentCount++;
    }

    //parametrized constructor
    public Department(String deptName, Course course) {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course);
        this.deptId = getAndIncrementDeptId();
        this.deptName = deptName;
        this.courses = courses;
        departmentCount++;
    }


    //insert course into courses, in sorted order
    //returns true if successful, false otherwise.
    public boolean insertNewCourse(Course course) {
        boolean added = false;
        //if this.departments is empty, add
        if(courses.size() == 0) {
            courses.add(course);
            return added = true;
        }

        int currentIndex = 0;
        for(Course savedCourse : courses) {
            if(course.lessThan(savedCourse)) {
                courses.add(currentIndex, course);
                return added = true;
            }
            else {
                currentIndex++;
            }
        }

        courses.add(course);
        return added = true;
    }

    //search for a course
    public Course findCourse(long courseId) {
        for(Course course : courses) {
            if(course.getCourseId() == courseId) {
                return course;
            }
        }

        throw new CoursePlannerController.CourseNotFoundException(courseId);
    }

    //compares the deptNames
    //returns true if thisDept is less than otherDept, false otherwise
    public boolean lessThan(Department otherDept) {
        String otherName = otherDept.getDeptName();
        String thisName = this.deptName;

        return myModel.compareString(thisName, otherName);
    }

    public void printCourses() {
        for(Course course : courses) {
            System.out.println(course.getCatalogNumber());
        }
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

    public long getAndIncrementDeptId() {
        this.deptId = Department.nextDeptId.getAndIncrement();
        return deptId;
    }
}
