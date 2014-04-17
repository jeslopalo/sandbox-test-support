package es.sandbox.test.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;

public final class SerializationUtils {

	/**
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static <T extends Serializable> byte[] serialize(T object)
			throws IOException {

		final ByteArrayOutputStream baos= new ByteArrayOutputStream();
		final ObjectOutputStream oos= new ObjectOutputStream(baos);
		oos.writeObject(object);
		oos.close();
		return baos.toByteArray();
	}

	/**
	 * @param bytes
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T extends Serializable> T unserialize(byte[] bytes, Class<T> type)
			throws IOException, ClassNotFoundException {

		final ByteArrayInputStream bais= new ByteArrayInputStream(bytes);
		final ObjectInputStream ois= new ObjectInputStream(bais);
		final Object o= ois.readObject();
		return type.cast(o);
	}

	/**
	 * @param object
	 * @return
	 */
	public static <T> boolean isSerializable(T object) {
		try {
			if (object instanceof Serializable) {
				@SuppressWarnings("unchecked")
				final T unserialized= (T) unserialize(serialize((Serializable) object), (Class<Serializable>) object.getClass());
				return EqualsBuilder.reflectionEquals(object, unserialized, true);
			}
		}
		catch (final Exception e) {
			/* NOP */
			e.printStackTrace();
		}
		return false;
	}
}
