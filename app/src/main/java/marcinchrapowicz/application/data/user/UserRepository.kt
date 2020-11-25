package marcinchrapowicz.application.data.user

import marcinchrapowicz.application.domain.entity.user.User

interface UserRepository {
    suspend fun getUser(): User
}
