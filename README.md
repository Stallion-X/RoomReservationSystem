# ReservationTest
编写一个首页，该页面能够自动跳转到WelcomeServlet

编写一个简单的机房预约系统，通过该系统实验室管理员可以完成机房的预约登记操作；

编写展示WelcomeServlet，该Servlet映射到首地址“/”，通过Java Bean获取所有机房的预约信息，并将数据转发到reservation .jsp页面；

编写机房预约页面（reservation .jsp)，该页面能够显示所有的机房预约信息，预约界面应该以表格的形式展示每个机房哪些时间段可选，哪些时间段已被哪位教师占用；同时管理员可以在该页面上完成预约登记操作，登记信息包括：教师、机房、时间段。

编写样式表reservation.css，利用DIV+CSS布局对机房预约页面（reservation .jsp)进行美化；

编写JavaScript脚本reservation.js，该脚本主要功能为：

（1）处理实验室管理员对时间段、机房的选择操作（可以通过在预约表格上单击单元格的方式来选择某机房的预约时间段，时间段选中与否应有状态区别）；

（2） 校验预约信息的正确性（当管理员单击已被占用的机房时间段或输入错误的教师信息时报错）

编写ReservationServlet，该Servlet能够接收页面传过来的预约数据，并能够调用Java Bean完成相关预约操作；

编写一个预约处理类ReservationBean，该处理类能够通过JDBC实现预约信息的查询以及预约信息的处理。

# RoomReservationSystem
简易机房预约系统 拙作

初次接触Servlet做的简单玩意

使用Tomcat10.0.17部署 在JDK16环境下通过测试

默认使用H2数据库，数据库位置为D:\h2db。

如需更改可至src/main/java/beans/ReservationBean.java第57-61行中修改数据库驱动（类型）及数据库位置。

# 预览
静态页面预览地址：https://stallion-x.github.io/RoomReservationSystem/

![preview](./preview.jpg)
