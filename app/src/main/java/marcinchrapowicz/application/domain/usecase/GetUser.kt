package marcinchrapowicz.application.domain.usecase

import marcinchrapowicz.application.data.user.UserRepository
import marcinchrapowicz.application.domain.entity.user.User

class GetUser(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User {
        return userRepository.getUser()
    }
}
