package ru.kozavbede.javap.constpool.impl;

import ru.kozavbede.javap.constpool.BaseConstantPoolRow;
import ru.kozavbede.javap.constpool.Tag;

public class FloatInfo extends BaseConstantPoolRow {

	private final float value;

	public FloatInfo(int index, float value) {
		super(index);
		this.value = value;
	}

	@Override
	public Tag getTag() {
		return Tag.FLOAT;
	}

	@Override
	public String toString() {
		return Float.toString(value) + "f";
	}

	public float getValue() {
		return value;
	}

}
