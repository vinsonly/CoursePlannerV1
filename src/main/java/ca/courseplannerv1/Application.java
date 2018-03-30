package ca.courseplannerv1;

import ca.courseplannerv1.model.myModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//tells spring that this is where it all starts and adds in a bunch of magic for us behind our backs
@SpringBootApplication

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        //parse \data\course_data_2018.csv into our system.
        CSVParser.readCSV();
//        myModel.printModelInfo();
//        myModel.printDepartments();
//        myModel.departments.get(1).printCourses();
//        myModel.departments.get(1).getCourses().get(1).printOfferings();
//        myModel.departments.get(1).getCourses().get(1).getCourseOfferings().get(1).printSections();
//        myModel.dumpModel();
//        myModel.dumpModelToFile();

    }
}
