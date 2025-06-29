package com.leandrobaroni2103.functions_fac_sao_pedro.utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.leandrobaroni2103.functions_fac_sao_pedro.entities.BaseModel;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Cloner {
  public static <T extends BaseModel> Object cloneDocument(T source) {
    if (source == null) return null;

    try {
      if(source.getCreatedAt() == null){
        source.setCreatedAt(new Date());
      }
      Class<? extends BaseModel> clazz = source.getClass();
      Object target = clazz.getDeclaredConstructor().newInstance();

      for (Field field: clazz.getDeclaredFields()) {
        if (!field.getName().equals("id")) {
          field.setAccessible(true);
          Object value = field.get(source);
          field.set(target, value);
        }
      }
      
      return target;
    } catch (Exception e) {
      throw new RuntimeException("Failed to clone object without id", e);
    }
  }

  public static <T extends BaseModel> T cloneObject(T source) {
    if (source == null) return null;

    try {
      Class<?> clazz = source.getClass();
      Object target = clazz.getDeclaredConstructor().newInstance();

      for (Field field : clazz.getDeclaredFields()) {
        if (!field.getName().equals("id")) {
          field.setAccessible(true);
          Object value = field.get(source);
          field.set(target, value);
        }

      }

      @SuppressWarnings("unchecked")
      T result = (T) target;

      if(source.getId() != null){
        result.setId(source.getId());
      }

      if(result.getCreatedAt() == null){
        result.setCreatedAt(new Date());
      }
      return result;
    } catch (Exception e) {
      throw new RuntimeException("Failed to clone object without id", e);
    }
  }

  public static <T extends BaseModel> T toObject(DocumentReference document, Class<T> clazz) throws ExecutionException, InterruptedException {
    String id = document.getId();

    ApiFuture<DocumentSnapshot> future = document.get();

    DocumentSnapshot snapshot = future.get();

    if(!snapshot.exists()){
      return null;
    }

    T doc = snapshot.toObject(clazz);

    if (doc != null) {
      doc.setId(id);
    }

    return doc;
  }
}
