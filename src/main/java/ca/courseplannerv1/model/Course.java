package ca.courseplannerv1.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

//each course contains an array of arraylists, which each arraylist corresponding to a course offering at a campus
//each course offering contains many classes.
public class Course {

    public static int courseCount = 0;

    private static AtomicLong nextCourseId = new AtomicLong();

    private long courseId;              // unique course id
    private String deptName;            // the subject (eg. CMPT, MACM, CMNS);
    private String catalogNumber;       // eg: 300, 250W, X99, 2XX
    private ArrayList<String> locations;// list of locations where this course is offered, sorted alphabetically

    //n CourseOfferings for n Locations
    //one courseOffering for each
    //stored in sorted order by location
    private ArrayList<CourseOffering> courseOfferings;

    //default constructor
    public Course() {
        this.courseId = incrementAndGetCourseId();
        this.deptName = new String();
        this.catalogNumber = new String();
        this.locations = new ArrayList<>();
        this.courseOfferings = new ArrayList<>();
        courseCount++;
    }

    //parametrized constructor
    public Course(String deptName, String catalogNumber, ArrayList<String> locations, ArrayList<CourseOffering> courseOfferings) {
        this.courseId = incrementAndGetCourseId();
        this.deptName = deptName;
        this.catalogNumber = catalogNumber;
        this.locations = locations;
        this.courseOfferings = courseOfferings;
        courseCount++;
    }

    //parametrized constructor
    public Course(String deptName, String catalogNumber, String location, CourseOffering courseOffering) {
        ArrayList<String> locations = new ArrayList<>();
        locations.add(location);
        ArrayList<CourseOffering> offerings = new ArrayList<>();
        offerings.add(courseOffering);
        this.courseId = incrementAndGetCourseId();
        this.deptName = deptName;
        this.catalogNumber = catalogNumber;
        this.locations = locations;
        this.courseOfferings = offerings;
        courseCount++;
    }

    //insert courseOffering into courseOfferings, in sorted order.
    //returns true if successful, false otherwise.
    public boolean insertNewCourseOffering(CourseOffering courseOffering) {
        courseOfferings.add(courseOffering);
        return true;
    }

    //getters and setters

    public long incrementAndGetCourseId() {
        this.courseId = Course.nextCourseId.incrementAndGet();
        return courseId;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public ArrayList<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }

    public void setCourseOfferings(ArrayList<CourseOffering> courseOfferings) {
        this.courseOfferings = courseOfferings;
    }
}
