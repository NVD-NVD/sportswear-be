package com.ute.sportswearbe.services.user;

import com.ute.sportswearbe.dtos.user.UserCoreDto;
import com.ute.sportswearbe.entities.User;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/1/2022
 * Time: 4:13 PM
 * Filename: UserService
 */
public interface UserService {
    List<User> getAllUser();

    Page<User> getUserPaging(String search, int page, int size, String sort, String column);

    User getUserByID(String id);

    User getUserCoreByEmail(String email);

    User getUserCoreByPhone(String phone);

    User getUserByPrincipal(Principal principal);

    List<String> getRoles();

    User addNewUserCore(String fullName, String email, String password);

    User createNewUser(UserCoreDto dto);

    User createAdmin(UserCoreDto dto);

}
