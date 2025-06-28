package com.leandrobaroni2103.functions_fac_sao_pedro.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import com.leandrobaroni2103.functions_fac_sao_pedro.dtos.admins.CreateAdminDto;
import com.leandrobaroni2103.functions_fac_sao_pedro.dtos.admins.UpdateEmailDto;
import com.leandrobaroni2103.functions_fac_sao_pedro.dtos.admins.UpdatePasswordDto;
import com.leandrobaroni2103.functions_fac_sao_pedro.entities.Admin;
import com.leandrobaroni2103.functions_fac_sao_pedro.enums.UserType;
import com.leandrobaroni2103.functions_fac_sao_pedro.exceptions.ResponseException;
import com.leandrobaroni2103.functions_fac_sao_pedro.services.AdminService;
import com.leandrobaroni2103.functions_fac_sao_pedro.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admins")
public class AdminController {
  @Autowired
  private AdminService adminService;

  @Autowired
  private AuthService authService;

  @PostMapping("/")
  public String createAdmin(@RequestBody CreateAdminDto createAdminDto) {
    try {
      String id = this.authService.createAuth(
              createAdminDto.getEmail(),
              createAdminDto.getPassword(),
              createAdminDto.getName(),
              createAdminDto.getActive()
      );

      UserType userType = UserType.admin;

      Admin admin = new Admin();
      admin.setActive(createAdminDto.getActive());
      admin.setEmail(createAdminDto.getEmail());
      admin.setName(createAdminDto.getName());
      admin.setRole(userType);
      admin.setId(id);

      this.adminService.set(admin);

      this.authService.setClaims(id, userType);

      return id;
    } catch (FirebaseAuthException | ExecutionException | InterruptedException e) {
      throw new RuntimeException(e.getMessage(), e.getCause());
    }
  }

  @GetMapping("")
  public List<Admin> getAdmins() throws ExecutionException, InterruptedException {
    return adminService.getAll();
  }

  @GetMapping("/{id}")
  public Admin getUser(@PathVariable String id) throws ExecutionException, InterruptedException {
    return this.adminService.getById(id);
  }

  @PostMapping("/update-password/{id}")
  public void updatePassword(@PathVariable String id, @RequestBody UpdatePasswordDto updatePasswordDto) {
    try {
      this.authService.updatePassword(id, updatePasswordDto.getPassword());
    } catch (FirebaseAuthException e) {
      throw new ResponseException(e.getMessage(), "@application/cannot-update-password", 500);
    }
  }

  @PostMapping("/update-email/{id}")
  public void updateEmail(@PathVariable String id, @RequestBody UpdateEmailDto updateEmailDto) {
    try {
      Admin admin = this.adminService.getById(id);

      if (admin.getEmail().equals(updateEmailDto.getEmail())) {
        throw new RuntimeException("@application/cannot-update-email");
      }

      this.authService.updateEmail(id, updateEmailDto.getEmail());

      admin.setEmail(updateEmailDto.getEmail());

      this.adminService.update(admin);
    } catch (FirebaseAuthException e) {
      throw new ResponseException(e.getMessage(), "@application/cannot-update-email", 500);
    } catch (ExecutionException | InterruptedException | RuntimeException e) {
      throw new ResponseException(e.getMessage(), "@application/failed-to-update-email", 500);
    }
  }
}
