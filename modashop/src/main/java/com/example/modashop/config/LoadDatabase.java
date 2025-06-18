// package com.example.modashop.config;
//
// import com.example.modashop.entity.*;
// import com.example.modashop.repository.*;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import java.util.Arrays;
// import java.util.List;
//
// @Configuration
// public class LoadDatabase {
//
//     private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//     @Bean
//     CommandLineRunner initDatabase(
//             UserRepository userRepository,
//             CategoryRepository categoryRepository,
//             ProductRepository productRepository,
//             ProductVariantRepository productVariantRepository
//     ) {
//         return args -> {
//             // Thêm dữ liệu mẫu cho User (gấp đôi)
//             List<User> users = Arrays.asList(
//                     new User(null, "user1@example.com", "1", "Nguyễn Văn A", "123 Đường ABC, Quận 1", "0901234567", false),
//                     new User(null, "user2@example.com", "1", "Trần Thị B", "456 Đường XYZ, Quận 2", "0912345678", false),
//                     new User(null, "user3@example.com", "1", "Lê Văn C", "789 Đường DEF, Quận 3", "0987654321", false),
//                     new User(null, "manager@example.com", "1", "Phạm Quản Lý", "111 Đường QL, Quận 1", "0909876543", true),
//                     new User(null, "user4@example.com", "1", "Hoàng Thị D", "222 Đường MNO, Quận 5", "0978123456", false),
//                     new User(null, "user5@example.com", "1", "Võ Văn E", "333 Đường GHI, Quận 4", "0965432198", false),
//                     new User(null, "user6@example.com", "1", "Đặng Thị F", "444 Đường KLM, Quận 7", "0956781234", false),
//                     new User(null, "admin@example.com", "1", "Admin", "555 Đường Admin, Quận 1", "0945678123", true)
//             );
//             userRepository.saveAll(users).forEach(user -> log.info("Preloaded User: " + user));
//
//             // Thêm dữ liệu mẫu cho Category (gấp đôi)
//             List<Category> categories = Arrays.asList(
//                     new Category(null, "Áo thun"),
//                     new Category(null, "Quần jeans"),
//                     new Category(null, "Váy"),
//                     new Category(null, "Áo khoác"),
//                     new Category(null, "Áo sơ mi"),
//                     new Category(null, "Quần short"),
//                     new Category(null, "Đầm"),
//                     new Category(null, "Áo len")
//             );
//             categoryRepository.saveAll(categories).forEach(category -> log.info("Preloaded Category: " + category));
//
//             // Thêm dữ liệu mẫu cho Product (gấp đôi, liên kết với Category)
//             List<Product> products = Arrays.asList(
//                     new Product(null, "Áo thun trắng", "Áo thun cotton thoáng mát", 150000, "https://example.com/ao-thun-trang.jpg", 0, categories.get(0)),
//                     new Product(null, "Quần jeans đen", "Quần jeans slim fit", 350000, "https://example.com/quan-jeans-den.jpg", 10, categories.get(1)),
//                     new Product(null, "Váy hoa nhí", "Váy dành cho mùa hè", 250000, "https://example.com/vay-hoa-nhi.jpg", 5, categories.get(2)),
//                     new Product(null, "Áo khoác dù", "Áo khoác chống nước", 500000, "https://example.com/ao-khoac-du.jpg", 15, categories.get(3)),
//                     new Product(null, "Áo sơ mi kẻ caro", "Áo sơ mi nam phong cách", 200000, "https://example.com/ao-so-mi-caro.jpg", 5, categories.get(4)),
//                     new Product(null, "Quần short kaki", "Quần short nam thoải mái", 180000, "https://example.com/quan-short-kaki.jpg", 0, categories.get(5)),
//                     new Product(null, "Đầm body suông", "Đầm nữ dự tiệc", 400000, "https://example.com/dam-body-suong.jpg", 20, categories.get(6)),
//                     new Product(null, "Áo len cổ lọ", "Áo len ấm áp mùa đông", 300000, "https://example.com/ao-len-co-lo.jpg", 10, categories.get(7))
//             );
//             productRepository.saveAll(products).forEach(product -> log.info("Preloaded Product: " + product));
//
//             // Thêm dữ liệu mẫu cho ProductVariant (gấp đôi, liên kết với Product)
//             List<ProductVariant> variants = Arrays.asList(
//                     new ProductVariant(null, "S", "Trắng", 50, products.get(0)),
//                     new ProductVariant(null, "M", "Trắng", 30, products.get(0)),
//                     new ProductVariant(null, "L", "Đen", 20, products.get(1)),
//                     new ProductVariant(null, "M", "Xanh", 40, products.get(2)),
//                     new ProductVariant(null, "XL", "Đen", 15, products.get(3)),
//                     new ProductVariant(null, "S", "Hồng", 25, products.get(4)),
//                     new ProductVariant(null, "M", "Xám", 35, products.get(5)),
//                     new ProductVariant(null, "L", "Be", 10, products.get(6)),
//                     new ProductVariant(null, "S", "Đỏ", 20, products.get(7)),
//                     new ProductVariant(null, "M", "Xanh navy", 30, products.get(0)),
//                     new ProductVariant(null, "L", "Vàng", 15, products.get(1)),
//                     new ProductVariant(null, "XL", "Trắng", 5, products.get(2))
//             );
//             productVariantRepository.saveAll(variants).forEach(variant -> log.info("Preloaded Variant: " + variant));
//         };
//     }
// }