package common.entities.notifications;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Overview {
    String name;
    String event;
    String recipient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Overview overview = (Overview) o;
        return Objects.equals(name, overview.name) && Objects.equals(event, overview.event) && Objects.equals(recipient, overview.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, event, recipient);
    }
}