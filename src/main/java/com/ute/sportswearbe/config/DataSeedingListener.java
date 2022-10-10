package com.ute.sportswearbe.config;

import com.ute.sportswearbe.entities.Category;
import com.ute.sportswearbe.entities.User;
import com.ute.sportswearbe.repositories.CategoryRepository;
import com.ute.sportswearbe.repositories.UserRepository;
import com.ute.sportswearbe.services.category.CategoryService;
import com.ute.sportswearbe.services.user.UserService;
import com.ute.sportswearbe.utils.enums.EnumGender;
import com.ute.sportswearbe.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DataSeedingListener implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0){
            User user = new User();
            user.setName("Admin");
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
        if (categoryRepository.count() == 0){
            List<Category> list = new ArrayList<>();
            if (categoryService.getCategoryByTitle("New") == null)
                list.add(new Category(null, "New", null, new Date(), new Date(), true));
            else
                System.out.println("Category NEW đã tồn tại");
            list.add(categoryService.createNewCategory("Nam"));

            categoryRepository.saveAll(list);
        }
    }
}
