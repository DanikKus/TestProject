package kz.nish.springapp.nishApp.repositories;

import kz.nish.springapp.nishApp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Integer> {
}
