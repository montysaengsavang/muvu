package login;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoggedInServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       

    public LoggedInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
	
		RequestDispatcher rd = request.getRequestDispatcher("homepage.html");
		rd.forward(request,response);
		
		/*
		 PrintWriter out = response.getWriter();
		String n=request.getParameter("useremail");
		out.print("Welcome to the homepage, "+n);
		*/
		//out.close();
	
	}

}
