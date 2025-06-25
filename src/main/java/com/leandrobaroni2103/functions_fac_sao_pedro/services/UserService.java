package com.leandrobaroni2103.functions_fac_sao_pedro.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.leandrobaroni2103.functions_fac_sao_pedro.entities.User;
import com.leandrobaroni2103.functions_fac_sao_pedro.utils.Cloner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
  private static final String COLLECTION_NAME = "users";

  public String add(User user) throws ExecutionException, InterruptedException {

    User cloneObject = Cloner.cloneObject(user);

    Firestore dbFirestore = FirestoreClient.getFirestore();

    ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document().set(cloneObject);

    return collectionApiFuture.get().getUpdateTime().toString();
  }

  public String update(User user) throws ExecutionException, CancellationException, InterruptedException {

    String id = user.getId();

    User cloneObject = Cloner.cloneObject(user);

    Firestore dbFirestore = FirestoreClient.getFirestore();

    cloneObject.setUpdatedAt(new Date());

    ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).set(cloneObject);

    return collectionApiFuture.get().getUpdateTime().toString();
  }

  public User getById(String id) throws ExecutionException, InterruptedException {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);

    System.out.println(documentReference.getId());

    ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();

    DocumentSnapshot documentSnapshot = apiFuture.get();

    User user = null;

    if(documentSnapshot.exists()){
      user = documentSnapshot.toObject(User.class);
      return user;
    }

    return user;
  }

  public void delete(String id) {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    dbFirestore.collection(COLLECTION_NAME).document(id).delete();
  }

  public List<User> getAll() throws ExecutionException, InterruptedException {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
    Iterator<DocumentReference> iterator = documentReference.iterator();

    List<User> users = new ArrayList<>();
    User user;

    while(iterator.hasNext()){
      DocumentReference doc = iterator.next();
      ApiFuture<DocumentSnapshot> future = doc.get();
      DocumentSnapshot document = future.get();
      user = document.toObject(User.class);
      users.add(user);
    }

    return users;
  }
}
