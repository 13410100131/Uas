package TokoObat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;


public class Laporan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection konek = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Laporan frame = new Laporan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void refresh()
	{
		try {
			 Class.forName(Koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
			String query="select A.ID_Penjualan, A.Nama_Customer, C.Nama_Product, A.Tanggal_Jual, B.Jumlah, C.Harga, A.Harga_Total from Penjualan A join Detail_Penjualan B on(A.ID_Penjualan=B.ID_Penjualan) join Product C on(B.ID_Product=C.ID_Product)";
			PreparedStatement pst=konek.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Laporan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 701, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblLaporanPenjualan = new JLabel("Laporan Penjualan");
		lblLaporanPenjualan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLaporanPenjualan.setBounds(293, 11, 157, 22);
		contentPane.add(lblLaporanPenjualan);
		
		refresh();
	}
}
