package ca.courseplannerv1.model;

import java.util.ArrayList;

//has many departments
public class myModel {
    //ArrayList of departments, in sorted order, by alphabetical order of deptName
    private ArrayList<Department> departments;

    public myModel() {
        this.departments = new ArrayList<>();
    }

    public myModel(ArrayList<Department> departments) {
        this.departments = departments;
    }

    //insert new department into departments, in sorted order.
    public void insertNewDepartment(Department department) {

    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }


}
