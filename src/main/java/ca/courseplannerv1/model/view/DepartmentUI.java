package ca.courseplannerv1.model.view;

import ca.courseplannerv1.model.system.Department;

public class DepartmentUI {
    private long deptId;
    private String name;

    public DepartmentUI() {
    }

    public DepartmentUI(long deptId, String name) {
        this.deptId = deptId;
        this.name = name;
    }

    public DepartmentUI(Department department) {
        this.deptId = department.getDeptId();
        this.name = department.getDeptName();
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
