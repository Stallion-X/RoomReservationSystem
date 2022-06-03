package servlets;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.ReservationBean;
import beans.ReservationBean.LabresBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		if("add".equals(action))
		{
			try {
				addRes(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("del".equals(action))
		{
			delRes(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void addRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		ReservationBean res = new ReservationBean();
		ReservationBean.LabresBean record = new ReservationBean.LabresBean();
		String tname = request.getParameter("tname");
		String tno = res.getTno(tname);
		int time = Integer.parseInt(request.getParameter("time"));
		int week = Integer.parseInt(request.getParameter("week"));
		int labid = Integer.parseInt(request.getParameter("labid"));
		int id = res.getMaxId()+1;
		record.setId(id);
		record.setLabid(labid);
		record.setTime(time);
		record.setTno(tno);
		record.setWeek(week);
		if (tno!=null) {
			if (res.queryDuplicate(record)) {
				boolean result = res.insertReservation(record);
				request.setAttribute("result", result);
			}
			else {
				request.setAttribute("result", false);
			}
		}
		else {
			request.setAttribute("result", false);
		}
		
		List <ReservationBean.LabresBean> list = res.getAllReservations();
		list.forEach(item->item.setTname(res.getTname(item.getTno())));
		request.setAttribute("allRes", list);
		List <ReservationBean.LabresBean> listlab = res.getReservationsByLab(labid);
		listlab.forEach(item->item.setTname(res.getTname(item.getTno())));
		request.setAttribute("labRes", listlab);
		request.setAttribute("Labid",labid);
		int maxid = res.getMaxId();
		request.setAttribute("maxid",maxid);
		int rescount = res.getResCount();
		request.setAttribute("rescount",rescount);
		int labscount = res.getLabsCount();
		request.setAttribute("labsCount", labscount);
		
		String path="/reservation.jsp";
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
	}
	
	public void delRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReservationBean res = new ReservationBean();
		String tname = request.getParameter("tname");
		String tno = res.getTno(tname);
		int time = Integer.parseInt(request.getParameter("time"));
		int week = Integer.parseInt(request.getParameter("week"));
		int labid = Integer.parseInt(request.getParameter("labid"));
		String confirm = request.getParameter("confirm");
		
		if (confirm.equals("1")) {
			ReservationBean.LabresBean record = new ReservationBean.LabresBean();
			record.setLabid(labid);
			record.setTime(time);
			record.setTno(tno);
			record.setWeek(week);
			boolean result = res.deleteReservation(res.getIdByRecord(record));
			request.setAttribute("result", result);
		}
		else {
			request.setAttribute("result", false);
		}
		
		List <ReservationBean.LabresBean> list = res.getAllReservations();
		list.forEach(item->item.setTname(res.getTname(item.getTno())));
		request.setAttribute("allRes", list);
		List <ReservationBean.LabresBean> listlab = res.getReservationsByLab(labid);
		listlab.forEach(item->item.setTname(res.getTname(item.getTno())));
		request.setAttribute("labRes", listlab);
		request.setAttribute("Labid",labid);
		int maxid = res.getMaxId();
		request.setAttribute("maxid",maxid);
		int rescount = res.getResCount();
		request.setAttribute("rescount",rescount);
		int labscount = res.getLabsCount();
		request.setAttribute("labsCount", labscount);
		
		String path="/reservation.jsp";
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher requestDistpatcher=request.getRequestDispatcher(path);
		requestDistpatcher.forward(request, response);
	}

}
