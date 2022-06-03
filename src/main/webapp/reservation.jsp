<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="beans.*" %>
<%@page import="servlets.*" %>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="UTF-8">
<title>机房预约页面</title>
<link rel="stylesheet" type="text/css" href="reservation.css">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="reservation.js"></script>
</head>
<body>
<%
List <ReservationBean.LabresBean> list=(List<ReservationBean.LabresBean>)request.getAttribute("allRes");
List <ReservationBean.LabresBean> listlab=(List<ReservationBean.LabresBean>)request.getAttribute("labRes");
int labscount = (int)request.getAttribute("labsCount");
int labid = (int)request.getAttribute("Labid");
int rescount = (int)request.getAttribute("rescount");
if (request.getAttribute("result")!=null) {
	if ((boolean)request.getAttribute("result") == true) {
		%>
		<script type="text/javascript">alert("操作成功");</script>
		<%
	}
	else {
		%>
		<script type="text/javascript">alert("操作失败\n原因可能有：\n1.您重复预约了同一时间\n2.数据库中查询不到您的信息，请检查姓名是否输入错误\n3.您取消了操作");</script>
		<%
	}
}
%>
<div class="stat">
<span>当前处于<%=labid %>号机房</span>
<span>共</span>
<span class="timer count-title" id="count-number" data-to="<%=labscount %>" data-speed="50">0</span>
<span>个</span>
<span>机房</span>
<span class="timer count-title" id="count-number" data-to="<%=rescount %>" data-speed="50">0</span>
<span>条</span>
<span>预约记录</span>
</div>
<div class="content">
<div class="title">欢迎进入机房预约系统</div>
<%
for (int i=1;i<=labscount;i++) {
	%>
	<form action="WelcomeServlet" method="post">
	<input type="hidden" name="Labid" value="<%=i%>">
	<input type="submit" class="lab" value="机房<%=i%>">
	</form>
	<%
}
%>
<table>
	<tr class="hd">
	<td>周一</td><td>周二</td><td>周三</td><td>周四</td><td>周五</td><td>周六</td><td>周日</td>
	<tr/>
	<%
	for (int i=1;i<=8;i++) {
		%>
		<tr>
		<%
		for (int j=1;j<=7;j++) {
			boolean flag = false;
			String tname = null;
			if(listlab!=null){
				for(int k=0;k<listlab.size();k++){
					if (i==listlab.get(k).getTime()&&j==listlab.get(k).getWeek()) {
						flag = true;
						tname = listlab.get(k).getTname();
					}
				}
			}
			%>
			<td>
			<form action="ReservationServlet" method="post">
			<input type="hidden" name="time" value="<%=i%>">
			<input type="hidden" name="week" value="<%=j%>">
			<input type="hidden" name="labid" value="<%=labid%>">
			<input type="hidden" name="tname" id="<%=i+"name"+j%>" value="">
			<%
			if (flag==true) {
				%>
				<input type="hidden" name="confirm" id="<%=i+"confirm"+j%>" value="">
				<input type="hidden" name="action" value="del">
				<%-- <input type="submit" class="res" id="<%=i+" "+j%>" value="<%=tname %>" onclick="delClick(<%=i+","+j%>)"> --%>
				<button class="res" id="<%=i+" "+j%>" value="<%=tname %>" onclick="delClick(<%=i+","+j%>)"><span style="background: rgb(215,185,156);
  background: linear-gradient(0deg, rgb(215,185,156) 0%, rgb(215,185,156) 100%);">选中！</span><span style="background: rgb(215,185,156);
  background: linear-gradient(0deg, rgb(215,185,156) 0%, rgb(215,185,156) 100%);"><%=tname %></span></button>
				<%
			}
			else {
				%>
				<input type="hidden" name="action" value="add">
				<%-- <input type="submit" class="res" id="<%=i+" "+j%>" value="空闲" onclick="resClick(<%=i+","+j%>)"> --%>
				<button class="res" id="<%=i+" "+j%>" onclick="resClick(<%=i+","+j%>)"><span>选中！</span><span>空闲</span></button>
				<%
			}
			%>
			</form>
			<%
		}
		%>
			<td/>
		<tr/>
		<%
	}
	%>
</table>
</div>
<%-- <table border="1">
	<tr>
	<td>编号</td><td>教师编号</td><td>教室号</td><td>周数</td><td>时间</td>
	<tr/>
	<%			
		if(list!=null){
			for(int i=0;i<list.size();i++){ 
	%>	
	<tr>
	<td><%=list.get(i).getId()%></td><td><%=list.get(i).getTno() %></td>
	<td><%=list.get(i).getLabid() %></td><td><%=list.get(i).getWeek() %></td>
	<td><%=list.get(i).getTime() %></td>
	</tr>
<%  }}%>
</table> --%>
</body>
</html>