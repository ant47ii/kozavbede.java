package ru.kozavbede.java.fields;

import ru.kozavbede.java.attributes.Attribute;

public class Field {

	private final int accessFlags;
	private final int nameIndex;
	private final int descriptorIndex;
	private Attribute[] attribures;

	public Field(int accessFlags, int nameIndex, int descriptorIndex) {
		this.accessFlags = accessFlags;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public Attribute[] getAttribures() {
		return attribures;
	}

	public void setAttribures(Attribute[] attribures) {
		this.attribures = attribures;
	}

}