package com.online.store.online.store.test.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.online.store.online.store.test.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

