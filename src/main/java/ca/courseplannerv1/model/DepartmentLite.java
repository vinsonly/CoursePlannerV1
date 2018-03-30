package ca.courseplannerv1.model;

public class DepartmentLite {
    private long deptId;
    private String name;

    public DepartmentLite() {
    }

    public DepartmentLite(long deptId, String name) {
        this.deptId = deptId;
        this.name = name;
    }

    public DepartmentLite(Department department) {
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
