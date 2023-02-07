package servlet_prc_price.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet_prc_price.dao.ProductDao;
import servlet_prc_price.dto.Product;

public class ProductController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String name =req.getParameter("name");
		 String brand =req.getParameter("brand");
		 double price=Double.parseDouble(req.getParameter("price"));
		 String manufacturer=req.getParameter("manufacturer");
		 String place=req.getParameter("place");
		 
		 ServletContext context=getServletContext();
		 int cgst= Integer.parseInt(context.getInitParameter("CGST"));
		 
		 ServletConfig config=getServletConfig();
		 double kar=Double.parseDouble(config.getInitParameter("kar"));
		 
		 double tn=Double.parseDouble(config.getInitParameter("tn"));
		 
		 
		 Product product=new Product();
		 product.setName(name);
		 product.setBrand(brand);
		 product.setPrice(price);
		 product.setManufacturer(manufacturer);
		 product.setCgst(cgst);
		 product.setState(place);
		 PrintWriter out=resp.getWriter();
		 
		 if(place.equals("kar")) {
			 double result=price+(price*(kar+cgst)/100);
			 product.setSgst(result); 
			 out.print(result);
		 }
		 else {
			 double result=price+(price*(tn+cgst)/100);
			 product.setSgst(result); 
			 out.print(result);
		 }
		 
		 ProductDao productDao=new ProductDao();
		 productDao.saveProduct(product);
		 System.out.println("Product added Successfully");
		 
		
		 
		 
		 
	}

}
