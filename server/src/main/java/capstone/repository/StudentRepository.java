package capstone.repository;

import javax.transaction.Transactional;

import capstone.model.users.Student;
import capstone.model.users.User;

@Transactional
public interface StudentRepository extends UserBaseRepository<Student> { /* ... */ }