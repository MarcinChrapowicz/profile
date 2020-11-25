package marcinchrapowicz.application.user.state

import marcinchrapowicz.application.domain.entity.user.User

fun User.mapToUserState(): UserProfileState.Loaded =
    UserProfileState.Loaded(
        "$firstName $lastName",
        email,
        mobile
    )
