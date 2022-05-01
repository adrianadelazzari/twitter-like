package org.ac.cst8277.deLazzari.adriana.domain.valueObject;

import java.util.List;
import lombok.Data;

@Data
public class UserVO {

  private Integer id;
  private String username;
  private List<RoleVO> roleList;
}
