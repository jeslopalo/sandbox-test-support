package es.sandbox.test.fixture.bean;

import java.io.Serializable;
import java.util.Random;

public class SerializableBean
    implements Serializable {

    private static final long serialVersionUID = -4356373131441586641L;

    private static final Random RANDOMIZER = new Random(System.currentTimeMillis());

    private final int value;


    public SerializableBean() {
        this.value = RANDOMIZER.nextInt();
    }

    public SerializableBean(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.value;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SerializableBean)) {
            return false;
        }
        final SerializableBean that = (SerializableBean) other;
        return this.value == that.value;
    }

    @Override
    public String toString() {
        return "SerializableBean [value=" + this.value + "]";
    }
}
