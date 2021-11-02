package com.example.session11.controller;

import com.example.session11.dao.ICategory;
import com.example.session11.dao.IProduct;
import com.example.session11.dao.impl.CategoryImpl;
import com.example.session11.dao.impl.ProductImpl;
import com.example.session11.entity.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CategoryController", value = "/CategoryController")
public class CategoryController extends HttpServlet {
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
                case "search":
                    search(request, response);
                    break;
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

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Keyword = request.getParameter("Keyword");
        request.setAttribute("search", Keyword);

        List<Category> list = categoryDAO.findByName(Keyword);
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/list.jsp");
        dispatcher.forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Category> list = categoryDAO.findAll();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/list.jsp");
        dispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/create.jsp");
        dispatcher.forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("CateId"));
        Category category = categoryDAO.findByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/edit.jsp");
        request.setAttribute("category", category);
        dispatcher.forward(request, response);

    }

    private void store(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String CateName = request.getParameter("CateName");
        String Description = request.getParameter("Description");
        Integer Status = 1;

        List<Category> check = categoryDAO.findByName(CateName);

        if (check == null) {
            Category category = new Category(
                    CateName,
                    Description,
                    Status
            );
            categoryDAO.create(category);
            response.sendRedirect("CategoryController");
        } else if (check.stream().anyMatch(x -> Objects.equals(x.getCateName(), CateName))) {
            request.setAttribute("error", "Category already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("category/create.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Integer CateId = Integer.parseInt(request.getParameter("CateId"));
        String CateName = request.getParameter("CateName");
        String Description = request.getParameter("Description");
        Integer Status = 1;

        List<Category> check = categoryDAO.findByName(CateName);

        if (check == null) {
            Category category = new Category(
                    CateId,
                    CateName,
                    Description,
                    Status
            );
            categoryDAO.update(category);
            response.sendRedirect("CategoryController");
        } else if (check.stream().anyMatch(x -> Objects.equals(x.getCateName(), CateName) && !Objects.equals(x.getCateId(), CateId))) {
            request.setAttribute("error", "Category already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("category/edit.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("CateId"));
        categoryDAO.delete(id);
        productDAO.deleteByCateId(id);
        response.sendRedirect("CategoryController");
    }

}
