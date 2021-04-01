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

public class AccommodationSelect extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccommodationSelect frame = new AccommodationSelect();
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void addRow(String id,String n,String p,String cd){
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();	
			String sql="insert into Accmodation values('"+id+"','"+n+"','"+p+"','"+cd+"')";	
			int rowCount=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "成功添加"+rowCount+"条记录！");
			stmt.close();
			conn.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "添加不成功！该学生或该宿舍不存在！");
		}
	}
	//根据学号修改数据
	public void updateRow(String id,String n,String p,String cd){
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();	
			String sql="update Accmodation set Dnumber='"+n+"',Bnumber='"+p+"',Check_Date='"+cd+"' where Snumber='"+id+"'";	
			int rowCount=stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "成功修改"+rowCount+"条记录！");
			stmt.close();
			conn.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "修改不成功！该学生不存在！");
		}
	}
	//根据学号和宿舍号删除数据
	public void deleteRow(String id,String n){
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();	
			String sql="delete from Accmodation where Snumber='"+id+"' and Dnumber='"+n+"'";	
			System.out.println(sql);
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
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();	
			String sql="select * from Accmodation where Snumber='"+id+"'";
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
	public String getstudentsex(String id) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();		
			String sql="select * from Student where studentId='"+id+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String s1=rs.getString("studentSex");
			    System.out.println(s1);
			    return s1;
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "加载不成功！");
		}
		return "123";
	}
	public String getdormitorysex(String n) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();		
			String sql="select * from Tdortmitory where Dnumber='"+n+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String s1=rs.getString("Dsex");
			    System.out.println(s1);
			    return s1;
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "加载不成功！");
		}
		return "321";
	}
	public String getdormitorysnumber(String n) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();		
			String sql="select * from Tdortmitory where Dnumber='"+n+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String s1=rs.getString("Dsnumber");
			    System.out.println(s1);
			    return s1;
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "加载不成功！");
		}
		return "321";
	}
	public String getdormitoryynumber(String n) {
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();		
			String sql="select * from Tdortmitory where Dnumber='"+n+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String s1=rs.getString("Dynumber");
			    System.out.println(s1);
			    return s1;
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "加载不成功！");
		}
		return "321";
	}
	public void updateDormitoryRow(String id,int x){
		try {
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=conn.createStatement();
			String i=getdormitorysnumber(id);
			int j=Integer.parseInt(i);
			System.out.println(i);
			if(x>0) {
				j++;
				}
			else if(x<0) {
				j--;
			}
			
			String s=String.valueOf(j);
			String sql="update Tdortmitory set Dsnumber='"+s+"' where Dnumber='"+id+"'";	
			System.out.println(sql);
			int rowCount=stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "修改不成功！请输入正确的学生学号！");
		}
	}
	/**
	 * Create the frame.
	 */
	public AccommodationSelect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 457);
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
		
		JButton button_1 = new JButton("\u6DFB\u52A0");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String n=textField_1.getText();
				String p=textField_2.getText();
				String cd=textField_3.getText();
				if(getstudentsex(id).equals(getdormitorysex(n))) {
					int i=getdormitoryynumber(n).compareTo(getdormitorysnumber(n));
					if(i>0) {
						addRow(id,n,p,cd);
						updateDormitoryRow(n,1);
					}
					else {
						JOptionPane.showMessageDialog(null, "该宿舍人数已满！");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "该宿舍性别与学生性别不符！");
				}
				
			}
		});
		
		JButton button_2 = new JButton("\u4FEE\u6539");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String n=textField_1.getText();
				String p=textField_2.getText();
				String cd=textField_3.getText();
				if(getstudentsex(id).equals(getdormitorysex(n))) {
					updateRow(id,n,p,cd);
				}
				else {
					JOptionPane.showMessageDialog(null, "宿舍性别与学生性别不符！");
				}
				
			}
		});
		
		JButton button_3 = new JButton("\u5220\u9664");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String n=textField_1.getText();
				deleteRow(id,n);
				updateDormitoryRow(n,-1);
			}
		});
		
		JButton button_4 = new JButton("\u67E5\u627E");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				selectRow(id);
			}
		});
		
		JLabel label = new JLabel("\u5B66\u751F\u5B66\u53F7\uFF1A");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BBF\u820D\u7F16\u53F7\uFF1A");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5BBF\u820D\u4EBA\u6570\uFF1A");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5165\u4F4F\u65E5\u671F\uFF1A");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JButton button_5 = new JButton("\u8FD4\u56DE");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu mn=new menu();
				mn.setVisible(true);
			}
		});
		
		JButton button_6 = new JButton("\u9009\u4E2D");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button_6)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button_5)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1)
								.addComponent(button_2)
								.addComponent(button_3))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_4)
								.addComponent(button)
								.addComponent(button_5))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_6))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
