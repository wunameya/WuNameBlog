package org.eu.wuname.service.impl;

import org.eu.wuname.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ps")
public class PermissionServiceImp {
    public boolean hasPermission(String permission){
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
