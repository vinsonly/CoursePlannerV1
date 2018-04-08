package ca.courseplannerv1.model.system;

import ca.courseplannerv1.controllers.CoursePlannerController;
import ca.courseplannerv1.model.list.CourseList;

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
    private CourseList courses;

    //class functions
//  --------------------------------------------------------

    //default constructor
    public Department() {
        this.deptId = getAndIncrementDeptId();
        this.deptName = new String();
        this.courses = new CourseList();
        departmentCount++;
    }

    //parametrized constructor
    public Department(String deptName, CourseList courses) {
        this.deptId = getAndIncrementDeptId();
        this.deptName = deptName;
        this.courses = courses;
        departmentCount++;
    }

    //parametrized constructor
    public Department(String deptName, Course course) {
//        System.out.println("Creating new Department");
        this.courses = new CourseList();
        courses.insert(course);
        this.deptId = getAndIncrementDeptId();
        this.deptName = deptName;
        departmentCount++;
    }


    //insert course into courses, in sorted order
    public void insertNewCourse(Course course) {
        courses.insertSorted(course);
    }

    public Course findCourseByCourseId(long courseId) {
        for(Course course : courses) {
            if(course.getCourseId() == courseId) {
                return course;
            }
        }

        throw new CoursePlannerController.CourseNotFoundException(courseId);
    }

    //returns true if both depts have the same departmentName
    public boolean isEqual(Department otherDept) {
        return this.deptName.equals(otherDept);
    }

    //compares the deptNames
    //returns true if thisDept is less than otherDept, false otherwise
    public boolean lessThan(Department otherDept) {
        String otherName = otherDept.getDeptName();
        String thisName = this.deptName;

        return myModel.compareString(thisName, otherName);
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

    public CourseList getCourses() {
        return courses;
    }

    public void setCourses(CourseList courses) {
        this.courses = courses;
    }

    public long getAndIncrementDeptId() {
        this.deptId = Department.nextDeptId.getAndIncrement();
        return deptId;
    }
}
