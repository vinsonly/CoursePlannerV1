package ca.courseplannerv1.model.watchers;

import ca.courseplannerv1.model.system.Course;
import ca.courseplannerv1.model.system.Department;
import ca.courseplannerv1.model.system.myModel;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Watcher {

    private static AtomicLong nextWatcherId = new AtomicLong();

    private long watcherId;
    private ArrayList<String> events;
    private Course course;
    private Department department;
    private Observer observer;

    public Watcher(Department department, Course course) {
        incrementAndGetWatcherId();
        this.department = department;
        this.course = course;
        this.events = new ArrayList<>();
        this.observer = createObserver();
        registerAsObserver();
    }

    public Watcher(long deptId, long courseId) {
        incrementAndGetWatcherId();
        this.department = myModel.departmentList.findDepartmentByDeptId(deptId);
        this.course = this.department.findCourseByCourseId(courseId);
        this.events = new ArrayList<>();
        this.observer = createObserver();
        registerAsObserver();
    }

    public long incrementAndGetWatcherId() {
        this.watcherId = Watcher.nextWatcherId.incrementAndGet();
        return watcherId;
    }

    public Observer createObserver() {
        Observer newObserver = new Observer() {
            @Override
            public void stateChanged(Object obj) {
//                System.out.println("Watcher stateChanged.");
                WatcherInfo watcherInfo = WatcherInfo.class.cast(obj);
                addedCourseEvent(watcherInfo);
            }
        };
        return newObserver;
    }

    private void registerAsObserver() {
        Department dept = this.department;
        Course course = this.course;
        course.addObserver(this.observer);
    }

    public void deregisterAsObserver() {
        course.removeObserver(this.observer);
    }

    private void addedCourseEvent(WatcherInfo watcherInfo) {
        String date = getPresentDateString();
        watcherInfo.setDate(date);

        String event = watcherInfo.getDate() + ": Added section " +
                watcherInfo.getType() + " with enrollment (" +
                watcherInfo.getEnrolmentTotal() + "/" + watcherInfo.getEnrolmentCapacity() +
                ") to offering " + watcherInfo.getSemester() + " " +
                watcherInfo.getYear();
        //call endpoint
//        System.out.println(event);
        this.events.add(event);


//        Sun Mar 25 21:41:35 PDT 2018: Added section LEC with enrollment (89 / 90)
//        to offering Spring 2019

    }

    private String getPresentDateString() {
        Date today = new Date();
        SimpleDateFormat isoFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy");
        isoFormat.setTimeZone(TimeZone.getTimeZone("PST"));

        String dateString = isoFormat.format(today);
//        System.out.println(dateString);
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

    public ArrayList<String> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
