package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "maTinhThanh")
    private String maTinhThanh;

    @Column(name = "tenTinhThanh")
    private String tenTinhThanh;

    // File: Province.java
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Dòng này cực kỳ quan trọng khi dùng Fetch trả về JSON
    private List<Ward> wards;
}
