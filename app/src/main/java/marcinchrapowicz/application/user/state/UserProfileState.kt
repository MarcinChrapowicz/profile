package marcinchrapowicz.application.user.state

import io.uniflow.core.flow.data.UIState

sealed class UserProfileState : UIState() {
    data class Loaded(
        val name: String,
        val email: String,
        val mobile: String
    ) : UserProfileState()

    object Loading : UserProfileState()
}

