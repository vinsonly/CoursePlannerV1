package ca.courseplannerv1.controllers;

import ca.courseplannerv1.model.myModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CoursePlannerController {
    @GetMapping("/api/dump-model")
    public void dumpModel() {
        myModel.dumpModel();
        myModel.dumpModelToFile();
    }
}
