package TokoObat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class MenuUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtPassword;
	private JTextField txtUsername;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblFormMenuUser;
	private JLabel lblpassword;
	private JLabel lblusername;
	private JLabel lblLevel;
	private JTextField txtLevel;
	private JButton btnCari;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUser frame = new MenuUser();
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
			String query="select * from Login";
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
	public MenuUser() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 360);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 54, 391, 256);
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
					String usernameS_=(table.getModel().getValueAt(row, 0).toString());
					String query="select * from Login where username='"+usernameS_+"' ";
					PreparedStatement pst=konek.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtUsername.setText(rs.getString("username"));
						txtPassword.setText(rs.getString("password"));
						
						
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
		
		txtPassword = new JTextField();
		txtPassword.setBounds(104, 88, 206, 21);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(104, 54, 129, 23);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try 
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
				 	String query="insert into Akun(Username,Password, level) values (?,?,?)";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtUsername.getText());
					pst.setString(2,txtPassword.getText());
					pst.setString(2,txtLevel.getText());
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
		btnInsert.setBounds(10, 252, 83, 23);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					String query="Update Login set Username='"+txtUsername.getText()+"',Password='"+txtPassword.getText()+"' where Username='"+txtUsername.getText()+"' ";    
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
		btnUpdate.setBounds(114, 252, 89, 23);
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
						String query="delete from Akun where Username='"+txtUsername.getText()+"' ";
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
		btnDelete.setBounds(227, 252, 83, 23);
		contentPane.add(btnDelete);
		
		lblFormMenuUser = new JLabel("Akun");
		lblFormMenuUser.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		lblFormMenuUser.setBounds(10, 9, 208, 32);
		contentPane.add(lblFormMenuUser);
		
		lblpassword = new JLabel("Password");
		lblpassword.setBounds(10, 91, 89, 14);
		contentPane.add(lblpassword);
		
		lblusername = new JLabel("User Name");
		lblusername.setBounds(10, 58, 83, 14);
		contentPane.add(lblusername);
		
		lblLevel = new JLabel("Level");
		lblLevel.setBounds(10, 126, 89, 14);
		contentPane.add(lblLevel);
		
		txtLevel = new JTextField();
		txtLevel.setColumns(10);
		txtLevel.setBounds(104, 120, 206, 21);
		contentPane.add(txtLevel);
		
		btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					String query="select Username, Password, level from Login where Username=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, txtUsername.getText());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtUsername.setText(rs.getString("Username"));
						txtPassword.setText(rs.getString("Password"));
						txtLevel.setText(rs.getString("level"));
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}
			}
		});
		btnCari.setBounds(233, 54, 77, 23);
		contentPane.add(btnCari);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MenuUtama().setVisible(true);
			}
		});
		btnBack.setBounds(227, 286, 83, 23);
		contentPane.add(btnBack);
		
		refresh();
	}
}
