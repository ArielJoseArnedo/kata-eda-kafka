package co.com.pichincha.users.infrastructure.events;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.io.InputStream;
import java.util.Map;

@Slf4j
public class Publisher {

    private  String secretKey;
    private  String accessKey;
    private  String topic;
    private  SnsClient snsClient;

    public Publisher() {
        try {
            this.secretKey = getCredential("secret-key");
            this.accessKey = getCredential("access-key");
            this.topic = getCredential("topic");
            this.snsClient = createClient();
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public <E extends Event> void sendEvent(E event) {
        log.info("Se emite el siguiente evento: {}", event.name());
        ObjectMapper objectMapper = new ObjectMapper();
        String dataEvent = objectMapper.convertValue(event, JsonNode.class)
          .toString();
        PublishRequest publishRequest = PublishRequest.builder()
          .message(dataEvent)
          .topicArn(topic)
          .build();
        PublishResponse publishResponse = snsClient.publish(publishRequest);
        log.info("Evento publicado con exito: {}", publishResponse);
    }

    private SnsClient createClient() {
        return SnsClient.builder()
          .credentialsProvider(createCredential())
          .region(Region.US_EAST_1)
          .build();
    }

    private StaticCredentialsProvider createCredential() {
        return StaticCredentialsProvider.create(
          AwsBasicCredentials.builder()
            .accessKeyId(accessKey)
            .secretAccessKey(secretKey)
            .build()
        );
    }

    String getCredential(String path) {
        InputStream inputStream = Publisher.class.getResourceAsStream("/application.yml");
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(inputStream);
        Map<String, String> awsProperties = (Map<String, String>) map.get("aws");
        return awsProperties.get(path);
    }
}
