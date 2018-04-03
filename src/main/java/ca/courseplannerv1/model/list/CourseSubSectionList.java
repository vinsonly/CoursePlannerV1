package ca.courseplannerv1.model.list;

import ca.courseplannerv1.model.system.CourseSubSection;
import ca.courseplannerv1.model.watchers.Observer;

public class CourseSubSectionList extends CustomList<CourseSubSection>{

    @Override
    public void insertSorted(CourseSubSection courseSubSection) {
        insert(courseSubSection);
        return;
    }

    @Override
    public void printItems() {
        System.out.println("SubSections:");
        for(CourseSubSection courseSubSection : getList()) {
            System.out.println(courseSubSection.getType());
        }
    }

    @Override
    protected void notifyObservers(CourseSubSection obj) {
        System.out.println("CourseSubSectionList notifying observers.");
        super.notifyObservers(obj);
    }


}

