package ca.courseplannerv1.model.watchers;

import ca.courseplannerv1.model.system.Course;
import ca.courseplannerv1.model.system.Department;
import ca.courseplannerv1.model.system.myModel;
import ca.courseplannerv1.model.view.CourseUI;
import ca.courseplannerv1.model.view.DepartmentUI;

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


    public Watcher(DepartmentUI department, CourseUI course) {
        this.watcherId = getAndIncrementWatcherId();
        this.department = department;
        this.course = course;
        this.events = new ArrayList<>();
        registerAsObserver();
    }

    public long getAndIncrementWatcherId() {
        this.watcherId = Watcher.nextWatcherId.getAndIncrement();
        return watcherId;
    }

    private void registerAsObserver() {
        String deptName = department.getName();
        String catalogNum = course.getCatalogNumber();
        Department dept = myModel.departmentList.findDepartmentByDeptName(deptName);
        Course course = dept.getCourses().findCourseByCatalogNumber(catalogNum);

        course.addObserver(new Observer() {
            @Override
            public void stateChanged(Object obj) {
                System.out.println("Watcher stateChanged.");
                WatcherInfo watcherInfo = WatcherInfo.class.cast(obj);
                addedCourseEvent(watcherInfo);
            }
        });
    }

    private void addedCourseEvent(WatcherInfo watcherInfo) {
        String date = getPresentDateString();
        watcherInfo.setDate(date);

        //call endpoint
        System.out.println(watcherInfo.getDate() + ": Added section " +
                watcherInfo.getType() + " with enrollment (" +
                watcherInfo.getEnrolmentTotal() + "/" + watcherInfo.getEnrolmentCapacity() +
                ") to offering " + watcherInfo.getSemester() + " " +
                watcherInfo.getYear());


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
