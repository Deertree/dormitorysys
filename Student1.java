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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Student1 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student1 frame = new Student1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void selectRow(String id) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="select * from Student where studentId='"+id+"'";
			ResultSet rs=stmt.executeQuery(sql);
			String[] column={"学号","姓名","年龄","性别","电话","国家","籍贯","密码"};
			DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {
				private static final long serialVersionUID = 1L;};
				while(rs.next()) {
					String id1=rs.getString("studentId");
					String name=rs.getString("studentName");
					String age=rs.getString("StudentAge");
					String sex=rs.getString("studentSex");
					String telephone=rs.getString("studentTelephne");
					String national=rs.getString("studentNational");
					String nativeplace=rs.getString("studentNatiePlace");
					Object[] str_row= {id1,name,age,sex,telephone,national,nativeplace,"***"};
					defaultTableModel.addRow(str_row);
				}
				rs.close();stmt.close();conn.close();
				table.setModel(defaultTableModel);	
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "查询不成功！");
		}
	}
	

	
	public void fillTable(JTable table) {
		try {
			String[] column={"学号","姓名","年龄","性别","电话","国家","籍贯","密码"};
			DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;};
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="select * from Student";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String id=rs.getString("studentId");
				String name=rs.getString("studentName");
				String age=rs.getString("StudentAge");
				String sex=rs.getString("studentSex");
				String telephone=rs.getString("studentTelephne");
				String national=rs.getString("studentNational");
				String nativeplace=rs.getString("studentNatiePlace");
				String password=rs.getString("studentPassword");
				Object[] str_row= {id,name,age,sex,telephone,national,nativeplace,"***"};
				defaultTableModel.addRow(str_row);
			}
			rs.close();stmt.close();conn.close();
			table.setModel(defaultTableModel);
		}catch(Exception e) { JOptionPane.showMessageDialog(null, "加载不成功！");}
	}
	
	/**
	 * Create the frame.
	 */
	public Student1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillTable(table);
			}
		});
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton button_1 = new JButton("\u67E5\u8BE2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				selectRow(id);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_1))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(46))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(26))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
