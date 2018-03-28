package ca.courseplannerv1.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class CourseSection {

    public static int sectionCount = 0;

    private static AtomicLong nextCourseSectionId = new AtomicLong();

    private long courseSectionId;                  // unique courseSectionId
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private ArrayList<String> instructors;          // instructor(s) that are teaching this section
    private String type;                            // component code (eg: LEC, SEM, TUT)

    public CourseSection() {
        this.courseSectionId = incrementAndGetCourseSectionId();
        this.instructors = new ArrayList<>();
        sectionCount++;
    }

    public CourseSection(int enrolmentCapacity, int enrolmentTotal, ArrayList<String> instructors, String type) {
        this.courseSectionId = incrementAndGetCourseSectionId();
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.type = type;
        sectionCount++;

    }

    public long incrementAndGetCourseSectionId() {
        this.courseSectionId = CourseSection.nextCourseSectionId.incrementAndGet();
        return courseSectionId;
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

