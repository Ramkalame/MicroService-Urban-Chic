package com.urbanchic.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

   private T data;
   private Object message;
   private LocalDateTime timestamp;
   private int statusCode;
   private  boolean success;

}
