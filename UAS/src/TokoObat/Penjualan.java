package TokoObat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Penjualan extends JFrame {

	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtIDPenjualan;
	private JTextField txtNCustomer;
	private JTextField txtIDProduct;
	private JTextField txtNProduct;
	private JTextField txtJumlah;
	private JTextField txtHarga;
	private JTextField txtTgl;
	private JTextField txtTotal;
	private JTextField txtStock;
	private JTextField txtSisaStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Penjualan frame = new Penjualan();
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
			String query="select A.ID_Penjualan, A.Nama_Customer, C.Nama_Product, A.Tanggal_Jual, A.Harga_Total from Penjualan A join Detail_Penjualan B on(A.ID_Penjualan=B.ID_Penjualan) join Product C on(B.ID_Product=C.ID_Product)";
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
	public Penjualan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdTranskasi = new JLabel("ID Penjualan");
		lblIdTranskasi.setBounds(13, 61, 93, 14);
		contentPane.add(lblIdTranskasi);
		
		JLabel lblTgl = new JLabel("tgl");
		lblTgl.setBounds(13, 95, 46, 14);
		contentPane.add(lblTgl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 60, 532, 386);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtIDPenjualan = new JTextField();
		txtIDPenjualan.setBounds(131, 61, 211, 20);
		contentPane.add(txtIDPenjualan);
		txtIDPenjualan.setColumns(10);
		
		JLabel lblNamaSupplier = new JLabel("Nama Customer");
		lblNamaSupplier.setBounds(13, 130, 93, 14);
		contentPane.add(lblNamaSupplier);
		
		txtNCustomer = new JTextField();
		txtNCustomer.setColumns(10);
		txtNCustomer.setBounds(131, 130, 211, 20);
		contentPane.add(txtNCustomer);
		
		JLabel lblIdProduk = new JLabel("ID Produk");
		lblIdProduk.setBounds(13, 169, 71, 14);
		contentPane.add(lblIdProduk);
		
		txtIDProduct = new JTextField();
		txtIDProduct.setColumns(10);
		txtIDProduct.setBounds(131, 166, 138, 20);
		contentPane.add(txtIDProduct);
		
		JButton btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					String query="select ID_Product, Nama_Product, Harga, Stock from Product where ID_Product=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, txtIDProduct.getText());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtIDProduct.setText(rs.getString("ID_Product"));
						txtNProduct.setText(rs.getString("Nama_Product"));
						txtHarga.setText(rs.getString("Harga"));
						txtStock.setText(rs.getString("Stock"));
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}
			}
		});
		btnCari.setBounds(276, 165, 66, 23);
		contentPane.add(btnCari);
		
		JLabel lblNamaProduk = new JLabel("Nama Produk");
		lblNamaProduk.setBounds(13, 201, 93, 14);
		contentPane.add(lblNamaProduk);
		
		txtNProduct = new JTextField();
		txtNProduct.setColumns(10);
		txtNProduct.setBounds(131, 198, 211, 20);
		contentPane.add(txtNProduct);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(13, 232, 71, 14);
		contentPane.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.setColumns(10);
		txtJumlah.setBounds(131, 229, 71, 20);
		contentPane.add(txtJumlah);
		
		JLabel lblTotal = new JLabel("Harga");
		lblTotal.setBounds(13, 325, 71, 14);
		contentPane.add(lblTotal);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(131, 322, 211, 20);
		contentPane.add(txtHarga);
		
		JLabel lblTransaksi = new JLabel("PENJUALAN");
		lblTransaksi.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		lblTransaksi.setBounds(13, 11, 206, 39);
		contentPane.add(lblTransaksi);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
				 	String query0="insert into Detail_Penjualan(ID_Penjualan,ID_Product,Nama_Barang,Harga_Satuan,Jumlah) values (?,?,?,?,?)";
				 	String query1="Update Product set Stock='"+txtSisaStock.getText()+"'where ID_Product='"+txtIDProduct.getText()+"' ";
					PreparedStatement pst0=konek.prepareStatement(query0);
					PreparedStatement pst1=konek.prepareStatement(query1);
					pst0.setString(1,txtIDPenjualan.getText());
					pst0.setString(2,txtIDProduct.getText());
					pst0.setString(3,txtNProduct.getText());
					pst0.setString(4,txtHarga.getText());
					pst0.setString(5,txtJumlah.getText());
					pst0.execute();
					pst1.execute();


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
		btnSave.setBounds(131, 423, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MenuUtama().setVisible(true);
			}
		});
		btnExit.setBounds(253, 423, 89, 23);
		contentPane.add(btnExit);
		
		txtTgl = new JTextField();
		txtTgl.setColumns(10);
		txtTgl.setBounds(131, 92, 211, 20);
		contentPane.add(txtTgl);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setBounds(13, 395, 71, 14);
		contentPane.add(lblTotal_1);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(131, 392, 211, 20);
		contentPane.add(txtTotal);
		
		JButton btnTotal = new JButton("Total");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Jumlah = txtJumlah.getText();
				String Harga = txtHarga.getText();
				String Stock = txtStock.getText();
				
				 int Jumlah_J = Integer.parseInt(Jumlah);
				 int Harga_H = Integer.parseInt(Harga);
				 int S_Stock = Integer.parseInt(Stock);
				 int Total = Jumlah_J * Harga_H;
				 int sStock = S_Stock - Jumlah_J;
				 
				 String Hasil = String.valueOf(Total);
				 txtTotal.setText(Hasil);
				 String Hasil_Stock = String.valueOf(sStock);
				 txtSisaStock.setText(Hasil_Stock);
				 
				 try 
					{
						Class.forName(Koneksi.DATABASE_DRIVER);
						konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					 	String query="insert into Penjualan(ID_Penjualan,Nama_Customer,Tanggal_Jual,Harga_Total) values (?,?,?,?)";					 	
						PreparedStatement pst=konek.prepareStatement(query);
						pst.setString(1,txtIDPenjualan.getText());
						pst.setString(2,txtNCustomer.getText());
						pst.setString(3,txtTgl.getText());
						pst.setString(4,txtTotal.getText());
						pst.execute();


						JOptionPane.showMessageDialog(null, "data saved");
						pst.close();

					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				 refresh();
			}
		});
		btnTotal.setBounds(253, 358, 89, 23);
		contentPane.add(btnTotal);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(131, 260, 71, 20);
		contentPane.add(txtStock);
		
		txtSisaStock = new JTextField();
		txtSisaStock.setColumns(10);
		txtSisaStock.setBounds(131, 291, 71, 20);
		contentPane.add(txtSisaStock);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 263, 71, 14);
		contentPane.add(lblStock);
		
		JLabel lblSisaStock = new JLabel("Sisa Stock");
		lblSisaStock.setBounds(10, 294, 71, 14);
		contentPane.add(lblSisaStock);
		
		JLabel label = new JLabel("");
		label.setBounds(141, 95, 46, 14);
		contentPane.add(label);
		
		refresh();
	}
}
