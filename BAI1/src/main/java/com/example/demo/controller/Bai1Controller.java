package com.example.demo.controller;

import com.example.demo.model.Province;
import com.example.demo.model.Ward;
import com.example.demo.repository.ProvinceRepository;
import com.example.demo.repository.WardRepository;
import com.example.demo.service.Bai1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class Bai1Controller {
    @Autowired
    private Bai1Service service;

    @GetMapping("/province")
    public Page<Province> getAllProvince(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        int pageIndex = (page < 1) ? 0 : page - 1;

        return service.getAllProvince(PageRequest.of(pageIndex, size));
    }

    @GetMapping("/ward")
    public Page<Ward> getAllWard(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        int pageIndex = (page < 1) ? 0 : page - 1;

        return service.getAllWard(PageRequest.of(pageIndex, size));
    }

    @DeleteMapping("/province/delete/{id}")
    public void deleteProvince(@PathVariable("id") int id){
        service.deleteProvince(id);
    }

    @DeleteMapping("/ward/delete/{id}")
    public void deleteWard(@PathVariable("id") int id){
        service.deleteWard(id);
    }

    @GetMapping("/province/detail/{id}")
    public Province detailProvince(@PathVariable("id") int id){
        return service.detailProvince(id);
    }

    @GetMapping("/ward/detail/{id}")
    public Ward detailWard(@PathVariable("id") int id){
        return service.detailWard(id);
    }

    @PostMapping("/province/add")
    public ResponseEntity<String> AddProvince(@RequestBody Province province){
        try {
            service.addProvince(province);
            return ResponseEntity.ok("Thêm thành công");
        }catch (RuntimeException e){
            //nhận lấy cái thông báo lỗi bên service
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/ward/add")
    public ResponseEntity<String> AddWard(@RequestBody Ward ward){
        try {
            service.addWard(ward);
            return ResponseEntity.ok("Thêm thành công");
        }catch (RuntimeException e){
            //nhận lấy cái thông báo lỗi bên service
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/province/update")
    public ResponseEntity<String> UpdateProvince(@RequestBody Province province){
        try {
            service.updateProvince(province);
            return ResponseEntity.ok("Sửa thành công");
        }catch (RuntimeException e){
            //nhận lấy cái thông báo lỗi bên service
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/ward/update")
    public ResponseEntity<String> UpdateWard(@RequestBody Ward ward){
        try {
            service.updateWard(ward);
            return ResponseEntity.ok("Sửa thành công");
        }catch (RuntimeException e){
            //nhận lấy cái thông báo lỗi bên service
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/province/soft-delete")
    public List<Province> getAllDeleteProvince(){
        return service.getAllDeleteProvince();
    }

    @GetMapping("/ward/soft-delete")
    public List<Ward> getAllDeleteWard(){
        return service.getAllDeleteWard();
    }
}
