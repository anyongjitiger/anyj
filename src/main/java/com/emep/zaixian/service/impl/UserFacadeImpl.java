package com.emep.zaixian.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emep.zaixian.dao.UserDao;
import com.emep.zaixian.model.User;
import com.emep.zaixian.security.component.PasswordHelper;
import com.emep.zaixian.service.RoleService;
import com.emep.zaixian.service.UserFacade;

@Service("userFacade")
public class UserFacadeImpl implements UserFacade{

	@Override
	public User getByUserName(String userName) {
		User user = new User();
		user.setId(Long.valueOf(110000));
		user.setPassword("ca6826d8cf0c2eb5d2865858f325df01");
		user.setSalt("40c3f90d8e473947d07ddbd8e9e6b13a");
		user.setUserName("admin");
		return user;
	}
	@Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
    }
}
