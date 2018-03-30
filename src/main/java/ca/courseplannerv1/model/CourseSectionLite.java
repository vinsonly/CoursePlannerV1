package ca.courseplannerv1.model;

public class CourseSectionLite {
    private String type;
    private int enrollmentCap;
    private int enrollmentTotal;

    public CourseSectionLite() {
    }

    public CourseSectionLite(String type, int enrollmentTotal, int enrollmentCap) {
        this.type = type;
        this.enrollmentCap = enrollmentCap;
        this.enrollmentTotal = enrollmentTotal;
    }

    public CourseSectionLite(CourseSection courseSection) {
        this.type = courseSection.getType();
        this.enrollmentCap = courseSection.getEnrolmentCapacity();
        this.enrollmentTotal = courseSection.getEnrolmentTotal();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public void setEnrollmentCap(int enrollmentCap) {
        this.enrollmentCap = enrollmentCap;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public void setEnrollmentTotal(int enrollmentTotal) {
        this.enrollmentTotal = enrollmentTotal;
    }
}
