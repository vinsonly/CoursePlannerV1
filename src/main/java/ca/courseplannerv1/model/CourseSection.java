package ca.courseplannerv1.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class CourseSection {

    private static AtomicLong nextCourseSectionId = new AtomicLong();

    private long courseOfferingId;                 // unique courseOfferingID, corresponding to the CourseOffering that this CourseOffering is associated with
    private long courseSectionId;                  // unique courseSectionId
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private ArrayList<String> instructors;          // instructor(s) that are teaching this section
    private String type;                            // component code (eg: LEC, SEM, TUT)

    public CourseSection(long courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
        this.courseSectionId = incrementAndGetCourseSectionId();
        this.instructors = new ArrayList<>();
        this.type = new String();
    }

    public CourseSection(long courseOfferingId, long courseSectionId, int enrolmentCapacity, int enrolmentTotal, ArrayList<String> instructors, String type) {
        this.courseOfferingId = courseOfferingId;
        this.courseSectionId = courseSectionId;
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.type = type;
    }

    public long incrementAndGetCourseSectionId() {
        this.courseSectionId = CourseSection.nextCourseSectionId.incrementAndGet();
        return courseSectionId;
    }

    public long getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(long courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public long getCourseSectionId() {
        return courseSectionId;
    }

    public void setCourseSectionId(long courseSectionId) {
        this.courseSectionId = courseSectionId;
    }

    public int getEnrolmentCapacity() {
        return enrolmentCapacity;
    }

    public void setEnrolmentCapacity(int enrolmentCapacity) {
        this.enrolmentCapacity = enrolmentCapacity;
    }

    public int getEnrolmentTotal() {
        return enrolmentTotal;
    }

    public void setEnrolmentTotal(int enrolmentTotal) {
        this.enrolmentTotal = enrolmentTotal;
    }

    public ArrayList<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<String> instructors) {
        this.instructors = instructors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

