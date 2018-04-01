package ca.courseplannerv1.model.watchers;

import ca.courseplannerv1.view.CourseUI;
import ca.courseplannerv1.view.DepartmentUI;
import ca.courseplannerv1.model.list.CourseList;
import ca.courseplannerv1.model.list.DepartmentList;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Watcher {

    private static AtomicLong nextWatcherId = new AtomicLong();

    private long watcherId;
    private DepartmentUI department;
    private CourseUI course;
    private ArrayList<String> events;

    private DepartmentList departmentList = new DepartmentList();
    private CourseList courseList = new CourseList();


    public Watcher() {
    }

    public Watcher(long watcherId, DepartmentUI department, CourseUI course, ArrayList<String> events, DepartmentList departmentList, CourseList courseList) {
        this.watcherId = watcherId;
        this.department = department;
        this.course = course;
        this.events = events;
        this.departmentList = departmentList;
        this.courseList = courseList;
    }

    public long getAndIncrementWatcherId() {
        this.watcherId = Watcher.nextWatcherId.getAndIncrement();
        return watcherId;
    }

    private void registerAsObserver() {
        String deptName = department.getName();
        String catalogNum = course.getCatalogNumber();

        departmentList.addObserver(new Observer() {
            @Override
            public void stateChanged() {
                addCourseEvent();
            }
        });
    }

    private void addCourseEvent() {
        String date = getPresentDateString();


//        Sun Mar 25 21:41:35 PDT 2018: Added section LEC with enrollment (89 / 90)
//        to offering Spring 2019

    }

    private String getPresentDateString() {
        Date today = new Date();
        SimpleDateFormat isoFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy");
        isoFormat.setTimeZone(TimeZone.getTimeZone("PST"));

        String dateString = isoFormat.format(today);
        System.out.println(dateString);
        return dateString;
    }

    public static void setNextWatcherId(AtomicLong nextWatcherId) {
        Watcher.nextWatcherId = nextWatcherId;
    }

    public long getWatcherId() {
        return watcherId;
    }

    public void setWatcherId(long watcherId) {
        this.watcherId = watcherId;
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
