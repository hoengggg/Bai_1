package com.example.demo.repository;

import com.example.demo.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    //phục vụ việc ko cho nhập mã tỉnh đã tồn tại
    Province findByMaTinhThanh(String maTinh);

    //phục vụ hiển thị những dữ liệu có trạng thái = true
    Page<Province> findByTrangThai(Boolean trangThai, Pageable pageable);

    //phục vụ hiển thị những dữ liệu có trạng thái = true
    List<Province> findByTrangThai(Boolean trangThai);
}
