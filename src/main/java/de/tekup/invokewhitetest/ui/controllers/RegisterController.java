package de.tekup.invokewhitetest.ui.controllers;

import de.tekup.invokewhitetest.client.SoapClient;
import de.tekup.invokewhitetest.soap.api.consume.whitetest.AllTestsResponse;
import de.tekup.invokewhitetest.soap.api.consume.whitetest.ObjectFactory;
import de.tekup.invokewhitetest.soap.api.consume.whitetest.StudentRequest;
import de.tekup.invokewhitetest.soap.api.consume.whitetest.WhiteTestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private SoapClient client;

    @GetMapping("/register/exam")
    public String registerExamForm(Model model) {
        StudentRequest studentRequest = new StudentRequest();
        model.addAttribute("request", studentRequest);
        return "request";
    }

    @PostMapping("/register/exam")
    public String registerExamResult(@ModelAttribute("request") StudentRequest studentRequest,
                                     Model model) {
        WhiteTestResponse response = client.getWhiteTestResp(studentRequest);
        model.addAttribute("response", response);
        return "result";
    }

    @GetMapping("/exams")
    public String allExams(Model model) {
        AllTestsResponse response = client.getAllExams(new ObjectFactory().createAllTestsRequest(null));
        System.out.println(response.getExam().size());
        model.addAttribute("allExams", response);
        return "all_exams";
    }
}
