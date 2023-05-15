package com.project_nhapmon.connect;

import com.project_nhapmon.model.MonAn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class GoiMonService extends MySqlService {
    public GoiMonService(){
        super();
    }

    /**
     * Phương thức themMonAn được dùng để thêm dữ liệu món ăn vào danh sách thực đơn của bàn ăn ở
     * vị trí (maTang, maBan) vào bảng goimon trong cơ sở dữ liệu
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     * @param monAn Món ăn cần thêm
     * @param soLuong Số lượng món ăn cần thêm
     */
    public void themMonAn(String maTang, String maBan, MonAn monAn, int soLuong) {
        int soLuongBanDau = kiemTraMonAn(maTang, maBan, monAn.getMaMonAn());
        if (soLuongBanDau == 0) {
            try {
                String sql = "insert into goimon values (?,?,?,?,?,?)";
                PreparedStatement preStatement = conn.prepareStatement(sql);
                preStatement.setString(1, maTang);
                preStatement.setString(2, maBan);
                preStatement.setString(3, monAn.getMaMonAn());
                preStatement.setString(4, monAn.getTenMonAn());
                preStatement.setInt(5, monAn.getDonGia());
                preStatement.setInt(6, soLuong);
                preStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            thayDoiSoLuongMonAn(maTang, maBan, monAn.getMaMonAn(), soLuong + soLuongBanDau);
        }
    }

    /**
     * Phương thức xoaMonAn được dùng để xóa dữ liệu món ăn từ danh sách thực đơn của bàn ăn ở
     * vị trí (maTang, maBan) ở bảng goimon trong cơ sở dữ liệu
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     * @param maMonAn Món ăn cần xóa
     */
    public void xoaMonAn(String maTang, String maBan, String maMonAn) {
        try {
            String sql = "delete from goimon where MaTang=? and MaBan=? and MaMonAn=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, maTang);
            preStatement.setString(2, maBan);
            preStatement.setString(3, maMonAn);
            preStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức xoaMonAn được dùng để xóa tất cả món ăn từ danh sách thực đơn của bàn ăn ở
     * vị trí (maTang, maBan) ở bảng goimon trong cơ sở dữ liệu
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     */
    public void xoaMonAn(String maTang, String maBan) {
        try {
            String sql = "delete from goimon where MaTang=? and MaBan=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, maTang);
            preStatement.setString(2, maBan);
            preStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức kiemTraMonAn được dùng để kiểm tra xem maMonAn có tồn tại trong danh sách thực đơn
     * của bàn ăn ở vị trí (maTang, maBan) ở bảng goimon trong cơ sở dũ liệu
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     * @param maMonAn Mã món ăn cần kiểm tra
     * @return Số lượng của maMonAn xuất hiện trong danh sách thực đơn của bàn
     */
    public int kiemTraMonAn(String maTang, String maBan, String maMonAn) {
        int soLuongBanDau = 0;
        try {
            String sql = "select * from goimon where MaTang=? and MaBan=? and MaMonAn=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, maTang);
            preStatement.setString(2, maBan);
            preStatement.setString(3, maMonAn);
            ResultSet result = preStatement.executeQuery();
            if (result.next()) {
                return result.getInt(6);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuongBanDau;
    }

    /**
     * Phương thức laySoLuongMonAn được dùng để lấy số loại món ăn trong thực đơn của bàn ăn ở vị
     * trí (maTang, maBan) trong bảng goimon trong cơ sở dữ liệu
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     * @return Số lượng loại món ăn theo vị trí bàn
     */
    public int laySoLuongMonAn(String maTang, String maBan) {
        int soMonAn = 0;
        try {
            String sql = "select * from goimon where MaTang=? and MaBan=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, maTang);
            preStatement.setString(2, maBan);
            ResultSet result = preStatement.executeQuery();
            while (result.next()) {
                soMonAn++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soMonAn;
    }

    /**
     * Phương thức thayDoiSoLuongMonAn được dùng để thay đổi số lượng món ăn theo maMonAn ở vị trí (maTang, maBan)
     * trong bảng goimon trong cơ sở dữ liệu
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     * @param maMonAn Mã món ăn muốn thay đổi số lượng
     * @param soLuong Số lượng muốn sau khi thay đổi
     */
    public void thayDoiSoLuongMonAn(String maTang, String maBan, String maMonAn, int soLuong) {
        try {
            String sql = "update goimon set SoLuong=? where MaTang=? and MaBan=? and MaMonAn=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, soLuong);
            preStatement.setString(2, maTang);
            preStatement.setString(3, maBan);
            preStatement.setString(4, maMonAn);
            preStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức layDsThucDonTheoBan được dùng để lấy danh sách các món ăn trong thực đon của bàn ăn ở
     * vị trí (maTang, maBan) trong bảng goimon trong cơ sở dữ liệu
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     * @return danh sách thực đơn theo bàn ăn
     */
    public Vector<MonAn> layDsThucDonTheoBan(String maTang, String maBan) {
        Vector<MonAn> dsThucDonTheoBan = new Vector<>();
        try {
            String sql = "select * from goimon where MaTang=? and MaBan=?";
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, maTang);
            preStatement.setString(2, maBan);
            ResultSet result = preStatement.executeQuery();
            while (result.next()) {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn(result.getString(3));
                monAn.setTenMonAn(result.getString(4));
                monAn.setDonGia(result.getInt(5));
                monAn.setSoLuong(result.getInt(6));
                dsThucDonTheoBan.add(monAn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsThucDonTheoBan;
    }
}
