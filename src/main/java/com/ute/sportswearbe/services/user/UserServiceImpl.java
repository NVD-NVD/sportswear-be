package com.ute.sportswearbe.services.user;

import com.ute.sportswearbe.dtos.PasswordDto;
import com.ute.sportswearbe.dtos.user.UserCoreDto;
import com.ute.sportswearbe.entities.User;
import com.ute.sportswearbe.exceptions.InvalidException;
import com.ute.sportswearbe.exceptions.NotFoundException;
import com.ute.sportswearbe.repositories.UserRepository;
import com.ute.sportswearbe.services.cloudinary.CloudinaryService;
import com.ute.sportswearbe.utils.PageUtils;
import com.ute.sportswearbe.utils.enums.EnumRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        return getUserCoreByPhone(principal.getName());
    }

    @Override
    public Page<User> getUserPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return userRepository.getUserPaging(search, pageable);
    }

    @Override
    public User getUserByID(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User có id %s không tồn tại", id)));
    }

    @Override
    public User getUserCoreByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User getUserCoreByPhone(String phone) {
        return userRepository.getUserCoreByPhone(phone).orElse(null);
    }

    @Override
    public List<String> getRoles() {
        return Arrays.stream(EnumRole.values()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public User addNewUserCore(String fullName, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(fullName);
        user.setPassword(password);
        user.setRoles(Collections.singletonList(EnumRole.ROLE_MEMBER.name()));
        user.setEnable(true);
        userRepository.save(user);
        return user;
    }


    @Override
    public User createNewUser(UserCoreDto dto) {
        User user = convertUserCoreDto(dto);
        user.setCreatedOn(new Date());
        user.setUpdateOn(new Date());
        user.setRoles(Collections.singletonList(EnumRole.ROLE_MEMBER.name()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User createAdmin(UserCoreDto dto) {
        User user = convertUserCoreDto(dto);
        user.setCreatedOn(new Date());
        user.setUpdateOn(new Date());
        user.setRoles(Arrays.asList(EnumRole.ROLE_ADMIN.name(), EnumRole.ROLE_MEMBER.name()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(Principal principal, UserCoreDto dto) {
        User user = getUserByPrincipal(principal);
        if (user == null){
            throw new NotFoundException("Không tìm thấy user");
        }
        user.setUpdateOn(new Date());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        user.setName(dto.getName());
        user.setBirthday(dto.getBirthday());
        user.setGender(dto.getGender());
        user.setAddress(dto.getAddress());
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateAvatar(Principal principal, MultipartFile file) {
        User user = getUserByPrincipal(principal);
        if (user == null){
            throw new NotFoundException("Không tìm thấy user");
        }
        user.setAvatar(cloudinaryService.uploadFile(file));
        return userRepository.save(user);
    }

    @Override
    public User changeStatus(String id) {
        User user = getUserByID(id);
        user.setEnable(!user.isEnable());
        userRepository.save(user);
        return user;
    }

    @Override
    public User changePassword(Principal principal, PasswordDto dto) {
        User user = getUserByPrincipal(principal);
        if (user == null){
            throw new NotFoundException("Không tìm thấy user");
        }
        if (!passwordEncoder.matches(dto.getOldPass(), user.getPassword()))
            throw  new InvalidException("Password không đúng");
        user.setPassword(passwordEncoder.encode(dto.getNewPass()));
        userRepository.save(user);
        return user;
    }

    //    @Override
//    public User changePassword(String id, Principal principal, PasswordDto dto) {
//        return null;
//    }

    private User convertUserCoreDto(UserCoreDto dto) {
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPhone())) {
            throw new InvalidException("Phone đã không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Password không được bỏ trống");
        }

        User userCoreByEmail = getUserCoreByEmail(dto.getEmail());
        if (!ObjectUtils.isEmpty(userCoreByEmail)) {
            throw new InvalidException(String.format("Email %s đã được sử dụng", dto.getEmail()));
        }

        User userCoreByPhone = getUserCoreByPhone(dto.getPhone());
        if (!ObjectUtils.isEmpty(userCoreByPhone)){
            throw new InvalidException(String.format("Phone %s đã được sử dụng", dto.getPhone()));
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());

        user.setName(dto.getName());
        user.setBirthday(dto.getBirthday());
        user.setGender(dto.getGender());
        user.setAddress(dto.getAddress());

        user.setEnable(true);
        return user;
    }

}
