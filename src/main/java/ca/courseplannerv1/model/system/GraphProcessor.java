package ca.courseplannerv1.model.system;

import ca.courseplannerv1.model.list.CourseList;
import ca.courseplannerv1.model.list.CourseOfferingList;
import ca.courseplannerv1.model.list.CourseSectionList;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by leom on 06/04/18.
 */
public class GraphProcessor {

    ArrayList<StudentPerSemester> listOfLectureSeatTaken;
    private final int FIRST_SEMESTER = 0;
    private final int LIST_OFFSET = 1;
    private final String LECTURE_SECTION = "LEC";

    public GraphProcessor() {
        this.listOfLectureSeatTaken = new ArrayList<>();
    }

    public void processGraphData(long departmentId){
        ArrayList<StudentPerSemester> listOfStudentPerSemester = getStudentPerSemesters(departmentId);
        insertSemesterWithNoStudent(listOfStudentPerSemester);
        ArrayList<StudentPerSemester> listOfLectureSeatTaken = getListOfLectureSeatTaken();
        listOfLectureSeatTaken.clear();
        listOfLectureSeatTaken.addAll(listOfStudentPerSemester);

    }

    public ArrayList<StudentPerSemester> getListOfLectureSeatTaken() {
        return listOfLectureSeatTaken;
    }

    private int getFirstSemester(ArrayList<StudentPerSemester> studentsPerSemesterList){
//        Collections.sort(listOfSemester,StudentPerSemester.SemesterCodeComparator);
        return studentsPerSemesterList.get(FIRST_SEMESTER).getSemesterCode();
    }

    private int getLastSemester(ArrayList<StudentPerSemester> studentsPerSemesterList){
//        Collections.sort(listOfSemester,StudentPerSemester.SemesterCodeComparator);
        int lastSemesterIndex = studentsPerSemesterList.size() - LIST_OFFSET;
        return studentsPerSemesterList.get(lastSemesterIndex).getSemesterCode();
    }

    private void insertSemesterWithNoStudent(ArrayList<StudentPerSemester> studentsPerSemesterList) {
        int firstSemesterCode = getFirstSemester(studentsPerSemesterList);
        Semester firstSemester = new Semester(firstSemesterCode);
        int firstSemesterYear = firstSemester.getYear();
        int lastSemesterCode = getLastSemester(studentsPerSemesterList);
        Semester lastSemester = new Semester(lastSemesterCode);
        int lastSemesterYear = lastSemester.getYear();
        for (int currentYear = firstSemesterYear; currentYear <= lastSemesterYear; currentYear++){
            ArrayList<Integer> listOfSemCodes = getSemesterCodeOfYear(currentYear,lastSemester);
            for (int currentSemesterCode : listOfSemCodes){
                StudentPerSemester currentSemester = new StudentPerSemester(currentSemesterCode);
                insertSorted(studentsPerSemesterList, currentSemester);
            }
        }
    }

    private ArrayList<StudentPerSemester> getStudentPerSemesters(long departmentId) {
        ArrayList<StudentPerSemester> studentsPerSemesterList = new ArrayList<>();
        Department currentDepartment = myModel.findDepartmentById(departmentId);
        CourseList listOfCourses = currentDepartment.getCourses();
        for (Course currentCourse : listOfCourses){
            CourseOfferingList listOfOffering = currentCourse.getCourseOfferings();
            for (CourseOffering currentOffering : listOfOffering){
                Semester currentSemester = currentOffering.getSem();
                StudentPerSemester studentPerSemester = new StudentPerSemester(currentSemester.getSemCode());
                CourseSectionList listOfCourseSection = currentOffering.getCourseSections();
                for (CourseSection currentSection : listOfCourseSection) {
                    if (currentSection.getType().toUpperCase().equals(LECTURE_SECTION)) {
                        studentPerSemester.addToTotalStudent(currentSection.getEnrolmentTotal());
                        break;
                    }
                }
                insertSorted(studentsPerSemesterList, studentPerSemester);
            }
        }
        return studentsPerSemesterList;
    }

    private ArrayList<Integer> getSemesterCodeOfYear(int year, Semester lastSemesterCode){
        int firstDigit = 1000;
        int secondThirdDigit = (year - 2000)*10;
        int springSemesterCode = firstDigit + secondThirdDigit + 1;
        int summerSemesterCode = firstDigit + secondThirdDigit + 4;
        int fallSemesterCode = firstDigit + secondThirdDigit + 7;
        ArrayList<Integer> listOfSemesterCode = new ArrayList<>();
        if (springSemesterCode < lastSemesterCode.getSemCode()){
            listOfSemesterCode.add(springSemesterCode);
        }
        if (summerSemesterCode < lastSemesterCode.getSemCode()){
            listOfSemesterCode.add(summerSemesterCode);
        }
        if (fallSemesterCode < lastSemesterCode.getSemCode()){
            listOfSemesterCode.add(fallSemesterCode);
        }
        return listOfSemesterCode;
    }

    private void insertSorted(ArrayList<StudentPerSemester> studentsPerSemesterList, StudentPerSemester studentPerSemester) {
        StudentPerSemester savedSemester = findSemester(studentsPerSemesterList, studentPerSemester);
        if (savedSemester != null){
            savedSemester.addToTotalStudent(studentPerSemester.getTotalCoursesTaken());
        } else {
            studentsPerSemesterList.add(studentPerSemester);
        }
        sort(studentsPerSemesterList);
    }

    private StudentPerSemester findSemester(ArrayList<StudentPerSemester> studentsPerSemesterList, StudentPerSemester studentPerSemester) {
        for (StudentPerSemester currentSemester : studentsPerSemesterList){
            if (currentSemester.getSemesterCode() == studentPerSemester.getSemesterCode()){
                return currentSemester;
            }
        }
        return null;
    }

    private void sort(ArrayList<StudentPerSemester> studentsPerSemesterList){
        Collections.sort(studentsPerSemesterList,StudentPerSemester.SemesterCodeComparator);
    }


}
