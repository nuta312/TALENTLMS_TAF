package common.entities.notifications;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class History {
    String recipient;
    String subject;
    String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(recipient, history.recipient) && Objects.equals(subject, history.subject) && Objects.equals(date, history.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, subject, date);
    }
}
