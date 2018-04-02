package ca.courseplannerv1.model.system;

import ca.courseplannerv1.model.list.CourseSubSectionList;
import ca.courseplannerv1.model.watchers.Observer;
import ca.courseplannerv1.model.watchers.WatcherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//A course section that aggregates the instructors, enrolment capacities, and enrolment totals
//for a unique type
//belongs to a course offering of a unique
public class CourseSection {

    public static int sectionCount = 0;

    private static AtomicLong nextCourseSectionId = new AtomicLong();

    private long courseSectionId;                  // unique courseSectionId
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private ArrayList<String> instructors;          // instructor(s) that are teaching this section
    private String type;                            // component code (eg: LEC, SEM, TUT)
    private CourseSubSectionList subSections;       // the individual classes that are in this section

    //default constructor
    public CourseSection() {
        this.courseSectionId = getAndIncrementCourseSectionId();
        this.instructors = new ArrayList<>();
        sectionCount++;
        this.enrolmentCapacity = 0;
        this.enrolmentTotal = 0;
        this.type = new String();
        this.subSections = new CourseSubSectionList();
        registerAsObserver();
    }

    //parameterized constructor
    public CourseSection(int enrolmentCapacity, int enrolmentTotal, ArrayList<String> instructors, String type) {
        this.courseSectionId = getAndIncrementCourseSectionId();
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.type = type;
        this.subSections = new CourseSubSectionList();
        registerAsObserver();
        sectionCount++;
    }

    //parameterized constructor
    public CourseSection(CourseSubSection subSection) {
        this.courseSectionId = getAndIncrementCourseSectionId();
        this.instructors = subSection.getInstructors();
        this.enrolmentCapacity = subSection.getEnrolmentCapacity();
        this.enrolmentTotal = subSection.getEnrolmentTotal();
        this.type = subSection.getType();
        this.subSections = new CourseSubSectionList();
        this.subSections.insert(subSection);
        registerAsObserver();
        sectionCount++;
    }

    public long getAndIncrementCourseSectionId() {
        this.courseSectionId = CourseSection.nextCourseSectionId.getAndIncrement();
        return courseSectionId;
    }

    //insert section into courseSections, in sorted order.
    //returns true if successful, false otherwise.
    public void insertCourseSubSection(CourseSubSection subSection) {
        System.out.println("Inserting new sub section.");
        if(this.type.equals(subSection.getType()) == false) {
            return;
        }

        this.subSections.insert(subSection);
        this.enrolmentCapacity += subSection.getEnrolmentCapacity();
        this.enrolmentTotal += subSection.getEnrolmentTotal();

        //for each newInstructor, check if it is already one of the old instructors, else insert
        for(String newInstructor : subSection.getInstructors()) {
            boolean found = false;
            for(String oldInstructor : instructors) {

                if(newInstructor.equals(oldInstructor)) {
                    found = true;
                    break;
                }

            }

            if(found == false) {
                instructors.add(newInstructor);
            }
        }

        return;
    }

    //returns true of both types of the sections are same, otherwise false.
    public boolean isEqual(CourseSection otherSection) {
        return this.type.equals(otherSection.getType());
    }

    // precondition: thisType is not equal to otherSection.getType()
    // returns true if thisType is less than otherType
    public boolean lessThan(CourseSection otherSection) {
        String otherType = otherSection.getType();
        String thisType = this.getType();

        if(thisType == otherType) {
            return false;
        }

        return myModel.compareString(thisType, otherType);
    }

    public long getCourseSectionId() {
        return courseSectionId;
    }

    public void setCourseSectionId(long courseSectionId) {
        this.courseSectionId = courseSectionId;
    }

    public int getEnrolmentCapacity() {
        return enrolmentCapacity;
    }

    public void setEnrolmentCapacity(int enrolmentCapacity) {
        this.enrolmentCapacity = enrolmentCapacity;
    }

    public int getEnrolmentTotal() {
        return enrolmentTotal;
    }

    public void setEnrolmentTotal(int enrolmentTotal) {
        this.enrolmentTotal = enrolmentTotal;
    }

    public ArrayList<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<String> instructors) {
        this.instructors = instructors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //register as an observer
    private void registerAsObserver() {
        System.out.println("CourseSection registering ss observer for subSections");
        subSections.addObserver(new Observer() {
            @Override
            public void stateChanged(Object obj) {
                System.out.println("CourseSection stateChanged.");
                CourseSubSection newSS = CourseSubSection.class.cast(obj);
                WatcherInfo watcherInfo = new WatcherInfo();
                watcherInfo.setEnrolmentCapacity(newSS.getEnrolmentCapacity());
                watcherInfo.setEnrolmentTotal(newSS.getEnrolmentTotal());
                watcherInfo.setType(newSS.getType());
                notifyObservers(watcherInfo);
            }
        });
        System.out.println("There are now " + subSections.getObservers().size() + " observers for CourseSubSectionList.");
    }

    //make observable
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(Object obj) {
        for(Observer observer : observers) {
            observer.stateChanged(obj);
        }
    }

}