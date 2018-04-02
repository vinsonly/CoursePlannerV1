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
    public void insert(CourseSubSection courseSubSection) {
        super.insert(courseSubSection);
        System.out.println("There are " + getObservers().size() + " observers for CourseSubSectionList");
        notifyObservers(courseSubSection);
    }

}

