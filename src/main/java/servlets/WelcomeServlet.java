package servlets;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import beans.*;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReservationBean res = new ReservationBean();
		List <ReservationBean.LabresBean> list = res.getAllReservations();
		list.forEach(item->item.setTname(res.getTname(item.getTno())));
		request.setAttribute("allRes", list);
		
		int labid = 1;
		if (request.getParameter("Labid")!=null) {
			labid = Integer.parseInt(request.getParameter("Labid"));
		}
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
