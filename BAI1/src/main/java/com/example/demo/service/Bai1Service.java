package com.example.demo.service;

import com.example.demo.model.Province;
import com.example.demo.model.Ward;
import com.example.demo.repository.ProvinceRepository;
import com.example.demo.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Bai1Service {
    @Autowired
    private ProvinceRepository repoProvince;

    @Autowired
    private WardRepository repoWard;

    public Page<Province> getAllProvince(Pageable pageable){
        return repoProvince.findAll(pageable);
    }

    public Page<Ward> getAllWard(Pageable pageable){
        return repoWard.findAll(pageable);
    }

    public void deleteProvince(int id){
        repoProvince.deleteById(id);
    }

    public void deleteWard(int id){
        repoWard.deleteById(id);
    }

    public Province detailProvince(int id){
        return repoProvince.findById(id).orElse(null);
    }

    public Ward detailWard(int id){
        return repoWard.findById(id).orElse(null);
    }

    public void addProvince(Province p) {
        repoProvince.save(p);
    }

    public void updateProvince(Province p) {
        Province existing = repoProvince.findById(p.getId()).orElse(null);
        if (existing != null) {
            existing.setMaTinhThanh(p.getMaTinhThanh());
            existing.setTenTinhThanh(p.getTenTinhThanh());
            repoProvince.save(existing);
        }
    }

    public void addWard(Ward w) {
        // Khi thêm mới, JPA sẽ tự động tạo ID nếu w.getId() là null
        repoWard.save(w);
    }

    public void updateWard(Ward w) {
        // Tìm kiếm dữ liệu dựa theo id nhận đc từ fe
        Ward existing = repoWard.findById(w.getId()).orElse(null);
        if (existing != null) {
            //cập nhật dữ liệu mới
            existing.setMaPhuong(w.getMaPhuong());
            existing.setTenPhuong(w.getTenPhuong());
            //cập nhật tỉnh mới
            existing.setProvince(w.getProvince());
            //lưu dữ liệu mới sau khi cập nhật
            repoWard.save(existing);
        }
    }
}
