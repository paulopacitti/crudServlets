package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbo.*;
import classes_bd.*;
import dao.*;

/**
 * Servlet implementation class Alteracao
 */
@WebServlet("/Alteracao")
public class Alteracao extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Alteracao()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub


		try
		{
			String ra = request.getParameter("ra");
			String endereco = request.getParameter("endereco");
			String cidade = request.getParameter("cidade");
			String UF = request.getParameter("UF");
			String curso = request.getParameter("curso");

			if(ra == "" || endereco == "" || cidade =="" || UF == "" || curso =="")
			{
					response.getWriter().println("<html>");
					response.getWriter().println("<head></head>");
					response.getWriter().println("<body>Algum campo está vazio. Tente novamente.</body>");
					response.getWriter().println("</html>");
					return;
			}

			MeuPreparedStatement comando = (MeuPreparedStatement) request.getSession().getAttribute("conexao");
			if(comando == null)
			{
				comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver",
			            "jdbc:sqlserver://regulus:1433;databasename=BDu14191",
			            "BDu14191", "cotuca");
				request.getSession().setAttribute("conexao", comando);

				try
				{
					Alunos alunos = new Alunos(comando);
					alunos.alterar(ra, endereco, cidade, UF, Integer.parseInt(curso));
					response.sendRedirect("sucesso.html");
				}
				catch (Exception e1)
				{
					response.sendRedirect("erro.html");
				}
			}
		}
		catch(IOException e)
		{
			response.sendRedirect("erro.html");
		}
		catch (ClassNotFoundException e1)
		{
			response.sendRedirect("erro.html");
		}
		catch (SQLException e1)
		{
			response.sendRedirect("erro.html");
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
