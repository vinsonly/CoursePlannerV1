package ca.courseplannerv1.model;

import ca.courseplannerv1.controllers.CoursePlannerController;

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

    //Each courseOffering has a unique location and semester pairing
    //one courseOffering for each
    //stored in sorted order by semesterCode and location
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
        boolean added = false;
        boolean found = false;

        if(courseOfferings.size() == 0) {
            courseOfferings.add(courseOffering);
            return added = true;
        }

        for(CourseOffering offering : this.courseOfferings) {
            //check if a courseOffering at the same location, during the same semester is present, if false, insert, else don't
            if (courseOffering.getLocation().equals(offering.getLocation()) && courseOffering.getSem().getSemCode() == offering.getSem().getSemCode()) {
                found = true;
                break;
            }
        }

        if(found == false) {
            int currentIndex = 0;
            for(CourseOffering savedOffering : courseOfferings) {
                if(courseOffering.lessThan(savedOffering)) {
                    courseOfferings.add(currentIndex, courseOffering);
                    locations.add(currentIndex, courseOffering.getLocation());
                    return added = true;
                }
                else {
                    currentIndex++;
                }
            }

            courseOfferings.add(courseOffering);
            locations.add(courseOffering.getLocation());
            return added = true;
        }
        return added;
    }

    //compares the catalogNumbers
    //returns true if thisCourse is less than otherCourse, false otherwise
    public boolean lessThan(Course otherCourse) {
        String otherNum = otherCourse.getCatalogNumber();
        String thisNum = this.catalogNumber;

        return myModel.compareString(thisNum, otherNum);
    }

    //find CourseOffering by courseOfferingId
    public CourseOffering findCourseOffering(long courseOfferingid) {
        for(CourseOffering courseOffering : courseOfferings) {
            if(courseOffering.getCourseOfferingId() == courseOfferingid) {
                return courseOffering;
            }
        }

        throw new CoursePlannerController.CourseOfferingNotFoundException(courseOfferingid);
    }

    public void printOfferings() {
        for(CourseOffering offering : courseOfferings) {
            System.out.println(offering.getSem().getSemCode() + ", " + offering.getLocation());
        }
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
