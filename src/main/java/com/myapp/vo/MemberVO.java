package com.myapp.vo;

import lombok.Data;

@Data
public class MemberVO {
    private String id;
    private String gameId;
    private String password;
    private String role;
}
