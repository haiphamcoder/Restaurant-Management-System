package com.project_nhapmon.launch;

import com.project_nhapmon.connect.MySqlService;
import com.project_nhapmon.gui.QuanLyNhaHangGUI;

import javax.swing.*;

public class Launch {
    public static void main(String[] args) {
        MySqlService database = new MySqlService();
        if (database.conn != null) {
            QuanLyNhaHangGUI ui = new QuanLyNhaHangGUI("Quản lý nhà hàng");
            ui.showWindow();
        } else {
            JOptionPane.showMessageDialog(null, "Kết nối cơ sở dữ liệu không thành công!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
