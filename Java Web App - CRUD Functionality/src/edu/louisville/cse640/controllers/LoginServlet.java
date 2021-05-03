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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Connection            dbConnection	= null;
    private DatabaseConnectionController dcc			= null;
    private UsersController              uc				= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/Pages/Landing.jsp";
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        ResultSet rs = null;
        
        if (user == null || user.length() == 0)
        {
            url = "/Pages/Login.jsp";
            request.setAttribute("error", "User name must not be empty");
        }
        else
        {
            try
            {
                connect2database();
                if (uc.findUser(user, password))
                {
                    request.setAttribute("fullname", uc.getFullName());
                    request.setAttribute("users", uc.getUsersList());
                    request.setAttribute("batting", uc.getBatting());
                    request.setAttribute("fielding", uc.getFielding());
                }
                else
                {
                    url = "/Pages/Login.jsp";
                    request.setAttribute("error", "User does not exist");
                }
                dcc.disconnectFromDatabase();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/Pages/Updated.jsp";
		String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String jersey = request.getParameter("jersey");
        
		try
        {
            connect2database();
			//uc.insertUser(id, username, password, firstName, lastName);
            //uc.deleteUser(username, password);
            uc.updateUser(id, username, password, firstName, lastName, jersey);
            
			//System.out.println("User inserted: SUCCESS.");
            //System.out.println("User deleted: SUCCESS.");
            System.out.println("User updated: SUCCESS.");
			dcc.disconnectFromDatabase();
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
		//doGet(request, response);
	}
}