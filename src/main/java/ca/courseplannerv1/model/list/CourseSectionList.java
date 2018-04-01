package ca.courseplannerv1.model.list;

import ca.courseplannerv1.model.system.CourseOffering;
import ca.courseplannerv1.model.system.CourseSection;

public class CourseSectionList extends CustomList<CourseSection> {

    @Override
    public void insertSorted(CourseSection courseSection) {
        //if list is empty, add to end
        if(size() == 0) {
            insert(courseSection);
            return;
        }

        int currentIndex = 0;
        for(CourseSection thisCourseSection : getList()) {

            if(thisCourseSection.isEqual(courseSection)){
                return;
            }

            if(courseSection.lessThan(thisCourseSection)) {
                insert(currentIndex, courseSection);
                return;
            }
            else {
                currentIndex++;
            }
        }

        insert(courseSection);
        return;
    }

    @Override
    public void printItems() {
        System.out.println("Departments:");
        for(CourseSection courseSection : getList()) {
            System.out.println(courseSection.getType());
        }
    }

    @Override
    public void insert(CourseSection courseSection) {
        super.insert(courseSection);
        super.notifyObservers(courseSection);
    }
}

