package com.lanou.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lanou.bean.Message;

//配置dao层注解,用于对controller的依赖注入
@Repository
public interface MessageMapper {

	// 加一个查找信息的方法
	List<Message> findALLMessage();

	int deleteByPrimaryKey(Integer id);

	int insert(Message record);

	int insertSelective(Message record);

	Message selectByPrimaryKey(Integer id);

	// 加一个顶的方法
	// int updateUpByPrimaryKey(Integer id);

	// 加一个踩的方法
	// int updateDownByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKey(Message record);
}