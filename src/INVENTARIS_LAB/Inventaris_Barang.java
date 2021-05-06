/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INVENTARIS_LAB;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rey
 */
public class Inventaris_Barang extends javax.swing.JFrame {
    
    private Connection con;
    private Statement stat;
    private ResultSet res;

    /**
     * Creates new form Inventaris_Barang
     */
    public Inventaris_Barang() {
        initComponents();
        koneksi();
        tabelMasuk();
        tabelKeluar();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2
        );
    }
    
    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_inventaris_lab", "root", "");
            stat = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void kosongkan() {
        txtKodeBarang.setText(null);
        txtNamaBarang.setText(null);
        txtTglMasuk.setText(null);
        txtTglKeluar.setText(null);
        txtJumlah.setText(null);
        cmbKondisi.setSelectedIndex(-1);
        
        txtKodeBarang.requestFocus();
    }
    
    private void tabelMasuk() {
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("Kode Barang");
        t.addColumn("Nama Barang");
        t.addColumn("Tanggal Masuk");
        t.addColumn("Jumlah");
        t.addColumn("Kondisi");
        tblMasuk.setModel(t);
        
        try {
            res = stat.executeQuery("SELECT * FROM inventaris_barang_masuk");
            while (res.next()) {
                t.addRow(new Object[] {
                    res.getString("Kode_barang"),
                    res.getString("Nama_barang"),
                    res.getString("Tanggal_masuk"),
                    res.getString("Jumlah"),
                    res.getString("Kondisi")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void tabelKeluar() {
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("Kode Barang");
        t.addColumn("Nama Barang");
        t.addColumn("Tanggal Masuk");
        t.addColumn("Tanggal Keluar");
        t.addColumn("Jumlah");
        t.addColumn("Kondisi");
        tblKeluar.setModel(t);
        
        try {
            res = stat.executeQuery("SELECT * FROM inventaris_barang_keluar");
            while (res.next()) {
                t.addRow(new Object[] {
                    res.getString("Kode_barang"),
                    res.getString("Nama_barang"),
                    res.getString("Tanggal_masuk"),
                    res.getString("Tanggal_keluar"),
                    res.getString("Jumlah"),
                    res.getString("Kondisi")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    private void tblMasuk() {
        int a = 100;
        Object[][] data = new Object[a][8];
        
        try {
            int i = 0;
            while (res.next()) {
                data[1][0] = res.getString("Kode_barang");
                data[1][1] = res.getString("Nama_barang");
                data[1][2] = res.getString("Tanggal_masuk");
                data[1][3] = res.getString("Jumlah");
                data[1][4] = res.getString("Kondisi");
                i++;
            }
            
            String[] setJudul = {
                "Kode_barang",
                "Nama_barang",
                "Tanggal_masuk",
                "Jumlah",
                "Kondisi"
            };
            
            tblMasuk.setModel(new DefaultTableModel(data, setJudul));
            data = new Object[i][8];
        } catch (Exception e) {
            System.out.println("Gagal tampil : " + e.toString());
        }
    }
    
    private void tblKeluar() {
        int a = 100;
        Object[][] data = new Object[a][8];
        
        try {
            int i = 0;
            while (res.next()) {
                data[1][0] = res.getString("Kode_barang");
                data[1][1] = res.getString("Nama_barang");
                data[1][2] = res.getString("Tanggal_masuk");
                data[1][3] = res.getString("Tanggal_keluar");
                data[1][4] = res.getString("Jumlah");
                data[1][5] = res.getString("Kondisi");
                i++;
            }
            
            String[] setJudul = {
                "Kode_barang",
                "Nama_barang",
                "Tanggal_masuk",
                "Tanggal_keluar",
                "Jumlah",
                "Kondisi"
            };
            
            tblKeluar.setModel(new DefaultTableModel(data, setJudul));
            data = new Object[i][8];
        } catch (Exception e) {
            System.out.println("Gagal tampil : " + e.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtTglMasuk = new javax.swing.JTextField();
        txtTglKeluar = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        cmbKondisi = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnTambahMasuk = new javax.swing.JButton();
        btnHapusMasuk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMasuk = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnTambahKeluar = new javax.swing.JButton();
        btnHapusKeluar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKeluar = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Inventaris Lab");
        setMinimumSize(new java.awt.Dimension(900, 600));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_mode_black_18dp.png"))); // NOI18N
        btnUpdate.setToolTipText("Update");
        btnUpdate.setBorder(null);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setPreferredSize(new java.awt.Dimension(36, 36));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(255, 255, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_cached_black_18dp.png"))); // NOI18N
        btnRefresh.setToolTipText("Refresh");
        btnRefresh.setBorder(null);
        btnRefresh.setBorderPainted(false);
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.setFocusPainted(false);
        btnRefresh.setPreferredSize(new java.awt.Dimension(36, 36));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_search_black_18dp.png"))); // NOI18N
        btnSearch.setToolTipText("Search");
        btnSearch.setBorder(null);
        btnSearch.setBorderPainted(false);
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.setFocusPainted(false);
        btnSearch.setPreferredSize(new java.awt.Dimension(36, 36));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_delete_black_18dp.png"))); // NOI18N
        btnClear.setToolTipText("Clear");
        btnClear.setBorder(null);
        btnClear.setBorderPainted(false);
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.setFocusPainted(false);
        btnClear.setPreferredSize(new java.awt.Dimension(36, 36));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Kode Barang");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nama Barang");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tanggal Masuk");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tanggal Keluar");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jumlah");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Kondisi");

        txtKodeBarang.setBackground(new java.awt.Color(255, 255, 255));
        txtKodeBarang.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBarangActionPerformed(evt);
            }
        });

        txtNamaBarang.setBackground(new java.awt.Color(255, 255, 255));
        txtNamaBarang.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtTglMasuk.setBackground(new java.awt.Color(255, 255, 255));
        txtTglMasuk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTglMasuk.setToolTipText("format : yyyy-mm-dd");

        txtTglKeluar.setBackground(new java.awt.Color(255, 255, 255));
        txtTglKeluar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTglKeluar.setToolTipText("format : yyyy-mm-dd");

        txtJumlah.setBackground(new java.awt.Color(255, 255, 255));
        txtJumlah.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        cmbKondisi.setBackground(new java.awt.Color(255, 255, 255));
        cmbKondisi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbKondisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baik", "Rusak Ringan", "Rusak Berat" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTglMasuk))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtKodeBarang)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTglKeluar)
                            .addComponent(txtJumlah)
                            .addComponent(cmbKondisi, 0, 150, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTglKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Barang Masuk");

        btnTambahMasuk.setBackground(new java.awt.Color(255, 255, 255));
        btnTambahMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_add_black_18dp.png"))); // NOI18N
        btnTambahMasuk.setToolTipText("Add");
        btnTambahMasuk.setBorder(null);
        btnTambahMasuk.setBorderPainted(false);
        btnTambahMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambahMasuk.setFocusPainted(false);
        btnTambahMasuk.setPreferredSize(new java.awt.Dimension(24, 24));
        btnTambahMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahMasukActionPerformed(evt);
            }
        });

        btnHapusMasuk.setBackground(new java.awt.Color(255, 255, 255));
        btnHapusMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_clear_black_18dp.png"))); // NOI18N
        btnHapusMasuk.setToolTipText("Delete");
        btnHapusMasuk.setBorder(null);
        btnHapusMasuk.setBorderPainted(false);
        btnHapusMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapusMasuk.setFocusPainted(false);
        btnHapusMasuk.setPreferredSize(new java.awt.Dimension(24, 24));
        btnHapusMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusMasukActionPerformed(evt);
            }
        });

        tblMasuk.setBackground(new java.awt.Color(255, 255, 255));
        tblMasuk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblMasuk.setForeground(new java.awt.Color(0, 0, 0));
        tblMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblMasuk.setGridColor(new java.awt.Color(0, 0, 0));
        tblMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMasukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMasuk);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Barang Keluar");

        btnTambahKeluar.setBackground(new java.awt.Color(255, 255, 255));
        btnTambahKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_add_black_18dp.png"))); // NOI18N
        btnTambahKeluar.setToolTipText("Add");
        btnTambahKeluar.setBorder(null);
        btnTambahKeluar.setBorderPainted(false);
        btnTambahKeluar.setFocusPainted(false);
        btnTambahKeluar.setPreferredSize(new java.awt.Dimension(24, 24));
        btnTambahKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKeluarActionPerformed(evt);
            }
        });

        btnHapusKeluar.setBackground(new java.awt.Color(255, 255, 255));
        btnHapusKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_clear_black_18dp.png"))); // NOI18N
        btnHapusKeluar.setToolTipText("Delete");
        btnHapusKeluar.setBorder(null);
        btnHapusKeluar.setBorderPainted(false);
        btnHapusKeluar.setFocusPainted(false);
        btnHapusKeluar.setPreferredSize(new java.awt.Dimension(24, 24));
        btnHapusKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusKeluarActionPerformed(evt);
            }
        });

        tblKeluar.setBackground(new java.awt.Color(255, 255, 255));
        tblKeluar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblKeluar.setForeground(new java.awt.Color(0, 0, 0));
        tblKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKeluarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKeluar);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambahMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapusMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambahKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapusKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHapusMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnTambahMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHapusKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTambahKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(0, 0, 0));
        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        itemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        itemExit.setBackground(new java.awt.Color(255, 255, 255));
        itemExit.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        itemExit.setForeground(new java.awt.Color(0, 0, 0));
        itemExit.setText("Exit");
        itemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExitActionPerformed(evt);
            }
        });
        jMenu1.add(itemExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBarangActionPerformed
        // TODO add your handling code here:
        try {
            res = stat.executeQuery("SELECT * FROM inventaris_barang_masuk WHERE Kode_barang='" 
                    + txtKodeBarang.getText() + "'");
            
            while (res.next()) {
                txtNamaBarang.setText(res.getString("Nama_barang"));
                txtTglMasuk.setText(res.getString("Tanggal_masuk"));
                txtJumlah.setText(res.getString("Jumlah"));
                cmbKondisi.setSelectedItem(res.getString("Kondisi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_txtKodeBarangActionPerformed

    private void btnTambahMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahMasukActionPerformed
        // TODO add your handling code here:
        try {
            stat.executeUpdate("INSERT INTO inventaris_barang_masuk VALUES ("
                    + "'" + txtKodeBarang.getText() + "',"
                    + "'" + txtNamaBarang.getText() + "',"
                    + "'" + txtTglMasuk.getText() + "',"
                    + "'" + txtJumlah.getText() + "',"
                    + "'" + cmbKondisi.getSelectedItem() + "')"
            );
            
            kosongkan();
            JOptionPane.showMessageDialog(null, "Berhasil menyimpan data");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + e);
        }
    }//GEN-LAST:event_btnTambahMasukActionPerformed

    private void btnTambahKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKeluarActionPerformed
        // TODO add your handling code here:
        try {
            stat.executeUpdate("INSERT INTO inventaris_barang_keluar VALUES ("
                    + "'" + txtKodeBarang.getText() + "',"
                    + "'" + txtNamaBarang.getText() + "',"
                    + "'" + txtTglMasuk.getText() + "',"
                    + "'" + txtTglKeluar.getText() + "',"
                    + "'" + txtJumlah.getText() + "',"
                    + "'" + cmbKondisi.getSelectedItem() + "')"
            );
            
            this.tblMasuk();
            this.tblKeluar();
            kosongkan();
            JOptionPane.showMessageDialog(null, "Data berhasil dikeluarkan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + e);
        }
    }//GEN-LAST:event_btnTambahKeluarActionPerformed

    private void btnHapusMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusMasukActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data ini?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        
        try {
            stat.executeUpdate("DELETE FROM inventaris_barang_masuk where Kode_barang='"
                    + txtKodeBarang.getText() + "'");
            
            kosongkan();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + e);
        }
    }//GEN-LAST:event_btnHapusMasukActionPerformed

    private void btnHapusKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKeluarActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data ini?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        try {
            stat.executeUpdate("DELETE FROM inventaris_barang_keluar WHERE Kode_barang='"
                    + txtKodeBarang.getText() + "'");
            
            kosongkan();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + e);
        }
    }//GEN-LAST:event_btnHapusKeluarActionPerformed

    private void tblMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMasukMouseClicked
        // TODO add your handling code here:
        int i = tblMasuk.getSelectedRow();
        
        if (i == -1) {
            return;
        }
        
        String code = (String) tblMasuk.getValueAt(i, 0);
        String code1 = (String) tblMasuk.getValueAt(i, 1);
        String code2 = (String) tblMasuk.getValueAt(i, 2);
        String code3 = (String) tblMasuk.getValueAt(i, 3);
        String code4 = (String) tblMasuk.getValueAt(i, 4);
        
        txtKodeBarang.setText(code);
        txtNamaBarang.setText(code1);
        txtTglMasuk.setText(code2);
        txtJumlah.setText(code3);
        cmbKondisi.setSelectedItem(code4);
    }//GEN-LAST:event_tblMasukMouseClicked

    private void tblKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKeluarMouseClicked
        // TODO add your handling code here:
        int i = tblKeluar.getSelectedRow();
        
        if (i == -1) {
            return;
        }
        
        String code = (String) tblKeluar.getValueAt(i, 0);
        String code1 = (String) tblKeluar.getValueAt(i, 1);
        String code2 = (String) tblKeluar.getValueAt(i, 2);
        String code3 = (String) tblKeluar.getValueAt(i, 3);
        String code4 = (String) tblKeluar.getValueAt(i, 4);
        String code5 = (String) tblKeluar.getValueAt(i, 5);
        
        txtKodeBarang.setText(code);
        txtNamaBarang.setText(code1);
        txtTglMasuk.setText(code2);
        txtTglKeluar.setText(code3);
        txtJumlah.setText(code4);
        cmbKondisi.setSelectedItem(code5);
    }//GEN-LAST:event_tblKeluarMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk update data ini?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        try {
            String sql = "UPDATE inventaris_barang_masuk SET "
                    + "Kode_barang=?,"
                    + "Nama_barang=?,"
                    + "Tanggal_masuk=?,"
                    + "Jumlah=?,"
                    + "Kondisi=? "
                    + "WHERE Kode_barang='"
                    + txtKodeBarang.getText() + "'";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            if (ok == 0) {
                try {
                    ps.setString(1, txtKodeBarang.getText());
                    ps.setString(2, txtNamaBarang.getText());
                    ps.setString(3, txtTglMasuk.getText());
                    ps.setString(4, txtJumlah.getText());
                    ps.setString(5, (String) cmbKondisi.getSelectedItem());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Update data berhasil");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Update gagal");
                }
            }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        new Inventaris_Barang().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {
            res = stat.executeQuery("SELECT * FROM inventaris_barang_masuk WHERE Kode_barang='"
                    + txtKodeBarang.getText() + "'");
            
            while (res.next()) {
                txtNamaBarang.setText(res.getString("Nama_barang"));
                txtTglMasuk.setText(res.getString("Tanggal_masuk"));
                txtJumlah.setText(res.getString("Jumlah"));
                cmbKondisi.setSelectedItem(res.getString("Kondisi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_btnClearActionPerformed

    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Anda yakin ingin keluar?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (ok == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_itemExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inventaris_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventaris_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventaris_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventaris_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventaris_Barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHapusKeluar;
    private javax.swing.JButton btnHapusMasuk;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTambahKeluar;
    private javax.swing.JButton btnTambahMasuk;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbKondisi;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblKeluar;
    private javax.swing.JTable tblMasuk;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtTglKeluar;
    private javax.swing.JTextField txtTglMasuk;
    // End of variables declaration//GEN-END:variables
}
