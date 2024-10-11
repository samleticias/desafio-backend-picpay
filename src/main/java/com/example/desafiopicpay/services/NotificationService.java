package com.example.desafiopicpay.services;

import com.example.desafiopicpay.domain.entities.User;
import com.example.desafiopicpay.rest.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String NOTIFICATION_URL = "https://util.devi.tools/api/v1/notify";

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO request = new NotificationDTO(email, message);

        try {
            restTemplate.postForObject(NOTIFICATION_URL, request, String.class);
            System.out.println("Notificação enviada com sucesso para " + email);
        } catch (HttpStatusCodeException e) {
            System.err.println("Erro ao enviar notificação: " + e.getStatusCode());
            throw new Exception("Serviço de notificação indisponível.");
        } catch (Exception e) {
            System.err.println("Error Type: " + e.getMessage());
            throw new Exception("Erro ao enviar notificação.");
        }
    }
}
