package rxjava.domain;

public class Hello {
    private String hello = "";

    public void setHello(final String hello) {
        System.out.println(String.format("Hello set with value %s", hello));
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }
}
