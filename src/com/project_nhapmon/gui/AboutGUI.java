package com.project_nhapmon.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Objects;

public class AboutGUI extends JDialog {
    // Các thuộc tính

    // Triển khai phương thức khởi tạo

    /**
     * Phương thức AboutGUI được dùng để khởi tạo một của sổ dialog
     *
     * @param parent Frame nơi mà dialog được gọi tới để hiển thị
     * @param title  Tiêu đề của của sổ dialog
     */
    public AboutGUI(Frame parent, String title) {
        super(parent, title, true);
        addControls();
    }

    // Triển khai phương thức addControls

    /**
     * Phương thức addControls được dùng để thêm các Component vào cửa sổ dialog chính
     */
    private void addControls() {
        // Tạo khu vực hiển thị tên project
        JPanel pnThongTinProject = new JPanel();
        pnThongTinProject.setLayout(new BorderLayout());
        JLabel lblThongTin = new JLabel("Project Nhập môn Java: Quản lý nhà hàng\n");
        lblThongTin.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        lblThongTin.setVerticalAlignment(JLabel.CENTER);
        lblThongTin.setHorizontalAlignment(JLabel.CENTER);
        pnThongTinProject.add(lblThongTin, BorderLayout.CENTER);

        // Tạo khu vực hiển thị thông tin tác giả
        JPanel pnThongTinTacGia = new JPanel();
        pnThongTinTacGia.setLayout(new BoxLayout(pnThongTinTacGia, BoxLayout.Y_AXIS));
        pnThongTinTacGia.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        JPanel pnLblFullName = new JPanel();
        pnLblFullName.setLayout(new BoxLayout(pnLblFullName, BoxLayout.X_AXIS));
        JLabel lblFullName = new JLabel("Tên tác giả: Phạm Ngọc Hải");
        lblFullName.setHorizontalAlignment(JLabel.CENTER);
        lblFullName.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 16));
        pnLblFullName.add(lblFullName);
        JPanel pnLblMSV = new JPanel();
        pnLblMSV.setLayout(new BoxLayout(pnLblMSV, BoxLayout.X_AXIS));
        JLabel lblMSV = new JLabel("MSSV: 20207601");
        lblMSV.setHorizontalAlignment(JLabel.CENTER);
        lblMSV.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 16));
        pnLblMSV.add(lblMSV);
        JPanel pnLblClass = new JPanel();
        pnLblClass.setLayout(new BoxLayout(pnLblClass, BoxLayout.X_AXIS));
        JLabel lblClass = new JLabel("Lớp: IT-LTU 02-K65");
        lblClass.setHorizontalAlignment(JLabel.CENTER);
        lblClass.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 16));
        pnLblClass.add(lblClass);
        JPanel pnLblSchool = new JPanel();
        pnLblSchool.setLayout(new BoxLayout(pnLblSchool, BoxLayout.X_AXIS));
        JLabel lblSchool = new JLabel("Trường Công nghệ Thông tin và Truyền thông - HUST");
        lblSchool.setHorizontalAlignment(JLabel.CENTER);
        lblSchool.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 16));
        pnLblSchool.add(lblSchool);
        JPanel pnLblGithub = new JPanel();
        pnLblGithub.setLayout(new BoxLayout(pnLblGithub, BoxLayout.X_AXIS));
        JLabel lblGithub = new JLabel("Github");
        lblGithub.setForeground(Color.BLUE.darker());
        lblGithub.setHorizontalAlignment(JLabel.CENTER);
        lblGithub.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 16));
        lblGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblGithub.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try{
                    Desktop.getDesktop().browse(new URI("https://github.com/haiphamcoder"));
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblGithub.setText("<html><a href=''>Github</a></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblGithub.setText("Github");
            }
        });
        pnLblGithub.add(lblGithub);

        pnThongTinTacGia.add(pnLblFullName);
        pnThongTinTacGia.add(pnLblMSV);
        pnThongTinTacGia.add(pnLblClass);
        pnThongTinTacGia.add(pnLblSchool);
        pnThongTinTacGia.add(pnLblGithub);

        // Tạo khu vực hiển thị logo
        JPanel pnLogo = new JPanel();
        pnLogo.setLayout(new BorderLayout());
        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(Objects.requireNonNull(AboutGUI.class.getResource("/com/project_nhapmon/images/restaurant.png"))));
        lblLogo.setHorizontalTextPosition(JLabel.CENTER);
        lblLogo.setVerticalTextPosition(JLabel.CENTER);
        lblLogo.setVerticalAlignment(JLabel.CENTER);
        lblLogo.setHorizontalAlignment(JLabel.CENTER);
        pnLogo.add(lblLogo, BorderLayout.CENTER);

        //
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnMain.add(pnThongTinProject, BorderLayout.NORTH);
        pnMain.add(pnThongTinTacGia, BorderLayout.CENTER);
        pnMain.add(pnLogo, BorderLayout.SOUTH);
        con.add(pnMain);
    }

    // Triển khai phương thức showDialog

    /**
     * Phương thức showDialog được dùng để hiển thị dialog lên màn hình
     *
     * @param parent Frame nơi mà hộp thoại sẽ hiển thị
     */
    public void showDialog(Frame parent) {
        this.setIconImage(new ImageIcon(Objects.requireNonNull(AboutGUI.class.getResource("/com/project_nhapmon/images/about.png"))).getImage());
        this.setSize(600, 400);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setVisible(true);
    }
}
