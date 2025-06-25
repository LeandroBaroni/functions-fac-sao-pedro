package com.leandrobaroni2103.functions_fac_sao_pedro.utils;

import com.leandrobaroni2103.functions_fac_sao_pedro.entities.BaseModel;

import java.lang.reflect.Field;
import java.util.Date;

public class Cloner {
  public static <T extends BaseModel> T cloneObject(T source) {
    if (source == null) return null;

    try {
      Class<?> clazz = source.getClass();
      Object target = clazz.getDeclaredConstructor().newInstance();

      for (Field field : clazz.getDeclaredFields()) {
        if (field.getName().equals("id")) continue;

        field.setAccessible(true);
        Object value = field.get(source);
        field.set(target, value);
      }

      @SuppressWarnings("unchecked")
      T result = (T) target;

      if(result.getCreatedAt() == null){
        result.setCreatedAt(new Date());
      }
      return result;
    } catch (Exception e) {
      throw new RuntimeException("Failed to clone object without id", e);
    }
  }
}
