package ca.courseplannerv1.model.system;

import ca.courseplannerv1.controllers.CoursePlannerController;
import ca.courseplannerv1.model.list.CourseOfferingList;
import ca.courseplannerv1.model.watchers.Observer;
import ca.courseplannerv1.model.watchers.WatcherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//each course contains an array of arraylists, which each arraylist corresponding to a course offering at a campus
//each course offering contains many classes.
public class Course {

    public static int courseCount = 0;

    private static AtomicLong nextCourseId = new AtomicLong();

    private long courseId;              // unique course id
    private String deptName;            // the subject (eg. CMPT, MACM, CMNS);
    private String catalogNumber;       // eg: 300, 250W, X99, 2XX
    private ArrayList<String> locations;// list of locations where this course is offered, sorted alphabetically

    //Each courseOffering has a unique location and semester pairing
    //one courseOffering for each
    //stored in sorted order by semesterCode and location
    private CourseOfferingList courseOfferings;

    //default constructor
    public Course() {
        this.courseId = getAndIncrementCourseId();
        this.deptName = new String();
        this.catalogNumber = new String();
        this.locations = new ArrayList<>();
        this.courseOfferings = new CourseOfferingList();
        courseCount++;
    }

    //parametrized constructor
    public Course(String deptName, String catalogNumber, ArrayList<String> locations, CourseOfferingList courseOfferings) {
        this.courseId = getAndIncrementCourseId();
        this.deptName = deptName;
        this.catalogNumber = catalogNumber;
        this.locations = locations;
        this.courseOfferings = courseOfferings;
        courseCount++;
    }

    //parametrized constructor
    public Course(String deptName, String catalogNumber, String location, CourseOffering courseOffering) {
        System.out.println("Creating new Course");
        ArrayList<String> locations = new ArrayList<>();
        locations.add(location);
        this.courseOfferings = new CourseOfferingList();
        insertNewCourseOffering(courseOffering);
        this.courseId = getAndIncrementCourseId();
        this.deptName = deptName;
        this.catalogNumber = catalogNumber;
        this.locations = locations;
        courseCount++;
    }

    //insert courseOffering into courseOfferings, in sorted order.
    public void insertNewCourseOffering(CourseOffering courseOffering) {
        courseOfferings.insertSorted(courseOffering);
        registerAsObserver(courseOffering);
    }

    //returns true if the catalogNumbers for both of the course are equal, otherwise return false
    public boolean isEqual(Course otherCourse) {
        return this.catalogNumber.equals(otherCourse.getCatalogNumber());
    }

    //compares the catalogNumbers
    //returns true if thisCourse is less than otherCourse, false otherwise
    public boolean lessThan(Course otherCourse) {
        String otherNum = otherCourse.getCatalogNumber();
        String thisNum = this.catalogNumber;

        return myModel.compareString(thisNum, otherNum);
    }

    //find CourseOffering by courseOfferingId
    public CourseOffering findCourseOffering(long courseOfferingid) {
        for(CourseOffering courseOffering : courseOfferings) {
            if(courseOffering.getCourseOfferingId() == courseOfferingid) {
                return courseOffering;
            }
        }

        throw new CoursePlannerController.CourseOfferingNotFoundException(courseOfferingid);
    }

    public void printOfferings() {
        for(CourseOffering offering : courseOfferings) {
            System.out.println(offering.getSem().getSemCode() + ", " + offering.getLocation());
        }
    }

    //getters and setters

    public long getAndIncrementCourseId() {
        this.courseId = Course.nextCourseId.getAndIncrement();
        return courseId;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public CourseOfferingList getCourseOfferings() {
        return courseOfferings;
    }

    public void setCourseOfferings(CourseOfferingList courseOfferings) {
        this.courseOfferings = courseOfferings;
    }

    //register as an observer
    private void registerAsObserver(CourseOffering courseOffering) {
        System.out.println("Registering as observer for courseOffering");
        courseOffering.addObserver(new Observer() {
            @Override
            public void stateChanged(Object obj) {
                System.out.println("Course stateChanged.");
                WatcherInfo watcherInfo = WatcherInfo.class.cast(obj);
                watcherInfo.printWatcherInfo();
                notifyObservers(watcherInfo);
            }
        });
    }

    //make observable
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {observers.remove(observer);}

    private void notifyObservers(Object obj) {
        for(Observer observer : observers) {
            observer.stateChanged(obj);
        }
    }

}
