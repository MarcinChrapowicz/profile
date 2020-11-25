package marcinchrapowicz.application.data.user

import marcinchrapowicz.application.data.datasource.user.UserWebService
import marcinchrapowicz.application.domain.entity.user.User
import marcinchrapowicz.application.domain.transformer.user.UserTransformer

internal class UserRepositoryImpl(
    private val userWebService: UserWebService,
    private val userTransformer: UserTransformer
): UserRepository {

    override suspend fun getUser(): User {
        return userWebService.getActiveSubscription().body().map(userTransformer)
    }
}

private fun UserResponse?.map(userTransformer: UserTransformer): User {
    return userTransformer.transform(this)
}
