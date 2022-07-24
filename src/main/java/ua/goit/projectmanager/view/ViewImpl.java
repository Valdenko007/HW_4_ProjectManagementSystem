package ua.goit.projectmanager.view;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ViewImpl implements View {

    private BufferedReader br;

    public ViewImpl() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }


    @SneakyThrows
    @Override
    public String read() {
        return br.readLine();
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void write(Object... objects) {
        Arrays.stream(objects).forEach(System.out::println);
    }
}
