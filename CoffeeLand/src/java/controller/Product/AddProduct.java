package controller.Product;

import model.Product.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Category.Category;
import model.Category.CategoryDAO;
import model.Size.*;

@MultipartConfig
public class AddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);

        CategoryDAO cDAO = new CategoryDAO();
        SizeDAO sDAO = new SizeDAO();

        List<Size> lstSize = sDAO.getListSize();
        List<Category> lstCategory = cDAO.getListCategory(-1);
               
        session.removeAttribute("x");
            session.removeAttribute("priceS");
            session.removeAttribute("priceM");
            session.removeAttribute("priceL");
            session.removeAttribute("filename");
            session.removeAttribute("lstSize");
            session.removeAttribute("lstCategory");
            session.removeAttribute("splst");
            session.removeAttribute("alert");
            session.removeAttribute("categoryId");
        
        session.setAttribute("lstSize", lstSize);
        session.setAttribute("lstCategory", lstCategory);
        response.sendRedirect("/CoffeeLand/server/product/AddProduct.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession(false);
        ProductDAO pDAO = new ProductDAO();
        CategoryDAO cDAO = new CategoryDAO();
        SizeDAO sDAO = new SizeDAO();

        String userId = request.getParameter("userId");
        String productName = request.getParameter("productName").trim();
        String categoryId = request.getParameter("categoryId");
        String description = request.getParameter("description");
        String thumbnail = request.getParameter("thumbnail");
        String statusId = request.getParameter("statusId");
        
        String priceS = request.getParameter("priceS");
        String priceM = request.getParameter("priceM");
        String priceL = request.getParameter("priceL");
        
        List<Size> lstSize = sDAO.getListSize();
        List<Category> lstCategory = cDAO.getListCategory(-1);
        
        Product x = new Product(userId, productName, description, categoryId, statusId, thumbnail);
        List<Product> splst = new ArrayList<>();
        
        
        if (pDAO.isNameExist(productName)) {
            String alert = "Tên sản phẩm " + productName + " đã tồn tại!";
            session.setAttribute("x", x);
            session.setAttribute("priceS", priceS);
            session.setAttribute("priceM", priceM);
            session.setAttribute("priceL", priceL);
            session.setAttribute("filename", thumbnail);
            session.setAttribute("lstSize", lstSize);
            session.setAttribute("lstCategory", lstCategory);
            session.setAttribute("splst", splst);
            session.setAttribute("alert", alert);
            session.setAttribute("categoryId", categoryId);
            String url = "/CoffeeLand/server/product/AddProduct.jsp";
            response.sendRedirect(url);
        }else if(priceS.equals("") && priceM.equals("") && priceL.equals("")){
            String alert = "Hãy điền giá tiền của sản phẩm!";
            session.setAttribute("x", x);
            session.setAttribute("priceS", priceS);
            session.setAttribute("priceM", priceM);
            session.setAttribute("priceL", priceL);
            session.setAttribute("lstSize", lstSize);
            session.setAttribute("lstCategory", lstCategory);
            session.setAttribute("filename", thumbnail);
            session.setAttribute("alert", alert);
            session.setAttribute("categoryId", categoryId);
            String url = "/CoffeeLand/server/product/AddProduct.jsp";
            response.sendRedirect(url);  
        }else{
            try {
                if (!priceS.equals("")) {
                    String sizeS="1";
                    int priceS_int = Integer.parseUnsignedInt(priceS);
                    Product spS = new Product(sizeS, priceS);
                    splst.add(spS);
                }else {
                    priceS = "0";
                    String sizeS="1";
                    int priceS_int = Integer.parseUnsignedInt(priceS);
                    Product spS = new Product(sizeS, priceS);
                    splst.add(spS);
                }
                if (!priceM.equals("")) {
                    String sizeM="2";
                    int priceM_int = Integer.parseUnsignedInt(priceM);
                    Product spM = new Product(sizeM, priceM);
                    splst.add(spM);
                }else{
                    priceM = "0";
                    String sizeM="2";
                    int priceM_int = Integer.parseUnsignedInt(priceM);
                    Product spM = new Product(sizeM, priceM);
                    splst.add(spM);
                }
                if (!priceL.equals("")) {
                    String sizeL="3";
                    int priceL_int = Integer.parseUnsignedInt(priceL);
                    Product spL = new Product(sizeL, priceL);
                    splst.add(spL);
                }else{
                    priceL = "0";
                    String sizeL="3";
                    int priceL_int = Integer.parseUnsignedInt(priceL);
                    Product spL = new Product(sizeL, priceL);
                    splst.add(spL);
                }
                
                pDAO.insert(x,splst);
                String alert = "Thêm sản phẩm " + productName + " thành công!";
                List<Product> lst = pDAO.getListProductServer();
                session.setAttribute("splst", splst);
                session.setAttribute("lstProduct", lst);
                session.setAttribute("lstSize", lstSize);
                session.removeAttribute("filename");
                session.removeAttribute("priceS");
                session.removeAttribute("priceM");
                session.removeAttribute("priceL");
                session.setAttribute("lstCategory", lstCategory);
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/product/ProductManage.jsp";
                response.sendRedirect(url);

            } catch (Exception e) {
                String alert = "Giá sản phẩm " + productName + " phải là số nguyên lớn hơn 0!";
                session.setAttribute("priceS", priceS);
                session.setAttribute("priceM", priceM);
                session.setAttribute("priceL", priceL);
                session.setAttribute("x", x);
                session.setAttribute("splst", splst);
                session.setAttribute("lstSize", lstSize);
                session.setAttribute("lstCategory", lstCategory);
                session.setAttribute("categoryId", categoryId);
                session.setAttribute("filename", thumbnail);
                session.setAttribute("alert", alert);
                String url = "/CoffeeLand/server/product/AddProduct.jsp";
                response.sendRedirect(url);
            }
        }
    }
}
