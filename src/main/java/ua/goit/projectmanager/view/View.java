package ua.goit.projectmanager.view;

public interface View {
    String read();
    void write(String message);
    void write(Object... objects);



}
