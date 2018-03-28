package ca.courseplannerv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//tells spring that this is where it all starts and adds in a bunch of magic for us behind our backs
@SpringBootApplication

public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

        //parse \data\course_data_2018.csv into our system.
        CSVParser.readCSV();

    }
}
