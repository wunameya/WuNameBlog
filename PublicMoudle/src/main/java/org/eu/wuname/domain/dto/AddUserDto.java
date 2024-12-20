package org.eu.wuname.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {
    //主键
    private Long id;
    //用户名
    private String userName;
    //密码
    private String password;

    private List<Long> roles;
}
