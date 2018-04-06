package ca.courseplannerv1.model.view;

public class Line {

    private String semester;
    private String subjectName;
    private String catalogNumber;
    private String location;
    private String enrollmentCap;
    private String component;
    private String enrollmentTotal;
    private String instructor;

    public Line() {
    }

    public Line(String semester, String subjectName, String catalogNumber, String location, String enrollmentCap, String component, String enrollmentTotal, String instructor) {
        this.semester = semester;
        this.subjectName = subjectName;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrollmentCap = enrollmentCap;
        this.component = component;
        this.enrollmentTotal = enrollmentTotal;
        this.instructor = instructor;
    }

    public void printLine() {
        System.out.println("semester = " + semester);
        System.out.println("subjectName = " + subjectName);
        System.out.println("catalogNumber = " + catalogNumber);
        System.out.println("location = " + location);
        System.out.println("enrollmentCap = " + enrollmentCap);
        System.out.println("component = " + component);
        System.out.println("enrollmentTotal = " + enrollmentTotal);
        System.out.println("instructor = " + instructor);
    }

    public String[] toStringArray() {

        //SEMESTER,SUBJECT,CATALOGNUMBER,LOCATION,ENROLMENTCAPACITY,ENROLMENTTOTAL,INSTRUCTORS,COMPONENTCODE

        String[] array = new String[8];
        array[0] = semester;
        array[1] = subjectName;
        array[2] = catalogNumber;
        array[3] = location;
        array[4] = enrollmentCap;
        array[5] = enrollmentTotal;
        array[6] = instructor;
        array[7] = component;

        return array;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEnrollmentCap() {
        return enrollmentCap;
    }

    public void setEnrollmentCap(String enrollmentCap) {
        this.enrollmentCap = enrollmentCap;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public void setEnrollmentTotal(String enrollmentTotal) {
        this.enrollmentTotal = enrollmentTotal;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
