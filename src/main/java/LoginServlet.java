import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginServlet extends HttpServlet {
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
            String sql = "select * from projectTCS where email = ? and password = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                RequestDispatcher rd=req.getRequestDispatcher("products.html");
                rd.forward(req,resp);
            }else{
                RequestDispatcher rd=req.getRequestDispatcher("login.html");
                rd.forward(req,resp);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}