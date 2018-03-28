package ca.courseplannerv1.model;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class CourseOffering {

    private static AtomicLong nextCourseOfferingId = new AtomicLong();

    private long courseOfferingId;                      // unique courseOfferingID
    private Semester sem;
    private String location;
    private ArrayList<String> aggregatedInstructors;    // all the instructors that teach a section, sorted alphabetically
    private int aggregatedEnrolmentCap;                 // the sum of all enrolment capacity of all sections at this location
    private int aggregatedEnrolmentTotal;               // the sum of all enrolment total of all sections at this location
    private ArrayList<CourseSection> courseSections;    // all CourseSections for particular CourseOffering
                                                        // stored in sorted order, by instructor, type
    //default constructor
    public CourseOffering() {
        this.courseOfferingId = incrementAndGetCourseOfferingId();
        this.location = new String();
        this.aggregatedInstructors = new ArrayList<>();
        this.courseSections = new ArrayList<>(courseSections);
    }

    //parameterized constructor
    public CourseOffering(long courseOfferingId, Semester sem, String location, ArrayList<String> aggregatedInstructors, int aggregatedEnrolmentCap, int aggregatedEnrolmentTotal, ArrayList<CourseSection> courseSections) {
        this.courseOfferingId = courseOfferingId;
        this.sem = sem;
        this.location = location;
        this.aggregatedInstructors = aggregatedInstructors;
        this.aggregatedEnrolmentCap = aggregatedEnrolmentCap;
        this.aggregatedEnrolmentTotal = aggregatedEnrolmentTotal;
        this.courseSections = courseSections;
    }


    public long incrementAndGetCourseOfferingId() {
        this.courseOfferingId = CourseOffering.nextCourseOfferingId.incrementAndGet();
        return courseOfferingId;
    }

    //insert section into courseSections, in sorted order.
    //returns true if successful, false otherwise.
    public boolean insertCourseSection(CourseSection section) {
        this.courseSections.add(section);
        return true;
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

    public ArrayList<String> getAggregatedInstructors() {
        return aggregatedInstructors;
    }

    public void setAggregatedInstructors(ArrayList<String> aggregatedInstructors) {
        this.aggregatedInstructors = aggregatedInstructors;
    }

    public int getAggregatedEnrolmentCap() {
        return aggregatedEnrolmentCap;
    }

    public void setAggregatedEnrolmentCap(int aggregatedEnrolmentCap) {
        this.aggregatedEnrolmentCap = aggregatedEnrolmentCap;
    }

    public int getAggregatedEnrolmentTotal() {
        return aggregatedEnrolmentTotal;
    }

    public void setAggregatedEnrolmentTotal(int aggregatedEnrolmentTotal) {
        this.aggregatedEnrolmentTotal = aggregatedEnrolmentTotal;
    }

    public ArrayList<CourseSection> getCourseSections() {
        return courseSections;
    }

    public void setCourseSections(ArrayList<CourseSection> courseSections) {
        this.courseSections = courseSections;
    }
}
