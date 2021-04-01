package 数据库课设;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class AdministratorSelect extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorSelect frame = new AdministratorSelect();
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					frame.setTitle("管理员编辑");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	
	public boolean addRow(String id,String name,String sex,String password) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();	
			String sql="insert into PersonManage values('"+id+"','"+name+"','"+sex+"','"+password+"')";	
			System.out.print(sql);
			int rowCount=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "成功添加！"+rowCount+"条记录！");
			stmt.close();
			conn.close();
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "添加不成功！");
			return false;
		}
	}
	//根据管理员编号来修改数据
	public void updateRow(String id,String name,String sex,String password) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="update PersonManage set personManageName='"+name+"',personManageSex='"+sex+"',personManagepassword='"+password+"' where personManageId='"+id+"'";		
			System.out.print(sql);
			int rowCount=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "成功修改"+rowCount+"条记录！");
			stmt.close();
			conn.close();			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "修改不成功！");
		}
	}
	//根据管理员编号来删除数据
	public void deleteRow(String id) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="delete from PersonManage where personManageId="+id+"";
			int rowCount=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "成功删除"+rowCount+"条记录！");
			stmt.close();
			conn.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "删除不成功！");
		}
	}
	
	public void selectRow(String id){
		try {
			String[] column={"管理员编号","管理员姓名","管理员性别","管理员密码"};
			DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;};
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="select * from PersonManage where personManageId='"+id+"'";
			ResultSet rs=stmt.executeQuery(sql);
			System.out.println(sql);
			while(rs.next()) {
				String id1=rs.getString("personManageId");
				String name1=rs.getString("personManageName");
				String sex1=rs.getString("personManageSex");
				String password1=rs.getString("personManagepassword");
				Object[] str_row= {id1,name1,sex1,password1};
				defaultTableModel.addRow(str_row);
			}
			rs.close();stmt.close();conn.close();
			table.setModel(defaultTableModel);
		}catch(Exception e) { JOptionPane.showMessageDialog(null, "查找不成功！");}

	}
	
	public void fillTable(JTable table) {
		try {
			String[] column={"管理员编号","管理员姓名","管理员性别","管理员密码"};
			DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;};
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="select * from PersonManage";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String id=rs.getString("personManageId");
				String name=rs.getString("personManageName");
				String sex=rs.getString("personManageSex");
				String password=rs.getString("personManagepassword");
				Object[] str_row= {id,name,sex,password};
				defaultTableModel.addRow(str_row);
			}
			rs.close();stmt.close();conn.close();
			table.setModel(defaultTableModel);
		}catch(Exception e) { JOptionPane.showMessageDialog(null, "加载不成功！");}
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public AdministratorSelect() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\javaicon\\administrator_72px_1170211_easyicon.net.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblId = new JLabel("\u7BA1\u7406\u5458\u7F16\u53F7\uFF1A");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("\u7BA1\u7406\u5458\u59D3\u540D\uFF1A");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458\u6027\u522B\uFF1A");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u7BA1\u7406\u5458\u5BC6\u7801\uFF1A");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		


		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id1=textField.getText();
				String name1=textField_1.getText();
				String sex1=textField_2.getText();
				String password1=textField_3.getText();
				if(sex1.equals("男")||sex1.equals("女")){
					boolean s=false;
					s=addRow(id1,name1,sex1,password1);
				}
				else {
					JOptionPane.showMessageDialog(null, "管理员性别只能为男或女！");
				}
				
				
			}
		});
		
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				String id2=textField.getText();
				String name2=textField_1.getText();
				String sex2=textField_2.getText();
				String password2=textField_3.getText();
				if(sex2.equals("男")||sex2.equals("女")){
					updateRow(id2,name2,sex2,password2);
				}
				else {
					JOptionPane.showMessageDialog(null, "管理员性别只能为男或女！");
				}
				
			}
		});
		
		JButton button_1 = new JButton("\u5220\u9664");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id3=textField.getText();
				deleteRow(id3);
			}
		});
		
		JButton button_2 = new JButton("\u5237\u65B0");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable(table);
	
			}
		});
		
		JButton button_3 = new JButton("\u67E5\u8BE2");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				selectRow(id);
			}
		});
		
		JButton button_4 = new JButton("\u8FD4\u56DE");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu mn=new menu();
				mn.setVisible(true);
			}
		});
		
		JButton button_5 = new JButton("\u9009\u4E2D");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "请选择要操作的记录");
					return;
				}
				int rowIndex=table.getSelectedRow();
				textField.setText(table.getValueAt(rowIndex, 0).toString());
				textField_1.setText(table.getValueAt(rowIndex, 1).toString());
				textField_2.setText(table.getValueAt(rowIndex, 2).toString());
				textField_3.setText(table.getValueAt(rowIndex, 3).toString());
				
				
				textField.setEditable(true);
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
	
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblName)
								.addComponent(lblId)
								.addComponent(lblNewLabel)
								.addComponent(lblPassword))
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
								.addComponent(textField_1)
								.addComponent(textField_2)
								.addComponent(textField_3)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_5))
						.addComponent(button_4))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(button)
						.addComponent(button_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_3)
						.addComponent(button_2)
						.addComponent(button_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_4)
					.addGap(100))
		);
		
		table = new JTable();
		int rowIndex=table.getSelectedRow();
		System.out.println(rowIndex);
	
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
