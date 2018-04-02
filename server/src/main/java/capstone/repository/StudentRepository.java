package capstone.repository;

import javax.transaction.Transactional;

import capstone.model.users.Student;

@Transactional
public interface StudentRepository extends UserBaseRepository<Student> { /* ... */ }