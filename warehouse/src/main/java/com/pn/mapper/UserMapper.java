package com.pn.mapper;

import com.pn.entity.User;
import com.pn.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

	//根据用户名查找用户的方法
	public User findUserByCode(String userCode);

	//查询用户总行数的方法
	public int selectUserCount(User user);

	//分页查询用户的方法
	public List<User> selectUserPage(@Param("page") Page page, @Param("user")User user);

	//添加用户的方法
	public int insertUser(User user);

	//根据用户id修改用户状态的方法
	public int updateUserState(User user);

	//根据用户id将用户状态修改为删除状态，可批量删除
	public int setDeleteByids(List<Integer> userList);


	//根据用户id修改用户昵称的方法
	public int updateNameById(User user);

	//根据用户id修改密码的方法
	public int updatePwdById(User user);
}
