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
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Connection            dbConnection	= null;
    private DatabaseConnectionController dcc			= null;
    private UsersController              uc				= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
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
		String url = "/Pages/Inserted.jsp";
		String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String jersey = request.getParameter("jersey");
        
		try
        {
            connect2database();
			uc.insertUser(id, username, password, firstName, lastName);
            
			System.out.println("User INSERTED - Success.");
			dcc.disconnectFromDatabase();
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}
}