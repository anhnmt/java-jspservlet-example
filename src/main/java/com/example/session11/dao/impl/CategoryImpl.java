package com.example.session11.dao.impl;

import com.example.session11.dao.ICategory;
import com.example.session11.entity.Category;
import com.example.session11.util.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements ICategory {
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("select * from categories where Status = 1");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("CateId"),
                        rs.getNString("CateName"),
                        rs.getNString("Description"),
                        rs.getInt("Status")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(con, pstmt, rs);
        }

        return list;
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> list = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("select * from categories where CateName like ? and Status = 1");
            pstmt.setNString(1, "%" + name + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("CateId"),
                        rs.getNString("CateName"),
                        rs.getNString("Description"),
                        rs.getInt("Status")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(con, pstmt, rs);
        }

        return list;
    }

    @Override
    public boolean create(Category c) {
        boolean result = false;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("insert into categories values (?,?,?)");
            pstmt.setNString(1, c.getCateName());
            pstmt.setNString(2, c.getDescription());
            pstmt.setInt(3, c.getStatus());

            int i = pstmt.executeUpdate();
            if (i > 0)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(con, pstmt, rs);
        }

        return result;
    }

    @Override
    public Category findByID(Integer id) {
        Category c = null;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("select * from categories where CateId = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                c = new Category(
                        rs.getInt("CateId"),
                        rs.getNString("CateName"),
                        rs.getNString("Description"),
                        rs.getInt("Status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(con, pstmt, rs);
        }

        return c;
    }

    @Override
    public boolean update(Category c) {
        boolean result = false;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("update categories set CateName=?,Description=?,Status=? where CateId=?");
            pstmt.setNString(1, c.getCateName());
            pstmt.setNString(2, c.getDescription());
            pstmt.setInt(3, c.getStatus());
            pstmt.setInt(4, c.getCateId());

            int i = pstmt.executeUpdate();
            if (i > 0)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(con, pstmt, rs);
        }

        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result = false;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("update categories set Status=0 where CateId=?");
            pstmt.setInt(1, id);
            int i = pstmt.executeUpdate();
            if (i > 0)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeAll(con, pstmt, rs);
        }

        return result;
    }
}
