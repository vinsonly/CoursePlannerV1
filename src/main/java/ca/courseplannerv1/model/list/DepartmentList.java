package ca.courseplannerv1.model.list;

import ca.courseplannerv1.controllers.CoursePlannerController;
import ca.courseplannerv1.model.system.Department;

public class DepartmentList extends CustomList<Department> {

    //insert new department into list, in sorted ascending alphabetical order of deptName
    //returns true if successful, false otherwise.
    @Override
    public void insertSorted(Department dept) {
        //if list is empty, add to end
        if(size() == 0) {
            insert(dept);
            return;
        }

        int currentIndex = 0;
        for(Department thisDept : getList()) {

            if(dept.isEqual(thisDept)) {
                return;
            }

            if(dept.lessThan(thisDept)) {
                insert(currentIndex, dept);
                return;
            }
            else {
                currentIndex++;
            }
        }

        insert(dept);
        return;
    }


    @Override
    public void printItems() {
        System.out.println("Departments:");
        for(Department dept : getList()) {
            System.out.println(dept.getDeptName());
        }
    }

    public Department findDepartmentByDeptId(long deptId) {
        for(Department thisDept : getList()) {
            if(thisDept.getDeptId() == deptId) {
                return thisDept;
            }
        }

        throw new CoursePlannerController.DepartmentNotFoundException(deptId);
    }

    public Department findDepartmentByDeptName(String deptName) {
        for(Department thisDept : getList()) {
            if(thisDept.getDeptName().equals(deptName)) {
                return thisDept;
            }
        }

        throw new CoursePlannerController.DepartmentNotFoundException(deptName);
    }





}
