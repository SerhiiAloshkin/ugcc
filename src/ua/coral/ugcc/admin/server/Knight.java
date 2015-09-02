package ua.coral.ugcc.admin.server;

public class Knight implements Person {
    private String name;

    public Knight(final String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(name);
    }
}
