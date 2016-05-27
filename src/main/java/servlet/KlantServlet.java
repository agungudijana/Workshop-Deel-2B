package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;




import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import pojo.*;



/**
 * Servlet implementation class KlantServlet
 */
public class KlantServlet extends HttpServlet {
	
	
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
		
		
		
		currentSession = getSessionFactory().openSession();
		List<Klant> klanten = (List<Klant>) getCurrentSession().createQuery("from Klant").list();
	
		PrintWriter out = response.getWriter();
		
		out.println(HTML_START + "<h2>De volgende klanten staan in de Klant tabel :</h2><br/>"+HTML_END);
	
		
		for (Klant k : klanten) {
			out.println(HTML_START + "-" + k.toString() + HTML_END);
		} 

		currentSession.close();
		
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
	
	private static SessionFactory getSessionFactory() {
		

		Configuration configuration = new Configuration()
				.addAnnotatedClass(Klant.class)
				.addAnnotatedClass(Account.class)
				.addAnnotatedClass(Bestelling.class)
				.addAnnotatedClass(Factuur.class)
				.addAnnotatedClass(Adres.class)
				.addAnnotatedClass(AdresType.class)
				.addAnnotatedClass(BestelArtikel.class)
				.addAnnotatedClass(Betaalwijze.class)
				.addAnnotatedClass(Betaling.class)
				.addAnnotatedClass(Artikel.class)
				.addAnnotatedClass(KlantAdres.class)
				.configure();
		
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties());
		
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

}
