/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Feedback;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.FeedbackClient.Feedback;
import model.FeedbackClient.FeedbackDAO;

/**
 *
 * @author dell
 */
@MultipartConfig
public class EditFeedbackServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditFeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditFeedbackServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/client/feedback/EditFeedback.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] byt = new byte[is.available()];
            is.read();

            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId_raw = request.getParameter("productId");
        int productId = Integer.parseInt(productId_raw);
        String userId_raw = request.getParameter("userId");
        int userId = Integer.parseInt(userId_raw);
        String oldTime = request.getParameter("postedTime");

        
        
            // Lấy nội dung phản hồi và đánh giá
            String content = request.getParameter("feedbackContent");
            String rating = request.getParameter("rating");
            int rate = 0; // Giá trị mặc định khi không có dữ liệu được gửi đi

            // Chuyển đổi đánh giá thành số nguyên nếu có
            if (rating != null && !rating.isEmpty()) {
                rate = Integer.parseInt(rating);
            }

            // Lấy thời gian phản hồi hiện tại
            Timestamp feedbackTime = new Timestamp(System.currentTimeMillis());

            // Lấy phần tử được tải lên (nếu có)
            Part part = request.getPart("imageUpload");
            String fileName = null;
        if (part != null) {
            fileName = part.getSubmittedFileName();
            if (fileName != null && !fileName.isEmpty()) {

            // Kiểm tra hợp lệ của tệp hình ảnh
            if (!isValidImage(fileName)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid image file.");
                return;
            }

            // Thư mục lưu trữ tệp tải lên
            String uploadDir = "/assests/image/feedback";
            String realPath = getServletContext().getRealPath(uploadDir);

            // Tạo thư mục nếu nó không tồn tại
            File uploadDirFile = new File(realPath);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // Đường dẫn lưu trữ tệp
            String filePath = uploadDir + "/" + fileName;

            // Ghi tệp tải lên vào ổ đĩa
            part.write(realPath + File.separator + fileName);
}
        }
            // Sửa phản hồi trong cơ sở dữ liệu
            FeedbackDAO fbD = new FeedbackDAO();
            fbD.editFeedback(userId, productId, oldTime, rate, fileName, content, feedbackTime);

            // Chuyển hướng người dùng đến trang thêm phản hồi mới
            response.sendRedirect("/CoffeeLand/client/feedback/AddNewFeedback.jsp?userId=" + userId + "&productId=" + productId);

            
    }

    private boolean isValidImage(String fileName) {
        // Thực hiện kiểm tra dựa trên phần mở rộng hoặc nội dung của tệp
        // Ví dụ: kiểm tra phần mở rộng (.jpg, .png, .gif) hoặc kiểm tra dữ liệu đầu vào để xác định loại tệp
        // Trong trường hợp này, tôi sẽ chỉ kiểm tra phần mở rộng
        return fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif"));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
