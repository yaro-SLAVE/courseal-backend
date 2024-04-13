package online.courseal.courseal_backend.responses.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServerInfo(
        @JsonProperty("server_name") String serverName,
        @JsonProperty("server_description") String serverDescription,
        @JsonProperty("server_registration_enabled") Boolean serverRegistrationEnabled) {
}
