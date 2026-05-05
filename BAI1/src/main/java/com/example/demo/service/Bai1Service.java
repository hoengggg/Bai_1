package com.example.demo.service;

import com.example.demo.model.Province;
import com.example.demo.model.Ward;
import com.example.demo.repository.ProvinceRepository;
import com.example.demo.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class Bai1Service {
    @Autowired
    private ProvinceRepository repoProvince;

    @Autowired
    private WardRepository repoWard;

    public Page<Province> getAllProvince(Pageable pageable){
        return repoProvince.findByTrangThai(true, pageable);
    }

    public Page<Ward> getAllWard(Pageable pageable){
        //lấy danh sách cả xã phường có trạng thái là true rồi cho vào biến wardPage
        Page<Ward> wardPage = repoWard.findByTrangThai(true, pageable);

        //lặp/ duyệt qua tất cả các dữ liệu trong biến wardPage
        wardPage.getContent().forEach(ward -> {
            //lấy tất cả các tỉnh có khóa ngoại các xã phường lấy đc ở trên
            if(ward.getProvince() != null){
                //nếu có tỉnh nào có trạng thái là false thì sẽ gắn cho tỉnh đó cái tenProvinceDelete với nội dung "Tỉnh này đã bị xóa/ ẩn"
                if(!Boolean.TRUE.equals(ward.getProvince().getTrangThai())){
                    //lấy tỉnh từ model province rồi lấy
                    //trạng thái của tỉnh lấy đc từ dữ liệu xã phường
                    //để kiểm tra true/false

                    //lấy tỉnh có trạng thái false và gắn cho nó cái tenProvinceDelete với nội dung "Tỉnh này đã bị xóa/ ẩn"
                    ward.getProvince().setTenProvinceDelete("Tỉnh này đã bị xóa/ ẩn");
                }
            }
        });
        return wardPage;
    }

    public void deleteProvince(int id){
        //tìm kiếm dựa theo id đc cung cấp, sau khi tìm đc thì lưu vào biến province
        Province province = repoProvince.findById(id).orElse(null);

        //nếu tìm thấy và lưu vào biến province thì sẽ thỏa mãn điều kiện province != null ở đây
        if(province != null){
            //thì sẽ set lại trạng thái thành false và đc lưu lại
            province.setTrangThai(false);
            province.setNgayXoa(LocalDateTime.now());
            repoProvince.save(province);
        }
    }

    public void deleteWard(int id){
        //tìm kiếm dựa theo id đc cung cấp, sau khi tìm đc thì lưu vào biến ward
        Ward ward = repoWard.findById(id).orElse(null);

        //nếu tìm thấy và lưu vào biến ward thì sẽ thỏa mãn điều kiện province != null ở đây
        if(ward != null){
            ward.setTrangThai(false);
            ward.setNgayXoa(LocalDateTime.now());
            repoWard.save(ward);
        }
    }

    public Province detailProvince(int id){
        return repoProvince.findById(id).orElse(null);
    }

    public Ward detailWard(int id){
        Ward ward =  repoWard.findById(id).orElse(null);

        if(ward != null){
            if(!Boolean.TRUE.equals(ward.getProvince().getTrangThai())){
                //lấy tỉnh từ model province rồi lấy
                //trạng thái của tỉnh lấy đc từ dữ liệu xã phường
                //để kiểm tra true/false

                //lấy tỉnh có trạng thái false và gắn cho nó cái tenProvinceDelete với nội dung "Tỉnh này đã bị xóa/ ẩn"
                ward.getProvince().setTenProvinceDelete("Tỉnh này đã bị xóa/ ẩn");
            }
        }
        return ward;
    }

    public void addProvince(Province p) {
        //tìm trong csdl xm có dữ liệu nào có mã tỉnh nào trùng vs cái của mik chọn ko
        //nếu có thì sẽ lấy dữ liệu của tất cả các cột của dữ liệu đó và lưu vào biến exis
        Province exist = repoProvince.findByMaTinhThanh(p.getMaTinhThanh());
        if(exist != null){
            throw new RuntimeException("Mã tỉnh thành này đã tồn tại");
        }

        //mặc định khi thêm trạng thái sẽ là 1 = true
        p.setTrangThai(true);
        repoProvince.save(p);
    }

    public void updateProvince(Province p) {
        //tìm trong db xem có dữ liệu nào có id giống vs id nhận đc từ fe ko, nếu có thì lưu vào biến existing
        Province existing = repoProvince.findById(p.getId()).orElse(null);

        //tìm trong csdl xm có dữ liệu nào có mã tỉnh nào trùng vs cái của mik chọn ko
        //nếu có thì sẽ lấy dữ liệu của tất cả các cột của dữ liệu đó và lưu vào biến exist
        Province exist = repoProvince.findByMaTinhThanh(p.getMaTinhThanh());

        //sau đó kiểm tra xem có tìm thấy ko, nếu có thì so sánh id của cái dữ liệu tìm đc đó vs id của cái mik đang định
        //sửa nếu trùng thì cho sửa còn ko hoặc ko thỏa mãn 1 trong 2 điều kiện hoặc cả 2 thì lỗi
        if(exist != null && exist.getId() != p.getId()){
            throw new RuntimeException("Mã tỉnh thành này đã tồn tại");
        }

        //sau khi qua đc cái trên và tìm đc dữ liệu cần sửa dựa theo id thì sẽ gắn dữ liệu mới vào thay thế dữ liệu cũ
        if (existing != null) {
            existing.setMaTinhThanh(p.getMaTinhThanh());
            existing.setTenTinhThanh(p.getTenTinhThanh());
            repoProvince.save(existing);
        }
    }

    public void addWard(Ward w) {
        //tìm trong csdl xm có dữ liệu nào có mã phường nào trùng vs cái của mik nhập ko
        //nếu có thì sẽ lấy dữ liệu của tất cả các cột của dữ liệu đó và lưu vào biến exis
        Ward exist = repoWard.findByMaPhuong(w.getMaPhuong());
        if(exist != null){
            throw new RuntimeException("Mã phường này đã tồn tại");
        }

        //nếu cbb chx đc chọn tỉnh thì báo lỗi
        if (w.getProvince() == null || w.getProvince().getId() == null) {
            throw new RuntimeException("Vui lòng chọn Tỉnh thành");
        }

        //tìm kiếm id, cụ thể là lấy id trog cbb và đưa sang đây để tìm trong bảng province xem trong bảng
        //province có id này ko, nếu có thì lưu vào trong biến province
        Province province = repoProvince.findById(w.getProvince().getId()).orElse(null);

        //== null tức là ko tìm đc nên ko có gì lưu vào province
        if(province == null){
            throw new RuntimeException("ID này ko tồn tại trong bảng province");
        }

        //nếu trạng thái của tỉnh là false thì ko chọn ddc (đề phòng trường hợp dù đã ẩn đi những dữ liệu có
        //trạng thái = false nhưng bằng cách nào đó vẫn hiển thị đc những dữ liệu đó)
        if(province.getTrangThai() == false){
            throw new RuntimeException("Tỉnh này đã bị ẩn đi, ko chọn đc");
        }

        //mặc định khi thêm trạng thái sẽ là 1 = true
        w.setTrangThai(true);

        // Khi thêm mới, JPA sẽ tự động tạo ID nếu w.getId() là null
        repoWard.save(w);
    }

    public void updateWard(Ward w) {
        // Tìm kiếm dữ liệu dựa theo id nhận đc từ fe, nếu có thì lưu vào biến existing
        Ward existing = repoWard.findById(w.getId()).orElse(null);

        //kiểm tra xem trong db mã phường mới nhập vào đã tồn tại chưa, nếu có rồi thì lưu vào biến exist
        Ward exist = repoWard.findByMaPhuong(w.getMaPhuong());

        //nếu != null tức là đã tìm đc và đc lưu vào exist thì sẽ báo là mã phường đã tồn tại
        if(exist != null && exist.getId() != w.getId()){
            throw new RuntimeException("Mã phường này đã tồn tại");
        }

        //nếu cbb chx đc chọn tỉnh thì báo lỗi
        if (w.getProvince() == null || w.getProvince().getId() == null) {
            throw new RuntimeException("Vui lòng chọn Tỉnh thành");
        }

        //tìm kiếm id, cụ thể là lấy id trog cbb và đưa sang đây để tìm trong bảng province xem trong bảng
        //province có id này ko, nếu có thì lưu vào trong biến province
        Province province = repoProvince.findById(w.getProvince().getId()).orElse(null);

        //== null tức là ko tìm đc nên ko có gì lưu vào province
        if(province == null){
            throw new RuntimeException("ID này ko tồn tại trong bảng province");
        }

        //nếu trạng thái của tỉnh là false thì ko chọn ddc (đề phòng trường hợp dù đã ẩn đi những dữ liệu có
        //trạng thái = false nhưng bằng cách nào đó vẫn hiển thị đc những dữ liệu đó)
        if(province.getTrangThai() == false){
            throw new RuntimeException("Tỉnh này đã bị ẩn đi, ko chọn đc");
        }

        //khi sửa sẽ gửi kèm id thì sau khi tìm kiếm dựa trên id nhập đc ở trên và có tìm đc thì sẽ lưu vào biến existing
        //thì nếu existing != null nghĩa là đã tìm đc và đc lưu vào exiting thì xuống đây sẽ bắt đầu thực hiện cập nhật
        //dữ liệu mới
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

    public List<Province> getAllDeleteProvince(){
        //hiển thị những dữ liệu có trạng thái là false
        return repoProvince.findByTrangThai(false);
    }

    public List<Ward> getAllDeleteWard(){
        //hiển thị những dữ liệu có trạng thái là false
        return repoWard.findByTrangThai(false);
    }
}
