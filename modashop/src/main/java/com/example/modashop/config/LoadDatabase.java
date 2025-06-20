package com.example.modashop.config;

import com.example.modashop.entity.*;
import com.example.modashop.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Profile("init")  // Run once
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            ProductVariantRepository productVariantRepository
    ) {
        return args -> {
            // Thêm dữ liệu mẫu cho User (giữ nguyên)
            List<User> users = Arrays.asList(
                    new User(null, "user1@example.com", "1", "Nguyễn Văn A", "123 Đường ABC, Quận 1", "0901234567", false),
                    new User(null, "user2@example.com", "1", "Trần Thị B", "456 Đường XYZ, Quận 2", "0912345678", false),
                    new User(null, "manager@example.com", "1", "Phạm Quản Lý", "111 Đường QL, Quận 1", "0909876543", true),
                    new User(null, "admin@example.com", "1", "Admin", "555 Đường Admin, Quận 1", "0945678123", true)
            );
            userRepository.saveAll(users).forEach(user -> log.info("Preloaded User: " + user));

            // Rút gọn chỉ còn 4 danh mục chính
            List<Category> categories = Arrays.asList(
                    new Category(null, "Áo thun"),
                    new Category(null, "Quần jeans"),
                    new Category(null, "Áo khoác"),
                    new Category(null, "Đầm/Váy")
            );
            categoryRepository.saveAll(categories).forEach(category -> log.info("Preloaded Category: " + category));

            // Thêm nhiều sản phẩm cùng danh mục
            List<Product> products = Arrays.asList(
                    // Áo thun (4 sản phẩm)
                    new Product(null, "Áo thun trắng basic", "Áo thun cotton thoáng mát", 150000, "https://example.com/ao-thun-trang.jpg", 0, categories.get(0)),
                    new Product(null, "Áo thun cổ tròn", "Áo thun unisex nhiều màu", 180000, "https://example.com/ao-thun-co-tron.jpg", 10, categories.get(0)),
                    new Product(null, "Áo thun tay dài", "Áo thun giữ ấm mùa đông", 200000, "https://example.com/ao-thun-tay-dai.jpg", 5, categories.get(0)),
                    new Product(null, "Áo thun hình in", "Áo thun trẻ trung có hình in", 170000, "https://example.com/ao-thun-hinh-in.jpg", 15, categories.get(0)),

                    // Quần jeans (3 sản phẩm)
                    new Product(null, "Quần jeans slim đen", "Quần jeans ôm phong cách", 350000, "https://example.com/quan-jeans-den.jpg", 10, categories.get(1)),
                    new Product(null, "Quần jeans rách gối", "Quần jeans thời trang", 400000, "https://example.com/quan-jeans-rach.jpg", 5, categories.get(1)),
                    new Product(null, "Quần jeans baggy", "Quần jeans rộng thoải mái", 380000, "https://example.com/quan-jeans-baggy.jpg", 0, categories.get(1)),

                    // Áo khoác (3 sản phẩm)
                    new Product(null, "Áo khoác dù", "Áo khoác chống nước", 500000, "https://example.com/ao-khoac-du.jpg", 15, categories.get(2)),
                    new Product(null, "Áo khoác jean", "Áo khoác chất liệu jean", 450000, "https://example.com/ao-khoac-jean.jpg", 10, categories.get(2)),
                    new Product(null, "Áo khoác len", "Áo khoác ấm mùa đông", 550000, "https://example.com/ao-khoac-len.jpg", 5, categories.get(2)),

                    // Đầm/Váy (4 sản phẩm)
                    new Product(null, "Váy hoa nhí", "Váy dành cho mùa hè", 250000, "https://example.com/vay-hoa-nhi.jpg", 5, categories.get(3)),
                    new Product(null, "Đầm body suông", "Đầm dự tiệc thanh lịch", 400000, "https://example.com/dam-body-suong.jpg", 20, categories.get(3)),
                    new Product(null, "Váy công sở", "Váy đi làm lịch sự", 300000, "https://example.com/vay-cong-so.jpg", 10, categories.get(3)),
                    new Product(null, "Đầm maxi", "Đầm dài dạo phố", 350000, "https://example.com/dam-maxi.jpg", 15, categories.get(3))
            );
            productRepository.saveAll(products).forEach(product -> log.info("Preloaded Product: " + product));

            // Thêm nhiều biến thể cho từng sản phẩm
            List<ProductVariant> variants = Arrays.asList(
                    // Áo thun trắng basic
                    new ProductVariant(null, "S", "Trắng", 50, products.get(0)),
                    new ProductVariant(null, "M", "Trắng", 30, products.get(0)),
                    new ProductVariant(null, "L", "Trắng", 20, products.get(0)),

                    // Áo thun cổ tròn
                    new ProductVariant(null, "S", "Đen", 40, products.get(1)),
                    new ProductVariant(null, "M", "Xanh navy", 35, products.get(1)),

                    // Quần jeans slim đen
                    new ProductVariant(null, "28", "Đen", 25, products.get(4)),
                    new ProductVariant(null, "30", "Đen", 30, products.get(4)),
                    new ProductVariant(null, "32", "Đen", 15, products.get(4)),

                    // Áo khoác dù
                    new ProductVariant(null, "M", "Đen", 20, products.get(8)),
                    new ProductVariant(null, "L", "Xám", 15, products.get(8)),

                    // Váy hoa nhí
                    new ProductVariant(null, "S", "Hồng", 10, products.get(12)),
                    new ProductVariant(null, "M", "Hồng", 15, products.get(12)),
                    new ProductVariant(null, "S", "Xanh", 8, products.get(12)),

                    // Đầm body suông
                    new ProductVariant(null, "M", "Đen", 12, products.get(13)),
                    new ProductVariant(null, "L", "Đen", 8, products.get(13))
            );
            productVariantRepository.saveAll(variants).forEach(variant -> log.info("Preloaded Variant: " + variant));
        };
    }
}