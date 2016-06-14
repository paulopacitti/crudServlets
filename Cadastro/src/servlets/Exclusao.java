package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes_bd.*;
import dao.*;

/**
 * Servlet implementation class Exclusao
 */
@WebServlet("/Exclusao")
public class Exclusao extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exclusao()
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

			if(ra == "")
			{
					response.sendRedirect("erro.html");
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
					alunos.excluir(ra);
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
