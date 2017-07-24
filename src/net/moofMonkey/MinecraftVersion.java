package net.moofMonkey;

public enum MinecraftVersion {
	MINECRAFT_1_6_4("MINECRAFT_1_6_4", 0, "1.6.4", 78),
	MINECRAFT_1_6_2("MINECRAFT_1_6_2", 1, "1.6.2", 74),
	MINECRAFT_1_5_2("MINECRAFT_1_5_2", 2, "1.5.2", 61),
	MINECRAFT_1_4_7("MINECRAFT_1_4_7", 3, "1.4.7", 51);

	private String name;
	private int protocolVersion;

	private MinecraftVersion(final String s, final int n, final String name,
			final int protocolVersion) {
		this.name = name;
		this.protocolVersion = protocolVersion;
	}

	public int getId() {
		return this.protocolVersion;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
