package com.leandrobaroni2103.functions_fac_sao_pedro.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.leandrobaroni2103.functions_fac_sao_pedro.enums.UserType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
  public String createAuth(String email, String password, String name, Boolean isEnabled) throws FirebaseAuthException {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    UserRecord.CreateRequest request = new UserRecord.CreateRequest();

    request.setEmail(email);
    request.setPassword(password);
    request.setDisplayName(name);
    request.setDisabled(!isEnabled);

    UserRecord record = auth.createUser(request);
    return record.getUid();
  }

  public void updatePassword(String uid, String password) throws FirebaseAuthException {
    FirebaseAuth.getInstance().updateUser(new UserRecord.UpdateRequest(uid).setPassword(password));
  }

  public void updateEmail(String uid, String email) throws FirebaseAuthException {
    FirebaseAuth.getInstance().updateUser(new UserRecord.UpdateRequest(uid).setEmail(email));
  }

  public void setClaims(String uid, UserType userType) throws FirebaseAuthException {
    Map<String, Object> claims = new HashMap<>();
    claims.put(userType.toString(), true);
    FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
  }
}
