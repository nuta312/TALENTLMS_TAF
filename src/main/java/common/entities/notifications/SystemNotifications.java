package common.entities.notifications;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SystemNotifications {

    String name;
    String recipient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemNotifications that = (SystemNotifications) o;
        return Objects.equals(name, that.name) && Objects.equals(recipient, that.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, recipient);
    }
}
