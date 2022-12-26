package com.ute.sportswearbe.config;

import com.ute.sportswearbe.entities.Category;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.entities.User;
import com.ute.sportswearbe.repositories.CategoryRepository;
import com.ute.sportswearbe.repositories.UserRepository;
import com.ute.sportswearbe.services.category.CategoryService;
import com.ute.sportswearbe.services.product.ProductService;
import com.ute.sportswearbe.services.user.UserService;
import com.ute.sportswearbe.utils.enums.EnumGender;
import com.ute.sportswearbe.utils.enums.EnumRole;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataSeedingListener implements CommandLineRunner {
    @Value("${host}")
    private String host;
    @Value("${default.avatar}")
    private String avatar;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
//        userRepository.deleteAll();
        if (userRepository.count() == 0){
            User user = new User();
            user.setName("Datng");
            user.setEmail("zerodev247@gmail.com");
            user.setPassword(passwordEncoder.encode("12345678"));
            user.setRoles(Arrays.asList(EnumRole.ROLE_ADMIN.name(), EnumRole.ROLE_MEMBER.name()));
            user.setPhone("0989542812");
            user.setGender(EnumGender.Male.name());
            user.setBirthday(new SimpleDateFormat("dd-MM-yyyy").parse("20-03-1997"));
            user.setCreatedOn(new Date());
            user.setUpdateOn(new Date());
            userRepository.save(user);
        }
//        if (userRepository.count() == 0){
//            User user = new User();
//            user.setName("Admin");
//            user.setEmail("zerodev247@gmail.com");
//            user.setPassword(passwordEncoder.encode("12345678"));
//            user.setRoles(Arrays.asList(EnumRole.ROLE_ADMIN.name(), EnumRole.ROLE_MEMBER.name()));
//            user.setPhone("0989542812");
//            user.setGender(EnumGender.Male.name());
//            user.setBirthday(new SimpleDateFormat("dd-MM-yyyy").parse("20-03-1997"));
//            user.setCreatedOn(new Date());
//            user.setUpdateOn(new Date());
//            userRepository.save(user);
//        } else{
//            List<User> users = userService.getAllUser();
//            Iterator<User> iterator = users.iterator();
//            while (iterator.hasNext()){
//                User user = iterator.next();
//                user.setAvatar(host+"/rest/image/avatar/"+avatar);
//                userService.save(user);
//            }
//        }
////        if (productService.getAllProduct().size() > 0){
////            List<Product> products = productService.getAllProduct();
////            Iterator<Product> iterator = products.iterator();
////            while (iterator.hasNext()){
////                Product product = iterator.next();
////                List<String> images = product.getImages()
////                                        .stream().map(e ->{
////                                            return host+"/rest/image/"+e;
////                        }).collect(Collectors.toList());
////                System.out.printf(product.getId());
////                images.forEach(e -> System.out.printf(" " +e));
////            }
////        }
//        if (categoryRepository.count() == 0){
//            List<Category> list = new ArrayList<>();
//            if (categoryService.getCategoryByTitle("New") == null)
//                list.add(new Category(null, "New", null, new Date(), new Date(), true));
//            else
//                System.out.println("Category NEW đã tồn tại");
//            list.add(categoryService.createNewCategory("Nam"));
//
//            categoryRepository.saveAll(list);
//        }
    }

}
