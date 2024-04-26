/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.LoaiPhong;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 84343
 */
public class LoaiPhong_DAO {

    private ArrayList<LoaiPhong> ls;

    public LoaiPhong_DAO() {
        ls = new ArrayList<>();
    }

    public ArrayList<LoaiPhong> getalltbLoaiPhong() {
        ArrayList<LoaiPhong> dslp = new ArrayList<LoaiPhong>();
        try {
            ConnectDB db = ConnectDB.getInstance();
            db.connect();
            Connection con = db.getConnection();
            String sql = "SELECT *FROM LoaiPhong";
            Statement statement = con.createStatement();

            // Thực thi câu lệnh SQL trả về đối tượng ResultSet
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maLoaiPhong = rs.getString(1);
                String tenLoaiPhong = rs.getString(2);
                Double Gia = rs.getDouble(3);
                LoaiPhong lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, Gia);
                dslp.add(lp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dslp;
    }

    public ArrayList<LoaiPhong> getLoaiPhongTheoMaLoai(String id) {
        ArrayList<LoaiPhong> dsLP = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT *FROM LoaiPhong WHERE maLoaiPhong=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maPhong = rs.getString(1);
                String tenLoai = rs.getString(2);
                Double gia = rs.getDouble(3);
                LoaiPhong lp = new LoaiPhong(maPhong, tenLoai, gia);
                dsLP.add(lp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsLP;
    }
}
