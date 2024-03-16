package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientService {
    private Map<Long, String> clients = new HashMap<>();
    private long nextId = 1;

    public long create(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім");
        }
        long id = nextId++;
        clients.put(id, name);
        return id;
    }

    public String getById(long id) throws IllegalArgumentException {
        validateClientId(id);
        return clients.get(id);
    }

    public void setName(long id, String name) throws IllegalArgumentException {
        validateClientId(id);
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім");
        }
        clients.put(id, name);
    }

    public void deleteById(long id) throws IllegalArgumentException {
        validateClientId(id);
        clients.remove(id);
    }

    public List<Client> listAll() {
        List<Client> clientList = new ArrayList<>();
        for (Map.Entry<Long, String> entry : clients.entrySet()) {
            clientList.add(new Client(entry.getKey(), entry.getValue()));
        }
        return clientList;
    }

    private void validateClientId(long id) throws IllegalArgumentException {
        if (!clients.containsKey(id)) {
            throw new IllegalArgumentException("Клієнт з вказаним ідентифікатором не існує");
        }
    }
}

