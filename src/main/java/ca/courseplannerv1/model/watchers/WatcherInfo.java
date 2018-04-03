package ca.courseplannerv1.model.watchers;

public class WatcherInfo {
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private String type;
    private String semester; //SPRING, FALL, SUMMER
    private int year;
    private String date;

    //default constructor

    public WatcherInfo() {
    }

    public WatcherInfo(int enrolmentCapacity, int enrolmentTotal, String type, String semester, int year) {
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.type = type;
        this.semester = semester;
        this.year = year;
    }

    public void printWatcherInfo() {
        System.out.println("enrolmentCapacity = " + getEnrolmentCapacity());
        System.out.println("enrolmentTotal = " + getEnrolmentTotal());
        System.out.println("type = " + type);
        System.out.println("semester = " + semester);
        System.out.println("year = " + year);
        System.out.println("date = " + date);
    }

    public int getEnrolmentCapacity() {
        return enrolmentCapacity;
    }

    public void setEnrolmentCapacity(int enrolmentCapacity) {
        this.enrolmentCapacity = enrolmentCapacity;
    }

    public int getEnrolmentTotal() {
        return enrolmentTotal;
    }

    public void setEnrolmentTotal(int enrolmentTotal) {
        this.enrolmentTotal = enrolmentTotal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
