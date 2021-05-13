package de.tekup.whitetest.service;

import de.tekup.soap.models.whitetest.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WhiteTestService {
    private ArrayList<Student> students;
    private ArrayList<Exam> exams;

    private void createStudents() {
        students = new ArrayList<>();
        for (int i = 1 ; i <= 10 ; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("Student" + i);
            student.setAddress("Address" + i);
            students.add(student);
        }
    }

    private void createExams() {
        exams = new ArrayList<>();
        for (int i = 1 ; i <= 10 ; i++) {
            Exam exam = new Exam();
            exam.setCode("Code" + i);
            exam.setName("Exam" + i);
            exams.add(exam);
        }
    }

    public WhiteTestResponse getWhiteTestResponse(StudentRequest studentRequest) {
        createStudents();
        createExams();
        WhiteTestResponse whiteTestResponse = new ObjectFactory().createWhiteTestResponse();
        whiteTestResponse.setExam(null);
        whiteTestResponse.setStudent(null);
        whiteTestResponse.setDate(null);
        for (Exam exam: exams) {
            if (exam.getCode().equals(studentRequest.getExamCode())) {
                whiteTestResponse.setExam(exam);
                break;
            }
        }
        for (Student student: students) {
            if (student.getId() == studentRequest.getStudentId()) {
                whiteTestResponse.setStudent(student);
                break;
            }
        }
        if (whiteTestResponse.getExam() != null && whiteTestResponse.getStudent() != null) {
            whiteTestResponse.setDate(null);
        }
        return whiteTestResponse;
    }
}
