package 数据库课设;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class login extends JFrame {
	static String judge;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	String sn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    login frame = new login();
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					frame.setTitle("登录");
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
	public login()throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\javaicon\\User_login_64px_500811_easyicon.net.png"));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 397);		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u7BA1\u7406\u5458", "\u5B66\u751F"}));
		
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setIcon(new ImageIcon("F:\\javaicon\\accept_check_login_success_32px_1534_easyicon.net.png"));
		btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				judge=(String) comboBox.getSelectedItem();
				String y=textField.getText();
				String m=passwordField.getText();
				boolean flag=false;
				try {
					String url ="jdbc:mysql://localhost:3306/YU"; 
					Class.forName("org.gjt.mm.mysql.Driver").newInstance();
					Connection conn= DriverManager.getConnection(url,"root","123456"); 
					java.sql.Statement stmt=  conn.createStatement();
					if("管理员".equals(comboBox.getSelectedItem())){
				    String sql="select * from PersonManage";
					ResultSet rs=stmt.executeQuery(sql);
						while(rs.next()) {				
						    if(textField.getText().equals(rs.getString("personManageName"))) {
						    	if(passwordField.getText().equals(rs.getString("personManagepassword"))) {
						    		flag=true;
						    	}
						    	else{
									JOptionPane.showMessageDialog(login.this,"不是系统的合法用户或密码错误" );
								}
						    	}
						    }
					}
					if("学生".equals(comboBox.getSelectedItem())){
						String sql="select * from Student";
						ResultSet rs=stmt.executeQuery(sql);
							while(rs.next()) {				
							    if(textField.getText().equals(rs.getString("studentId"))) {
							    	if(passwordField.getText().equals(rs.getString("studentPassword"))) {
							    		flag=true;
							    	}
							    	else{
										JOptionPane.showMessageDialog(login.this,"不是系统的合法用户或密码错误" );
									}
							    	}
							    }
						
					}
					} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				if(flag==true) {
					menu mn=new menu();
				    mn.setVisible(true);
				    }
				
				
					
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setIcon(new ImageIcon("F:\\javaicon\\Login_64px_583562_easyicon.net.png"));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.PINK);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u5BC6\u7801");
		lblPassword.setFont(new Font("SimSun", Font.PLAIN, 18));
		lblPassword.setIcon(new ImageIcon("F:\\javaicon\\login_48px_557826_easyicon.net.png"));
		
		passwordField = new JPasswordField();
		
		JLabel label = new JLabel("\u5B66\u751F\u5BBF\u820D\u7BA1\u7406\u7CFB\u7EDF");
		label.setFont(new Font("宋体", Font.PLAIN, 26));
		
		
		JLabel label_1 = new JLabel("\u767B\u5F55\u8EAB\u4EFD\uFF1A");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(142, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(140))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(85, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(43))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(label_1))
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(119, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(193)
					.addComponent(btnNewButton)
					.addContainerGap(211, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(48))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
