package com.cos.navernews.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CMRespDto<T> { // 코드, 메시지, 데이터
   private int code;
   private String msg;
   private T data;
}