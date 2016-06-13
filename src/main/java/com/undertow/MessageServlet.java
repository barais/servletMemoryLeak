package com.undertow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String MESSAGE = "message";

    private String message;

     
    @Override
    public void destroy() {
    	// TODO Auto-generated method stub
    	super.destroy();
    }
    
    
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        message = config.getInitParameter(MESSAGE);
    }

     
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    	
    	PrintWriter writer = resp.getWriter();
        writer.write("<h1>Hello world INSA</h1>");
        writer.close();
    }

     
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    	String string = req.getParameter("postsample");
    	System.out.println(string);
    	doGet(req, resp);
    }

}
