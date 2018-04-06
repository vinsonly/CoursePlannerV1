package ca.courseplannerv1.model.system;

import java.util.Comparator;

public class StudentPerSemester {
    private int semesterCode;
    private int totalStudent;
    private final int INITIALIZER = 0;

    public StudentPerSemester(int semesterCode) {
        this.semesterCode = semesterCode;
        this.totalStudent = INITIALIZER;
    }

    public int getSemester() {
        return semesterCode;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void addToTotalStudent(int newStudents) {
        this.totalStudent += newStudents;
    }

    public static Comparator<StudentPerSemester> SemesterCodeComparator = new Comparator<StudentPerSemester>() {
        @Override
        public int compare(StudentPerSemester semester1, StudentPerSemester semester2) {
            return semester1.getSemester() - semester2.getSemester();
        }
    };

}
