package ca.courseplannerv1.model.view;

public class WatcherPostUI {
    private long deptId;
    private long courseId;

    public WatcherPostUI(long deptId, long courseId) {
        this.deptId = deptId;
        this.courseId = courseId;
    }

    public WatcherPostUI() {
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
