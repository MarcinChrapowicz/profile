package marcinchrapowicz.application.data.datasource.user

import marcinchrapowicz.application.data.user.UserResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface UserWebService {

    @GET("user.json")
    suspend fun getActiveSubscription(): Response<UserResponse>

}
