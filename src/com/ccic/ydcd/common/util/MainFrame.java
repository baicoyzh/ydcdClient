package com.ccic.ydcd.common.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame implements ItemListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxType;
	private JComboBox<String> comboBoxTranType;
	private JComboBox<String> comboBoxThemes;
	private JTextField textFieldPassword;
	private JTextField textFieldIp;
	private JTextField textFieldPort;
	private JTextArea jtaRequest;
	private JTextArea jtaRespone;
	private JButton buttonTest;
	private Properties properties = new Properties();
	private JButton buttonBeautify;
	private JFrame mainFrame;
	//private JLabel labelThemes;
	private JLabel labelTimeout;
	private JTextField textFieldTimeout;
	private JLabel labelMs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		com.incors.plaf.alloy.AlloyLookAndFeel.setProperty("alloy.isLookAndFeelFrameDecoration", "true");
		com.incors.plaf.alloy.AlloyLookAndFeel.setProperty("alloy.theme", "glass");

		try {

			javax.swing.LookAndFeel alloyLnF = new com.incors.plaf.alloy.AlloyLookAndFeel();
			UIManager.setLookAndFeel(alloyLnF);

		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		try {
			MainFrame frame = new MainFrame();
			JFrame.setDefaultLookAndFeelDecorated(true);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */

	public MainFrame() {

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screensize.getWidth() - 300;
		int height = (int) screensize.getHeight() - 80;

		setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("logo.png")));
		setTitle("\u79FB\u52A8\u51FA\u5355\u63A5\u53E3\u6D4B\u8BD5");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 1024, 768);
		setBounds(100, 100, width, height);
		setLocationRelativeTo(getOwner());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTop = new JPanel();
		// panelTop.setBounds(5, 5, 998, 33);
		panelTop.setBounds(5, 5, width - 26, 33);
		contentPane.add(panelTop);

		JLabel lblTestType = new JLabel("\u7C7B\u578B");
		@SuppressWarnings("unused")
		String[] comboText = { "\u7EDF\u4E00\u63A5\u53E3", "\u8F66\u7AD9\u7CFB\u7EDF" };

		JLabel lblPassword = new JLabel("\u5BC6\u94A5");

		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(7);

		JLabel lblIp = new JLabel("IP\u5730\u5740");

		textFieldIp = new JTextField();
		textFieldIp.setColumns(7);

		JLabel lblPort = new JLabel("\u7AEF\u53E3");

		textFieldPort = new JTextField();
		textFieldPort.setColumns(3);

		buttonTest = new JButton("\u6D4B\u8BD5");
		buttonTest.addActionListener(this);

		buttonBeautify = new JButton("\u7F8E\u5316XML");
		buttonBeautify.addActionListener(this);

		comboBoxType = new JComboBox<String>();
		comboBoxType.addActionListener(this);
		comboBoxType.addItemListener(this);

//		comboBoxThemes = new JComboBox<String>();
//		comboBoxThemes.addActionListener(this);
//		comboBoxThemes.addItemListener(this);
//
//		labelThemes = new JLabel("\u4E3B\u9898");
//		panelTop.add(labelThemes);
//
//		comboBoxThemes
//				.setModel(new DefaultComboBoxModel<String>(new String[] { "Glass", "Bedouin", "Acid", "Metal" }));
//		panelTop.add(comboBoxThemes);

		labelTimeout = new JLabel("\u8D85\u65F6\u65F6\u95F4");
		panelTop.add(labelTimeout);

		textFieldTimeout = new JTextField();
		textFieldTimeout.setText("10000");
		textFieldTimeout.setColumns(4);
		panelTop.add(textFieldTimeout);

		labelMs = new JLabel("\u5355\u4F4D\uFF1Ams");
		panelTop.add(labelMs);
		comboBoxType.setModel(new DefaultComboBoxModel<String>(
				new String[] { "\u7EDF\u4E00\u63A5\u53E3", "\u8F66\u7AD9\u7CFB\u7EDF" }));
		panelTop.add(comboBoxType);

		panelTop.add(lblTestType);

		comboBoxTranType = new JComboBox<String>();
		comboBoxTranType.addActionListener(this);
		comboBoxTranType.addItemListener(this);
		comboBoxTranType.setModel(new DefaultComboBoxModel<String>(new String[] { "\u51FA\u5355", "\u9000\u5E9F\u5355",
				"\u65B9\u6848\u4E0B\u8F7D", "\u5355\u8BC1\u6821\u9A8C" }));
		panelTop.add(comboBoxTranType);
		panelTop.add(lblPassword);
		panelTop.add(textFieldPassword);
		panelTop.add(lblIp);
		panelTop.add(textFieldIp);
		panelTop.add(lblPort);
		panelTop.add(textFieldPort);
		panelTop.add(buttonTest);
		panelTop.add(buttonBeautify);

		jtaRequest = new JTextArea(10, 15);
		jtaRequest.setLineWrap(true);// 激活自动换行功能
		jtaRequest.setWrapStyleWord(true);// 激活断行不断字功能

		JScrollPane jscrollPaneRequest = new JScrollPane(jtaRequest);
		// jscrollPaneRequest.setBounds(10, 42, 495, 695);
		jscrollPaneRequest.setBounds(5, 42, width / 2 - 10, height - 73);
		contentPane.add(jscrollPaneRequest);

		jtaRespone = new JTextArea(10, 15);
		jtaRespone.setLineWrap(true);// 激活自动换行功能
		jtaRespone.setWrapStyleWord(true);// 激活断行不断字功能

		JScrollPane jscrollPaneRespone = new JScrollPane(jtaRespone);
		// jscrollPaneRespone.setBounds(511, 42, 495, 695);
		jscrollPaneRespone.setBounds(width / 2 - 3, 42, width / 2 - 10, height - 73);
		contentPane.add(jscrollPaneRespone);

		properties = DealPropertiesFile.readPropertiesFile("ydcd.properties");
		String strPreText = comboBoxType.getSelectedIndex() + "_" + comboBoxTranType.getSelectedIndex();
		String strPreParam = String.valueOf(comboBoxType.getSelectedIndex());
		textFieldPassword.setText(properties.getProperty(strPreParam + "_password"));
		textFieldIp.setText(properties.getProperty(strPreParam + "_ip"));
		textFieldPort.setText(properties.getProperty(strPreParam + "_port"));

		jtaRequest.setText(properties.getProperty(strPreText + "_request"));
		// jtaRespone.setText(properties.getProperty(strPreText + "_respone"));
		jtaRequest.setCaretPosition(0);
		jtaRespone.setCaretPosition(0);
	}

	public void sendUniformData() {
		String strRespone = "";
		String strTimeout = textFieldTimeout.getText();
		if (strTimeout.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("超时时间不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("\u8d85\u65f6\u65f6\u95f4\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}

		String strPassword = textFieldPassword.getText();
		if (strPassword.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("密钥不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("\u5bc6\u94a5\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}

		String strIp = textFieldIp.getText();
		if (strIp.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("IP地址不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("IP\u5730\u5740\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}

		String strPort = textFieldPort.getText();
		if (strPort.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("端口不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("\u7aef\u53e3\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}

		String strRequest = jtaRequest.getText();
		if (strRequest.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("请求报文不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("\u8bf7\u6c42\u62a5\u6587\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}

		jtaRespone.setText("");
		int iPort = Integer.parseInt(strPort, 10);

		try {
			UniformInterface ui = new UniformInterface();
			String xmlData;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			String strTime = sdf.format(new Date());
			xmlData = strTime + strRequest;
			// 明文报文前面增加8位报文长度，不足8位补0
			String strTotal = String.valueOf((xmlData).length());
			strTotal = "00000000" + strTotal;
			strTotal = strTotal.substring(strTotal.length() - 8, strTotal.length());
			xmlData = strTotal + xmlData;
			// 明文报文前面增加交易类型：0-出单，1-退废单，2-方案下载，3-单证校验
			switch (comboBoxTranType.getSelectedIndex()) {
			case 0:
				xmlData = "8000000" + xmlData;
				break;
			case 1:
				xmlData = "9000000" + xmlData;
				break;
			case 2:
				xmlData = "3000000" + xmlData;
				break;
			case 3:
				xmlData = "4000000" + xmlData;
				break;
			}
			// 加密报文
			xmlData = ui.encryptData(xmlData, strPassword);
			// 加密报文前面增加8位报文长度，不足8位补0
			strTotal = String.valueOf((xmlData).length());
			strTotal = "00000000" + strTotal;
			strTotal = strTotal.substring(strTotal.length() - 8, strTotal.length());
			xmlData = strTotal + xmlData;

			strRespone = "\u52a0\u5bc6\u540e\u62a5\u6587：\n" + xmlData;

			// 设置超时时间
			ui.setTimeout(Integer.parseInt(strTimeout));
			// 向服务器发送报文并接收返回
			String strReturn = ui.sendData(xmlData, strIp, iPort);

			strRespone = "\u8fd4\u56de\u7684\u5bc6\u6587：\n" + strReturn + "\n\n\n" + strRespone;
			// 解密返回的报文
			strReturn = ui.decryptData(strReturn.substring(8, strReturn.length()), strPassword);
			if (strReturn.length() > 33) {
				// 对xml格式进行格式化
				XmlFormatter xf = new XmlFormatter();

				int index = strReturn.indexOf("<");
				if (index >= 0)
					strReturn = strReturn.substring(0, index) + "\n"
							+ xf.format(strReturn.substring(index, strReturn.length()));

				strRespone = "\u8fd4\u56de\u7684\u660e\u6587：\n" + strReturn + "\n\n\n" + strRespone;
			}

			jtaRespone.setForeground(Color.BLACK);
			jtaRespone.setText(strRespone);
			jtaRespone.setCaretPosition(0);
		} catch (Exception ex) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText(ex.toString());
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage(ex.toString());
			jDialogForError.setVisible(true);
		}

		try {
			String strPreText = comboBoxType.getSelectedIndex() + "_" + comboBoxTranType.getSelectedIndex();
			String strPreParam = String.valueOf(comboBoxType.getSelectedIndex());
			properties.setProperty(strPreParam + "_password", strPassword);
			properties.setProperty(strPreParam + "_ip", strIp);
			properties.setProperty(strPreParam + "_port", strPort);
			properties.setProperty(strPreText + "_request", strRequest);
			// properties.setProperty(strPreText + "_respone", strRespone);
			DealPropertiesFile.writePropertiesFile("ydcd.properties", properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendCyxData() {
		String strRespone = "";
		String strTimeout = textFieldTimeout.getText();
		if (strTimeout.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("超时时间不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("\u8d85\u65f6\u65f6\u95f4\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}

		String strPassword = textFieldPassword.getText();
		if (strPassword.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("密钥不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("\u5bc6\u94a5\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}
		String strIp = textFieldIp.getText();
		if (strIp.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("IP地址不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("IP\u5730\u5740\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}
		String strPort = textFieldPort.getText();
		if (strPort.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("端口不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("\u7aef\u53e3\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}

		String strRequest = jtaRequest.getText();
		if (strRequest.trim().equals("")) {
			// jtaRespone.setForeground(Color.RED);
			// jtaRespone.setText("请求报文不能为空！");
			// return;
			JDialogForError jDialogForError = new JDialogForError(mainFrame);
			jDialogForError.setLocationRelativeTo(mainFrame);
			jDialogForError.setErrorMessage("\u8bf7\u6c42\u62a5\u6587\u4e0d\u80fd\u4e3a\u7a7a\uff01");
			jDialogForError.setVisible(true);
			return;
		}

		jtaRespone.setText("");
		jtaRespone.setCaretPosition(0);

		int iPort = Integer.parseInt(strPort, 10);

		if (comboBoxType.getSelectedIndex() == 1) {
			try {
				CyxInterface ci = new CyxInterface();
				String xmlData = null;

				SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
				String baoWen = "0000008" + formate.format(new Date());// 定时出单接口
				// 将生成的报文打包
				DataPackage dataPack = new DataPackage();
				dataPack.addHeadItem(baoWen, 133);
				baoWen = dataPack.getSendbuf();
				xmlData = baoWen + strRequest;

				String strKey = "";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				String str = "CCIC" + df.format(new Date());
				// 加密报文
				CheckMd5 checkmd5 = new CheckMd5();
				strKey = checkmd5.getMD5ofStr(str);
				xmlData = strKey + xmlData;
				xmlData = ci.encryptData(xmlData, strPassword);
				xmlData = xmlData + "\n";
				// 在报文前面增加8位表示报文长度
				int cn = FunctionUtils.checkByteLen(xmlData);// 计算中文个数

				String strTotal = String.valueOf(xmlData.length() + cn);
				// 报文前八位表示报文长度
				strTotal = "00000000" + strTotal;
				strTotal = strTotal.substring(strTotal.length() - 8, strTotal.length());

				xmlData = strTotal + xmlData;// 报文前八位表示报文长度
				strRespone = "\u52a0\u5bc6\u540e\u62a5\u6587：\n" + xmlData;
				// 设置超时时间
				ci.setTimeout(Integer.parseInt(strTimeout));
				// 向服务器发送报文并接收返回
				String strReturn = ci.sendData(xmlData, strIp, iPort);

				strRespone = "\u8fd4\u56de\u7684\u5bc6\u6587：\n" + strReturn + "\n\n\n" + strRespone;
				// 解密返回的报文
				strReturn = ci.decryptData(strReturn.substring(8, strReturn.length()), strPassword);
				if (strReturn.length() > 133) {
					// 对xml格式进行格式化
					XmlFormatter xf = new XmlFormatter();

					int index = strReturn.indexOf("<");
					if (index >= 0)
						strReturn = strReturn.substring(0, index) + "\n"
								+ xf.format(strReturn.substring(index, strReturn.length()));
					// 返回的明文
					strRespone = "\u8fd4\u56de\u7684\u660e\u6587：\n" + strReturn + "\n\n\n" + strRespone;
				}

				jtaRespone.setForeground(Color.BLACK);
				jtaRespone.setText(strRespone);
				jtaRespone.setCaretPosition(0);
			} catch (Exception ex) {
				// jtaRespone.setForeground(Color.RED);
				// jtaRespone.setText(ex.toString());
				JDialogForError jDialogForError = new JDialogForError(mainFrame);
				jDialogForError.setLocationRelativeTo(mainFrame);
				jDialogForError.setErrorMessage(ex.toString());
				jDialogForError.setVisible(true);
			}
		}

		try {
			String strPreText = comboBoxType.getSelectedIndex() + "_" + comboBoxTranType.getSelectedIndex();
			String strPreParam = String.valueOf(comboBoxType.getSelectedIndex());
			properties.setProperty(strPreParam + "_password", strPassword);
			properties.setProperty(strPreParam + "_ip", strIp);
			properties.setProperty(strPreParam + "_port", strPort);
			properties.setProperty(strPreText + "_request", strRequest);
			// properties.setProperty(strPreText + "_respone", strRespone);
			DealPropertiesFile.writePropertiesFile("ydcd.properties", properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("\u6D4B\u8BD5")) {
			jtaRespone.setText("");
			if (comboBoxType.getSelectedIndex() == 0)
				sendUniformData();
			else {
				sendCyxData();
			}
			jtaRespone.requestFocus();
		}
		if (ae.getActionCommand().equals("\u7F8E\u5316XML")) {
			if (jtaRequest.getText() == null || jtaRequest.getText().trim().equals("")) {

			} else {
				XmlFormatter xf = new XmlFormatter();
				try {
					String formatString = xf.format(jtaRequest.getText().trim());
					jtaRequest.setText(formatString);
					jtaRequest.setCaretPosition(0);
				} catch (Exception e) {
					e.printStackTrace();
					JDialogForError jDialogForError = new JDialogForError(mainFrame);
					jDialogForError.setLocationRelativeTo(mainFrame);
					jDialogForError.setErrorMessage(e.toString());
					jDialogForError.setVisible(true);
				}
			}
			jtaRequest.requestFocus();
		}
		if (ae.getSource().equals(comboBoxThemes)) {
			String themes = (String) comboBoxThemes.getSelectedItem();

			LookAndFeel laf = null;

			if (themes.equals("Glass")) {
				com.incors.plaf.alloy.AlloyLookAndFeel.setProperty("alloy.theme", "glass");
				laf = new com.incors.plaf.alloy.AlloyLookAndFeel();
			} else if (themes.equals("Bedouin")) {
				com.incors.plaf.alloy.AlloyLookAndFeel.setProperty("alloy.theme", "bedouin");
				laf = new com.incors.plaf.alloy.AlloyLookAndFeel();
			} else if (themes.equals("Acid")) {
				com.incors.plaf.alloy.AlloyLookAndFeel.setProperty("alloy.theme", "acid");
				laf = new com.incors.plaf.alloy.AlloyLookAndFeel();
			} else if (themes.equals("Metal")) {
				laf = new javax.swing.plaf.metal.MetalLookAndFeel();
			}
			JFrame jFrame = (JFrame) this.getRootPane().getParent(); 
			setLookAndFeel(jFrame, laf);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {

		if (ie.getSource().equals(comboBoxType)) {
			if (comboBoxType.getSelectedIndex() == 0)
				comboBoxTranType.setModel(new DefaultComboBoxModel<String>(new String[] { "\u51FA\u5355",
						"\u9000\u5E9F\u5355", "\u65B9\u6848\u4E0B\u8F7D", "\u5355\u8BC1\u6821\u9A8C" }));
			else
				comboBoxTranType
						.setModel(new DefaultComboBoxModel<String>(new String[] { "\u901A\u7528\u3000\u3000" }));
		}

		if (ie.getStateChange() == ItemEvent.SELECTED) {
			jtaRespone.setForeground(Color.BLACK);
			properties = DealPropertiesFile.readPropertiesFile("ydcd.properties");
			String strPreText = comboBoxType.getSelectedIndex() + "_" + comboBoxTranType.getSelectedIndex();
			String strPreParam = String.valueOf(comboBoxType.getSelectedIndex());
			textFieldPassword.setText(properties.getProperty(strPreParam + "_password"));
			textFieldIp.setText(properties.getProperty(strPreParam + "_ip"));
			textFieldPort.setText(properties.getProperty(strPreParam + "_port"));
			jtaRequest.setText(properties.getProperty(strPreText + "_request"));
			// jtaRespone.setText(properties.getProperty(strPreText +
			// "_respone"));
			jtaRequest.setCaretPosition(0);
			jtaRespone.setCaretPosition(0);
		}
	}

	public void setLookAndFeel(Component target, LookAndFeel lnf) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(lnf.getSupportsWindowDecorations());
			JDialog.setDefaultLookAndFeelDecorated(lnf.getSupportsWindowDecorations());
			UIManager.setLookAndFeel(lnf);
			SwingUtilities.updateComponentTreeUI(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLookAndFeel(JFrame frame, LookAndFeel laf) {
		try {
			// 改变全局设置
			JFrame.setDefaultLookAndFeelDecorated(laf.getSupportsWindowDecorations());
			JDialog.setDefaultLookAndFeelDecorated(laf.getSupportsWindowDecorations());
			UIManager.setLookAndFeel(laf);
			// 改变当前frame的窗口,边框,标题
			frame.dispose();
			//frame.setVisible(false);
			frame.setUndecorated(laf.getSupportsWindowDecorations());
			frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void setLookAndFeel(JDialog frame, LookAndFeel laf) {
		try {
			// 改变全局设置
			JFrame.setDefaultLookAndFeelDecorated(laf.getSupportsWindowDecorations());
			JDialog.setDefaultLookAndFeelDecorated(laf.getSupportsWindowDecorations());
			UIManager.setLookAndFeel(laf);

			// 改变当前frame的窗口,边框,标题
			//frame.dispose();
			frame.setUndecorated(laf.getSupportsWindowDecorations());
			frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
			
			SwingUtilities.updateComponentTreeUI(frame);
			
			frame.show();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
