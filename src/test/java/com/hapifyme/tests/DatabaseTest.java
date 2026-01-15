package com.hapifyme.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {

    // Configurare conexiune (Credențialele din docker-compose.yml)
    private static final String DB_URL = "jdbc:mysql://localhost:3307/hapify_social";
    private static final String DB_USER = "hapify_user";
    private static final String DB_PASS = "secret123";

    @Test
    public void testSelectUsers() {
        System.out.println("🔄 Încerc conectarea la baza de date Docker...");

        // Folosim try-with-resources pentru a închide automat conexiunea
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement statement = connection.createStatement()) {

            System.out.println("✅ Conexiune reușită!");

            // 1. Executăm Query-ul
            String query = "SELECT * FROM users LIMIT 5";
            ResultSet resultSet = statement.executeQuery(query);

            // 2. Iterăm prin rezultate și numărăm utilizatorii
            int userCount = 0;
            System.out.println("\n--- LISTA UTILIZATORI (Top 5) ---");

            while (resultSet.next()) {
                userCount++;
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");

                System.out.println("User #" + userCount + ": " + username + " | " + email);
            }
            System.out.println("---------------------------------\n");

            // 3. Verificări automate (Assertions)
            // Testul pică dacă nu găsește niciun user (înseamnă că importul SQL a eșuat)
            Assertions.assertTrue(userCount > 0, "❌ Eroare: Tabela 'users' este goală! Verificați importul database.sql.");

            System.out.println("✅ Test trecut: S-au găsit " + userCount + " utilizatori în baza de date.");

        } catch (Exception e) {
            // Dacă apare o eroare de conexiune, picăm testul cu mesajul erorii
            Assertions.fail("❌ Conexiunea la DB a eșuat: " + e.getMessage());
        }
    }
}