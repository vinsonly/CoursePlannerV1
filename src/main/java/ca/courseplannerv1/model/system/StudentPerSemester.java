package ca.courseplannerv1.model.system;

public class StudentPerSemester {
    int semesterCode;
    int totalCourseTaken;

    public StudentPerSemester() {
    }

    public int getSemester() {
        return semesterCode;
    }

    public int getTotalCourseTaken() {
        return totalCourseTaken;
    }

    public void setTotalCourseTaken(int totalCourseTaken) {
        this.totalCourseTaken += totalCourseTaken;
    }
}
