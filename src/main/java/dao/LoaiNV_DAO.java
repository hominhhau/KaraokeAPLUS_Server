
package dao;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author HO MINH HAU
 */
public class LoaiNV_DAO {
    public LoaiNV_DAO() {

    }

    public ArrayList<LoaiNhanVien> getAllLoaiNhanVien() {
        ArrayList<LoaiNhanVien> ds = new ArrayList<LoaiNhanVien>();
        PreparedStatement sta = null;

        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT *FROM LoaiNhanVien";

            sta = con.prepareStatement(sql);
            ResultSet rs = sta.executeQuery();
            while (rs.next()) {
                String maLoai = rs.getString(1);
                String tenLoai = rs.getString(2);

                LoaiNhanVien lnv = new LoaiNhanVien(maLoai, tenLoai);
                ds.add(lnv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

}
