package edu.louisville.cse640.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.louisville.cse640.controllers.DatabaseConnectionController;
import edu.louisville.cse640.controllers.UsersController;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Connection            dbConnection	= null;
    private DatabaseConnectionController dcc			= null;
    private UsersController              uc				= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
    }
    
    private void connect2database()
    {
        dcc = new DatabaseConnectionController("PPGHAY01");
        if (dcc != null)
        {
            dbConnection = dcc.getDbConnection();
            if (dbConnection == null)
            {
                System.out.println("Could not establish a connection.");
            }
            else
            {
                uc = new UsersController(dbConnection);
            }
        }
        else
        {
            System.out.println("Database Driver Failed");
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/Pages/Deleted.jsp";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
		try
        {
            connect2database();
            uc.deleteUser(username, password);
            System.out.println("User DELETED - Success");
			dcc.disconnectFromDatabase();
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
		//doGet(request, response);
	}
}