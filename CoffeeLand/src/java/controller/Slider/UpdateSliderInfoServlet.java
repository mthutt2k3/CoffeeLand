/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Slider;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import model.Slider.Slider;
import model.Slider.SliderDAO;

/**
 *
 * @author dell
 */
@MultipartConfig
public class UpdateSliderInfoServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateSliderInfoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSliderInfoServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/CoffeeLand/server/slider/UpdateSlider.jsp").forward(request, response);
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
        Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();

        // Kiểm tra tính hợp lệ của tệp hình ảnh
        if (!isValidImage(fileName)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid image file.");
            return;
        }

        String uploadDir = "/assests/image/background";
        String realPath = getServletContext().getRealPath(uploadDir);

        // Tạo thư mục nếu nó không tồn tại
        File uploadDirFile = new File(realPath);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String filePath = uploadDir + "/" + fileName;
        part.write(realPath + File.separator + fileName);
        
        String sliderID_raw = request.getParameter("sliderID");
        int sliderID = Integer.parseInt(sliderID_raw);
        String orderS_raw = request.getParameter("orderS");
        String link = request.getParameter("link");

        // Kiểm tra tính hợp lệ của dữ liệu đầu vào
        if (!isValidInput(orderS_raw)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
            return;
        }

        int orderS = Integer.parseInt(orderS_raw);

        SliderDAO sd = new SliderDAO();
        Slider slider = new Slider();
        slider.setSliderID(sliderID);
        slider.setImage(fileName);
        slider.setLink(link);
        slider.setOrder(orderS);
        sd.updateSlider(slider);

        response.sendRedirect("/CoffeeLand/sliderlist");
    }
    private boolean isValidImage(String fileName) {
        // Thực hiện kiểm tra dựa trên phần mở rộng hoặc nội dung của tệp
        // Ví dụ: kiểm tra phần mở rộng (.jpg, .png, .gif) hoặc kiểm tra dữ liệu đầu vào để xác định loại tệp
        // Trong trường hợp này, tôi sẽ chỉ kiểm tra phần mở rộng
        return fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif"));
    }

// Kiểm tra tính hợp lệ của dữ liệu đầu vào
    private boolean isValidInput(String input) {
        // Thực hiện kiểm tra tính hợp lệ của dữ liệu đầu vào, ví dụ: kiểm tra xem nó có phải là một số nguyên dương không
        // Trong trường hợp này, tôi sẽ kiểm tra xem nó có phải là một số nguyên không âm
        try {
            int value = Integer.parseInt(input);
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
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
