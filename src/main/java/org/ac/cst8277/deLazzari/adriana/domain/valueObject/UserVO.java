package org.ac.cst8277.deLazzari.adriana.domain.valueObject;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {

    private Integer id;
    private String username;
    private List<RoleVO> roleList;
}
