package 数据库课设;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopPaneUI;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class menu extends JFrame {

	private JDesktopPane desktopPane;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
					frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
					frame.setTitle("菜单");
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
	public menu() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\javaicon\\menu_128px_1211515_easyicon.net.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 438);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u7BA1\u7406\u5458\u7BA1\u7406");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("\u7BA1\u7406");
		mnNewMenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministratorSelect am=new AdministratorSelect();
				am.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("\u5B66\u751F\u7BA1\u7406");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("\u7BA1\u7406");
		mnNewMenu_1.add(menuItem_1);
		
		JMenuItem menuItem_4 = new JMenuItem("\u67E5\u8BE2");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Student1 s1=new Student1();
				s1.setVisible(true);
			}
		});
		mnNewMenu_1.add(menuItem_4);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentSelect sd=new StudentSelect();
				sd.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_2 = new JMenu("\u5BBF\u820D\u7BA1\u7406");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("\u7BA1\u7406");
		mnNewMenu_2.add(menuItem_2);
		
		JMenuItem menuItem_5 = new JMenuItem("\u67E5\u8BE2");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dormitory1 d1=new Dormitory1();
				d1.setVisible(true);
			}
		});
		mnNewMenu_2.add(menuItem_5);
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DormitorySelect dm=new DormitorySelect();
				dm.setVisible(true);
			}
		});
		
		JMenu menu = new JMenu("\u4F4F\u5BBF\u7BA1\u7406");
		menuBar.add(menu);
		
		JMenuItem menuItem_3 = new JMenuItem("\u7BA1\u7406");
		menu.add(menuItem_3);
		
		JMenuItem menuItem_6 = new JMenuItem("\u67E5\u8BE2");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accomodation1 a1=new Accomodation1();
				a1.setVisible(true);
			}
		});
		menu.add(menuItem_6);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccommodationSelect as=new AccommodationSelect();
				as.setVisible(true);				
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 557, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 356, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
		
		if("学生".equals(login.judge)){
			menuItem.setEnabled(false);
			menuItem_1.setEnabled(false);
			menuItem_2.setEnabled(false);
			menuItem_3.setEnabled(false);
			mnNewMenu.setEnabled(false);
			System.out.println(login.judge);
		}
		if("管理员".equals(login.judge)){
			System.out.println(login.judge);
		}
	}
}
