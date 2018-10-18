package com.xnx3.spider.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.xnx3.SystemUtil;
import com.xnx3.UI;
import com.xnx3.UrlUtil;
import com.xnx3.spider.Global;
import com.xnx3.spider.Initialize;
import com.xnx3.spider.cache.PageSpider;
import com.xnx3.util.StringUtil;
import com.xnx3.util.ui.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea_url;
	private JButton btnNewButton;
	private JPanel panel_explain;
	private boolean moreSetPanel_use = true;	//是否使用更多设置的面板显示。若为true，则是显示更多设置面板。这里主要用于记录当前是显示还是隐藏的，以便判断按下按钮后要显示还是隐藏。仅此而已
	private JButton buttonMoreSet;
	private JPanel panel_MoreSet;
	private JTextArea textArea_cooikes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
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
	public MainUI() {
		setTitle("扒网站工具");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(Menu.aboutMenu());
		
		JMenu actionMenu = new JMenu("功能");
		menuBar.add(actionMenu);
		JMenuItem logNewMenuItem = new JMenuItem("运行日志");
		actionMenu.add(logNewMenuItem);
		logNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Global.logUI == null){
					UI.showMessageDialog("工具尚未运行，暂无日志");
					return;
				}
				Global.logUI.setVisible(true);
			}
		});
		
		
		JMenu helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("使用说明");
		helpMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SystemUtil.openUrl("http://www.wang.market/2712.html");
			}
		});
		
		
		JMenu siteMenu = new JMenu("建站");
		menuBar.add(siteMenu);
		
		JMenuItem mianfeiMenuItem = new JMenuItem("免费开通网站");
		siteMenu.add(mianfeiMenuItem);
		mianfeiMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SystemUtil.openUrl("http://wang.market/regByPhone.do?inviteid=50");
			}
		});
		JMenuItem wscMenuItem = new JMenuItem("网市场云建站系统");
		siteMenu.add(wscMenuItem);
		wscMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SystemUtil.openUrl("http://www.wang.market");
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		
		panel_explain = new JPanel();
		
		panel_MoreSet = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_explain, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
				.addComponent(panel_MoreSet, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_MoreSet, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_explain, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
		);
		
		JLabel lblCookie = new JLabel("Cookies：");
		lblCookie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCookie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				UI.showMessageForMouse(e, 350, 150, "扒取对方网站页面时，可设置一块提交的Cookies<br>比如，cookies有 a、b、c 三个参数，那么填入的格式为：a=1;b=2;c=3 多个参数用;分割");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				UI.hiddenMessageForMouse();
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_MoreSet = new GroupLayout(panel_MoreSet);
		gl_panel_MoreSet.setHorizontalGroup(
			gl_panel_MoreSet.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_MoreSet.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCookie, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_MoreSet.setVerticalGroup(
			gl_panel_MoreSet.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_MoreSet.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_MoreSet.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_MoreSet.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_MoreSet.createSequentialGroup()
							.addComponent(lblCookie, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addGap(9))))
		);
		
		textArea_cooikes = new JTextArea();
		textArea_cooikes.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_cooikes);
		panel_MoreSet.setLayout(gl_panel_MoreSet);
		
		btnNewButton = new JButton("开始抓取");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//高级设置中，设置的数据进行全局缓存
				PageSpider.setCookiesMap(getTextArea_cooikes().getText());
				
				String t = getTextArea_url().getText();
				if(t.indexOf("请将要抓取") > -1){
					if(UI.showConfirmDialog("提示：请先设定要抓取的URL<br/>是否查看帮助说明？") == UI.CONFIRM_YES){
						SystemUtil.openUrl("http://www.wang.market/2712.html");
					}
					return;
				}
				
				
				final String[] urls = t.split("\r|\n");
				
				if(urls.length > 0 && urls[0].length() > 0){
					
				}else{
					UI.showMessageDialog("请输入目标网址");
					return;
				}
				
				Global.log("开始对输入网址检测...");
				Global.logUI.setVisible(true);
				getBtnNewButton_Start().setText("扒取中..");
				getBtnNewButton_Start().setEnabled(false);
				
				com.xnx3.spider.Global.templateDomain = UrlUtil.getDomain(urls[0]);
				Global.log("初始化创建存放文件夹： "+Global.getLocalTemplatePath());
				Initialize.createCacheFile();
				
				
				new Thread(new Runnable() {
					public void run() {
						for (int i = 0; i < urls.length; i++) {
							if(urls[i].length() > 6){
								new PageSpider(urls[i]);
							}
						}
						
						Global.log("扒取完毕");
						getBtnNewButton_Start().setText("开始提取");
						getBtnNewButton_Start().setEnabled(true);
						
						try {
							java.awt.Desktop.getDesktop().open(new File(Global.getLocalTemplatePath()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
				
			}
		});
		
		buttonMoreSet = new JButton("更多设置");
		buttonMoreSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moreSetPanel_showAndHidden();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonMoreSet, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
						.addComponent(buttonMoreSet, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JLabel lblNewLabel = new JLabel("作者：管雷鸣    微信公众号:wangmarket");
		
		JLabel lblNewLabel_1 = new JLabel("开源发布 gitee.com/mail_osc/templatespider");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SystemUtil.openUrl("https://gitee.com/mail_osc/templatespider");
			}
		});
		GroupLayout gl_panel_explain = new GroupLayout(panel_explain);
		gl_panel_explain.setHorizontalGroup(
			gl_panel_explain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_explain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_explain.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_explain.setVerticalGroup(
			gl_panel_explain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_explain.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_explain.setLayout(gl_panel_explain);
		
		textArea_url = new JTextArea();
		scrollPane.setViewportView(textArea_url);
		contentPane.setLayout(gl_contentPane);
	}
	public JTextArea getTextArea_url() {
		return textArea_url;
	}

	public JButton getBtnNewButton_Start() {
		return btnNewButton;
	}
	public JPanel getPanel_explain() {
		return panel_explain;
	}
	public JButton getButtonMoreSet() {
		return buttonMoreSet;
	}
	public JPanel getPanel_MoreSet() {
		return panel_MoreSet;
	}

	public JTextArea getTextArea_cooikes() {
		return textArea_cooikes;
	}
	
	/**
	 * 更多设置面板的显示、隐藏。当点击显示或隐藏按钮时，执行的方法
	 */
	public void moreSetPanel_showAndHidden(){
		if(moreSetPanel_use){
			//有使用变为隐藏更多设置
			panel_MoreSet.setVisible(false);
			buttonMoreSet.setText("更多设置");
			moreSetPanel_use = false;
		}else{
			//有隐藏变为使用
			panel_MoreSet.setVisible(true);
			buttonMoreSet.setText("隐藏设置");
			moreSetPanel_use = true;
		}
	} 
}
