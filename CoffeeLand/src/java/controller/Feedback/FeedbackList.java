package controller.Feedback;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Feedback.Feedback;
import model.Feedback.FeedbackDAO;

public class FeedbackList extends HttpServlet {
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        FeedbackDAO u = new FeedbackDAO();
        int PPP = 10; // Số lượng sản phẩm trên mỗi trang

        // Lấy tham số trang từ request
        int page = 1;
        String xpage = request.getParameter("page");
        if (xpage != null) {
            page = Integer.parseInt(xpage);
        }

        // Lấy tổng số trang
        String totalFeedback = u.getTotalFeedback();
        int totalPages = (Integer.parseInt(totalFeedback) + PPP - 1) / PPP;
        // Lấy danh sách danh mục sản phẩm cho trang hiện tại
        List<Feedback> lstCat = u.pagingFeedbacks(page, PPP);

        // Lưu các giá trị vào session để sử dụng trong JSP
        session.setAttribute("lst", lstCat);
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", totalPages);

        // Redirect tới trang JSP hiển thị danh sách danh mục sản phẩm
        response.sendRedirect("/CoffeeLand/server/feedback/FeedbackManage.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        FeedbackDAO u = new FeedbackDAO();
        
        List<Feedback> product = u.getListProduct();
        int PPP = 10; // Số lượng sản phẩm trên mỗi trang

        // Lấy tham số trang từ request
        int page = 1;
        String xpage = request.getParameter("page");
        if (xpage != null) {
            page = Integer.parseInt(xpage);
        }

        // Lấy tổng số trang
        String totalFeedback = u.getTotalFeedback();
        int totalPages = (Integer.parseInt(totalFeedback) + PPP - 1) / PPP;
        // Lấy danh sách danh mục sản phẩm cho trang hiện tại
        List<Feedback> lstCat = u.pagingFeedbacks(page, PPP);

        // Lưu các giá trị vào session để sử dụng trong JSP
        session.setAttribute("listproduct", product);
        session.setAttribute("lst", lstCat);
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", totalPages);

        // Redirect tới trang JSP hiển thị danh sách danh mục sản phẩm
        response.sendRedirect("/CoffeeLand/server/feedback/FeedbackManage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin sản phẩm được chọn từ form
        String productName = request.getParameter("productName");
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> listFeedback = feedbackDAO.getListFeedback();
      //  List<Feedback> product = feedbackDAO.getListProduct();

        request.setAttribute("lst", listFeedback);
       // request.setAttribute("listproduct", product);

        // Nếu có productName, tức là người dùng đã chọn sản phẩm để lọc
//        if (productName != null && !productName.isEmpty()) {
//         
//
//            // Lọc danh sách phản hồi theo tên sản phẩm
//            List<Feedback> filteredFeedbackList = feedbackDAO.getFeedbacksByProductName(productName);
//
//            // Set danh sách phản hồi đã lọc vào request để hiển thị trên trang
//            request.setAttribute("lst", filteredFeedbackList);
//        }

        // Kiểm tra xem người dùng có gửi yêu cầu cập nhật trạng thái không
        String feedbackId = request.getParameter("feedbackId");
        String statusId = request.getParameter("statusId");

        if (feedbackId != null && statusId != null) {
        
            feedbackDAO.updateFeedbackStatus(feedbackId, statusId);
        }

        // Chuyển hướng trở lại trang hiển thị danh sách phản hồi sau khi đã xử lý
        response.sendRedirect(request.getContextPath() + "/feedbacklist");
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Feedback List";
    }
}
