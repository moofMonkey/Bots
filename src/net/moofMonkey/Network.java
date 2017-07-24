package net.moofMonkey;

import java.io.DataOutputStream;
import java.io.IOException;

public class Network {
	public static void sendHandshake(int version, String name, final DataOutputStream dos, final String ip, final int port) throws IOException {
		dos.writeByte(2);
		dos.writeByte(version);
		writeString(dos, name);
		writeString(dos, ip);
		dos.writeInt(port);
	}
	
	public static void sendChat(final DataOutputStream dos, final String msg) throws Throwable {
		dos.writeByte(3);
		writeString(dos, msg);
	}

	public static void sendClientSettings(final DataOutputStream dos) throws IOException {
		dos.writeByte(204);
		writeString(dos, "en_US");
		dos.writeByte(1);
		dos.writeByte(8);
		dos.writeByte(1);
		dos.writeBoolean(true);
	}

	public static void sendReady(final DataOutputStream dos) throws IOException {
		dos.write(205);
		dos.writeByte(0);
	}
	
	public static void writeString(final DataOutputStream dos, final String string) throws IOException {
		dos.writeShort(string.length());
		dos.writeChars(string);
	}
}
