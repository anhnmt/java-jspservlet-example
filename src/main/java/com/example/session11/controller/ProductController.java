package com.example.session11.controller;

import com.example.session11.dao.ICategory;
import com.example.session11.dao.IProduct;
import com.example.session11.dao.impl.CategoryImpl;
import com.example.session11.dao.impl.ProductImpl;
import com.example.session11.entity.Category;
import com.example.session11.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICategory categoryDAO;
    private IProduct productDAO;

    public void init() {
        categoryDAO = new CategoryImpl();
        productDAO = new ProductImpl();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";

        try {
            switch (action) {
                case "create":
                    create(request, response);
                    break;
                case "store":
                    store(request, response);
                    break;
                case "edit":
                    edit(request, response);
                    break;
                case "update":
                    update(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                default:
                    index(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> list = productDAO.findAll();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ProId"));
        Product product = productDAO.findByID(id);
        request.setAttribute("product", product);

        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void store(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String ProName = request.getParameter("ProName");
        Integer CateId = Integer.parseInt(request.getParameter("CateId"));
        String Producer = request.getParameter("Producer");
        Integer YearMaking = Integer.parseInt(request.getParameter("YearMaking"));
        LocalDate ExpireDate = LocalDate.parse(request.getParameter("ExpireDate"));
        Integer Quantity = Integer.parseInt(request.getParameter("Quantity"));
        Double Price = Double.parseDouble(request.getParameter("Price"));
        Integer Status = 1;

        Product product = new Product(
                ProName,
                CateId,
                Producer,
                YearMaking,
                ExpireDate,
                Quantity,
                Price,
                Status
        );
        productDAO.create(product);
        response.sendRedirect("ProductController");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Integer ProId = Integer.parseInt(request.getParameter("ProId"));
        String ProName = request.getParameter("ProName");
        Integer CateId = Integer.parseInt(request.getParameter("CateId"));
        String Producer = request.getParameter("Producer");
        Integer YearMaking = Integer.parseInt(request.getParameter("YearMaking"));
        LocalDate ExpireDate = LocalDate.parse(request.getParameter("ExpireDate"));
        Integer Quantity = Integer.parseInt(request.getParameter("Quantity"));
        Double Price = Double.parseDouble(request.getParameter("Price"));
        Integer Status = 1;

        Product product = new Product(
                ProId,
                ProName,
                CateId,
                Producer,
                YearMaking,
                ExpireDate,
                Quantity,
                Price,
                Status
        );
        productDAO.update(product);
        response.sendRedirect("ProductController");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("ProId"));
        productDAO.delete(id);
        response.sendRedirect("ProductController");
    }

}
