package ca.courseplannerv1.model.list;

import ca.courseplannerv1.model.system.StudentPerSemester;

import java.util.Collections;
import java.util.List;

public class StudentPerSemesterList extends CustomList<StudentPerSemester> {
    private final int FIRST_SEMESTER = 0;
    private final int LIST_OFFSET = 1;

    @Override
    public void printItems() {

    }

    @Override
    public void insertSorted(StudentPerSemester obj) {
        List<StudentPerSemester> listOfSemester = getList();
        StudentPerSemester findSemester = findSemester(listOfSemester,obj);
        if (findSemester != null){
            findSemester.setTotalStudent(obj.getTotalStudent());
        } else {
            listOfSemester.add(obj);
        }
        sort();
    }

    private StudentPerSemester findSemester(List<StudentPerSemester> listOfSemester, StudentPerSemester obj) {
        for (StudentPerSemester currentSemester : listOfSemester){
            if (currentSemester.getSemester() == obj.getSemester()){
                return currentSemester;
            }
        }
        return null;
    }

    public void sort(){
        List<StudentPerSemester> listOfSemester = getList();
        Collections.sort(listOfSemester,StudentPerSemester.SemesterCodeComparator);
    }

    public int getFirstSemester(){
        List<StudentPerSemester> listOfSemester = getList();
//        Collections.sort(listOfSemester,StudentPerSemester.SemesterCodeComparator);
        return listOfSemester.get(FIRST_SEMESTER).getSemester();
    }

    public int getLastSemester(){
        List<StudentPerSemester> listOfSemester = getList();
//        Collections.sort(listOfSemester,StudentPerSemester.SemesterCodeComparator);
        int lastSemesterIndex = listOfSemester.size() - LIST_OFFSET;
        return listOfSemester.get(lastSemesterIndex).getSemester();
    }

    @Override
    public void remove(StudentPerSemester obj) {
        super.remove(obj);
    }
}
