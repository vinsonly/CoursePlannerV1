package ca.courseplannerv1.controllers;

import ca.courseplannerv1.model.view.*;
import ca.courseplannerv1.model.system.*;
import ca.courseplannerv1.model.watchers.Watcher;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CoursePlannerController {

    @GetMapping("/api/about")
    public AboutUI getInfo() {
        AboutUI newAboutUI = new AboutUI("Leo and Vinson's course planner app", "Leo Mai & Vinson Ly");
        return newAboutUI;
    }

    @GetMapping("/api/dump-model")
    public void dumpModel() {
        myModel.dumpModel();
        myModel.dumpModelToFile();
    }

    //Access Departments, Courses, Offerings, and Sections
    @GetMapping("/api/departments")
    //list of all departments
    public List<DepartmentUI> getDepartments() {

        ArrayList<DepartmentUI> deptLites = new ArrayList<>();

        for(Department savedDepartment : myModel.departmentList) {
            DepartmentUI newDeptLite = new DepartmentUI(savedDepartment.getDeptId(), savedDepartment.getDeptName());
            deptLites.add(newDeptLite);
        }
        return deptLites;
    }

    //List all courses with the given deptId
    @GetMapping("/api/departments/{deptId}/courses")
    public List<CourseUI> getCourses(@PathVariable("deptId") long deptId) {

        Department foundDepartment = myModel.findDepartmentById(deptId);

        ArrayList<CourseUI> courseUIS = new ArrayList<>();

        for(Course savedCourse : foundDepartment.getCourses()) {
            CourseUI newCourseUI = new CourseUI(savedCourse.getCourseId(), savedCourse.getCatalogNumber());
            courseUIS.add(newCourseUI);
        }

        return courseUIS;

    }

    //List the offerings of the course with courseID inside department with deptId
    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings")
    public List<CourseOfferingUI> getOfferings(@PathVariable("deptId") long deptId, @PathVariable("courseId") long courseId) {

        Department foundDepartment = myModel.findDepartmentById(deptId);

        Course foundCourse = foundDepartment.findCourseByCourseId(courseId);

        CourseUI thisCourseUI = new CourseUI(foundCourse.getCourseId(), foundCourse.getCatalogNumber());

        ArrayList<CourseOfferingUI> courseOfferingUIS = new ArrayList<>();

        for(CourseOffering courseOffering : foundCourse.getCourseOfferings()) {
            CourseOfferingUI thisCourseOfferingUI = new CourseOfferingUI(courseOffering, thisCourseUI);
            courseOfferingUIS.add(thisCourseOfferingUI);
        }

        return courseOfferingUIS;
    }

    //Return the list of sections for offering with courseOfferingID, in course with courseId, in department with deptId
    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings/{courseOfferingId}")
    public List<CourseSectionUI> getSections(@PathVariable("deptId") long deptId, @PathVariable("courseId") long courseId, @PathVariable("courseOfferingId") long courseOfferingId) {
        Department foundDepartment = myModel.findDepartmentById(deptId);

        Course foundCourse = foundDepartment.findCourseByCourseId(courseId);

        CourseOffering foundCourseOffering = foundCourse.findCourseOffering(courseOfferingId);

        ArrayList<CourseSectionUI> sectionLites = new ArrayList<>();

        for(CourseSection savedSection : foundCourseOffering.getCourseSections()) {
            CourseSectionUI newCourseSectionUI = new CourseSectionUI(savedSection);
            sectionLites.add(newCourseSectionUI);
        }

        return sectionLites;

    }

    //Add a new section to the data stored by the system.
    @PostMapping("/api/addoffering")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseSectionUI addOffering(@RequestBody String lineJson){

        Gson gson = new Gson();

        Line newLine = gson.fromJson(lineJson, Line.class);

        String[] lineArray = newLine.toStringArray();

        myModel.saveLineIntoSystem(lineArray);

        CourseSectionUI newCourseSectionUI = new CourseSectionUI(newLine.getComponent(), Integer.valueOf(newLine.getEnrollmentTotal()), Integer.valueOf(newLine.getEnrollmentCap()));

        return newCourseSectionUI;
    }

    @GetMapping("/api/watchers")
    public ArrayList<WatcherUI> listAllWatchers() {
        ArrayList<WatcherUI> watcherUIS = new ArrayList<>();
        for(Watcher watcher : myModel.watcherList) {
            WatcherUI newWatcherUI = new WatcherUI(watcher);
            watcherUIS.add(newWatcherUI);
        }
        return watcherUIS;
    }

    @PostMapping("/api/watchers")
    @ResponseStatus(HttpStatus.CREATED)
    public WatcherUI createWatcher(@RequestBody String watcherPostUIJSON) {
        System.out.println(watcherPostUIJSON);

        Gson gson = new Gson();

        WatcherPostUI watcherPostUI = gson.fromJson(watcherPostUIJSON, WatcherPostUI.class);

        System.out.println(watcherPostUI.getDeptId());
        System.out.println(watcherPostUI.getCourseId());

        Watcher newWatcher = new Watcher(watcherPostUI.getDeptId(), watcherPostUI.getCourseId());

        myModel.watcherList.insert(newWatcher);

        WatcherUI newWatcherUI = new WatcherUI(newWatcher);

        return newWatcherUI;
    }

    @GetMapping("/api/watchers/{watcherId}")
    public WatcherUI getWatcher(@PathVariable("watcherId") long watcherId) {
        Watcher watcher = myModel.watcherList.findWatcherByWatcherId(watcherId);
        WatcherUI watcherUI = new WatcherUI(watcher);
        return watcherUI;
    }

    @DeleteMapping("/api/watchers/{watcherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWatcher(@PathVariable("watcherId") long watcherId) {
        Watcher watcher = myModel.watcherList.findWatcherByWatcherId(watcherId);
        myModel.watcherList.remove(watcher);
        watcher.deregisterAsObserver();
    }

    @GetMapping("/api/stats/students-per-semester")
    @ResponseBody
    public StudentPerSemesterList getStudentsPerSemester(@RequestParam("deptId") long departmentId){
        GraphProcessor graphProcessor = new GraphProcessor();
        graphProcessor.processGraphData(departmentId);

        return graphProcessor.getListOfLectureSeatTaken();
    }





    //Exception Handlers
    public static class DepartmentNotFoundException extends RuntimeException {
        private long deptId;
        private String deptName;
        public DepartmentNotFoundException(long deptId) {
            super();
            this.deptId = deptId;
        }

        public DepartmentNotFoundException(String deptName) {
            super();
            this.deptName = deptName;
        }


        public long getDeptId() {
            return this.deptId;
        }
    }

    public static class CourseNotFoundException extends RuntimeException {
        private long courseId;
        private String catalogNumber;

        public CourseNotFoundException(long courseId) {
            super();
            this.courseId = courseId;
        }

        public CourseNotFoundException(String catalogNumber) {
            super();
            this.catalogNumber = catalogNumber;
        }

        public long getCourseId() {
            return this.courseId;
        }
    }

    public static class CourseOfferingNotFoundException extends RuntimeException {
        private long courseOfferingId;

        public CourseOfferingNotFoundException(long courseOfferingId) {
            super();
            this.courseOfferingId = courseOfferingId;
        }

        public long getCourseOfferingId() {
            return this.courseOfferingId;
        }
    }

    public static class WatcherNotFoundException extends RuntimeException {
        private long watcherId;

        public WatcherNotFoundException(long watcherId) {
            super();
            this.watcherId = watcherId;
        }

        public long getWatcherId() {
            return this.watcherId;
        }
    }


    //If deptID is invalid
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(DepartmentNotFoundException.class)
    public void badIdExceptionHandler(DepartmentNotFoundException e, HttpServletResponse response) throws IOException {
        String message = "Department of ID " + e.getDeptId() + " not found.";
        response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseNotFoundException.class)
    public void badIdExceptionHandler(CourseNotFoundException e, HttpServletResponse response) throws IOException {
        String message = "Course of ID " + e.getCourseId() + " not found.";
        response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseOfferingNotFoundException.class)
    public void badIdExceptionHandler(CourseOfferingNotFoundException e, HttpServletResponse response) throws IOException {
        String message = "Course offering of ID " + e.getCourseOfferingId() + " not found.";
        response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(WatcherNotFoundException.class)
    public void badIdExceptionHandler(WatcherNotFoundException e, HttpServletResponse response) throws IOException {
        String message = "Unable to find requested watcher.";
        response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    }




}
