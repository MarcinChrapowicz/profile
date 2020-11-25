package marcinchrapowicz.application.user.state

import io.uniflow.core.flow.data.UIEvent

sealed class UserProfileEvent : UIEvent() {
    object RetryView : UserProfileEvent()
}

