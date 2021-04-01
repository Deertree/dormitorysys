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

public class DormitorySelect extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton button;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;


	public void fillTable(JTable table) {
		try {
			String[] column={"宿舍号","宿舍性别","应住人数","实住人数","住宿费","宿舍长","备注"};
			DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;};
			String url ="jdbc:mysql://localhost:3306/YU"; 
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			Connection conn= DriverManager.getConnection(url,"root","123456"); 
			java.sql.Statement stmt=  conn.createStatement();
			String sql="select * from Tdortmitory";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				String id=rs.getString("Dnumber");
				String sex=rs.getString("Dsex");
				String y=rs.getString("Dynumber");
				String s=rs.getString("Dsnumber");
				String money=rs.getString("Dmoney");
				String st=rs.getString("Dstudent");
				String note=rs.getString("Dnote");
				Object[] str_row= {id,sex,y,s,money,st,note};
				defaultTableModel.addRow(str_row);
			}
			rs.close();stmt.close();conn.close();
			table.setModel(defaultTableModel);
		}catch(Exception e) { JOptionPane.showMessageDialog(null, "加载不成功！");}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DormitorySelect frame = new DormitorySelect();
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					frame.setTitle("宿舍管理");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
public void addRow(String id,String sex,String y,String s,String money,String st,String note){
	try {
		String url ="jdbc:mysql://localhost:3306/YU"; 
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		Connection conn= DriverManager.getConnection(url,"root","123456"); 
		java.sql.Statement stmt=conn.createStatement();	
		String sql="insert into Tdortmitory values('"+id+"','"+sex+"','"+y+"','"+s+"','"+money+"','"+st+"','"+note+"')";
		int rowCount=stmt.executeUpdate(sql);
		JOptionPane.showMessageDialog(null, "成功添加"+rowCount+"条记录！");
		stmt.close();
		conn.close();
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, "添加不成功！请输入正确的宿舍长学号！");
	}
}

public void updateRow(String id,String sex,String y,String s,String money,String st,String note){
	try {
		String url ="jdbc:mysql://localhost:3306/YU"; 
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		Connection conn= DriverManager.getConnection(url,"root","123456"); 
		java.sql.Statement stmt=conn.createStatement();	
		String sql="update Tdortmitory set Dsex='"+sex+"',Dynumber='"+y+"',Dsnumber='"+s+"',Dmoney='"+money+"',Dstudent='"+st+"',Dnote='"+note+"' where Dnumber='"+id+"'";	
		int rowCount=stmt.executeUpdate(sql);
		JOptionPane.showMessageDialog(null, "成功修改"+rowCount+"条记录！");
		stmt.close();
		conn.close();
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, "修改不成功！请输入正确的学生学号！");
	}
}
//根据宿舍号删除数据
public void deleteRow(String id){
	try {
		String url ="jdbc:mysql://localhost:3306/YU"; 
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		Connection conn= DriverManager.getConnection(url,"root","123456"); 
		java.sql.Statement stmt=conn.createStatement();	
		String sql1="select * from Accmodation";
		ResultSet rs1=stmt.executeQuery(sql1);
		while(rs1.next()) {
			if(id.equals(rs1.getString("Dnumber"))) {
				String sql3="delete from Accmodation where Dnumber='"+id+"'";
				System.out.println(sql3);
				int i=stmt.executeUpdate(sql3);
				break;		            			  
		        }
			}
		String sql="delete from Tdortmitory where Dnumber='"+id+"'";	
		System.out.println(sql);
		int rowCount=stmt.executeUpdate(sql);
		JOptionPane.showMessageDialog(null, "成功删除"+rowCount+"条记录！");
		stmt.close();
		conn.close();
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, "删除不成功！该宿舍与其他表有连接！");
	}
}
//根据宿舍号来查询信息
public void selectRow(String id){
	try {
		String url ="jdbc:mysql://localhost:3306/YU"; 
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		Connection conn= DriverManager.getConnection(url,"root","123456"); 
		java.sql.Statement stmt=conn.createStatement();	
		String sql="select * from Tdortmitory where Dnumber='"+id+"'";	
		ResultSet rs=stmt.executeQuery(sql);
		String[] column={"宿舍号","宿舍性别","应住人数","实住人数","住宿费","宿舍长","备注"};
		DefaultTableModel defaultTableModel=new DefaultTableModel(column,0) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;};
		while(rs.next()) {
			String id1=rs.getString("Dnumber");
			String sex=rs.getString("Dsex");
			String y=rs.getString("Dynumber");
			String s=rs.getString("Dsnumber");
			String money=rs.getString("Dmoney");
			String st=rs.getString("Dstudent");
			String note=rs.getString("Dnote");
			Object[] str_row= {id1,sex,y,s,money,st,note};
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
	public DormitorySelect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 983, 590);
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
		
		JButton button_1 = new JButton("\u6DFB\u52A0");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String sex=textField_1.getText();
				String y=textField_2.getText();
				String s=textField_3.getText();
				String money=textField_4.getText();
				String st=textField_5.getText();
				String note=textField_6.getText();
				if(sex.equals("男")||sex.equals("女")){
					addRow(id,sex,y,s,money,st,note);
				}
				else {
					JOptionPane.showMessageDialog(null, "管理员性别只能为男或女！");
				}
				
			}
		});
		
		JButton button_2 = new JButton("\u4FEE\u6539");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				String sex=textField_1.getText();
				String y=textField_2.getText();
				String s=textField_3.getText();
				String money=textField_4.getText();
				String st=textField_5.getText();
				String note=textField_6.getText();
				if(sex.equals("男")||sex.equals("女")){
					updateRow(id,sex,y,s,money,st,note);
				}
				else {
					JOptionPane.showMessageDialog(null, "管理员性别只能为男或女！");
				}
						
			}
		});
		
		JLabel label = new JLabel("\u5BBF\u820D\u53F7\uFF1A");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6027   \u522B\uFF1A");
		
		JLabel label_2 = new JLabel("\u5E94\u4F4F\u4EBA\u6570\uFF1A");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5B9E\u4F4F\u4EBA\u6570\uFF1A");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel label_4 = new JLabel("\u4F4F\u5BBF\u8D39\uFF1A");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5BBF\u820D\u957F\uFF1A");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel label_6 = new JLabel("\u5907   \u6CE8\uFF1A");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JButton button_3 = new JButton("\u5220\u9664");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id=textField.getText();
				deleteRow(id);				
			}
		});
		
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
				
				textField.setEditable(true);
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				textField_4.setEditable(true);
				textField_5.setEditable(true);
				textField_6.setEditable(true);
			}
		});
		
		JButton button_6 = new JButton("\u8FD4\u56DE");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu mn=new menu();
				mn.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 623, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(label_1)
								.addComponent(label_3)
								.addComponent(label_4)
								.addComponent(label_5)
								.addComponent(label_6)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_1)
							.addGap(18)
							.addComponent(button_2)
							.addGap(18)
							.addComponent(button_3))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button_6)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_4)
									.addGap(18)
									.addComponent(button)
									.addGap(18)
									.addComponent(button_5)))))
					.addGap(93))
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
							.addGap(27)
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
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_6)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1)
								.addComponent(button_2)
								.addComponent(button_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_4)
								.addComponent(button)
								.addComponent(button_5))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_6))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
