package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        try {
            long clientId1 = clientService.create("Client 1");
            long clientId2 = clientService.create("Client 2");
            System.out.println("Створено клієнтів з id: " + clientId1 + ", " + clientId2);

            System.out.println("Ім'я клієнта з id " + clientId1 + ": " + clientService.getById(clientId1));
            System.out.println("Ім'я клієнта з id " + clientId2 + ": " + clientService.getById(clientId2));

            clientService.setName(clientId1, "New Name for Client 1");
            System.out.println("Нове ім'я для клієнта з id " + clientId1 + ": " + clientService.getById(clientId1));

            clientService.deleteById(clientId2);
            System.out.println("Клієнт з id " + clientId2 + " був видалений");

            System.out.println("Список всіх клієнтів:");
            List<Client> clients = clientService.listAll();
            for (Client client : clients) {
                System.out.println("ID: " + client.getId() + ", Name: " + client.getName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}

