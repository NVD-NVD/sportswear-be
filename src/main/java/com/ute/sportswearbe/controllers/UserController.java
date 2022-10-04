package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.entities.User;
import com.ute.sportswearbe.services.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/8/2022
 * Time: 9:10 PM
 * Filename: UserController
 */
@RestController
@RequestMapping("/rest/users")
public class UserController {

    @Autowired
    private UserService userService;

    /***
    *  @author: NVD-NVD
    *  @param principal
    *  @return : User info
    */
    @ApiOperation(value = "Get user info by principal", notes = "For user")
    @GetMapping
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<User> getUserByPrincipal(Principal principal){
        return new ResponseEntity<>(userService.getUserByPrincipal(principal), HttpStatus.OK);
    }
    /***
    *  @author: NVD-NVD
    *  @param id: user id
    *  @return : user info
    */
    @ApiOperation(value = "Admin get user info", notes = "For ADMIN")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserByID(@PathVariable String id){
        return new ResponseEntity<>(userService.getUserByID(id), HttpStatus.OK);
    }
    /***
    *  @author: NVD-NVD
    *  @param :
    *  @return : List user
    */
    @ApiOperation(value = "Admin get all users", notes = "For ADMIN")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    /***
    *  @author: NVD-NVD
    *  @param :
    *  @return :
    */
    @ApiOperation(value = "Get user paging", notes = "For ADMIN")
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
}
