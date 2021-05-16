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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;

/**
 *
 * @author Rey
 */
public class Inventaris_Barang extends javax.swing.JFrame {
    
    // Declaring variable to make a connection with Database
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
        
        // Make the app position to the center of the screen when running
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2
        );
    }
    
    // Light Theme
    private void light() {
        jmbInv.setBackground(new java.awt.Color(255, 255, 255));
        itemExit.setBackground(new java.awt.Color(255, 255, 255));
        
        jpInv1.setBackground(new java.awt.Color(204, 204, 204));
        jpInv2.setBackground(new java.awt.Color(255, 255, 255));
        jpInv3.setBackground(new java.awt.Color(255, 255, 255));
        jpInv4.setBackground(new java.awt.Color(255, 255, 255));
        
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
    }
    
    // Dark Theme
    private void dark() {
        jmbInv.setBackground(new java.awt.Color(35, 39, 42));
        itemExit.setBackground(new java.awt.Color(35, 39, 42));
        
        jpInv1.setBackground(new java.awt.Color(47, 51, 55));
        jpInv2.setBackground(new java.awt.Color(35, 39, 42));
        jpInv3.setBackground(new java.awt.Color(35, 39, 42));
        jpInv4.setBackground(new java.awt.Color(35, 39, 42));
        
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
    }
    
    // Method to refresh the app
    private void refresh() {
        new Inventaris_Barang().setVisible(true);
        dispose();
    }
    
    // Method to connect to database
    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_inventaris_lab", "root", "");
            stat = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Method to empty the value
    private void kosongkan() {
        txtKodeBarang.setText(null);
        txtNamaBarang.setText(null);
        txtTglMasuk.setText(null);
        txtTglKeluar.setText(null);
        txtJumlah.setText(null);
        cmbKondisi.setSelectedIndex(-1);
        
        txtKodeBarang.requestFocus();
    }
    
    // Make the inventory input table
    private void tabelMasuk() {
        
        // Create the table
        DefaultTableModel t = new DefaultTableModel();
        
        // Set table column
        t.addColumn("Kode Barang");
        t.addColumn("Nama Barang");
        t.addColumn("Tanggal Masuk");
        t.addColumn("Jumlah");
        t.addColumn("Kondisi");
        tblMasuk.setModel(t);
        
        // Get data from table
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
        } catch (Exception e) { // if contain error
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    
    // Make the inventory output table
    private void tabelKeluar() {
        
        // Same as the inventory input table
        DefaultTableModel t = new DefaultTableModel();
        t.addColumn("Kode Barang");
        t.addColumn("Nama Barang");
        t.addColumn("Tanggal Masuk");
        t.addColumn("Tanggal Keluar");
        t.addColumn("Jumlah");
        t.addColumn("Kondisi");
        tblKeluar.setModel(t);
        
        // Get data from table
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
    
    // Make a loop to send the data to inventory input table
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
    
    // Same as above
    // Make a loop to send the data to inventory input table
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

        jpInv1 = new javax.swing.JPanel();
        jpInv2 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnSetTheme = new javax.swing.JToggleButton();
        jpInv3 = new javax.swing.JPanel();
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
        jpInv4 = new javax.swing.JPanel();
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
        jmbInv = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventaris Lab App");
        setMinimumSize(new java.awt.Dimension(900, 600));

        jpInv1.setBackground(new java.awt.Color(204, 204, 204));
        jpInv1.setForeground(new java.awt.Color(0, 0, 0));

        jpInv2.setBackground(new java.awt.Color(255, 255, 255));

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

        btnSetTheme.setBackground(new java.awt.Color(255, 255, 255));
        btnSetTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_light_mode_black_18.png"))); // NOI18N
        btnSetTheme.setToolTipText("Theme");
        btnSetTheme.setBorder(null);
        btnSetTheme.setBorderPainted(false);
        btnSetTheme.setFocusPainted(false);
        btnSetTheme.setFocusable(false);
        btnSetTheme.setMaximumSize(new java.awt.Dimension(18, 18));
        btnSetTheme.setMinimumSize(new java.awt.Dimension(18, 18));
        btnSetTheme.setPreferredSize(new java.awt.Dimension(36, 36));
        btnSetTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetThemeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpInv2Layout = new javax.swing.GroupLayout(jpInv2);
        jpInv2.setLayout(jpInv2Layout);
        jpInv2Layout.setHorizontalGroup(
            jpInv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInv2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSetTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpInv2Layout.setVerticalGroup(
            jpInv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInv2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSetTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpInv3.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jpInv3Layout = new javax.swing.GroupLayout(jpInv3);
        jpInv3.setLayout(jpInv3Layout);
        jpInv3Layout.setHorizontalGroup(
            jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInv3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpInv3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTglMasuk))
                    .addGroup(jpInv3Layout.createSequentialGroup()
                        .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(19, 19, 19)
                        .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtKodeBarang)))
                    .addGroup(jpInv3Layout.createSequentialGroup()
                        .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTglKeluar)
                            .addComponent(txtJumlah)
                            .addComponent(cmbKondisi, 0, 150, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jpInv3Layout.setVerticalGroup(
            jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInv3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTglMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTglKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpInv3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbKondisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jpInv4.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jpInv4Layout = new javax.swing.GroupLayout(jpInv4);
        jpInv4.setLayout(jpInv4Layout);
        jpInv4Layout.setHorizontalGroup(
            jpInv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInv4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpInv4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambahMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapusMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jpInv4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambahKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapusKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jpInv4Layout.setVerticalGroup(
            jpInv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInv4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpInv4Layout.createSequentialGroup()
                        .addGroup(jpInv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHapusMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpInv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnTambahMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jpInv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHapusKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTambahKeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpInv1Layout = new javax.swing.GroupLayout(jpInv1);
        jpInv1.setLayout(jpInv1Layout);
        jpInv1Layout.setHorizontalGroup(
            jpInv1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpInv2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpInv1Layout.createSequentialGroup()
                .addComponent(jpInv3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpInv4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpInv1Layout.setVerticalGroup(
            jpInv1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInv1Layout.createSequentialGroup()
                .addComponent(jpInv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInv1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpInv4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpInv3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jmbInv.setBackground(new java.awt.Color(255, 255, 255));

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setForeground(new java.awt.Color(0, 0, 0));
        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

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

        jmbInv.add(jMenu1);

        setJMenuBar(jmbInv);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpInv1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpInv1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Inventory Output table
    // Highlight the data from Inventory Output table if the table row is clicked
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

    // Inventory Output Delete Button
    private void btnHapusKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKeluarActionPerformed
        // TODO add your handling code here:
        
        // Confirmation Dialog
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data ini?",
            "Confirmation", JOptionPane.YES_NO_OPTION);
        
        // Try to delete the table row based on 'Kode_barang'
        try {
            stat.executeUpdate("DELETE FROM inventaris_barang_keluar WHERE Kode_barang='"
                + txtKodeBarang.getText() + "'");
            
            // Empty the value
            kosongkan();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus"); // Return a Message
            
            // Refresh the App
            refresh();
        } catch (Exception e) { // If failed to delete the data
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + e);
        }
    }//GEN-LAST:event_btnHapusKeluarActionPerformed
    
    // Inventory Output Add Button
    private void btnTambahKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKeluarActionPerformed
        // TODO add your handling code here:
        // Try to input the data to Inventory Output table
        try {
            stat.executeUpdate("INSERT INTO inventaris_barang_keluar VALUES ("
                + "'" + txtKodeBarang.getText() + "',"
                + "'" + txtNamaBarang.getText() + "',"
                + "'" + txtTglMasuk.getText() + "',"
                + "'" + txtTglKeluar.getText() + "',"
                + "'" + txtJumlah.getText() + "',"
                + "'" + cmbKondisi.getSelectedItem() + "')"
            );
            
            // Delete the data from Inventory Input table
            stat.executeUpdate("DELETE FROM inventaris_barang_masuk where Kode_barang='"
                + txtKodeBarang.getText() + "'");
            
            // Get the table object
            this.tblMasuk();
            this.tblKeluar();
            kosongkan();
            JOptionPane.showMessageDialog(null, "Data berhasil dikeluarkan"); // Return a Message
            
            // Refresh the App
            refresh();
        } catch (Exception e) { // If failed to input the data
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + e);
        }
    }//GEN-LAST:event_btnTambahKeluarActionPerformed
    
    // Inventory Input table
    // Highlight the data from Inventory Input table if the table row is clicked
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
    
    // Inventory Input Delete Button
    private void btnHapusMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusMasukActionPerformed
        // TODO add your handling code here:
        // Confirmation Dialog
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data ini?",
            "Confirmation", JOptionPane.YES_NO_OPTION);
        
        // Try to delete from Inventory Input table
        try {
            stat.executeUpdate("DELETE FROM inventaris_barang_masuk where Kode_barang='"
                + txtKodeBarang.getText() + "'");
            
            // Empty the value
            kosongkan();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus"); // Return a Message
            
            // Refresh the App
            refresh();
        } catch (Exception e) { // If failed to delete the data
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + e);
        }
    }//GEN-LAST:event_btnHapusMasukActionPerformed
    
    // Inventory Input Add Button
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
            
            refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + e);
        }
    }//GEN-LAST:event_btnTambahMasukActionPerformed
    
    // Get the data based on 'Kode_barang'
    private void txtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBarangActionPerformed
        // TODO add your handling code here:
        try {
            res = stat.executeQuery("SELECT * FROM inventaris_barang_masuk WHERE Kode_barang='"
                + txtKodeBarang.getText() + "'");
            
            // Get data row
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
    
    // Clear Button
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        kosongkan();
    }//GEN-LAST:event_btnClearActionPerformed
    
    // Search Button
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {
            res = stat.executeQuery("SELECT * FROM inventaris_barang_masuk WHERE Kode_barang='"
                + txtKodeBarang.getText() + "'");
            
            // Get the data row
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
    
    // Refresh Button
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed
    
    // Update Button
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        // Confirmation Dialog
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk update data ini?",
            "Confirmation", JOptionPane.YES_NO_OPTION);
        
        // Try to update the data based on 'Kode_barang'
        try {
            String sql = "UPDATE inventaris_barang_masuk SET "
            + "Kode_barang=?,"
            + "Nama_barang=?,"
            + "Tanggal_masuk=?,"
            + "Jumlah=?,"
            + "Kondisi=? "
            + "WHERE Kode_barang='"
            + txtKodeBarang.getText() + "'";
            
            // Connect to database
            PreparedStatement ps = con.prepareStatement(sql);
            
            // Update the data
            if (ok == 0) {
                
                // Try to Insert the Data
                try {
                    ps.setString(1, txtKodeBarang.getText());
                    ps.setString(2, txtNamaBarang.getText());
                    ps.setString(3, txtTglMasuk.getText());
                    ps.setString(4, txtJumlah.getText());
                    ps.setString(5, (String) cmbKondisi.getSelectedItem());
                    
                    // Do update
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Update data berhasil"); // Return a Message
                    
                    // Refresh the App
                    refresh();
                } catch (Exception e) { // If failed to update
                    JOptionPane.showMessageDialog(null, "Update gagal"); // Return a Message
                    
                    // Refresh the App
                    refresh();
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    // Exit Item
    private void itemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExitActionPerformed
        // TODO add your handling code here:
        // If user wants to exit
        int ok = JOptionPane.showConfirmDialog(null, "Anda yakin ingin keluar?",
            "Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (ok == 0) { // If True
            System.exit(0);
        }
    }//GEN-LAST:event_itemExitActionPerformed

    // Theme Button
    private void btnSetThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetThemeActionPerformed
        // TODO add your handling code here:
        if (btnSetTheme.isSelected()) { // If the button turned ON
            dark(); // Set to Dark Theme
            
            // Change the Buttons Icon
            btnSetTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_dark_mode_black_18.png")));
        } else { // If the button turned OFF
            light(); // Set to Light Theme
            
            // Change the Buttons Icon
            btnSetTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/INVENTARIS_LAB_IMAGE/outline_light_mode_black_18.png")));
        }
    }//GEN-LAST:event_btnSetThemeActionPerformed

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
    private javax.swing.JToggleButton btnSetTheme;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar jmbInv;
    private javax.swing.JPanel jpInv1;
    private javax.swing.JPanel jpInv2;
    private javax.swing.JPanel jpInv3;
    private javax.swing.JPanel jpInv4;
    private javax.swing.JTable tblKeluar;
    private javax.swing.JTable tblMasuk;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtTglKeluar;
    private javax.swing.JTextField txtTglMasuk;
    // End of variables declaration//GEN-END:variables
}
