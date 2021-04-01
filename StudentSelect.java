package 数据库课设;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class StudentSelect extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSelect frame = new StudentSelect();
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					frame.setTitle("学生管理");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean addRow(String id,String name,String age,String sex,String telephone,String national,String nativeplace,String password) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();	
			String sql="insert into Student values('"+id+"','"+name+"','"+age+"','"+sex+"','"+telephone+"','"+national+"','"+nativeplace+"','"+password+"')";
			System.out.print(sql);
			int rowCount=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "成功添加"+rowCount+"条记录！");
			stmt.close();
			conn.close();
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "添加不成功！");
			return false;
		}
	}

	//根据学生学号来修改数据
	public void updateRow(String i,String n,String a,String s,String t,String nt,String np,String p) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="update Student set studentName='"+n+"',studentAge='"+a+"',studentSex='"+s+"',studentTelephne='"+t+"',studentNational='"+nt+"',studentNatiePlace='"+np+"',studentPassword='"+p+"' where studentId='"+i+"'";		
			System.out.print(sql);
			int rowCount=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "成功修改"+rowCount+"条记录！");
			stmt.close();
			conn.close();			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "修改不成功！");
		}
	}
	
	//根据学生学号来删除数据
	public void deleteRow(String id) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql2="select * from Accmodation";
	
			ResultSet rs1=stmt.executeQuery(sql2);
			while(rs1.next()) {
				if(id.equals(rs1.getString("Snumber"))) {
					String sql3="delete from Accmodation where Snumber='"+id+"'";
					System.out.println(sql3);
					int i=stmt.executeUpdate(sql3);
					break;		            			  
			        }
				}
			String sql="select * from Tdortmitory";
			ResultSet rs=stmt.executeQuery(sql);	
			while(rs.next()) {
				if(id.equals(rs.getString("Dstudent"))) {
					String sql1="delete from Tdortmitory where Dstudent='"+id+"'";
					System.out.println(sql1);
					int i1=stmt.executeUpdate(sql1);
					break;
					}
				}			   
			String sql4="delete from Student where studentId='"+id+"'";
			System.out.println(sql4);
			int rowCount=stmt.executeUpdate(sql4);
			
			JOptionPane.showMessageDialog(null, "成功删除"+rowCount+"条记录！");
			stmt.close();
			conn.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "删除不成功！");
		}
	}
	//根据学号查询学生信息
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
					String password=rs.getString("studentPassword");
					Object[] str_row= {id1,name,age,sex,telephone,national,nativeplace,password};
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
				Object[] str_row= {id,name,age,sex,telephone,national,nativeplace,password};
				defaultTableModel.addRow(str_row);
			}
			rs.close();stmt.close();conn.close();
			table.setModel(defaultTableModel);
		}catch(Exception e) { JOptionPane.showMessageDialog(null, "加载不成功！");}
	}
	
	/**
	 * Create the frame.
	 */
	public StudentSelect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblId = new JLabel("\u5B66\u53F7\uFF1A");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("\u59D3\u540D\uFF1A");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblAge = new JLabel("\u5E74\u9F84\uFF1A");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblSex = new JLabel("\u6027\u522B\uFF1A");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblTelephone = new JLabel("\u7535\u8BDD\uFF1A");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNational = new JLabel("\u56FD\u7C4D\uFF1A");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblNativeplace = new JLabel("\u7C4D\u8D2F\uFF1A");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String name=textField_1.getText();
				String age=textField_2.getText();
				String sex=textField_3.getText();
				String telephone=textField_4.getText();
				String national=textField_5.getText();
				String nativeplace=textField_6.getText();
				String password=textField_7.getText();
				if(sex.equals("男")||sex.equals("女")) {
					addRow(id,name,age,sex,telephone,national,nativeplace,password);
				}
				else {
					JOptionPane.showMessageDialog(null, "管理员性别只能为男或女！");
				}
				
				
			}
		});
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				String name=textField_1.getText();
				String age=textField_2.getText();
				String sex=textField_3.getText();
				String telephone=textField_4.getText();
				String national=textField_5.getText();
				String nativeplace=textField_6.getText();
				String password=textField_7.getText();
				if(sex.equals("男")||sex.equals("女")){
					updateRow(id,name,age,sex,telephone,national,nativeplace,password);
				}
				else {
					JOptionPane.showMessageDialog(null, "管理员性别只能为男或女！");
				}
				
			}
		});
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				deleteRow(id);
			}
		});
		
		JButton button_3 = new JButton("\u5237\u65B0");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id1=textField.getText();
				String name1=textField.getText();
				
				fillTable(table);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button_4 = new JButton("\u67E5\u8BE2");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=textField.getText();
				selectRow(id);
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
				textField_4.setText(table.getValueAt(rowIndex, 4).toString());
				textField_5.setText(table.getValueAt(rowIndex, 5).toString());
				textField_6.setText(table.getValueAt(rowIndex, 6).toString());
				textField_7.setText(table.getValueAt(rowIndex, 7).toString());
				
				textField.setEditable(true);
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				textField_4.setEditable(true);
				textField_5.setEditable(true);
				textField_6.setEditable(true);
				textField_7.setEditable(true);
			}
		});
		
		JButton button_6 = new JButton("\u8FD4\u56DE");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu mn=new menu();
				mn.setVisible(true);
				
			}
		});
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblTelephone)
								.addGap(18)
								.addComponent(textField_4))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblAge)
								.addGap(18)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblId)
								.addGap(18)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblName)
								.addGap(18)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblSex)
								.addGap(18)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNational)
							.addGap(18)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNativeplace)
							.addGap(18)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_4)
								.addComponent(button)
								.addComponent(button_3))
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(button_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_5)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(button_6))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAge)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSex)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelephone)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNational)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNativeplace)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button)
								.addComponent(button_2)
								.addComponent(button_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_4)
								.addComponent(button_5)
								.addComponent(button_6))
							.addGap(1)
							.addComponent(button_3))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
