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

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

import javax.swing.UIManager;


public class Supplier extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtNSupplier;
	private JTextField txtIDSupplier;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblFormMenuSupplier;
	private JLabel lblNamaSupplier;
	private JLabel lblIdSupplier;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Supplier frame = new Supplier();
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
			String query="select * from Supplier";
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
	public Supplier() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 451);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 435, 181);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtNSupplier = new JTextField();
		txtNSupplier.setBounds(100, 115, 345, 21);
		contentPane.add(txtNSupplier);
		txtNSupplier.setColumns(10);
		
		txtIDSupplier = new JTextField();
		txtIDSupplier.setBounds(100, 83, 101, 23);
		contentPane.add(txtIDSupplier);
		txtIDSupplier.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try 
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
				 	String query="insert into Supplier(ID_Supplier,Nama_Supplier) values (?,?)";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtIDSupplier.getText());
					pst.setString(2,txtNSupplier.getText());
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
		btnInsert.setBounds(116, 345, 101, 23);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					String query="Update Supplier set ID_Supplier='"+txtIDSupplier.getText()+"',Nama_Supplier='"+txtNSupplier.getText()+"' where ID_Supplier='"+txtIDSupplier.getText()+"' ";    
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
		btnUpdate.setBounds(227, 345, 107, 23);
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
							String query="delete from Supplier where ID_Supplier='"+txtIDSupplier.getText()+"' ";
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
		btnDelete.setBounds(344, 345, 101, 23);
		contentPane.add(btnDelete);
		
		lblFormMenuSupplier = new JLabel("Supplier");
		lblFormMenuSupplier.setFont(new Font("Bodoni MT", Font.PLAIN, 25));
		lblFormMenuSupplier.setBounds(164, 11, 208, 32);
		contentPane.add(lblFormMenuSupplier);
		
		lblNamaSupplier = new JLabel("Nama Supplier");
		lblNamaSupplier.setBounds(10, 118, 89, 14);
		contentPane.add(lblNamaSupplier);
		
		lblIdSupplier = new JLabel("ID Supplier");
		lblIdSupplier.setBounds(10, 87, 83, 14);
		contentPane.add(lblIdSupplier);
		
		JButton btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			try
			{
				Class.forName(Koneksi.DATABASE_DRIVER);
				konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
				String query="select ID_Supplier, Nama_Supplier from Supplier where ID_Supplier=?";
				PreparedStatement pst=konek.prepareStatement(query);
				pst.setString(1, txtIDSupplier.getText());
				ResultSet rs=pst.executeQuery();
				
				while(rs.next())
				{
					txtIDSupplier.setText(rs.getString("ID_Supplier"));
					txtNSupplier.setText(rs.getString("Nama_Supplier"));			
				}
				
				pst.close();

			}
			catch (Exception ex)
			{
				ex.printStackTrace();

			}
		}
	});
		btnCari.setBounds(211, 83, 89, 23);
		contentPane.add(btnCari);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MenuUtama().setVisible(true);
			}
		});
		btnBack.setBounds(344, 379, 101, 23);
		contentPane.add(btnBack);
		
		refresh();
	}
}
