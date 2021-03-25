package com.example.demo.repository;

import com.example.demo.model.Supervisor;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.repository.CrudRepository;

public interface SupervisorRepository extends CrudRepository<Supervisor, Long> {
}
