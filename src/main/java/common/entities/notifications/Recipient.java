package common.entities.notifications;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Recipient {

    String relatedUser;
    String user;
    String accountOwner;
    String superAdmins;
    String branchAdmins;
    String specificRecipient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(user, recipient.user) && Objects.equals(accountOwner, recipient.accountOwner) && Objects.equals(superAdmins, recipient.superAdmins) && Objects.equals(branchAdmins, recipient.branchAdmins) && Objects.equals(specificRecipient, recipient.specificRecipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, accountOwner, superAdmins, branchAdmins, specificRecipient);
    }
}
