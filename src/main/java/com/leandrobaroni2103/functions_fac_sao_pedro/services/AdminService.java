package com.leandrobaroni2103.functions_fac_sao_pedro.services;

import com.leandrobaroni2103.functions_fac_sao_pedro.entities.Admin;
import org.springframework.stereotype.Service;

@Service
//public class AdminService {
//  private static final String COLLECTION_NAME = "admins";
//
//  public String add(Admin admin) throws ExecutionException, InterruptedException {
//
//    Admin cloneObject = Cloner.cloneObject(admin);
//
//    String id = cloneObject.getId();
//
//    Firestore dbFirestore = FirestoreClient.getFirestore();
//
//    ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).set(cloneObject);
//
//    return collectionApiFuture.get().getUpdateTime().toString();
//  }
//
//  public String update(Admin admin) throws ExecutionException, CancellationException, InterruptedException {
//
//    String id = admin.getId();
//
//    Admin cloneObject = Cloner.cloneObject(admin);
//
//    Firestore dbFirestore = FirestoreClient.getFirestore();
//
//    cloneObject.setUpdatedAt(new Date());
//
//    ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(id).set(cloneObject);
//
//    return collectionApiFuture.get().getUpdateTime().toString();
//  }
//
//  public Admin getById(String id) throws ExecutionException, InterruptedException {
//    Firestore dbFirestore = FirestoreClient.getFirestore();
//
//    DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
//
//    System.out.println(documentReference.getId());
//
//    return Cloner.toObject(documentReference, Admin.class);
//  }
//
//  public void delete(String id) {
//    Firestore dbFirestore = FirestoreClient.getFirestore();
//
//    dbFirestore.collection(COLLECTION_NAME).document(id).delete();
//  }
//
//  public List<Admin> getAll() throws ExecutionException, InterruptedException {
//    Firestore dbFirestore = FirestoreClient.getFirestore();
//
//    Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
//    Iterator<DocumentReference> iterator = documentReference.iterator();
//
//    List<Admin> admins = new ArrayList<>();
//    Admin admin;
//
//    while(iterator.hasNext()){
//      DocumentReference doc = iterator.next();
//      admin = Cloner.toObject(doc, Admin.class);
////      ApiFuture<DocumentSnapshot> future = doc.get();
////      DocumentSnapshot document = future.get();
////      admin = document.toObject(Admin.class);
//      admins.add(admin);
//    }
//
//    return admins;
//  }
//}
public class AdminService extends FirestoreService<Admin> {
  protected AdminService() {
    super("admins", Admin.class);
  }
}

