package net.javatutorial.tutorials;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PersonServiceImpl person = new PersonServiceImpl();

		database cnn = database.getDbCon();
//		cnn.insert("Dragoslav","dwadwa","dwadada",123);
//		cnn.selectAll();

		Person pers  = person.getGagiPerson(2);
		Person perrc = person.getDummyPerson(3);
		cnn.select(pers.getId());


		response.getWriter().println(person.addPerson(pers).getMessage() + " : " +  pers.getName() + " : " + pers.getId());
		response.getWriter().println(person.addPerson(perrc).getMessage() + " : " +  perrc.getName() + " : " + perrc.getId());

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