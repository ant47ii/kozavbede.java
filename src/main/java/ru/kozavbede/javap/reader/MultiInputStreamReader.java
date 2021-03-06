package ru.kozavbede.javap.reader;

import java.io.IOException;
import java.io.InputStream;

public abstract class MultiInputStreamReader<T> extends BaseInputStreamReader {

	protected MultiInputStreamReader(InputStream is) {
		super(is);
	}

	public abstract T read(int count) throws IOException;

}
