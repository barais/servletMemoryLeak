package com.undertow;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userinfo", urlPatterns = { "/UserInfo" })
public class UserInfo extends HttpServlet {


	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		config.getInitParameter("persistenceUnitNAme");

	}
	int i = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");


		PrintWriter out = response.getWriter();
		i = i + 1;




		out.println(
				"<html><body>"
				+ "<form action=\"UserInfo\" method=\"POST\">"
				+ "First Name: <input type=\"text\" name=\"first_name\">"
				+ "<br />"
				+ "Last Name: "
				+ "<input type=\"text\" name=\"last_name\" />"
				+ "<input type=\"submit\" value=\"Submit\" /></form></body></html>");/*
																																																															 * +
																																																															 * "<H1>Recapitulatif des informations</H1>\n"
																																																															 * +
																																																															 * "<UL>\n"
																																																															 * +
																																																															 * " <LI>Nom: "
																																																															 * +
																																																															 * request
																																																															 * .
																																																															 * getParameter
																																																															 * (
																																																															 * "name")
																																																															 * +
																																																															 * "\n"
																																																															 * +
																																																															 * " <LI>Prenom: "
																																																															 * +
																																																															 * request
																																																															 * .
																																																															 * getParameter
																																																															 * (
																																																															 * "firstname")
																																																															 * +
																																																															 * "\n"
																																																															 * +
																																																															 * " <LI>Age: "
																																																															 * +
																																																															 * request
																																																															 * .
																																																															 * getParameter
																																																															 * (
																																																															 * "age")
																																																															 * +
																																																															 * "\n"
																																																															 * +
																																																															 * "</UL>\n"
																																																															 * +
																																																															 * "</BODY></HTML>"
																																																															 * )
																																																															 * ;
																																																															 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// super.doPost(req, resp);
		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Reading All Form Parameters";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
				+ "<table width=\"100%\" border=\"1\" align=\"center\">\n" + "<tr bgcolor=\"#949494\">\n"
				+ "<th>Param Name</th><th>Param Value(s)</th>\n" + "</tr>\n");

		Enumeration paramNames = request.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			out.print("<tr><td>" + paramName + "</td>\n<td>");
			String[] paramValues = request.getParameterValues(paramName);
			// Read single valued data
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					out.println("<i>No Value</i>");
				else
					out.println(paramValue);
			} else {
				// Read multiple valued data
				out.println("<ul>");
				for (int i = 0; i < paramValues.length; i++) {
					out.println("<li>" + paramValues[i]);
				}
				out.println("</ul>");
			}
		}
		out.println("</tr>\n</table>\n</body></html>");

	}

}
