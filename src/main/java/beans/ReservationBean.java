package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationBean {
	public static class LabresBean{
		private int id;
		private String tno;
		private int labid;
		private int week;
		private int time;
		private String tname;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTno() {
			return tno;
		}
		public void setTno(String tno) {
			this.tno = tno;
		}
		public int getLabid() {
			return labid;
		}
		public void setLabid(int labid) {
			this.labid = labid;
		}
		public int getWeek() {
			return week;
		}
		public void setWeek(int week) {
			this.week = week;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public String getTname() {
			return tname;
		}
		public void setTname(String tname) {
			this.tname = tname;
		}	
	}
	private String driver="org.h2.Driver";
	private String url="jdbc:h2:";
	private String database="D:/h2db/teaching";
	private String userName="sa";
	private String password="";
	
	private Connection connection=null;
	public Connection getConnection() {
		try{
			Class.forName(driver);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection=DriverManager.getConnection(url+database,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(connection!=null)
			return connection;
		else{
			System.out.println("connection is not correct");
			return null;
		}
	}
	
	public void closeConnection() {
		try{
			if(connection!=null){
				connection.close();
			}
			connection=null;
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<LabresBean> getAllReservations() {
		ResultSet rs=null;
		Statement stmt=null;
		ArrayList <LabresBean> list=new ArrayList<LabresBean>();
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from reservations";
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				LabresBean res=new LabresBean();
				res.setId(rs.getInt(1));
				res.setTno(rs.getString(2));
				res.setLabid(rs.getInt(3));
				res.setWeek(rs.getInt(4));
				res.setTime(rs.getInt(5));
				list.add(res);
			}
			stmt.close();					
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return list;
	}
	
	public List<LabresBean> getReservationsByLab(int labid) {
		ResultSet rs=null;
		Statement stmt=null;
		ArrayList <LabresBean> list=new ArrayList<LabresBean>();
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select * from reservations where labid="+labid;
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				LabresBean res=new LabresBean();
				res.setId(rs.getInt(1));
				res.setTno(rs.getString(2));
				res.setLabid(rs.getInt(3));
				res.setWeek(rs.getInt(4));
				res.setTime(rs.getInt(5));
				list.add(res);
			}
			stmt.close();					
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return list;
	}
	
	public int getLabsCount() {
		ResultSet rs=null;
		Statement stmt=null;
		int count = 0;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="SELECT COUNT(*) FROM LABROOMS";
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				count = rs.getInt(1);
			}
			stmt.close();					
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return count;
	}
	
	public int getResCount() {
		ResultSet rs=null;
		Statement stmt=null;
		int count = 0;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="SELECT COUNT(*) FROM RESERVATIONS";
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				count = rs.getInt(1);
			}
			stmt.close();					
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return count;
	}
	
	public String getTno(String tname) {
		ResultSet rs=null;
		Statement stmt=null;
		String tno = null;
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select no from teachers where name='"+tname+"'";
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs!=null && rs.next()){
				tno = rs.getString(1);
			}
			else
			{	
				System.out.println("null");
			}
			stmt.close();					
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return tno;
	}
	
	public String getTname(String tno) {
		ResultSet rs=null;
		Statement stmt=null;
		String tname = null;
		try {			
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="select name from teachers where no='"+tno+"'";
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs!=null && rs.next()){
				tname = rs.getString(1);
			}
			else
			{	
				System.out.println("null");
			}
			stmt.close();					
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return tname;
	}
	
	public int getMaxId() {
		ResultSet rs=null;
		Statement stmt=null;
		int maxid = 0;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="SELECT MAX(id) FROM RESERVATIONS";
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				maxid = rs.getInt(1);
			}
			stmt.close();					
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return maxid;
	}
	
	public boolean insertReservation(LabresBean record) {
		PreparedStatement pstmt=null;
		String sql="insert into reservations values(?,?,?,?,?)";
		if(record==null) 
			return false;
		boolean result=false;
		int number=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, record.getId());
			pstmt.setString(2, record.getTno());
			pstmt.setInt(3, record.getLabid());
			pstmt.setInt(4, record.getWeek());
			pstmt.setInt(5, record.getTime());
			number=pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(number>0)
			result=true;
		return result;
	}
	
	public int getIdByRecord(LabresBean record) {
		ResultSet rs=null;
		Statement stmt=null;
		String tno = record.getTno();
		int time = record.getTime();
		int week = record.getWeek();
		int labid = record.getLabid();
		int id = -1;
		try {		
			connection = getConnection();
			if(connection==null)
				System.out.println("error");
			String sql="SELECT id FROM RESERVATIONS WHERE tno='"+tno+"' AND time="+time+" AND week="+week+" AND labid="+labid;
			stmt =connection.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				id = rs.getInt(1);
			}
			stmt.close();					
			connection.close();			
			
	}catch (SQLException e) {
		e.printStackTrace();
	} finally{
		try{
			if (connection!=null && (!connection.isClosed())){
				connection.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		return id;
	}
	
	public boolean deleteReservation(int id) {
		PreparedStatement pstmt=null;
		String sql="delete from reservations where id=?";
		int result=0;
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			result=pstmt.executeUpdate();
			pstmt.close();
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(result>0)
			return true;
		else
			return false;
	}
	
	public boolean queryDuplicate(LabresBean record) throws SQLException {
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String tno = record.getTno();
		int time = record.getTime();
		int week = record.getWeek();
		boolean flag = false;
		String sql="SELECT * FROM RESERVATIONS WHERE tno=? AND time=? AND week=?";
		try{
			connection=getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, tno);
			pstmt.setInt(2, time);
			pstmt.setInt(3, week);
			rs=pstmt.executeQuery();
			if(rs.next()&&rs.getRow()>0) {
				flag = false;
			}
			else {
				flag = true;
			}
			pstmt.close();
			connection.close();	
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try{
				if (connection!=null && (!connection.isClosed())){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	
}
