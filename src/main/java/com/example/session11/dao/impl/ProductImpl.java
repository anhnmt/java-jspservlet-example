package com.example.session11.dao.impl;

import com.example.session11.dao.IProduct;
import com.example.session11.entity.Product;
import com.example.session11.util.DbUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProduct {
    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("select * from products where Status = 1");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product c = new Product(
                        rs.getInt("ProId"),
                        rs.getNString("ProName"),
                        rs.getInt("CateId"),
                        rs.getNString("Producer"),
                        rs.getInt("YearMaking"),
                        LocalDate.parse(rs.getString("ExpireDate")),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
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
    public List<Product> findByName(String name) {
        List<Product> list = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("select * from products where ProName like ? and Status = 1");
            pstmt.setNString(1, "%" + name + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product c = new Product(
                        rs.getInt("ProId"),
                        rs.getNString("ProName"),
                        rs.getInt("CateId"),
                        rs.getNString("Producer"),
                        rs.getInt("YearMaking"),
                        LocalDate.parse(rs.getString("ExpireDate")),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
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
    public boolean create(Product c) {
        boolean result = false;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("insert into products values (?,?,?,?,?,?,?,?)");
            pstmt.setNString(1, c.getProName());
            pstmt.setInt(2, c.getCateId());
            pstmt.setNString(3, c.getProducer());
            pstmt.setInt(4, c.getYearMaking());
            pstmt.setDate(5, Date.valueOf(c.getExpireDate()));
            pstmt.setInt(6, c.getQuantity());
            pstmt.setDouble(7, c.getPrice());
            pstmt.setInt(8, c.getStatus());

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
    public Product findByID(Integer id) {
        Product c = null;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("select * from products where ProId = ? and Status = 1");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                c = new Product(
                        rs.getInt("ProId"),
                        rs.getNString("ProName"),
                        rs.getInt("CateId"),
                        rs.getNString("Producer"),
                        rs.getInt("YearMaking"),
                        LocalDate.parse(rs.getString("ExpireDate")),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
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
    public boolean update(Product c) {
        boolean result = false;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("update products set ProName=?,CateId=?,Producer=?,YearMaking=?,ExpireDate=?,Quantity=?,Price=?,Status=? where ProId=?");
            pstmt.setNString(1, c.getProName());
            pstmt.setInt(2, c.getCateId());
            pstmt.setNString(3, c.getProducer());
            pstmt.setInt(4, c.getYearMaking());
            pstmt.setDate(5, Date.valueOf(c.getExpireDate()));
            pstmt.setInt(6, c.getQuantity());
            pstmt.setDouble(7, c.getPrice());
            pstmt.setInt(8, c.getStatus());
            pstmt.setInt(9, c.getProId());

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
            pstmt = con.prepareStatement("update products set Status=0 where ProId=?");
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

    @Override
    public boolean deleteByCateId(Integer id) {
        boolean result = false;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DbUtils.openConnection();
        try {
            pstmt = con.prepareStatement("update products set Status=0 where CateId=?");
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
