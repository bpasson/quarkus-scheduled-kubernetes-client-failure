package org.example.kubernetes.services;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KubernetesService {

    private static final Logger log = LoggerFactory.getLogger(KubernetesService.class);

    @Inject
    KubernetesClient kubernetesClient;

    @Scheduled(every="1m", delayed = "5s")
    public void listServices() {
        kubernetesClient.services().inNamespace("default").list().getItems().forEach( s -> {
            log.info("Found service {}", s.getMetadata().getName());
        });
    }
}
