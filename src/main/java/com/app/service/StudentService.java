package com.app.service;

import static com.app.converters.StudentConverter.convertStudentRequestDtoToStudent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dto.StudentRequestDto;
import com.app.enums.CardStatus;
import com.app.model.Card;
import com.app.model.Student;
import com.app.repository.StudentRepository;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(StudentRequestDto studentRequestDto) {
        logger.info("addStudent method has started");
        // create model class object
        Student student = new Student();

        // take requestdto input and set it inside student object
//        student.setName(studentRequestDto.getName());
//        student.setEmail(studentRequestDto.getEmail());
//        student.setAge(studentRequestDto.getAge());
//        student.setMobile(studentRequestDto.getMobile());
//        student.setGrade(studentRequestDto.getGrade());

        student = convertStudentRequestDtoToStudent(studentRequestDto);

        // whenever a student is created a card for the student is also created
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);

        // save the student object
        studentRepository.save(student); // when saving student in student Repository even card gets saved in cardRepository through cascading effect
        logger.info("student has successfully saved");
        logger.info("addStudent method has ended");
        return "Student and Card added successfully";
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public Student getStudentById(int studentId) {
        logger.info("getStudentById method has started");
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            logger.error("student is not present in database");
            throw new RuntimeException("Student not found");
        }
        Student student = studentOptional.get();
        logger.info("student details are present");
        logger.info("getStudentById method has ended");
        return student;
    }

    public String updateStudent(StudentRequestDto studentRequestDto, int studentId) {
        // whenever we perform update operation first we have to do find operation and after that update
        Student studentFromDb = studentRepository.findById(studentId).get();
        if (studentFromDb != null) {
            studentFromDb.setName(studentRequestDto.getName());
            studentFromDb.setAge(studentRequestDto.getAge());
            studentFromDb.setMobile(studentRequestDto.getMobile());
            studentFromDb.setEmail(studentRequestDto.getEmail());
            studentFromDb.setGrade(studentRequestDto.getGrade());
            studentRepository.save(studentFromDb);
            return "Student updated successfully";
        }
        return "student not exists so update operation cannot be performed";
    }

    public String deleteStudentById(int studentId) {
        studentRepository.deleteById(studentId);
        return "Student deleted";
    }

    public Student getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        return student;
    }

    public List<Student> getStudentByGrade(String grade) {
        List<Student> studentList = studentRepository.findByGrade(grade);
        return studentList;
    }

    /* pagination - fetching records/data in the form of pages
    pagenumber - the number of page we want to see( page no starts from 0)
    pagesize - the total number of records in each page(fixed in each)
    sorting - arranging the records/data in ascending or descending format
     */
    public List<Student> getStudentsBasedOnPagination(int pageNo, int pageSize, String sortParameter){
        // only pagination - Page<Student> studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize));
        //pagination and sorting
        Page<Student> studentPage = studentRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortParameter).ascending()));
        // we cannot return studentPage directly
        // so traverse studentPage and convert it to list and then return list
        List<Student> studentList = new ArrayList<>();
        for(Student stu : studentPage){
            studentList.add(stu);
        }
        return studentList;
    }

    public String getStudentCount(){
        long studentCount = studentRepository.count();
        return "The total students present are : "+studentCount;
    }
}
