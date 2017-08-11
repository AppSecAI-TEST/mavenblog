package com.lanou.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanou.bean.Message;
import com.lanou.mapper.MessageMapper;

/**
 * 类
 *
 * @author Andy_Lu
 * 
 * @time 2017年8月9日 上午10:53:50
 * 
 */

@Controller
public class MessageController {

	// 使用spring依赖注入(DI)
	// 可以使用resource和autowired注解
	@Autowired
	private MessageMapper messageMapper;

	@RequestMapping(value = "/")
	public String frontPage() {
		return "messageBoard";
	}

	@RequestMapping(value = "/findall")
	@ResponseBody
	public List<Message> findAllMsg() {
		
		System.out.println("获取所有数据");
		
		List<Message> messages = messageMapper.findALLMessage();

		return messages;
	}

	@RequestMapping(value = "/addnew")
	@ResponseBody
	public Message addNew(Message message) {

		message.setUp(0);
		message.setDown(0);
		messageMapper.insert(message);

		return message;
	}

	@RequestMapping(value = "/deldata")
	@ResponseBody
	public Map<String, Object> delData(@RequestParam("msgid") Integer id) {

		Integer num = messageMapper.deleteByPrimaryKey(id);

		Map<String, Object> maps = new HashMap<String, Object>();

		maps.put("msg", "删除成功");
		maps.put("error", "0");
		maps.put("obj", id);

		return maps;
	}

	// @RequestMapping(value = "/top")
	// @ResponseBody
	// public Map<String, Object> top(@RequestParam("topid") Integer id) {
	//
	// messageMapper.updateUpByPrimaryKey(id);
	//
	// Map<String, Object> maps = new HashMap<String, Object>();
	//
	// maps.put("msg", "顶成功");
	// maps.put("error", "0");
	//
	// return maps;
	// }
	
	@RequestMapping(value="/top")
	@ResponseBody
	public Map<String, Object> top(Message message) {
		
		Message msg = messageMapper.selectByPrimaryKey(message.getId());
		Integer up= msg.getUp();
		msg.setUp(up+1);
		
		System.out.println(msg.getId());
		
		messageMapper.updateByPrimaryKey(msg);
		
		Map<String, Object> maps = new HashMap<String, Object>();

		maps.put("msg", "顶成功");
		maps.put("error", "0");

		return maps;
	}

//	@RequestMapping(value = "/foot")
//	@ResponseBody
//	public Map<String, Object> foot(@RequestParam("footid") Integer id) {
//		
//		messageMapper.updateDownByPrimaryKey(id);
//
//		Map<String, Object> maps = new HashMap<String, Object>();
//
//		maps.put("msg", "踩成功");
//		maps.put("error", "0");
//
//		return maps;
//	}
	
	@RequestMapping(value="/down")
	@ResponseBody
	public Map<String, Object> down(Message message) {
		
		Message msg = messageMapper.selectByPrimaryKey(message.getId());
		Integer down= msg.getDown();
		msg.setDown(down+1);
		
		System.out.println(msg.getId());
		
		messageMapper.updateByPrimaryKey(msg);
		
		Map<String, Object> maps = new HashMap<String, Object>();

		maps.put("msg", "踩成功");
		maps.put("error", "0");

		return maps;
	}

}
