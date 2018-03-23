import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author   */
public class RegisterUser extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
try{
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    Connection con=null;
    String n=request.getParameter("userName");
    String p=request.getParameter("userPass");
    String e=request.getParameter("userEmail");
    String c=request.getParameter("userCountry");
    
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/reguser","root","password");
   
    PreparedStatement ps = null;
    try {
        ps = con.prepareStatement("insert into registeruser values(?,?,?,?)");
    } catch (SQLException ex) {
        Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
    }
    ps.setString(1,n);
    ps.setString(2,p);
    ps.setString(3,e);
    ps.setString(4,c);
    int i = ps.executeUpdate();
    
    if( i > 0)
    {
        out.print("You are successfully registered...");
    }
    out.close();

}       catch (SQLException ex) {
            Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
