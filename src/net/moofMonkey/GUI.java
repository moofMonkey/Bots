package net.moofMonkey;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import net.moofMonkey.bots.BotsThread;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JTextField ipfield;
	private static JTextField namefield;
	public static JTextField Text;
	private static JButton Send;
	private static JButton button;
	private static JComboBox<MinecraftVersion> version;

	static {
		GUI.ipfield = new JTextField();
		GUI.namefield = new JTextField();
		GUI.Text = new JTextField();
		GUI.Send = new JButton();
		GUI.button = new JButton();
		GUI.version = new JComboBox<MinecraftVersion>(MinecraftVersion.values());
	}

	public static void logger(final String text) {
		System.out.println(text);
	}

	public GUI() {
		logger("Creating text fields...");
		GUI.ipfield.setBounds(0, 0, 200, 20);
		GUI.ipfield.setText("Enter IP:Port");
		this.add(GUI.ipfield);
		GUI.namefield.setBounds(0, 20, 200, 20);
		GUI.namefield.setText("Enter Bot NickName");
		this.add(GUI.namefield);
		GUI.Text.setBounds(0, 80, 200, 20);
		GUI.Text.setText("Command/Text");
		this.add(GUI.Text);
		logger("Created text fields!");
		logger("Creating combobox...");
		GUI.version.setEditable(false);
		GUI.version.setSelectedIndex(0);
		GUI.version.setBounds(0, 40, 200, 20);
		this.add(GUI.version);
		logger("Combobox created!");
		logger("Creating button...");
		GUI.button.setBounds(0, 60, 200, 20);
		GUI.button.setText("Nuke it!");
		this.add(GUI.button);
		GUI.Send.setBounds(0, 100, 200, 20);
		GUI.Send.setText("Send to server");
		this.add(GUI.Send);
		logger("Button created!");
		logger("Set non-resizable...");
		this.setResizable(false);
		logger("Setted non-resizable!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setTitle("Bots (c) MoofMonkey");
		this.setSize(new Dimension(207, 150));
		this.setLayout(null);
		GUI.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				new BotsThread(ipfield.getText().trim(), namefield.getText(), ((MinecraftVersion) version.getSelectedItem()).getId()).start();
			}
		});
		GUI.Send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				BotsThread.botsCommand(GUI.Text.getText());
			}
		});
	}
}
