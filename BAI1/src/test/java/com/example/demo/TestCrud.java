package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestCrud {
    WebDriver webDriver;

    @BeforeClass //chạy một lần trước khi chạy các hàm test bên dưới giúp mở trình duyệt
    //beforeclass thì sẽ mở 1 lần và dùng chung giao diện cho tất cả các hàm test còn
    //beforemethod thì cứ mỗi 1 hàm test là 1 lần mở giao diện riêng
    public void setUp(){
        // 1. Phải setup driver TRƯỚC
        //tự động tải xuống và cấu hình, nó sẽ tự check phiên bản chrome trên máy và tải driver phù hợp về
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        // 2. Sau đó mới khởi tạo trình duyệt
        //khởi tạo 1 cửa sổ trình duyện chrome mới, chắc là để mở cửa sổ
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();   //phóng to cửa số đấy lơn hết cỡ

        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));  //tgian timeout cho toàn bộ code trong file
        //chỉ cần là có gọi đến webDriver là sẽ vậy

        // 3. Truy cập link (Nhớ thêm http://)
        webDriver.get("http://localhost:8080/index.html");
    }

    @Test
    public void testCrud() throws InterruptedException {
        String tenTinhMoi = "lam thu auto test";

        //Thêm tỉnh mới
        webDriver.findElement(By.cssSelector("#maTinh")).sendKeys("Automation Test5");
        webDriver.findElement(By.cssSelector("#tenTinh")).sendKeys(tenTinhMoi);
        webDriver.findElement(By.cssSelector("#addProvince")).click();

        try {
            Thread.sleep(1000);
            // Chấp nhận Alert (Nhấn OK)
            webDriver.switchTo().alert().accept();
            System.out.println("Đã đóng Alert thành công!");
        } catch (Exception e) {
            Assert.fail("ko dong dc alert hoac do alert ko xuat hien"); //chỉ cần ko đóng đc alert hoặc
            //alert ko xuất hiện là sẽ báo lỗi
        }

        //phân trang tìm đến khi thấy dòng có nội dung giống trong nội dung của tenTinhMoi
        boolean foundAddProvince = false;
        while (!foundAddProvince) {
            try {
                // Thử tìm xem tên tỉnh có trong bảng hiện tại không
                webDriver.findElement(By.xpath("//table[@id='table-tinh']//td[text()='" + tenTinhMoi + "']"));
                foundAddProvince = true; //nếu tìm thấy thì chuyển found thành true và hiện tbao
                System.out.println("Đã tìm thấy tỉnh ở trang hiện tại!");
            } catch (Exception e) {
                // Nếu không thấy, tìm nút Next của bảng Tỉnh
                WebElement btnNext = webDriver.findElement(By.id("btnNextProvince"));
                if (btnNext.isEnabled()) {
                    btnNext.click();
                    Thread.sleep(2000); // Chờ load dữ liệu trang mới
                } else {
                    System.out.println("Không tìm thấy tỉnh sau khi đã lật hết các trang!");
                    break;
                }
            }
        }

        //Xem chi tiết
        WebElement btnDetailProvince = webDriver.findElement(By.xpath("//table[@id='table-tinh']//td[text()='"+ tenTinhMoi +"']/following-sibling::td/button[text()='Xem']"));
        btnDetailProvince.click();
        Thread.sleep(2000);

        String tenTinhUpdate = "Tinh moi moi";
        webDriver.findElement(By.cssSelector("#maTinh")).clear();
        webDriver.findElement(By.cssSelector("#maTinh")).sendKeys("moi moi moi");
        webDriver.findElement(By.cssSelector("#tenTinh")).clear();
        webDriver.findElement(By.cssSelector("#tenTinh")).sendKeys(tenTinhUpdate);
        webDriver.findElement(By.cssSelector("#suaTinhThanh")).click();

        try {
            Thread.sleep(1000);
            // Chấp nhận Alert (Nhấn OK)
            webDriver.switchTo().alert().accept();
            System.out.println("Đã đóng Alert thành công!");
        } catch (Exception e) {
            Assert.fail("ko dong dc alert hoac do alert ko xuat hien"); //chỉ cần ko đóng đc alert hoặc
            //alert ko xuất hiện là sẽ báo lỗi
        }

        Thread.sleep(2000);

        boolean foundDetailProvince = false;
        while (!foundDetailProvince) {
            try {
                // Thử tìm xem tên tỉnh có trong bảng hiện tại không
                webDriver.findElement(By.xpath("//table[@id='table-tinh']//td[text()='" + tenTinhUpdate + "']"));
                foundDetailProvince = true; //nếu tìm thấy thì chuyển found thành true và hiện tbao
                System.out.println("Đã tìm thấy tỉnh ở trang hiện tại!");
            } catch (Exception e) {
                // Nếu không thấy, tìm nút Next của bảng Tỉnh
                WebElement btnNext = webDriver.findElement(By.id("btnNextProvince"));
                if (btnNext.isEnabled()) {
                    btnNext.click();
                    Thread.sleep(2000); // Chờ load dữ liệu trang mới
                } else {
                    System.out.println("Không tìm thấy tỉnh sau khi đã lật hết các trang!");
                    break;
                }
            }
        }

        Thread.sleep(6000);

        String tenXaPhuongMoi = "auto test";
        webDriver.findElement(By.cssSelector("#maPhuong")).sendKeys("AT");
        webDriver.findElement(By.cssSelector("#tenPhuong")).sendKeys(tenXaPhuongMoi);
        //cách 1 để chọn trong cbb
        webDriver.findElement(By.xpath("//option[text()='Tinh moi moi']")).click();
        webDriver.findElement(By.cssSelector("#addWard")).click();


        Thread.sleep(5000);
        try {
            // Chờ một chút cho alert hiện lên
            Thread.sleep(1000);
            // Chấp nhận Alert (Nhấn OK)
            webDriver.switchTo().alert().accept();
            System.out.println("Đã đóng Alert thành công!");
        } catch (Exception e) {
            Assert.fail("ko dong dc alert hoac do alert ko xuat hien"); //chỉ cần ko đóng đc alert hoặc
            //alert ko xuất hiện là sẽ báo lỗi
        }

        boolean foundAddWard = false;
        while (!foundAddWard) {
            try {
                // Thử tìm xem tên tỉnh có trong bảng hiện tại không
                webDriver.findElement(By.xpath("//table[@id='table-xa']//td[text()='" + tenXaPhuongMoi + "']"));
                foundAddWard = true;
                System.out.println("Đã tìm thấy xã ở trang hiện tại!");
            } catch (Exception e) {
                // Nếu không thấy, tìm nút Next của bảng Tỉnh
                WebElement btnNext = webDriver.findElement(By.id("btnNextWard"));
                if (btnNext.isEnabled()) {
                    btnNext.click();
                    Thread.sleep(2000); // Chờ load dữ liệu trang mới
                } else {
                    System.out.println("Không tìm thấy xã sau khi đã lật hết các trang!");
                    break;
                }
            }
        }

        //Xem chi tiết
        WebElement btnDetailWard = webDriver.findElement(By.xpath("//table[@id='table-xa']//td[text()='"+ tenXaPhuongMoi +"']/following-sibling::td/button[text()='Xem']"));
        btnDetailWard.click();
        Thread.sleep(2000);

        String tenXaPhuongUpdate = "Update ten xa phuong";
        webDriver.findElement(By.cssSelector("#maPhuong")).clear();
        webDriver.findElement(By.cssSelector("#maPhuong")).sendKeys("xa phuong moi moi");
        webDriver.findElement(By.cssSelector("#tenPhuong")).clear();
        webDriver.findElement(By.cssSelector("#tenPhuong")).sendKeys(tenXaPhuongUpdate);
        webDriver.findElement(By.cssSelector("#updateWard")).click();

        try {
            Thread.sleep(1000);
            // Chấp nhận Alert (Nhấn OK)
            webDriver.switchTo().alert().accept();
            System.out.println("Đã đóng Alert thành công!");
        } catch (Exception e) {
            System.out.println("Không thấy Alert hoặc có lỗi khi đóng Alert.");
        }

        boolean foundDetailWard = false;
        while (!foundDetailWard) {
            try {
                // Thử tìm xem tên tỉnh có trong bảng hiện tại không
                webDriver.findElement(By.xpath("//table[@id='table-xa']//td[text()='" + tenXaPhuongUpdate + "']"));
                foundDetailWard = true; //nếu tìm thấy thì chuyển found thành true và hiện tbao
                System.out.println("Đã tìm thấy tỉnh ở trang hiện tại!");
            } catch (Exception e) {
                // Nếu không thấy, tìm nút Next của bảng Tỉnh
                WebElement btnNext = webDriver.findElement(By.id("btnNextWard"));
                if (btnNext.isEnabled()) {
                    btnNext.click();
                    Thread.sleep(2000); // Chờ load dữ liệu trang mới
                } else {
                    System.out.println("Không tìm thấy tỉnh sau khi đã lật hết các trang!");
                    break;
                }
            }
        }

        WebElement btnDeleteWard = webDriver.findElement(By.xpath("//table[@id='table-xa']//td[text()='" + tenXaPhuongUpdate + "']/following-sibling::td/button[text()='Xóa']"));
        btnDeleteWard.click();
        Thread.sleep(1000);

        boolean foundProvince = false;
        while (!foundProvince) {
            try {
                // Thử tìm xem tên tỉnh có trong bảng hiện tại không
                webDriver.findElement(By.xpath("//table[@id='table-tinh']//td[text()='" + tenTinhUpdate + "']"));
                foundProvince = true;
                System.out.println("Đã tìm thấy tỉnh ở trang hiện tại!");
            } catch (Exception e) {
                // Nếu không thấy, tìm nút Next của bảng Tỉnh
                WebElement btnNext = webDriver.findElement(By.id("btnNextProvince"));
                if (btnNext.isEnabled()) {
                    btnNext.click();
                    Thread.sleep(2000); // Chờ load dữ liệu trang mới
                } else {
                    System.out.println("Không tìm thấy tỉnh sau khi đã lật hết các trang!");
                    break;
                }
            }
        }

        WebElement btnDeleteProvince = webDriver.findElement(By.xpath("//table[@id='table-tinh']//td[text()='" + tenTinhUpdate + "']/following-sibling::td/button[text()='Xóa']"));
        btnDeleteProvince.click();
        Thread.sleep(600);
    }

    @Test
    public void TestExist() throws InterruptedException{
        webDriver.findElement(By.cssSelector("#maTinh")).sendKeys("TPHCM");
        webDriver.findElement(By.cssSelector("#tenTinh")).sendKeys("Ho Chi Minh City");
        webDriver.findElement(By.cssSelector("#addProvince")).click();

        try {
            Thread.sleep(1000);
            // Chấp nhận Alert (Nhấn OK)
            webDriver.switchTo().alert().accept();
            System.out.println("Da don alert!");
        } catch (Exception e) {
            Assert.fail("ko dong dc alert hoac do alert ko xuat hien"); //chỉ cần ko đóng đc alert hoặc
            //alert ko xuất hiện là sẽ báo lỗi
        }


        Thread.sleep(2000);

        //Xem chi tiết
        WebElement btnDetailProvince = webDriver.findElement(By.xpath("//table[@id='table-tinh']//td[text()='Thành phố Hải Phòng']/following-sibling::td/button[text()='Xem']"));
        btnDetailProvince.click();
        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("#maTinh")).clear();
        webDriver.findElement(By.cssSelector("#maTinh")).sendKeys("TPHCM");
        webDriver.findElement(By.cssSelector("#suaTinhThanh")).click();

        try {
            Thread.sleep(1000);
            // Chấp nhận Alert (Nhấn OK)
            webDriver.switchTo().alert().accept();
            System.out.println("Da don alert!");
        } catch (Exception e) {
            Assert.fail("ko dong dc alert hoac do alert ko xuat hien"); //chỉ cần ko đóng đc alert hoặc
            //alert ko xuất hiện là sẽ báo lỗi
        }

        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#maPhuong")).sendKeys("P_MYDINH");
        webDriver.findElement(By.cssSelector("#tenPhuong")).sendKeys("My Dinh Ward");
        //cách 2 để chọn trong cbb
        WebElement cbb = webDriver.findElement(By.cssSelector("#select-province"));
        Select select = new Select(cbb);  //select này chọn select của selenium

        select.selectByVisibleText("Thành phố Hồ Chí Minh 2");

        webDriver.findElement(By.cssSelector("#addWard")).click();

        try {
            Thread.sleep(1000);
            // Chấp nhận Alert (Nhấn OK)
            webDriver.switchTo().alert().accept();
            System.out.println("Da don alert!");
        } catch (Exception e) {
            Assert.fail("ko dong dc alert hoac do alert ko xuat hien"); //chỉ cần ko đóng đc alert hoặc
            //alert ko xuất hiện là sẽ báo lỗi
        }


        //Xem chi tiết
        WebElement btnDetailWard = webDriver.findElement(By.xpath("//table[@id='table-xa']//td[text()='P_HANGTRONG']/following-sibling::td/button[text()='Xem']"));
        btnDetailWard.click();
        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("#maPhuong")).clear();
        webDriver.findElement(By.cssSelector("#maPhuong")).sendKeys("P_MYDINH");
        webDriver.findElement(By.cssSelector("#updateWard")).click();

        Thread.sleep(3000);

        try {
            Thread.sleep(1000);
            // Chấp nhận Alert (Nhấn OK)
            webDriver.switchTo().alert().accept();
            System.out.println("Da don alert!");
        } catch (Exception e) {
            Assert.fail("ko dong dc alert hoac do alert ko xuat hien"); //chỉ cần ko đóng đc alert hoặc
            //alert ko xuất hiện là sẽ báo lỗi
        }
    }

    @AfterClass
    public void tearDown(){
        webDriver.quit();
    }
}
