package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "trangThai")
    private Boolean trangThai;

    @Column(name = "ngayXoa")
    private LocalDateTime ngayXoa;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Dòng này cực kỳ quan trọng khi dùng Fetch trả về JSON
    private List<Ward> wards;

    //transient chỉ lưu trên ram, tắt prj là mất
    @Transient
    private String tenProvince = "province";

    //phục vụ cho chức năng xóa mềm, cụ thể thì những dữ liệu nào có trạng thái là false thì sẽ lưu 1 nội dung
    //là "Tình này đã bị ẩn/ xóa" và nội dung đó sẽ đc lưu trong cái này
    //nhưng nếu tắt prj là lại mất nên khi chạy lại là nó sẽ lại chạy lại hàm kia để tìm dữ liệu false để hiển thi
    //nội dung kia
    @Transient
    private String tenProvinceDelete;
}
