package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "maPhuong")
    private String maPhuong;

    @Column(name = "tenPhuong")
    private String tenPhuong;

    @Column(name = "trangThai")
    private Boolean trangThai;

    @Column(name = "ngayXoa")
    private LocalDateTime ngayXoa;

//    {
//        "id": 1,
//            "tenPhuong": "Phường A",
//            "province": {
//        "id": 10,
//                "tenTinhThanh": "Thái Bình",
//                "trangThai": true
//    }
//    }

    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id")
    private Province province;

    //transient chỉ lưu trên ram, tắt prj là mất
    @Transient
    private String tenWard = "ward";
}
