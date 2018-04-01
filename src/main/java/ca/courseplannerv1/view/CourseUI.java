package ca.courseplannerv1.view;

import ca.courseplannerv1.model.system.Course;

public class CourseUI {
    private long courseId;
    private String catalogNumber;

    public CourseUI() {
    }

    public CourseUI(long courseId, String catalogNumber) {
        this.courseId = courseId;
        this.catalogNumber = catalogNumber;
    }

    public CourseUI(Course course) {
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
