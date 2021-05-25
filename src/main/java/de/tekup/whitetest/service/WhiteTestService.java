package de.tekup.whitetest.service;

import de.tekup.soap.models.whitetest.*;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

@Service
public class WhiteTestService {
    private ArrayList<Student> students;
    private ArrayList<Exam> exams;

    public WhiteTestResponse getWhiteTestResponse(StudentRequest studentRequest) {
        createStudents();
        createExams();
        Student student = getStudent(studentRequest.getStudentId());
        Exam exam = getExam(studentRequest.getExamCode());
        WhiteTestResponse whiteTestResponse = new ObjectFactory().createWhiteTestResponse();
        if (student == null)
            whiteTestResponse.getErrors().add("Student with id '" + studentRequest.getStudentId() + "' does not exist.");
        if (exam == null)
            whiteTestResponse.getErrors().add("Exam with code '" + studentRequest.getExamCode() + "' does not exist.");
        if (whiteTestResponse.getErrors().isEmpty()) {
            whiteTestResponse.setStudent(student);
            whiteTestResponse.setExam(exam);
            try {
                whiteTestResponse.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                        GregorianCalendar.from(
                                ZonedDateTime.now(
                                        ZoneId.of("Pacific/Auckland")
                                )
                        )
                ));
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }
        }
        return whiteTestResponse;
    }

    public AllTestsResponse getAllTests() {
        createExams();
        AllTestsResponse allTestsResponse = new ObjectFactory().createAllTestsResponse();
        allTestsResponse.getExam().addAll(exams);
        return allTestsResponse;
    }

    private void createStudents() {
        students = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("Student" + i);
            Address address = new Address();
            address.setStreet("Street" + i);
            address.setCity("City" + i);
            address.setPostCode("PostCode" + i);
            student.setAddress(address);
            students.add(student);
        }
    }

    private void createExams() {
        exams = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Exam exam = new Exam();
            exam.setCode("Code" + i);
            exam.setName("Exam" + i);
            exams.add(exam);
        }
    }

    private Student getStudent(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElse(null);
    }

    private Exam getExam(String code) {
        return exams.stream()
                .filter(e -> e.getCode().equalsIgnoreCase(code))
                .findAny()
                .orElse(null);
    }

}
