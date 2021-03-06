package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.BaseConstantPoolRow;
import ru.kozavbede.javap.constpool.Tag;

public class NameAndTypeInfo extends BaseConstantPoolRow {

	private final int nameIndex;
	private final int descriptorIndex;

	public NameAndTypeInfo(int index, int nameIndex, int descriptorIndex) {
		super(index);
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	@Override
	public Tag getTag() {
		return Tag.NAME_AND_TYPE;
	}

	@Override
	public String toString() {
		return "#" + nameIndex + ".#" + descriptorIndex;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}
}
