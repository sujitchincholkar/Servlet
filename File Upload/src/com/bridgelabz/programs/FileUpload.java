package com.bridgelabz.programs;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String URL="jdbc:mysql://localhost:3306/MyDb";
		String username="root";
		String password="root";
		PrintWriter out = response.getWriter();
		Part filePart = request.getPart("file");
		InputStream inputStream = filePart.getInputStream();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String query = "insert into fileSave(uid,filename,filecol) values(?,?,?)";
			Connection connection=DriverManager.getConnection(URL,username,password);
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, 1);
			prepareStatement.setString(2, filePart.getSubmittedFileName());
			prepareStatement.setBinaryStream(3, inputStream, inputStream.available());
			prepareStatement.executeUpdate();
			inputStream.close();
			out.print("File uploaded");
			request.getRequestDispatcher("index.html").include(request, response);;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
