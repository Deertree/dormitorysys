package 数据库课设;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Accomodation1 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton button;
	private JButton button_1;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accomodation1 frame = new Accomodation1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fillTable(JTable table) {
		try {
			String[] column={"学号","宿舍号","宿舍人数","入住日期"};
			DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;};
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="select * from Accmodation";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String sn=rs.getString("Snumber");
				String dn=rs.getString("Dnumber");
				String bn=rs.getString("Bnumber");
				String cd=rs.getString("Check_Date");
				Object[] str_row= {sn,dn,bn,cd};
				defaultTableModel.addRow(str_row);
			}
			rs.close();stmt.close();conn.close();
			table.setModel(defaultTableModel);
		}catch(Exception e) { JOptionPane.showMessageDialog(null, "加载不成功！");}
	}
	public void selectRow1(String n){
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();	
			String sql="select * from Accmodation where Dnumber='"+n+"'";					
			
			System.out.println(sql);
			ResultSet rs=stmt.executeQuery(sql);
			String[] column={"学号","宿舍号","住宿人数","入住日期"};
			DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;};
			while(rs.next()) {
				String id1=rs.getString("Snumber");
				String n1=rs.getString("Dnumber");
				String p1=rs.getString("Bnumber");
				String cd=rs.getString("Check_Date");
				Object[] str_row= {id1,n1,p1,cd};
				defaultTableModel.addRow(str_row);
			}
			rs.close();stmt.close();conn.close();
			table.setModel(defaultTableModel);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "查询不成功！");
		}
	}
	public void selectRow(String id){
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();	
			String sql="select * from Accmodation where Snumber='"+id+"'";					
			
			System.out.println(sql);
			ResultSet rs=stmt.executeQuery(sql);
			String[] column={"学号","宿舍号","住宿人数","入住日期"};
			DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;};
			while(rs.next()) {
				String id1=rs.getString("Snumber");
				String n1=rs.getString("Dnumber");
				String p1=rs.getString("Bnumber");
				String cd=rs.getString("Check_Date");
				Object[] str_row= {id1,n1,p1,cd};
				defaultTableModel.addRow(str_row);
			}
			rs.close();stmt.close();conn.close();
			table.setModel(defaultTableModel);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "查询不成功！");
		}
	}

	/**
	 * Create the frame.
	 */
	public Accomodation1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable(table);
			}
		});
		
		button_1 = new JButton("\u67E5\u8BE2\u5B66\u53F7");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				selectRow(id);
			}
		});
		
		label = new JLabel("\u5BBF\u820D\u53F7\uFF1A");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		label_1 = new JLabel("\u5B66\u53F7\uFF1A");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton button_2 = new JButton("\u67E5\u8BE2\u5BBF\u820D\u53F7");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String n=textField_1.getText();
				selectRow1(n);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(118, Short.MAX_VALUE)
					.addComponent(button_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1)
					.addGap(95)
					.addComponent(button)
					.addGap(29))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addGap(4)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(147, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1)
						.addComponent(button_2))
					.addGap(75))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
