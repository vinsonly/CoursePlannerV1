package ca.courseplannerv1.model.list;

import ca.courseplannerv1.model.system.Course;
import ca.courseplannerv1.model.system.Department;
import ca.courseplannerv1.model.watchers.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CourseList extends CustomList<Course>{

    @Override
    public void insertSorted(Course course) {
        //if list is empty, add to end
        if(size() == 0) {
            insert(course);
            return;
        }

        int currentIndex = 0;
        for(Course thisCourse : getList()) {

            if(course.isEqual(thisCourse)) {
                return;
            }

            if(course.lessThan(thisCourse)) {
                insert(currentIndex, course);
                return;
            }
            else {
                currentIndex++;
            }
        }

        insert(course);
        return;
    }

    @Override
    public void printItems() {
        System.out.println("Courses:");
        for(Course course : getList()) {
            System.out.println(course.getCatalogNumber());
        }
    }

}

