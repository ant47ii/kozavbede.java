package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.BaseConstantPoolRow;
import ru.kozavbede.javap.constpool.Tag;

public class LongInfo extends BaseConstantPoolRow {

	private final long value;

	public LongInfo(int index, long value) {
		super(index);
		this.value = value;
	}

	@Override
	public Tag getTag() {
		return Tag.LONG;
	}

	@Override
	public String toString() {
		return Long.toString(value) + "l";
	}

	public long getValue() {
		return value;
	}
}
