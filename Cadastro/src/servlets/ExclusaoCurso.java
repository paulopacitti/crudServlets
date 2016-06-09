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
 * Servlet implementation class ExclusaoCurso
 */
@WebServlet("/ExclusaoCurso")
public class ExclusaoCurso extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExclusaoCurso()
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
			response.getWriter().append("Served at: ").append(request.getContextPath());
			String codCurso = request.getParameter("codCurso");

			if(codCurso == "")
			{
					response.getWriter().println("<html>");
					response.getWriter().println("<head></head>");
					response.getWriter().println("<body>Algum campo está vazio. Tente novamente.</body>");
					response.getWriter().println("</html>");
					return;
			}


			MeuPreparedStatement comando = null;
			if (request.getSession().getAttribute("conexao") == null)
				comando = (MeuPreparedStatement) request.getSession().getAttribute("conexao");
			if(comando == null)
			{
				comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver",
			            "jdbc:sqlserver://regulus:1433;databasename=BDu14191",
			            "BDu14191", "cotuca");
				request.getSession().setAttribute("conexao", comando);
			}
				try
				{
					Cursos cursos = new Cursos(comando);
					cursos.excluir(Integer.parseInt(codCurso));
					
					response.getWriter().println("<html>");
					response.getWriter().println("<head></head>");
					response.getWriter().println("<body>Exclusao realizada com sucesso</body>");
					response.getWriter().println("</html>");
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
					e1.getMessage();
					System.out.println("22222222222222222222");
					response.getWriter().println("<html>");
					response.getWriter().println("<head></head>");
					response.getWriter().println("<body>SQLException arroz</body>");
					response.getWriter().println("</html>");
				}
		}
		catch(IOException e)
		{
			response.getWriter().println("<html>");
			response.getWriter().println("<head></head>");
			response.getWriter().println("<body>IOException</body>");
			response.getWriter().println("</html>");
		}
		catch (ClassNotFoundException e1)
		{
			response.getWriter().println("<html>");
			response.getWriter().println("<head></head>");
			response.getWriter().println("<body>ClassNotFoundException</body>");
			response.getWriter().println("</html>");
			e1.printStackTrace();
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
			e1.getMessage();
			response.getWriter().println("<html>");
			response.getWriter().println("<head></head>");
			response.getWriter().println("<body>SQLException feijaum</body>");
			response.getWriter().println("</html>");
		}

		//float var = Float.parseFloat(request.getParameter("money");
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
