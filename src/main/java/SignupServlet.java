import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String dbDriver = "oracle.jdbc.OracleDriver";
        String dbUrl= "jdbc:oracle:thin:@localhost:1521/orcl";
        String user = "hr";
        String password = "hra";
        String email = req.getParameter("e-mail");
        String pass = req.getParameter("password");
            try {
                //register driver
                Class.forName(dbDriver);
                System.out.println("==============Connection successful==============");
                Connection cn = DriverManager.getConnection(dbUrl, user, password);
                String sql = "insert into projectTCS (email, password) values (?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1,email);
                pst.setString(2,pass);
                pst.executeQuery();
                RequestDispatcher rd = req.getRequestDispatcher("login.html");
                rd.forward(req,resp);
            }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
