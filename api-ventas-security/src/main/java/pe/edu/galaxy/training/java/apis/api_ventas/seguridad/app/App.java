package pe.edu.galaxy.training.java.apis.api_ventas.seguridad.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class App {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }
}
