package net.javatutorial.tutorials;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServer extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersonServiceImpl person = new PersonServiceImpl();

		database db = database.getDbCon();

		Person pers  = person.getGagiPerson(88);
		Person perrc = person.getDummyPerson(99);

		response.getWriter().println(person.addPerson(pers).getMessage() + ":" +  pers.getName() + ":" + pers.getId());
		response.getWriter().println(person.addPerson(perrc).getMessage() + ":" +  perrc.getName() + ":" + perrc.getId());
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		PersonServiceImpl person = new PersonServiceImpl();
		request.equals(person);
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}

}