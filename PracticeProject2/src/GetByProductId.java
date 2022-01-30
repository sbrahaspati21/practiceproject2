

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetByProductId")
public class GetByProductId extends HttpServlet {

	 private static final long serialVersionUID = 1L;
     
	    /**
	* @see HttpServlet#HttpServlet()
	*/
	    public GetByProductId() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    
	   

	        /**
	         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	         */
	        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	                // TODO Auto-generated method stub
	                
	                try {
	                        PrintWriter out = response.getWriter();
	                        out.println("<html><body>");
	                         
	                        InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
	                        Properties props = new Properties();
	                        //props.load(in);
	                        
	                        //connection information
	                        DBConnection conn = new DBConnection("jdbc:mysql://localhost:3306/db_world", "root", "ROOT");
	                        Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	                      //  stmt.executeUpdate("insert into products (productid, price, date_added) values ('New Product', 17800.00, now())");
	                        
	                        //query the table and get all information
	                        ResultSet rst = stmt.executeQuery("select * from product");
	                        
	                        //find what the user typed into the search box
	                        String productid = request.getParameter("productid");
	                        
	                        //user hasn't typed anything so display table
	                        if(productid == null)
	                        {	
		                        out.println("The following are the elements in the products table" + "<Br>" + "<Br>");
		                        //simple while loop to print all elements in table
		                        while (rst.next()) {
		                                out.println("Product Id "+rst.getInt("productid") + ":	 " +" Product name : "+ rst.getString("productname") + " 	" 
		                                +" Product Price : "+ rst.getDouble("productprice") + "<Br>");
		                        }
	                        }
	                        //user typed something
	                        else 
	                        {
	                        	//select the row corresponding to the id number
	                        	String sql_res= "select * from products where productid=" + productid;
	                            ResultSet inTable = stmt.executeQuery(sql_res);
	                            
	                            //if not empty then print all product details
	                            if(inTable.next())
	                            	out.println("Product Id "+inTable.getInt("productid") + ":	 " +" Product name : "+ inTable.getString("productname") + "	 " 
	                            		+" Product Price : "+ inTable.getInt("productprice") + "<Br>");
	                            //empty so print error message
	                            else
	                            	out.println("There was no element with product ID: " + productid + " found in the table, please try again");
	                           
	                        }
	                    	
	                        stmt.close();
	                        
	                        
	                        
	                        out.println("</body></html>");
	                        conn.closeConnection();
	                        
	                } catch (ClassNotFoundException e) {
	                        e.printStackTrace();
	                } catch (SQLException e) {
	                        e.printStackTrace();
	                }
	        }

	        /**
	         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	         */
	        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	                // TODO Auto-generated method stub
	                doGet(request, response);
	        }
	    
}

class DBConnection {

    private Connection connection;
    
    public DBConnection(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException{
            
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(dbURL, user, pwd);
    }
    
    public Connection getConnection(){
            return this.connection;
    }
    
    public void closeConnection() throws SQLException {
            if (this.connection != null)
                    this.connection.close();
    }
}

	


