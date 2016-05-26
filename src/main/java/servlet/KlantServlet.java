package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.GenericKlantMenu;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.GenericDao;
import pojo.Klant;

/**
 * Servlet implementation class KlantServlet
 */
public class KlantServlet extends HttpServlet {
	
	private static final Logger logger =  LoggerFactory.getLogger(KlantServlet.class);
	
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
	
	private Session currentSession;
	private Transaction currentTransaction;

    /**
     * Default constructor. 
     */
    public KlantServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("doGet methode wordt aangeroepen");
		
		List<Klant> klanten = (List<Klant>) getCurrentSession().createQuery("from Klant").list();

		System.out.println("De volgende klanten staan in de Klant tabel :");

		PrintWriter out = response.getWriter();
		
		for (Klant k : klanten) {
			out.println(HTML_START + "-" + k.toString() + HTML_END);
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public Session getCurrentSession() {
		return currentSession;
	}

}
