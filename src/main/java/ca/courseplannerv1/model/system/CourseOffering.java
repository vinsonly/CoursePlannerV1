package ca.courseplannerv1.model.system;


import ca.courseplannerv1.model.list.CourseSectionList;
import ca.courseplannerv1.model.watchers.Observer;
import ca.courseplannerv1.model.watchers.WatcherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CourseOffering {

    public static int courseOfferingCount = 0;

    private static AtomicLong nextCourseOfferingId = new AtomicLong();

    private long courseOfferingId;                      // unique courseOfferingID
    private Semester sem;
    private String location;
    private ArrayList<String> instructors;
    private CourseSectionList courseSections;
                                                        // all CourseSections for particular CourseOffering
                                                        // stored in sorted order, by type, instructors
                                                        // one unique courseSection per type

    //default constructor
    public CourseOffering() {
        this.courseOfferingId = getAndIncrementCourseOfferingId();
        this.location = new String();
        this.courseSections = new CourseSectionList();
        this.instructors = new ArrayList<>();
        courseOfferingCount++;
    }

    //parametrized contrusctor
    public CourseOffering(Semester sem, String location, ArrayList<String> instructors, CourseSection section) {
        this.courseSections = new CourseSectionList();
        this.courseOfferingId = getAndIncrementCourseOfferingId();
        this.sem = sem;
        this.location = location;
        this.instructors = instructors;
        courseOfferingCount++;
        this.courseSections.insert(section);
    }


    public long getAndIncrementCourseOfferingId() {
        this.courseOfferingId = CourseOffering.nextCourseOfferingId.getAndIncrement();
        return courseOfferingId;
    }

    //insert section into courseSections, in sorted order.
    public void insertCourseSection(CourseSection section) {
        courseSections.insertSorted(section);
        insertInstructors(section.getInstructors());
        registerAsObserver(section);
    }

    //insert instructor, in sorted order, no duplicates
    public void insertInstructors(ArrayList<String> newInstructors) {

        if(this.instructors.size() == 0) {
            this.instructors = newInstructors;
            return;
        }

        boolean duplicate = false;
        for(String newInstructor : newInstructors) {
            int currentIndex = 0;
            for(String savedInstructor : this.instructors) {
                if(newInstructor.equals(savedInstructor)) {
                    break;
                }

                if(myModel.compareString(newInstructor, savedInstructor)) {
                    this.instructors.add(currentIndex, newInstructor);
                    break;
                }
                else {
                    currentIndex++;
                }
            }

            this.instructors.add(newInstructor);
        }
    }



    //returns true if the location and semesters are the same for both the courseOfferings, otherwise returns false.
    public boolean isEqual(CourseOffering otherCourseOffering) {
        return this.getLocation().equals(otherCourseOffering.getLocation()) && this.sem.getSemCode() == otherCourseOffering.getSem().getSemCode();
    }

    //compare semesterCode and location
    //return true if this semCode is less than other semCode
    //if both semCodes are equal
    //return true if this location is less than other location
    //return false otherwise.
    public boolean lessThan(CourseOffering otherOffering) {
        String otherLocation = otherOffering.getLocation();
        String thisLocation = this.getLocation();
        int otherSem = otherOffering.getSem().getSemCode();
        int thisSem = this.getSem().getSemCode();

        //if semCodes are the same, compare location
        if(otherSem == thisSem) {
            return myModel.compareString(thisLocation, otherLocation);
        }
        else if(thisSem < otherSem) {
            return true;
        }
        else {
            return false;
        }
    }

    public void printSections() {
        for(CourseSection savedSection : courseSections) {
            System.out.println(savedSection.getType());
        }
    }

    public ArrayList<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<String> instructors) {
        this.instructors = instructors;
    }

    public long getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(long courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public Semester getSem() {
        return sem;
    }

    public void setSem(Semester sem) {
        this.sem = sem;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CourseSectionList getCourseSections() {
        return courseSections;
    }

    public void setCourseSections(CourseSectionList courseSections) {
        this.courseSections = courseSections;
    }

    //register as an observer
    private void registerAsObserver(CourseSection courseSection) {
        courseSection.addObserver(new Observer() {
            @Override
            public void stateChanged(Object obj) {
                System.out.println("CourseOffering stateChanged.");

                WatcherInfo watcherInfo = WatcherInfo.class.cast(obj);
                watcherInfo.setSemester(sem.getSem().toString());
                watcherInfo.setYear(sem.getYear());

                notifyObservers(watcherInfo);
            }
        });
    }

    //make observable
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(Object obj) {
        System.out.println("SubSection notifying observers.");
        for(Observer observer : observers) {
            observer.stateChanged(obj);
        }
    }

}
