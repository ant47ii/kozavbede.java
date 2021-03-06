package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.BaseConstantPoolRow;
import ru.kozavbede.javap.constpool.Tag;

public class StringInfo extends BaseConstantPoolRow {

	private final int nameIndex;

	public StringInfo(int index, int nameIndex) {
		super(index);
		this.nameIndex = nameIndex;
	}

	@Override
	public Tag getTag() {
		return Tag.STRING;
	}

	@Override
	public String toString() {
		return "#" + nameIndex;
	}

	public int getNameIndex() {
		return nameIndex;
	}
}
