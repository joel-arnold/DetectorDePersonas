package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.*;
import entidades.*;

@WebServlet("/Identificador")
public class Identificador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Identificador() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		
		String nombre = (String)request.getParameter("persona");
		
		Persona persona = new Persona();
		ControladorPersonas controlador = new ControladorPersonas();
		
		persona = controlador.buscarPersona(nombre);
		
		if(persona != null) {
			sesion.setAttribute("nombre", persona.getNombre());
			sesion.setAttribute("fechaNac", persona.getFechaNac());
			sesion.setAttribute("telefono", persona.getTelefono());
			System.out.println(persona.getNombre());
			System.out.println(persona.getFechaNac());
			System.out.println(persona.getTelefono());
			response.sendRedirect("resultado.jsp");
		}
		else {
			sesion.setAttribute("mensaje", "Sal� cabeza, vos no exist�s");
			response.sendRedirect("resultado.jsp");
		}
		
		
		doGet(request, response);
	}
}