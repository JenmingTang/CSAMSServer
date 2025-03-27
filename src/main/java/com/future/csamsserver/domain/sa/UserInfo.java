package com.future.csamsserver.domain.sa;

import lombok.Data;

import java.util.List;

/**
 * @description
 * @dateTime 2025:03:04 22:31
 * @user Jenming
 */
@Data
public class UserInfo {
    /*
       interface UserInfo {
     userId: string;
     userName: string;
     roles: string[];
     buttons: string[];
   }
        */
    String userId;
    String userName;
    List<String> roles;
    List<String> buttons;
}