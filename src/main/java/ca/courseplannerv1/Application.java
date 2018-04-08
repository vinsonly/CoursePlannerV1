package ca.courseplannerv1;

import ca.courseplannerv1.model.list.CourseList;
import ca.courseplannerv1.model.list.DepartmentList;
import ca.courseplannerv1.model.system.*;
import ca.courseplannerv1.model.view.CourseUI;
import ca.courseplannerv1.model.view.DepartmentUI;
import ca.courseplannerv1.model.watchers.Watcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

//tells spring that this is where it all starts and adds in a bunch of magic for us behind our backs
@SpringBootApplication

public class Application {
    public static void main(String[] args) {
        CSVParser.readCSV();
//        myModel.dumpModel();
//        myModel.dumpModelToFile();

        SpringApplication.run(Application.class, args);

    }
}
