package ca.courseplannerv1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CoursePlannerController {

    @GetMapping("/dump-model")
    public void dumpModel() {
        //executes myModel.dumpModel()
    }
}
