package net.moofMonkey;

import net.moofMonkey.GUI;

public class Main {
	public static void main(final String[] args) {
		System.out.println("Creating GUI...");
		final GUI gui = new GUI();
		System.out.println("GUI Created!");
		System.out.println("Showing GUI...");
		gui.setVisible(true);
		System.out.println("GUI Showed!");
	}
}
