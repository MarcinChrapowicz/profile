package marcinchrapowicz.application.user.viewmodel

import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.actionOn
import marcinchrapowicz.application.domain.exception.user.MissingUserException
import marcinchrapowicz.application.domain.usecase.GetUser
import marcinchrapowicz.application.user.state.UserProfileEvent
import marcinchrapowicz.application.user.state.UserProfileState
import marcinchrapowicz.application.user.state.mapToUserState

internal class UserProfileViewModel(private val getUser: GetUser) : AndroidDataFlow() {

    fun getUserData() = action(
        onAction = {
            setState(UserProfileState.Loading)
            val state = getUser().mapToUserState()
            setState(state)
        },
        onError = { exception, _ ->
            when(exception) {
                is MissingUserException -> sendEvent(UserProfileEvent.RetryView)
                else -> sendEvent(UserProfileEvent.RetryView)
            }
        }
    )

    fun updateUserInformation(name: String, email: String, mobile: String) = actionOn<UserProfileState>(
        onAction = {
            setState (
                UserProfileState.Loaded(
                    name,
                    email,
                    mobile
                )
            )
        }
    )

}
