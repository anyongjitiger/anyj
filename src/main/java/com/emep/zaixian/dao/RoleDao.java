package com.emep.zaixian.dao;

import java.util.List;
import com.emep.zaixian.model.Role;;

/**
 * <p>User: anyj
 * <p>Date: 17-1-19
 * <p>Version: 1.0
 */
public interface RoleDao {

    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();
}
