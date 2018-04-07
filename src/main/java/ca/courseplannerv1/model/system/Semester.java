package ca.courseplannerv1.model.system;


import java.util.ArrayList;

public class Semester {
    private int semCode;
    private int year;
    private Sem sem;

    public enum Sem{
        Spring, Summer, Fall;
    }

    //default constructor
    public Semester() {
    }

    //parametrized constructor
    //example year: 2018
    //example sem: Summer
    public Semester(int semCode, int year, Sem sem) {
        this.semCode = semCode;
        this.year = year;
        this.sem = sem;
    }

//    creates a new semester object, given a semester code.
//    The semester code of 1187 is:
//    Year = 1900 + (100 * first digit) + (middle two digits)
//    Semester = final digit: 1=Spring, 4=Summer (I think), 7=fall.
    public Semester(int semCode) {
        this.semCode = semCode;

        //divide the semCode into its 4 digits
        int digit1 = semCode/1000;
        int digit2 = semCode/100 - digit1*10;
        int digit3 = semCode/10 - digit1*100 - digit2*10;
        int digit4 = semCode - digit1*1000 - digit2*100 - digit3*10;

        this.year = 1900 + (100 * digit1) + (digit2*10 + digit3);

        switch(digit4) {
            case 1:
                this.sem = Sem.Spring;
                break;
            case 4:
                this.sem = Sem.Summer;
                break;
            case 7:
                this.sem = Sem.Fall;
                break;
            default:
                throw new RuntimeException("Invalid semester code inputted.");
        }
    }

    //getters and setters


    public int getSemCode() {
        return semCode;
    }

    public void setSemCode(int semCode) {
        this.semCode = semCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Sem getSem() {
        return sem;
    }

    public void setSem(Sem sem) {
        this.sem = sem;
    }

    public void setSem(int intSem) {
        switch(intSem) {
            case 1:
                this.sem = Semester.Sem.Spring;
                break;
            case 4:
                this.sem = Semester.Sem.Summer;
                break;
            case 7:
                this.sem = Semester.Sem.Fall;
                break;
            default:
                //throw exception
                System.out.println("Invalid input for setSem.");
                System.out.println("intSem = " + intSem);
                throw new RuntimeException("Invalid semester code inputted.");
        }
    }

}
