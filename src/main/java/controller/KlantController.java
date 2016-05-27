// created especially for opdracht 7b. Workshop Part 2

package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opdracht7dao.*;
import pojo.*;

public class KlantController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/klant.jsp";
    private static String LIST_KLANT = "/listKlant.jsp";
    private KlantDao dao;

    public KlantController() {
        super();
        dao = new KlantDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int klantId = Integer.parseInt(request.getParameter("klantId"));
            dao.deleteKlant(klantId);
            forward = LIST_KLANT;
            request.setAttribute("klant", dao.getAllKlanten());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int klantId = Integer.parseInt(request.getParameter("klantId"));
            Klant klant = dao.getKlantById(klantId);
            request.setAttribute("klant", klant);
        } else if (action.equalsIgnoreCase("listKlant")){
            forward = LIST_KLANT;
            request.setAttribute("klant", dao.getAllKlanten());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Klant klant = new Klant();
        klant.setVoornaam(request.getParameter("voornaam"));
        klant.setAchternaam(request.getParameter("achternaam"));
        klant.setTussenvoegsel(request.getParameter("tussenvoegsel"));
        klant.setEmail(request.getParameter("email"));
        String klantid = request.getParameter("klantid");
        if(klantid == null || klantid.isEmpty())
        {
            dao.addKlant(klant);
        }
        else
        {
            klant.setId(Integer.parseInt(klantid));
            dao.updateKlant(klant);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_KLANT);
        request.setAttribute("klants", dao.getAllKlanten());
        view.forward(request, response);
    }
}