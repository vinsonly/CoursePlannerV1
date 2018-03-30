package ca.courseplannerv1.model;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class CourseOffering {

    public static int courseOfferingCount = 0;

    private static AtomicLong nextCourseOfferingId = new AtomicLong();

    private long courseOfferingId;                      // unique courseOfferingID
    private Semester sem;
    private String location;
    private ArrayList<String> instructors;
    private ArrayList<CourseSection> courseSections;    // all CourseSections for particular CourseOffering
                                                        // stored in sorted order, by type, instructors
                                                        // one unique courseSection per type
    //default constructor
    public CourseOffering() {
        this.courseOfferingId = incrementAndGetCourseOfferingId();
        this.location = new String();
        this.courseSections = new ArrayList<>(courseSections);
        this.instructors = new ArrayList<String>();
        courseOfferingCount++;
    }

    //parameterized constructor
    public CourseOffering(Semester sem, String location, ArrayList<String> instructors, ArrayList<CourseSection> courseSections) {
        this.courseOfferingId = incrementAndGetCourseOfferingId();
        this.sem = sem;
        this.location = location;
        this.courseSections = courseSections;
        this.instructors = instructors;
        courseOfferingCount++;
    }

    //parametrized contrusctor
    public CourseOffering(Semester sem, String location, ArrayList<String> instructors, CourseSection section) {
        ArrayList<CourseSection> sections = new ArrayList<>();
        sections.add(section);
        this.courseOfferingId = incrementAndGetCourseOfferingId();
        this.sem = sem;
        this.location = location;
        this.courseSections = sections;
        this.instructors = instructors;
        courseOfferingCount++;
    }


    public long incrementAndGetCourseOfferingId() {
        this.courseOfferingId = CourseOffering.nextCourseOfferingId.incrementAndGet();
        return courseOfferingId;
    }

    //insert section into courseSections, in sorted order.
    //returns true if successful, false otherwise.
    public boolean insertCourseSection(CourseSection section) {
        //find if this section already exists.
        //if the section already exists, return false;

        int currentIndex = 0;
        for(CourseSection savedSection : courseSections) {
            if(savedSection.getType().equals(section.getType())) {
                return false;
            }
            if(section.lessThan(savedSection)) {
                courseSections.add(currentIndex, section);
                return true;
            }
        }
        courseSections.add(section);
        return true;
    }

    //compare semesterCode and location
    //return true if this semCode is less than other semCode
    //if both semCodes are equal
    //return true if this location is less than other location
    //return false otherwise.
    public boolean lessThan(CourseOffering otherOffering) {
        String otherLocation = otherOffering.getLocation();
        String thisLocation = this.getLocation();
        int otherSem = otherOffering.getSem().getSemCode();
        int thisSem = this.getSem().getSemCode();

        //if semCodes are the same, compare location
        if(otherSem == thisSem) {
            return myModel.compareString(thisLocation, otherLocation);
        }
        else if(thisSem < otherSem) {
            return true;
        }
        else {
            return false;
        }
    }

    public void printSections() {
        for(CourseSection savedSection : courseSections) {
            System.out.println(savedSection.getType());
        }
    }

    public ArrayList<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<String> instructors) {
        this.instructors = instructors;
    }

    public long getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(long courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public Semester getSem() {
        return sem;
    }

    public void setSem(Semester sem) {
        this.sem = sem;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<CourseSection> getCourseSections() {
        return courseSections;
    }

    public void setCourseSections(ArrayList<CourseSection> courseSections) {
        this.courseSections = courseSections;
    }
}
