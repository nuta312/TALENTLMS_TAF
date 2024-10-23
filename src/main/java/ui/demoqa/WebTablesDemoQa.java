package ui.demoqa;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebTablesDemoQa {
    String firstName;
    String lastName;
    int age;
    String email;
   long salary;
    String department;
}
