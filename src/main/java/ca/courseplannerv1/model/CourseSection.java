package ca.courseplannerv1.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

//A course section that aggregates the instructors, enrolment capacities, and enrolment totals
//for a unique type
//belongs to a course offering of a unique
public class CourseSection {

    public static int sectionCount = 0;

    private static AtomicLong nextCourseSectionId = new AtomicLong();

    private long courseSectionId;                  // unique courseSectionId
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private ArrayList<String> instructors;          // instructor(s) that are teaching this section
    private String type;                            // component code (eg: LEC, SEM, TUT)
    private ArrayList<CourseSubSection> subSections;// the individual classes that are in this section

    //default constructor
    public CourseSection() {
        this.courseSectionId = incrementAndGetCourseSectionId();
        this.instructors = new ArrayList<>();
        sectionCount++;
        this.enrolmentCapacity = 0;
        this.enrolmentTotal = 0;
        this.type = new String();
        this.subSections = new ArrayList<>();
    }

    //parameterized constructor
    public CourseSection(int enrolmentCapacity, int enrolmentTotal, ArrayList<String> instructors, String type) {
        this.courseSectionId = incrementAndGetCourseSectionId();
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.type = type;
        sectionCount++;
    }

    //parameterized constructor
    public CourseSection(CourseSubSection subSection) {
        this.courseSectionId = incrementAndGetCourseSectionId();
        this.instructors = subSection.getInstructors();
        this.enrolmentCapacity = subSection.getEnrolmentCapacity();
        this.enrolmentTotal = subSection.getEnrolmentTotal();
        this.type = subSection.getType();
        this.subSections = new ArrayList<>();
        this.subSections.add(subSection);
        sectionCount++;
    }

    public long incrementAndGetCourseSectionId() {
        this.courseSectionId = CourseSection.nextCourseSectionId.incrementAndGet();
        return courseSectionId;
    }

    //insert section into courseSections, in sorted order.
    //returns true if successful, false otherwise.
    public boolean insertCourseSubSection(CourseSubSection subSection) {

        if(this.type.equals(subSection.getType()) == false) {
            return false;
        }

        this.subSections.add(subSection);
        this.enrolmentCapacity += subSection.getEnrolmentCapacity();
        this.enrolmentTotal += subSection.getEnrolmentTotal();

        //for each newInstructor, check if it is already one of the old instructors, else insert
        for(String newInstructor : subSection.getInstructors()) {
            boolean found = false;
            for(String oldInstructor : instructors) {

                if(newInstructor.equals(oldInstructor)) {
                    found = true;
                    break;
                }

            }

            if(found == false) {
                instructors.add(newInstructor);
            }
        }

        return true;
    }

    // precondition: thisType is not equal to otherSection.getType()
    // returns true if thisType is less than otherType
    public boolean lessThan(CourseSection otherSection) {
        String otherType = otherSection.getType();
        String thisType = this.getType();

        if(thisType == otherType) {
            return false;
        }

        return myModel.compareString(thisType, otherType);
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