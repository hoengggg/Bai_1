package com.example.demo.repository;

import com.example.demo.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Integer> {
}
