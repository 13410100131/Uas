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

import java.awt.Color;

public class StockBarang extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblIdProduct;
	private JTextField txtIDProduct;
	private JTextField txtNProduct;
	private JTextField txtStockAwal;
	private JLabel lblStock;
	private JLabel lblNamaProduct;
	private JLabel lblFormMenuStockBarang;
	private JButton btnSimpan;
	private JButton btnCari;
	private JLabel lblStock_1;
	private JTextField txtTambahStock;
	private JTextField txtStock;
	private JLabel lblStock_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockBarang frame = new StockBarang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int coba() 
	{
		int a=1;
		do
		{
			int b= a+1;
		}
		while(a==100);
		return a;
	}
	
	public void refresh()
	{
		try 
		{
			 Class.forName(Koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
			 String query="select ID_Product, Nama_Product, Stock from Product";
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
	public StockBarang() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 368);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 52, 345, 266);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				try
				{	
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					
					int row=table.getSelectedRow();
					String ID_Product=(table.getModel().getValueAt(row, 0).toString());
					String query="select ID_Product,Nama_Product,ID_Supplier,Stock from Product where ID_Product='"+ID_Product+"' order by id";
					PreparedStatement pst=konek.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtIDProduct.setText(rs.getString("ID_Product"));
						txtNProduct.setText(rs.getString("Nama_Product"));
						txtStockAwal.setText(rs.getString("Stock"));
						
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}

				
			}
		});
		scrollPane.setViewportView(table);
		
		lblIdProduct = new JLabel("ID Product");
		lblIdProduct.setBounds(10, 56, 73, 14);
		contentPane.add(lblIdProduct);
		
		txtIDProduct = new JTextField();
		txtIDProduct.setBounds(103, 53, 111, 21);
		contentPane.add(txtIDProduct);
		txtIDProduct.setColumns(10);
		
		txtNProduct = new JTextField();
		txtNProduct.setBounds(103, 85, 207, 23);
		contentPane.add(txtNProduct);
		txtNProduct.setColumns(10);
		
		lblStock = new JLabel("Stock  Awal");
		lblStock.setBounds(10, 123, 73, 14);
		contentPane.add(lblStock);
		
		txtStockAwal = new JTextField();
		txtStockAwal.setBounds(103, 119, 207, 23);
		contentPane.add(txtStockAwal);
		txtStockAwal.setColumns(10);
		
		lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setBounds(10, 89, 83, 14);
		contentPane.add(lblNamaProduct);
		
		lblFormMenuStockBarang = new JLabel("Form Stock Barang");
		lblFormMenuStockBarang.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		lblFormMenuStockBarang.setBounds(22, 11, 269, 32);
		contentPane.add(lblFormMenuStockBarang);
		
		btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					String query="select ID_Product, Nama_Product, Stock from Product where ID_Product=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, txtIDProduct.getText());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtIDProduct.setText(rs.getString("ID_Product"));
						txtNProduct.setText(rs.getString("Nama_Product"));
						txtStockAwal.setText(rs.getString("Stock"));
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}
			}
		});
		btnCari.setBounds(224, 52, 83, 23);
		contentPane.add(btnCari);
		
		JButton btnBatal = new JButton("Back");
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MenuUtama().setVisible(true);
			}
		});
		btnBatal.setBounds(221, 295, 89, 23);
		contentPane.add(btnBatal);
		
		lblStock_1 = new JLabel("Tambah Stock");
		lblStock_1.setBounds(10, 155, 73, 14);
		contentPane.add(lblStock_1);
		
		txtTambahStock = new JTextField();
		txtTambahStock.setColumns(10);
		txtTambahStock.setBounds(103, 152, 207, 23);
		contentPane.add(txtTambahStock);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String Stock_A = txtStockAwal.getText();
				String Stock_B = txtTambahStock.getText();
				
				int Stock_AA = Integer.parseInt(Stock_A);
				int Stock_BB = Integer.parseInt(Stock_B);
				
				int Jumlah_Stock = Stock_AA + Stock_BB;
				
				String Hasil_Stock = String.valueOf(Jumlah_Stock);
				txtStock.setText(Hasil_Stock);
				
				try 
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
				 	String query1="Update Product set Stock='"+txtStock.getText()+"'where ID_Product='"+txtIDProduct.getText()+"' ";
					PreparedStatement pst0=konek.prepareStatement(query1);
					pst0.execute();


					JOptionPane.showMessageDialog(null, "data saved");
					pst0.close();

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
	
				refresh();
				
			}
		});
		btnTambah.setBounds(103, 295, 89, 23);
		contentPane.add(btnTambah);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(103, 186, 207, 23);
		contentPane.add(txtStock);
		
		lblStock_2 = new JLabel("Stock");
		lblStock_2.setBounds(10, 190, 73, 14);
		contentPane.add(lblStock_2);
	
		refresh();
	}
}
