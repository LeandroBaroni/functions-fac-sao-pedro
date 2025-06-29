package com.leandrobaroni2103.functions_fac_sao_pedro.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.leandrobaroni2103.functions_fac_sao_pedro.entities.BaseModel;
import com.leandrobaroni2103.functions_fac_sao_pedro.enums.FirestoreOperator;
import com.leandrobaroni2103.functions_fac_sao_pedro.utils.Cloner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class FirestoreService<T extends BaseModel> {

  private final String collectionName;
  private final Class<T> clazz;

  protected FirestoreService(String collectionName, Class<T> clazz) {
    this.collectionName = collectionName;
    this.clazz = clazz;
  }

  protected Firestore getFirestore() {
    return FirestoreClient.getFirestore();
  }

  public String add(T entity) throws ExecutionException, InterruptedException {
    T clone = Cloner.cloneObject(entity);
    ApiFuture<WriteResult> future = getFirestore().collection(collectionName).document().set(clone);
    return future.get().getUpdateTime().toString();
  }

  public String set(T entity) throws ExecutionException, InterruptedException {
    String id = entity.getId();
    Object obj = Cloner.cloneDocument(entity);
    getFirestore().collection(collectionName).document(id).set(obj);
    return id;
  }

  public String update(T entity) throws ExecutionException, InterruptedException {
    String id = entity.getId();
    T clone = Cloner.cloneObject(entity);
    clone.setUpdatedAt(new Date());
    ApiFuture<WriteResult> future = getFirestore().collection(collectionName).document(id).set(clone);
    return future.get().getUpdateTime().toString();
  }

  public T getById(String id) throws ExecutionException, InterruptedException {
    DocumentReference docRef = getFirestore().collection(collectionName).document(id);
    return Cloner.toObject(docRef, clazz);
  }

  public void delete(String id) {
    getFirestore().collection(collectionName).document(id).delete();
  }

  public List<T> getAll() throws ExecutionException, InterruptedException {
    Iterable<DocumentReference> docs = getFirestore().collection(collectionName).listDocuments();
    List<T> result = new ArrayList<>();

    for (DocumentReference doc : docs) {
      T entity = Cloner.toObject(doc, clazz);
      result.add(entity);
    }

    return result;
  }

//  public List<T> getWhereMany(
//          String field,
//          FirestoreOperator operator,
//          Object value
//  ) throws Exception {
//    CollectionReference collection =getFirestore().collection(collectionName);
//
//    Query query = switch (operator) {
//      case EQUAL -> collection.whereEqualTo(field, value);
//      case NOT_EQUAL -> collection.whereNotEqualTo(field, value);
//      case GREATER_THAN -> collection.whereGreaterThan(field, value);
//      case GREATER_THAN_OR_EQUAL -> collection.whereGreaterThanOrEqualTo(field, value);
//      case LESS_THAN -> collection.whereLessThan(field, value);
//      case LESS_THAN_OR_EQUAL -> collection.whereLessThanOrEqualTo(field, value);
//      case ARRAY_CONTAINS -> collection.whereArrayContains(field, value);
//      case IN -> collection.whereIn(field, (List<?>) value);
//      case ARRAY_CONTAINS_ANY -> collection.whereArrayContainsAny(field, (List<?>) value);
//      case NOT_IN -> collection.whereNotIn(field, (List<?>) value);
//      default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
//    };
//
//    ApiFuture<QuerySnapshot> querySnapshot = query.get();
//    List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
//    List<Map<String, Object>> results = new ArrayList<>();
//
//    for (QueryDocumentSnapshot document : documents) {
//      results.add(document.getData());
//    }
//
//    return results;
//  }
}
