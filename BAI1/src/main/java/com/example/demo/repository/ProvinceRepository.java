package com.example.demo.repository;

import com.example.demo.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
}
