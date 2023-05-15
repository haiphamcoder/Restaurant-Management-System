package com.project_nhapmon.gui;

import com.project_nhapmon.connect.BanAnService;
import com.project_nhapmon.connect.GoiMonService;
import com.project_nhapmon.connect.MenuService;
import com.project_nhapmon.model.MonAn;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Vector;

public class GoiMonGUI extends JFrame {
    // Các thuộc tính
    private final String maTang;
    private final String maBan;
    private DefaultTableModel dtmDsMenuNhaHang;
    private JTextField txtTimKiem;
    private Vector<MonAn> dsMonAnTrongMenu = new Vector<>();
    private final MenuService menuService = new MenuService();
    private JButton btnThem;
    private final GoiMonService goiMonService = new GoiMonService();
    private final DefaultTableModel dtmThucDonTheoBan;
    private final BanAnService banAnService = new BanAnService();
    private JTable tblDsMenuNhaHang;
    private JTextField txtSoLuong;
    private final JPanel[] pnStatusBanAn;

    // Triển khai phương thức khởi tạo

    /**
     * Phương thức GoiMonGUI được dùng để khởi tạo cửa sổ gọi món cho từng bàn ăn
     *
     * @param maTang            Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan             Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     * @param dtmThucDonTheoBan Bảng hiển thị danh sách thực đơn theo bàn ở cửa sổ chính
     * @param pnStatusBanAn     Panel hiển thị trạng thái bàn ăn ở cửa sổ chính (Green nếu trạng thái bàn ăn
     *                          là active/Red nếu trạng thái bàn ăn là busy)
     */
    public GoiMonGUI(String maTang, String maBan, DefaultTableModel dtmThucDonTheoBan, JPanel[] pnStatusBanAn) {
        super("Gọi món bàn " + maBan + " tầng " + maTang);
        this.maTang = maTang;
        this.maBan = maBan;
        this.dtmThucDonTheoBan = dtmThucDonTheoBan;
        this.pnStatusBanAn = pnStatusBanAn;
        addControls();
        addEvents();
    }

    // Triển khai phương thức addControls

    /**
     * Phương thức addControls được dùng thể thêm các Conponent vào cửa sổ
     */
    private void addControls() {
        // Tạo khu vực tìm kiếm
        JPanel pnTimKiem = new JPanel();
        pnTimKiem.setLayout(new FlowLayout());
        JLabel lblTimKiem = new JLabel();
        lblTimKiem.setIcon(new ImageIcon(Objects.requireNonNull(GoiMonGUI.class.getResource("/com/project_nhapmon/images/search.png"))));
        lblTimKiem.setVerticalTextPosition(JLabel.CENTER);
        lblTimKiem.setHorizontalTextPosition(JLabel.CENTER);
        txtTimKiem = new JTextField(15);
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);

        // Tạo khu vực hiển thị danh sách menu
        JPanel pnDsMenuNhaHang = new JPanel();
        pnDsMenuNhaHang.setLayout(new BorderLayout());
        dtmDsMenuNhaHang = new DefaultTableModel();
        dtmDsMenuNhaHang.addColumn("Mã món ăn");
        dtmDsMenuNhaHang.addColumn("Tên món ăn");
        dtmDsMenuNhaHang.addColumn("Đơn giá");
        tblDsMenuNhaHang = new JTable(dtmDsMenuNhaHang);
        tblDsMenuNhaHang.setDefaultEditor(Object.class, null);
        hienThiMenuNhaHang();
        JScrollPane crollDsMenuNhaHang = new JScrollPane(tblDsMenuNhaHang, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnDsMenuNhaHang.add(crollDsMenuNhaHang, BorderLayout.CENTER);

        // Tạo khu vực hiển thị chức năng thêm số lượng
        JPanel pnThemMonAn = new JPanel();
        pnThemMonAn.setLayout(new FlowLayout());
        Border border = BorderFactory.createLineBorder(Color.BLUE);
        TitledBorder titledBorder = new TitledBorder(border, "Thêm số lượng");
        titledBorder.setTitleFont(new Font(Font.SERIF, Font.BOLD, 15));
        pnThemMonAn.setBorder(titledBorder);
        JLabel lblSoLuong = new JLabel("Số lượng: ");
        txtSoLuong = new JTextField(15);
        txtSoLuong.setText("1");
        btnThem = new JButton("Thêm món ăn");
        btnThem.setIcon(new ImageIcon(Objects.requireNonNull(GoiMonGUI.class.getResource("/com/project_nhapmon/images/add.png"))));
        pnThemMonAn.add(lblSoLuong);
        pnThemMonAn.add(txtSoLuong);
        pnThemMonAn.add(btnThem);

        //
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnMain.add(pnTimKiem, BorderLayout.NORTH);
        pnMain.add(pnDsMenuNhaHang, BorderLayout.CENTER);
        pnMain.add(pnThemMonAn, BorderLayout.SOUTH);
        con.add(pnMain);
    }

    // Triển khai phương thức addEvents

    /**
     * Phương thức addEvents được dùng để thêm các sự kiện cho các Component trong cửa sổ
     */
    private void addEvents() {
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                locThongTin();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                locThongTin();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                locThongTin();
            }
        });

        btnThem.addActionListener(e -> {
            int rowSelected = tblDsMenuNhaHang.getSelectedRow();
            if (rowSelected != -1) {
                MonAn monAn = new MonAn();
                monAn.setMaMonAn((String) dtmDsMenuNhaHang.getValueAt(rowSelected, 0));
                monAn.setTenMonAn((String) dtmDsMenuNhaHang.getValueAt(rowSelected, 1));
                monAn.setDonGia((Integer) dtmDsMenuNhaHang.getValueAt(rowSelected, 2));
                String strSoLuong = txtSoLuong.getText();
                if (strSoLuong != null && strSoLuong.trim().length() > 0 && strSoLuong.matches("^\\d+$")) {
                    int soLuong = Integer.parseInt(strSoLuong);
                    if (soLuong >= 1) {
                        goiMonService.themMonAn(maTang, maBan, monAn, soLuong);
                        hienThiThucDonBanAn(maTang, maBan);
                        if (goiMonService.laySoLuongMonAn(maTang, maBan) > 0) {
                            banAnService.setTrangThaiBanAn(maTang, maBan, "busy");
                        } else {
                            banAnService.setTrangThaiBanAn(maTang, maBan, "active");
                        }
                        capNhatTrangThaiBanAn(maTang);
                    } else {
                        JOptionPane.showMessageDialog(GoiMonGUI.this, "Số lượng ít nhất phải là 1",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else if (strSoLuong != null && strSoLuong.trim().equals("")) {
                    JOptionPane.showMessageDialog(GoiMonGUI.this, "Số lượng không được để trống!");
                } else if (strSoLuong != null && strSoLuong.trim().length() > 0 && !strSoLuong.matches("^\\d+$")) {
                    JOptionPane.showMessageDialog(GoiMonGUI.this, "Số lượng nhập không đúng định dạng!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(GoiMonGUI.this, "Vui lòng chọn món ăn cần thêm!");
            }
        });
    }

    // Triển khai phương thức showWindow

    /**
     * Phương thức showWindow được dùng để hiển thị cửa sổ ra màn hình
     *
     * @param parent Frame nơi mà cửa sổ mới xuất hiện
     */
    public void showWindow(Frame parent) {
        this.setIconImage(new ImageIcon(Objects.requireNonNull(GoiMonGUI.class.getResource("/com/project_nhapmon/images/icon.png"))).getImage());
        this.setSize(600, 400);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    /**
     * Phương thức docDuLieu được dùng để lấy toàn bộ danh sách món ăn trong menu nhà hàng từ bảng
     * menu_nhahang trong cơ sở dữ liệu
     */
    private void docDuLieu() {
        dsMonAnTrongMenu = menuService.layDanhSachMenu();
    }

    public void locThongTin() {
        docDuLieu();
        String duLieuLoc = txtTimKiem.getText();
        Vector<MonAn> dsMonAnDaLoc = new Vector<>();
        for (MonAn monAn : dsMonAnTrongMenu) {
            if (monAn.getMaMonAn().toUpperCase().contains(duLieuLoc.toUpperCase())
                    || monAn.getTenMonAn().toUpperCase().contains(duLieuLoc.toUpperCase())) {
                dsMonAnDaLoc.add(monAn);
            }
        }
        if (duLieuLoc.trim().length() > 0) {
            hienThiMenuNhaHang(dsMonAnDaLoc);
        } else {
            hienThiMenuNhaHang();
        }
    }

    /**
     * Phương thức hienThiMenuNhaHang được dùng để hiển thị toàn bộ danh sách menu của nhà hàng ra
     * bảng trên cửa sổ gọi món
     */
    private void hienThiMenuNhaHang() {
        dtmDsMenuNhaHang.setRowCount(0);
        dsMonAnTrongMenu = menuService.layDanhSachMenu();
        for (MonAn monAn : dsMonAnTrongMenu) {
            Vector<Object> vec = new Vector<>();
            vec.add(monAn.getMaMonAn());
            vec.add(monAn.getTenMonAn());
            vec.add(monAn.getDonGia());
            dtmDsMenuNhaHang.addRow(vec);
        }
    }

    /**
     * Phương thức hienThiMenuNhaHang được dùng để hiện thị một danh sách món ăn nào đó ra bảng
     * trên cửa sổ gọi món
     *
     * @param dsMonAn Danh sách món ăn muốn hiển thị
     */
    private void hienThiMenuNhaHang(Vector<MonAn> dsMonAn) {
        dtmDsMenuNhaHang.setRowCount(0);
        for (MonAn monAn : dsMonAn) {
            Vector<Object> vec = new Vector<>();
            vec.add(monAn.getMaMonAn());
            vec.add(monAn.getTenMonAn());
            vec.add(monAn.getDonGia());
            dtmDsMenuNhaHang.addRow(vec);
        }
    }

    /**
     * Phương thức hienThiDucDonBanAn được dùng để hiển thị danh sách thực đơn của bàn ăn theo vị
     * trí (maTang, maBan) ra cửa sổ chính
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     * @param maBan  Mã bàn ăn theo từng tầng {1, 2, 3,..., 11, 12}
     */
    private void hienThiThucDonBanAn(String maTang, String maBan) {
        Vector<MonAn> dsThucDonTheoBan = goiMonService.layDsThucDonTheoBan(maTang, maBan);
        dtmThucDonTheoBan.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#,###");
        for (MonAn monAn : dsThucDonTheoBan) {
            Vector<Object> vec = new Vector<>();
            vec.add(monAn.getMaMonAn());
            vec.add(monAn.getTenMonAn());
            vec.add(df.format(monAn.getDonGia()));
            vec.add(monAn.getSoLuong());
            vec.add(df.format((long) monAn.getDonGia() * monAn.getSoLuong()));
            dtmThucDonTheoBan.addRow(vec);
        }
    }

    /**
     * Phương thức capNhatTrangThaiBanAn được dùng để cập nhật là màu sắc của panel hiển trị trạng thái bàn ăn
     * trên cửa sổ chính
     *
     * @param maTang Tầng chứa bàn ăn {1, 2, 3}
     */
    private void capNhatTrangThaiBanAn(String maTang) {
        for (int j = 0; j < 12; j++) {
            if (banAnService.getTrangThaiBanAn(maTang, String.valueOf(j + 1)).equalsIgnoreCase("active")) {
                pnStatusBanAn[j].setBackground(Color.GREEN);
            } else {
                pnStatusBanAn[j].setBackground(Color.RED);
            }
        }
    }
}
