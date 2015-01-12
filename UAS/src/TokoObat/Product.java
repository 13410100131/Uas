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

public class Product extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblIdProduct;
	private JTextField txtProduct;
	private JTextField txtNamaProduct;
	private JLabel lblIdSupplier;
	private JTextField txtSupplier;
	private JLabel lblHarga;
	private JTextField txtHarga;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNamaProduct;
	private JLabel lblFormMenuProduct;
	private JTextField txtCari;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product vframe = new Product();
					vframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refresh()
	{
		try 
		{
			 Class.forName(Koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
			 String query="select ID_Product, Nama_Product, ID_Supplier, Harga from Product";
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
	public Product() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 368);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 27, 582, 276);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblIdProduct = new JLabel("ID Product");
		lblIdProduct.setBounds(665, 73, 73, 14);
		contentPane.add(lblIdProduct);
		
		txtProduct = new JTextField();
		txtProduct.setBounds(748, 70, 217, 21);
		contentPane.add(txtProduct);
		txtProduct.setColumns(10);
		
		txtNamaProduct = new JTextField();
		txtNamaProduct.setBounds(748, 136, 217, 23);
		contentPane.add(txtNamaProduct);
		txtNamaProduct.setColumns(10);
		
		lblIdSupplier = new JLabel("ID Supplier");
		lblIdSupplier.setBounds(665, 106, 73, 14);
		contentPane.add(lblIdSupplier);
		
		txtSupplier = new JTextField();
		txtSupplier.setBounds(748, 102, 217, 23);
		contentPane.add(txtSupplier);
		txtSupplier.setColumns(10);
		
		lblHarga = new JLabel("Harga");
		lblHarga.setBounds(665, 174, 73, 14);
		contentPane.add(lblHarga);
		
		txtHarga = new JTextField();
		txtHarga.setBounds(748, 170, 217, 23);
		contentPane.add(txtHarga);
		txtHarga.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					 Class.forName(Koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					String query="insert into Product(ID_Product,Nama_Product,ID_Supplier,Harga) values (?,?,?,?)";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtProduct.getText());
					pst.setString(2,txtNamaProduct.getText());
					pst.setString(3,txtSupplier.getText());
					pst.setString(4,txtHarga.getText());
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
		btnInsert.setBounds(666, 246, 94, 23);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					String query="Update Product set ID_Product='"+txtProduct.getText()+"',Nama_Product='"+txtNamaProduct.getText()+"',ID_Supplier='"+txtSupplier.getText()+"',Harga='"+txtHarga.getText()+"' where ID_Product='"+txtProduct.getText()+"' ";    
					PreparedStatement pst=konek.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "data Updated");
					pst.close();
					
				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}
				refresh();
			}	
		});
		btnUpdate.setBounds(767, 246, 94, 23);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null,"Apakah anda yakin mau menghapus data tersebut","delete",JOptionPane.YES_NO_OPTION);
				if(action==0)
				{
					try
					{
						Class.forName(Koneksi.DATABASE_DRIVER);
						konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
						String query="delete from Product where ID_Product='"+txtProduct.getText()+"' ";
						PreparedStatement pst=konek.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "data deleted");
						pst.close();
						
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
	
					}
				}
				refresh();
			}
		});
		btnDelete.setBounds(871, 246, 94, 23);
		contentPane.add(btnDelete);
		
		lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setBounds(665, 140, 83, 14);
		contentPane.add(lblNamaProduct);
		
		lblFormMenuProduct = new JLabel("Product");
		lblFormMenuProduct.setBackground(Color.WHITE);
		lblFormMenuProduct.setFont(new Font("Bodoni MT", Font.PLAIN, 25));
		lblFormMenuProduct.setBounds(633, 27, 208, 32);
		contentPane.add(lblFormMenuProduct);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MenuUtama().setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setBounds(876, 280, 89, 23);
		contentPane.add(btnBack);
	
		refresh();
	}
}
