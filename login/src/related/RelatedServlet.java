package related;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginDao;
import movies.Film;


public class RelatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RelatedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		List<Film> relatedFilms = new ArrayList<>();
		String relatedGenre = request.getParameter("relatedGenre");
		
		String temp1 = request.getParameter("temp1");
		String temp2 = request.getParameter("temp2");		
		List<Film> topMovies = new ArrayList<Film>();
		List<Film> favoritesList = new ArrayList<Film>();
		int[] favPID = new int[5];
		LoginDao.getTopMovies(topMovies);
		LoginDao.validate(temp1, temp2, favoritesList, favPID);
		request.setAttribute("favoritesList", favoritesList);
		request.setAttribute("error", "");
		request.setAttribute("topMovies", topMovies);
		request.setAttribute("temp1", temp1);
		request.setAttribute("temp2", temp2);
		
		
		System.out.println("related" + relatedGenre);
		
		if(RelatedDao.getRelated(relatedFilms, relatedGenre))
		{ //call search function for related films
			
			int size = relatedFilms.size();
			
			for(int i = 0; i < size; i++)
			{
				
				out.println("		<h3>" + (i+1) + "). " + relatedFilms.get(i).title + "</h3>");
				out.println("<img src = \"https://raw.githubusercontent.com/montysaengsavang/Muvu-Images/master/"
				+ relatedFilms.get(i).url + "\" alt=\"Movie Image\" height=\"390\" width=\"280\"><br><br>");
				out.println("				" + relatedFilms.get(i).description);
				out.println("<br>");
			}
			 
		}
		else{ //if relatedDao returns false, print to screen and recall the homepage.
			
			/*
			String temp1 = request.getParameter("temp1");
			String temp2 = request.getParameter("temp2");
			
			List<Film> topMovies = new ArrayList<Film>();
			List<Film> favoritesList = new ArrayList<Film>();
			int[] favPID = new int[5];
			
			LoginDao.getTopMovies(topMovies);
			LoginDao.validate(temp1, temp2, favoritesList, favPID); 
			request.setAttribute("favoritesList", favoritesList);
			request.setAttribute("error", "Search was unsuccessful. Please try again.");
			request.setAttribute("topMovies", topMovies);
			
			request.setAttribute("temp1", temp1);
			request.setAttribute("temp2", temp2);
			*/
			RequestDispatcher rd=request.getRequestDispatcher("homepage.jsp");
			rd.forward(request,response);
		}
		
		out.close();
	}


}
