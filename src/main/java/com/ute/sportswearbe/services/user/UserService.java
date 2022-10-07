package com.ute.sportswearbe.services.user;

import com.ute.sportswearbe.dtos.PasswordDto;
import com.ute.sportswearbe.dtos.user.UserCoreDto;
import com.ute.sportswearbe.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

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

    User updateUser(Principal principal, UserCoreDto dto);

    User updateAvatar(Principal principal, MultipartFile file);

    User changeStatus(String id);

    User changePassword(Principal principal, PasswordDto dto);
}
