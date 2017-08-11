<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>留言板</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-2.2.4.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="input-group">
			<input type="text" id="content" class="form-control" placeholder="Search for...">
			<span class="input-group-btn">
				<button id="btn" class="btn btn-default" type="button">Go!</button>
			</span>
		</div>
		<table class="table table-hover" id="tab">
		
		</table>
	</div>
</body>
<script type="text/javascript">
	//1.点击发布消息的按钮
	$("#btn").click(function(){
		//向后台发送文本框的内容
		//需要一个添加数据的ajax请求
		//发送的信息：文本框输入的内容
		//一旦添加成功：就在table中添加一行
		
		$.ajax({
			url:"/MessageBoard_001/addnew",
			type:"get",
			data:{
				content:$("#content").val()
			},
			success:function(res){
				addNewTR(res.content,res.id,res.up,res.down,true)
			}
		})
		
		//addNewTR("suibian",200,22,2,true)
		
	})
	//2.访问页面就展示所有的消息列表
	function getAllMessages(){
		//利用ajax请求所有的数据
		//利用for循环遍历所有的数据，在table中依次添加一行
		
		console.log("刷新")
		
		$.ajax({
			url:"/MessageBoard_001/findall",
			success:function(res){
				for(var i=0;i<res.length;i++){
					var ab=res[i]
					addNewTR(ab.content,ab.id,ab.up,ab.down,true)
				}
			}
		})
	}
	
	getAllMessages()
	
	//3.负责添加一行标签tr
	function addNewTR(content,id,upCount,downCount,how){
		
		var tdleft = $("<td></td>").html("<p>"+content+"</p>")
		var tdright = $("<td></td>")
		
		tdright.append("顶:")
		$("<a href='###' num="+ id +"></a>").html(upCount).appendTo(tdright).click(top1)
		tdright.append("踩:")
		$("<a href='###' num="+ id +"></a>").html(downCount).appendTo(tdright).click(down1)
		$("<a href='###' num="+ id +">删除</a>").appendTo(tdright).click(delfunc)
		
		var trOb = $("<tr></tr>").append(tdleft).append(tdright).attr("id","tr"+id)
		
		//把设置好的tr标签加到table中
		$("#tab").prepend(trOb)
		
	}
	var delfunc = function(){
		
		//拿到a标签的id值
		var aid = $(this).attr("num")
		console.log(aid)
		
		//将要删除的id发送给后台处理
		$.ajax({
			
			url:"/MessageBoard_001/deldata",
			type:"get",
			data:{
				msgid:aid
			},
			success:function(res){		
				
				//成功之后删除tr标签
				$("#tr"+res.obj).remove()
			}	
			
		})
		
	}
	
	//4.前端顶的方法
	
	var top1 = function(){
		
		console.log("哈哈")
		var topid = $(this).attr("num")
		
		$.ajax({
			
			url:"/MessageBoard_001/top",
			data:{
				id:topid
			},
			success:function(res){
				getAllMessages()
			}	
		})	
	}
	
	//5.前端踩的方法
	
	var down1 = function(){
		
		console.log("呵呵")
		var downid = $(this).attr("num")
		
		$.ajax({
			
			url:"/MessageBoard_001/down",
			data:{
				id:downid
			},
			success:function(res){
				location.reload()
			}	
		})	
	}
	
</script>
</html>