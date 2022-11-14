package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.dtos.PasswordDto;
import com.ute.sportswearbe.dtos.user.UserCoreDto;
import com.ute.sportswearbe.entities.User;
import com.ute.sportswearbe.services.cloudinary.CloudinaryService;
import com.ute.sportswearbe.services.file.FilesStorageService;
import com.ute.sportswearbe.services.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get thông tin User bằng Token", notes = "User")
    @GetMapping
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<User> getUserByPrincipal(Principal principal){
        return new ResponseEntity<>(userService.getUserByPrincipal(principal), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin get thông tin User", notes = "Admin")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserByID(@PathVariable String id){
        return new ResponseEntity<>(userService.getUserByID(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin get tất cả User (không phân trang)", notes = "Admin")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get danh sách User có phân trang", notes = "Admin")
    @GetMapping("/paging")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<User>> getUserPaging(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "page", required = false, defaultValue = "${paging.default.page}") int page,
            @RequestParam(name = "size", required = false, defaultValue = "${paging.default.size}") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "timestamp") String column
    ){
        return new ResponseEntity<>(userService.getUserPaging(search, page, size, sort, column), HttpStatus.OK);
    }

    @ApiOperation(value = "Tạo mới một User", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<User> createNewUser(@RequestBody UserCoreDto dto) {
        return new ResponseEntity<>(userService.createNewUser(dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Tạo mới một Admin", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createNewAdmin")
    public ResponseEntity<User> createNewAdmin(@RequestBody UserCoreDto dto) {
        return new ResponseEntity<>(userService.createAdmin(dto), HttpStatus.OK);
    }

    @ApiOperation(value = "User cập nhập thông tin", notes = "User")
    @PreAuthorize("hasRole('MEMBER')")
    @PutMapping("/update/info")
    public ResponseEntity<User> updateUser(
            Principal principal, @RequestBody UserCoreDto dto) {
        return new ResponseEntity<>(userService.updateUser(principal, dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Update avatar", notes = "User")
    @PreAuthorize("hasRole('MEMBER')")
    @PutMapping("/update/avatar")
    public ResponseEntity<User> updateAvatar(
            Principal principal, @RequestParam(name = "file") MultipartFile file) {
        return new ResponseEntity<>(userService.updateAvatar(principal, file), HttpStatus.OK);
    }

    @ApiOperation(value = "Get avatar")
    @GetMapping("/avatar/{filename}")
    public ResponseEntity<?> getAvatar(@PathVariable("filename") String filename){
        return new ResponseEntity<>(userService.getAvatar(filename), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin thay đổi trạng thái kích hoạt tài khoản user", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> changeStatus(@PathVariable String id) {
        return new ResponseEntity<>(userService.changeStatus(id), HttpStatus.OK);
    }

    @ApiOperation(value = "User thay đổi mật khẩu", notes = "User")
    @PreAuthorize("hasRole('MEMBER')")
    @PutMapping(value = "/password")
    @ResponseBody
    public ResponseEntity<User> changePassword(
            Principal principal,
            @RequestBody PasswordDto dto){
        return new ResponseEntity<>(userService.changePassword(principal, dto) , HttpStatus.OK);
    }
}
