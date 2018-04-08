package ca.courseplannerv1.model.system;

import java.util.Comparator;

public class StudentPerSemester {
    private int semesterCode;
    private int totalCoursesTaken;
    private final int INITIALIZER = 0;

    public StudentPerSemester(int semesterCode) {
        this.semesterCode = semesterCode;
        this.totalCoursesTaken = INITIALIZER;
    }

    public int getSemesterCode() {
        return semesterCode;
    }

    public int getTotalCoursesTaken() {
        return totalCoursesTaken;
    }

    public void addToTotalStudent(int newStudents) {
        this.totalCoursesTaken += newStudents;
    }

    public static Comparator<StudentPerSemester> SemesterCodeComparator = new Comparator<StudentPerSemester>() {
        @Override
        public int compare(StudentPerSemester semester1, StudentPerSemester semester2) {
            return semester1.getSemesterCode() - semester2.getSemesterCode();
        }
    };

}
