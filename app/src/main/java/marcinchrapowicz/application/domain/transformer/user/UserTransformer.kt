package marcinchrapowicz.application.domain.transformer.user

import marcinchrapowicz.application.data.user.UserResponse
import marcinchrapowicz.application.domain.entity.user.User
import marcinchrapowicz.application.domain.exception.user.MissingUserException
import marcinchrapowicz.application.domain.transformer.Transformer

class UserTransformer: Transformer<UserResponse, User> {

    override fun transform(from: UserResponse?): User {
        return from?.let { userResponse ->
            User(
                userResponse.id,
                userResponse.firstName,
                userResponse.lastName,
                userResponse.email,
                userResponse.mobile
            )
        } ?: throw MissingUserException("UserTransformer")
    }
}