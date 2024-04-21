package kz.nish.springapp.nishApp.services;


import kz.nish.springapp.nishApp.models.Student;
import kz.nish.springapp.nishApp.repositories.StudentsRepository;
import kz.nish.springapp.nishApp.util.StudentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> findALL(){return studentsRepository.findAll();}

    public Student findOne(int id){
        Optional<Student> foundStudent = studentsRepository.findById(id);
        return foundStudent.orElseThrow(StudentNotFoundException::new);

    }

    public void save(Student student){
        studentsRepository.save(student);
    }

}
