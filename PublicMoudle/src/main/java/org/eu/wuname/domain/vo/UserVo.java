package org.eu.wuname.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.eu.wuname.domain.entity.Menu;
import org.eu.wuname.domain.entity.Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVo {
    private Long id;
    private String userName;
    private List<Role> role;
}
