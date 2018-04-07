package ca.courseplannerv1.model.view;

import ca.courseplannerv1.model.watchers.Watcher;

import java.util.ArrayList;

public class WatcherUI {
    private long id;
    private DepartmentUI department;
    private CourseUI course;
    private ArrayList<String> events;

    public WatcherUI() {
    }

    public WatcherUI(long id, DepartmentUI department, CourseUI course, ArrayList<String> events) {
        this.id = id;
        this.department = department;
        this.course = course;
        this.events = events;
    }

    public WatcherUI(Watcher watcher) {
        this.id = watcher.getWatcherId();
        this.department = new DepartmentUI(watcher.getDepartment());
        this.course = new CourseUI(watcher.getCourse());
        this.events = watcher.getEvents();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DepartmentUI getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentUI department) {
        this.department = department;
    }

    public CourseUI getCourse() {
        return course;
    }

    public void setCourse(CourseUI course) {
        this.course = course;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }
}
