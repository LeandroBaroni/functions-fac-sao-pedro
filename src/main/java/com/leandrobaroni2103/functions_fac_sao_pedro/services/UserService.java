package com.leandrobaroni2103.functions_fac_sao_pedro.services;

import com.leandrobaroni2103.functions_fac_sao_pedro.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends FirestoreService<User> {
  protected UserService() {
    super("users", User.class);
  }
}
