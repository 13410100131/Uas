package TokoObat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuUtama extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUtama frame = new MenuUtama();
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
	public MenuUtama() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 341);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFormProduct = new JButton("Form Product");
		btnFormProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFormProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Product().setVisible(true);
				frame.dispose();
			}
		});
		btnFormProduct.setBounds(10, 70, 258, 48);
		contentPane.add(btnFormProduct);
		
		JButton btnFormSupplier = new JButton("Form Supplier");
		btnFormSupplier.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFormSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Supplier().setVisible(true);
				frame.dispose();
			}
		});
		btnFormSupplier.setBounds(319, 70, 258, 48);
		contentPane.add(btnFormSupplier);
		
		JButton btnFormStockBarang = new JButton("Form Stock Barang");
		btnFormStockBarang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFormStockBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new StockBarang().setVisible(true);
				frame.dispose();
			}
		});
		btnFormStockBarang.setBounds(10, 129, 258, 48);
		contentPane.add(btnFormStockBarang);
		
		JLabel lblMenuUtama = new JLabel("Menu Utama");
		lblMenuUtama.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		lblMenuUtama.setBounds(192, 11, 212, 48);
		contentPane.add(lblMenuUtama);
		
		JButton btnFormMenuUser = new JButton("Form Menu User");
		btnFormMenuUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFormMenuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MenuUser().setVisible(true);
				frame.dispose();
			}
		});
		btnFormMenuUser.setBounds(319, 129, 258, 48);
		contentPane.add(btnFormMenuUser);
		
		JButton btnLaporan = new JButton("Laporan Penjualan");
		btnLaporan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLaporan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Laporan().setVisible(true);
				frame.dispose();
			}
		});
		btnLaporan.setBounds(319, 188, 258, 48);
		contentPane.add(btnLaporan);
		
		JButton btnKeluar = new JButton("Keluar");
		btnKeluar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnKeluar.setBounds(319, 247, 258, 45);
		contentPane.add(btnKeluar);
		
		JButton btnFormPenjualan = new JButton("Form Penjualan");
		btnFormPenjualan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFormPenjualan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Penjualan().setVisible(true);
				frame.dispose();
			}
		});
		btnFormPenjualan.setBounds(10, 188, 258, 48);
		contentPane.add(btnFormPenjualan);
		
		JButton btnLaporanStock = new JButton("Laporan Stock");
		btnLaporanStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LaporanStock().setVisible(true);
			}
		});
		btnLaporanStock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLaporanStock.setBounds(10, 247, 258, 48);
		contentPane.add(btnLaporanStock);
	}
}
