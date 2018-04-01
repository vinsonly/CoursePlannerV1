package ca.courseplannerv1.model.list;

import ca.courseplannerv1.model.system.CourseOffering;

public class CourseOfferingList extends CustomList<CourseOffering> {

    @Override
    public void insertSorted(CourseOffering courseOffering) {
        //if list is empty, add to end
        if(size() == 0) {
            insert(courseOffering);
            return;
        }

        int currentIndex = 0;
        for(CourseOffering thisCourseOffering : getList()) {
            if(thisCourseOffering.isEqual(courseOffering)) {
                return;
            }

            if(courseOffering.lessThan(thisCourseOffering)) {
                insert(currentIndex, courseOffering);
                return;
            }
            else {
                currentIndex++;
            }
        }

        insert(courseOffering);
        return;
    }

    @Override
    public void printItems() {
        System.out.println("Departments:");
        for(CourseOffering courseOffering : getList()) {
            System.out.println(courseOffering.getSem() + ", " + courseOffering.getLocation());
        }
    }

}

