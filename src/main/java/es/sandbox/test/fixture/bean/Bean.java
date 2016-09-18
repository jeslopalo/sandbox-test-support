package es.sandbox.test.fixture.bean;


public final class Bean {

    private String a;
    private String b;


    public Bean(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return this.a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return this.b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.a == null) ? 0 : this.a.hashCode());
        result = prime * result + ((this.b == null) ? 0 : this.b.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Bean other = (Bean) obj;
        if (this.a == null) {
            if (other.a != null)
                return false;
        } else if (!this.a.equals(other.a))
            return false;
        if (this.b == null) {
            if (other.b != null)
                return false;
        } else if (!this.b.equals(other.b))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FixtureBean [a=" + this.a + ", b=" + this.b + "]";
    }
}
