package com.example.demo.repository;

import com.example.demo.model.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Integer> {
//    //phục vụ việc ko cho nhập mã tỉnh đã tồn tại (chức năng sửa)
//    Ward findByMaPhuong (String maPhuong);

    //phục vụ chức năng add
    Ward findByMaPhuongAndTrangThaiTrue(String maPhuong);

    //phục vụ hiển thị những dữ liệu có trạng thái = true
    Page<Ward> findByTrangThai (Boolean trangThai, Pageable pageable);

    //phục vụ hiển thị những dữ liệu có trạng thái = true
    List<Ward> findByTrangThai(Boolean trangThai);
}
