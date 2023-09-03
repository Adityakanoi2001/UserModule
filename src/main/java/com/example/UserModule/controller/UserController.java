package com.example.UserModule.controller;


import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.example.UserModule.dto.*;
import com.example.UserModule.exceptions.AuthenticationFailException;
import com.example.UserModule.exceptions.CustomException;
import com.example.UserModule.helper.UserModuleApiPath;
import com.example.UserModule.service.serviceImpl.service.GeoIPLocationService;
import com.example.UserModule.service.serviceImpl.service.UserService;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api("Campus Connect User API's")
@RequestMapping(UserModuleApiPath.BASE_PATH)
@RestController
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  GeoIPLocationService geoIPLocationService;

  @Operation(description = "API ENDPOINT FOR SIGN-UP")
  @PostMapping(UserModuleApiPath.SIGN_UP)
  public ResponseEntity<SignInResponseDto> Signup(@RequestBody SignupDto signupDto) throws CustomException {
    log.warn("Invoking API for Sign-Up with UserName : {} and UserEmail : {}", signupDto.getFirstName(),
        signupDto.getEmail());
    return new ResponseEntity(userService.signUp(signupDto), HttpStatus.OK);
  }

  @Operation(description = "API ENDPOINT FOR SIGN-IN")
  @PostMapping(UserModuleApiPath.SIGN_IN)
  public ResponseEntity<SignInResponseDto> SignIn(@RequestBody SignInDto signInDto)
      throws CustomException, AuthenticationFailException {
    log.warn("Invoking API for Sign-In with UserEmail : {}", signInDto.getEmail());
    return new ResponseEntity<>(userService.signIn(signInDto), HttpStatus.OK);
  }

  @Operation(description = "API ENDPOINT FOR RESET_PASSWORD_LINK")
  @PostMapping(UserModuleApiPath.RESET_PASSWORD_LINK)
  public ResponseEntity<PasswordResetLinkDto> PasswordResetLink(@RequestParam String email)
      throws CustomException, AuthenticationFailException {
    log.warn("Invoking API for Password Reset Link with UserEmail : {}", email);
    return new ResponseEntity<>(userService.passwordResetLinkFunction(email), HttpStatus.OK);
  }

  @Operation(description = "API ENDPOINT FOR CHANGE OF PASSWORD")
  @PutMapping(UserModuleApiPath.UPDATE_PASSWORD)
  public ResponseEntity<PasswordChangeResponseDto> PasswordChange(@RequestBody PasswordChangeDto passwordChangeDto,
      HttpServletRequest request) {
    log.warn("Invoking API for Change of Password at Time : {}", new Date());
    PasswordChangeResponseDto passwordChangeResponseDto = userService.passwordChangeFunction(passwordChangeDto);
    return new ResponseEntity<>(passwordChangeResponseDto, HttpStatus.OK);
  }
}
