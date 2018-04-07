package ca.courseplannerv1.model.view;

import ca.courseplannerv1.model.system.CourseOffering;
import ca.courseplannerv1.model.system.Semester;

import java.util.ArrayList;

public class CourseOfferingUI {
    private long courseOfferingId;
    private CourseUI course;
    private String location;
    private String instructors;
    private Semester.Sem term;
    private int semesterCode;
    private int year;

    public CourseOfferingUI() {
    }

    public CourseOfferingUI(long courseOfferingId, CourseUI course, String location, String instructors, Semester.Sem term, int semesterCode, int year) {
        this.courseOfferingId = courseOfferingId;
        this.course = course;
        this.location = location;
        this.instructors = instructors;
        this.term = term;
        this.semesterCode = semesterCode;
        this.year = year;
    }

    public CourseOfferingUI(CourseOffering courseOffering, CourseUI courseUI) {
        this.courseOfferingId = courseOffering.getCourseOfferingId();
        this.course = courseUI;
        this.location = courseOffering.getLocation();
        this.instructors = arrayListToString(courseOffering.getInstructors());
        this.term = courseOffering.getSem().getSem();
        this.semesterCode = courseOffering.getSem().getSemCode();
        this.year = courseOffering.getSem().getYear();
    }


    public long getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(long courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public CourseUI getCourse() {
        return course;
    }

    public void setCourse(CourseUI course) {
        this.course = course;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructors() {
        return instructors;
    }

    public void setInstructors(String instructors) {
        this.instructors = instructors;
    }

    public Semester.Sem getTerm() {
        return term;
    }

    public void setTerm(Semester.Sem term) {
        this.term = term;
    }

    public int getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(int semesterCode) {
        this.semesterCode = semesterCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private String arrayListToString(ArrayList<String> arrayList) {
        int size = arrayList.size();
        String string = new String();

        for(int i = 0; i < size; i++) {
            string = string + arrayList.get(i);
            if(i != size - 1) {
                string = string + ", ";
            }
        }
        return string;
    }
}
