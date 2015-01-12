package TokoObat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import com.mckoi.database.User;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

	private Connection konek = null;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 208);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblPassword.setBounds(44, 83, 76, 14);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Georgia", Font.PLAIN, 12));
		txtUsername.setBounds(156, 53, 205, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Georgia", Font.PLAIN, 12));
		passwordField.setBounds(155, 81, 206, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			 User usr = null;
			private long eventMask;
			private String level;
			private int kondisilogin=3;
			private String userlogin;

			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName(Koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(Koneksi.URL, Koneksi.USERNAME, Koneksi.PASSWORD);
					String query="select * from Login where Username=? and Password=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtUsername.getText());
					pst.setString(2,passwordField.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					while (rs.next())
					{
						level = rs.getString(3);
						count=count+1;
					}
					if(count==1)
					{
							if(level.equals("admin"))
							{
								kondisilogin=0;	
								JOptionPane.showMessageDialog(null, "Anda Masuk Sebagai Admin");
								new MenuUtama().setVisible(true);
								frame.dispose();

							}
							else if (level.equals("user"))
							{
								kondisilogin=1;
								JOptionPane.showMessageDialog(null, "Anda Masuk Sebagai User");
								new Penjualan().setVisible(true);
								frame.dispose();
							}

							userlogin=txtUsername.getText();

						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Maaf username atau password yang anda masukkan salah");
					}
					
				
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				final String user = txtUsername.getText();
			}
		});
		
		JButton btnBatal = new JButton("Cancel");
		btnBatal.setFont(new Font("Georgia", Font.PLAIN, 15));
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnLogin.setBounds(155, 109, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel label = new JLabel("Username");
		label.setFont(new Font("Georgia", Font.PLAIN, 15));
		label.setBounds(44, 56, 76, 20);
		contentPane.add(label);
		
		btnBatal.setBounds(271, 109, 89, 23);
		contentPane.add(btnBatal);
		
		JLabel lblTokoObatHerbal = new JLabel("LOGIN TOKO OBAT HERBAL");
		lblTokoObatHerbal.setForeground(Color.BLACK);
		lblTokoObatHerbal.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		lblTokoObatHerbal.setBounds(34, 8, 351, 34);
		contentPane.add(lblTokoObatHerbal);
		
	}
}
