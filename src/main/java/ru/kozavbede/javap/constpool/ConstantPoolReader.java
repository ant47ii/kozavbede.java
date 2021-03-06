package ru.kozavbede.javap.constpool;

import java.io.IOException;
import java.io.InputStream;

import ru.kozavbede.javap.constpool.impl.ClassInfo;
import ru.kozavbede.javap.constpool.impl.DoubleInfo;
import ru.kozavbede.javap.constpool.impl.FieldRefInfo;
import ru.kozavbede.javap.constpool.impl.FloatInfo;
import ru.kozavbede.javap.constpool.impl.IntegerInfo;
import ru.kozavbede.javap.constpool.impl.InterfaceMethodRefInfo;
import ru.kozavbede.javap.constpool.impl.LongInfo;
import ru.kozavbede.javap.constpool.impl.MethodRefInfo;
import ru.kozavbede.javap.constpool.impl.NameAndTypeInfo;
import ru.kozavbede.javap.constpool.impl.StringInfo;
import ru.kozavbede.javap.constpool.impl.Utf8Info;
import ru.kozavbede.javap.reader.MultiInputStreamReader;

public class ConstantPoolReader extends MultiInputStreamReader<ConstantPool> {

	private ConstantPoolReader(InputStream is) {
		super(is);
	}

	@Override
	public ConstantPool read(int constantPoolCount) throws IOException {
		IConstantPoolRow[] rows = new IConstantPoolRow[constantPoolCount - 1];
		for (int i = 0; i < constantPoolCount - 1; i++) {
			int infoTagType = read1Int();
			Tag tag = Tag.fromIndex(infoTagType);

			rows[i] = createRow(tag, i + 1);
			if (tag == Tag.LONG || tag == Tag.DOUBLE) {
				// then the next usable item in the pool is located at index n+2. The
				// constant_pool index n+1 must be valid but is considered unusable.
				i++;
			}
		}

		return new ConstantPool(rows);
	}

	private IConstantPoolRow createRow(Tag tag, int tagIndex) throws IOException {
		switch (tag) {
		case CLASS:
			return createClass(tagIndex);
		case NAME_AND_TYPE:
			return createNameAndType(tagIndex);
		case STRING:
			return createString(tagIndex);
		case UTF8:
			return createUtf8(tagIndex);
		case INTEGER:
			return createInteger(tagIndex);
		case FLOAT:
			return createFloat(tagIndex);
		case LONG:
			return createLong(tagIndex);
		case DOUBLE:
			return createDouble(tagIndex);
		case FIELD_REF:
			return createFieldRef(tagIndex);
		case METHOD_REF:
			return createMethodRef(tagIndex);
		case INTERFACE_METHOD_REF:
			return createInterfaceMethodRef(tagIndex);
		default:
			return null;
		}
	}

	private IConstantPoolRow createClass(int tagIndex) throws IOException {
		int nameIndex = read2Int();
		return new ClassInfo(tagIndex, nameIndex);
	}

	private IConstantPoolRow createNameAndType(int tagIndex) throws IOException {
		int nameIndex = read2Int();
		int descriptorIndex = read2Int();
		return new NameAndTypeInfo(tagIndex, nameIndex, descriptorIndex);
	}

	private IConstantPoolRow createString(int tagIndex) throws IOException {
		int stringIndex = read2Int();
		return new StringInfo(tagIndex, stringIndex);
	}

	private IConstantPoolRow createUtf8(int tagIndex) throws IOException {
		int len = read2Int();
		byte[] str = readNBytes(len);
		return new Utf8Info(tagIndex, new String(str));
	}

	private IConstantPoolRow createFieldRef(int tagIndex) throws IOException {
		int classIndex = read2Int();
		int nameAndTypeIndex = read2Int();
		return new FieldRefInfo(tagIndex, classIndex, nameAndTypeIndex);
	}

	private IConstantPoolRow createInterfaceMethodRef(int tagIndex) throws IOException {
		int classIndex = read2Int();
		int nameAndTypeIndex = read2Int();
		return new InterfaceMethodRefInfo(tagIndex, classIndex, nameAndTypeIndex);
	}

	private IConstantPoolRow createMethodRef(int tagIndex) throws IOException {
		int classIndex = read2Int();
		int nameAndTypeIndex = read2Int();
		return new MethodRefInfo(tagIndex, classIndex, nameAndTypeIndex);
	}

	private IConstantPoolRow createInteger(int tagIndex) throws IOException {
		int value = read4Int();
		return new IntegerInfo(tagIndex, value);
	}

	private IConstantPoolRow createFloat(int tagIndex) throws IOException {
		float value = Float.intBitsToFloat(read4Int());
		return new FloatInfo(tagIndex, value);
	}

	private IConstantPoolRow createLong(int tagIndex) throws IOException {
		long value = read8Long();
		return new LongInfo(tagIndex, value);
	}

	private IConstantPoolRow createDouble(int tagIndex) throws IOException {
		long longValue = read8Long();
		double value = Double.longBitsToDouble(longValue);
		return new DoubleInfo(tagIndex, value);
	}

	public static class Builder {

		private Builder() {

		}

		public static ConstantPoolReader from(InputStream is) {
			return new ConstantPoolReader(is);
		}
	}
}
