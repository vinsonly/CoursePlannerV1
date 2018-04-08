package ca.courseplannerv1.model.system;

import java.util.ArrayList;

//an individual class, representing one line from the csv file
public class CourseSubSection {

    public static int subSectionCount = 0;

    private int enrolmentCapacity;
    private int enrolmentTotal;
    private ArrayList<String> instructors;          // instructor(s) that are teaching this section
    private String type;                            // component code (eg: LEC, SEM, TUT)

    public CourseSubSection() {
        this.instructors = new ArrayList<>();
        this.type = new String();
        subSectionCount++;
    }

    public CourseSubSection(int enrolmentCapacity, int enrolmentTotal, ArrayList<String> instructors, String type) {
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.type = type;
        subSectionCount++;
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
