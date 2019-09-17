package com.registration.controller;

import com.registration.dto.UserBO;
import com.registration.model.SearchResultDTO;
import com.registration.model.User;
import com.registration.service.UserRegistrationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserRegistrationController {

    @Autowired
    UserRegistrationService regsitrationService;

    @Resource(name = "userMapper")
    Mapper dozerBeanMapper;

    @ApiOperation(value="get user detail based on user id", notes="userId is numeric")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "User Not found!!!") })
    @GetMapping("/user/{userId}")
    public UserBO getUser(
            @ApiParam(value = "Request with long user Id", required = true) @PathVariable("userId") Long userId) {
        return dozerBeanMapper.map(regsitrationService.getUser(userId), UserBO.class);
    }

    @ApiOperation(value="get users based on query parameter", notes="columns parameter should be separated by comma. valid columns are : " +
            "firstName, lastName, email, gender or telNum", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "User not found!!!") })
    @GetMapping("/users")
    public List<SearchResultDTO> getUsers(
            @ApiParam(value="column for which data are needed in comma separated, ex: firstName,lastName", example = "firstName,lastName") @RequestParam(required = false) List<String> columns,
            @ApiParam(value="page number for which data are needed, starting with 0", example = "0") @RequestParam(required = false) Integer pageNum,
            @ApiParam(value="records per page", example = "10") @RequestParam(required = false) Integer recPerPage) {
        if(CollectionUtils.isEmpty(columns)){
            columns = new ArrayList<>();
        }
        if(pageNum == null){
            pageNum = 0;
        }
        if(recPerPage == null){
            recPerPage = 0;
        }
        return regsitrationService.getUsers(columns,pageNum,recPerPage);
    }

    @ApiOperation(value="Provide data to create user through request body", notes = "This operation creates user", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "User Created Successfully"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "User not found!!!") })
    @PostMapping(path = "/createuser", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity createUser(
            @ApiParam(value = "Request with user details", required = true) @Valid @RequestBody UserBO user,
            Errors errors){
        if (errors.hasErrors()) {
            StringBuilder result = new StringBuilder();
            // get all errors
            result.append(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(" , ")));

            return ResponseEntity.badRequest().body(result);
        }

        User createdUser = regsitrationService.saveUser(dozerBeanMapper.map(user, User.class));
        return ResponseEntity.ok(createdUser);
    }
}
