package ca.courseplannerv1.model;

public class CourseLite {
    private long courseId;
    private String catalogNumber;

    public CourseLite() {
    }

    public CourseLite(long courseId, String catalogNumber) {
        this.courseId = courseId;
        this.catalogNumber = catalogNumber;
    }

    public CourseLite(Course course) {
        this.courseId = course.getCourseId();
        this.catalogNumber = course.getCatalogNumber();
    }


    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }
}
