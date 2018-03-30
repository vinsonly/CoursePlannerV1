package ca.courseplannerv1.model;

public class CourseSectionLite {
    private String type;
    private int enrollmentTotal;
    private int enrollmentCap;

    public CourseSectionLite() {
    }

    public CourseSectionLite(String type, int enrollmentTotal, int enrollmentCap) {
        this.type = type;
        this.enrollmentTotal = enrollmentTotal;
        this.enrollmentCap = enrollmentCap;
    }

    public CourseSectionLite(CourseSection courseSection) {
        this.type = courseSection.getType();
        this.enrollmentTotal = courseSection.getEnrolmentTotal();
        this.enrollmentCap = courseSection.getEnrolmentCapacity();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public void setEnrollmentTotal(int enrollmentTotal) {
        this.enrollmentTotal = enrollmentTotal;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public void setEnrollmentCap(int enrollmentCap) {
        this.enrollmentCap = enrollmentCap;
    }
}
