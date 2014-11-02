package erik.test.robotium;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import erik.test.robotium.GlobalResource;



/**
 * Servlet implementation class TestSyncServlet
 */
@WebServlet(
		urlPatterns = { "/TestSyncServlet" }, 
		initParams = { 
				@WebInitParam(name = "clientAReady", value = ""), 
				@WebInitParam(name = "clientBReady", value = ""), 
				@WebInitParam(name = "testMethodName", value = "")
		})
public class TestSyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSyncServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("testMethodName");//more development for methods sync
		String clientAReady = request.getParameter("clientAReady");
		String clientBReady = request.getParameter("clientBReady");
//		debuglog("methodName:"+methodName);		
		/*sync code*/
		if(clientAReady == null || clientBReady == null){
			return ;
		}
		
		if(clientAReady.equals("unknown")){//passenger request
			GlobalResource.clientBMark = "ready";
			clientAReady = GlobalResource.clientAMark;
		}else if (clientBReady.equals("unknown")){//driver request
			GlobalResource.clientAMark = "ready";
			clientBReady = GlobalResource.clientBMark;
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("test");

		
	}

	private void debuglog(String log) {
		System.out.print("erik ");
		System.out.println(log);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	

}
