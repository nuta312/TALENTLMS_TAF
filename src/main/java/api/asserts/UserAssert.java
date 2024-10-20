package api.asserts;

import api.entity.BaseEntity;
import api.entity.User;

public class UserAssert extends EntityAssert {
    public UserAssert(BaseEntity actualBaseEntity) {
        super(actualBaseEntity);
    }

    public static UserAssert assertThat(User user) {
        return new UserAssert(user);
    }
}