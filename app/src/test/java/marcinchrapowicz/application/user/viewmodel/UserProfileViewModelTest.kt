package marcinchrapowicz.application.user.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifySequence
import io.uniflow.android.test.MockedViewObserver
import io.uniflow.android.test.mockObservers
import io.uniflow.core.flow.data.UIState
import io.uniflow.core.logger.DebugMessageLogger
import io.uniflow.core.logger.UniFlowLogger
import io.uniflow.test.rule.TestDispatchersRule
import marcinchrapowicz.application.domain.entity.user.User
import marcinchrapowicz.application.domain.exception.user.MissingUserException
import marcinchrapowicz.application.domain.usecase.GetUser
import marcinchrapowicz.application.user.state.UserProfileEvent
import marcinchrapowicz.application.user.state.UserProfileState
import marcinchrapowicz.application.user.state.mapToUserState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

internal class UserProfileViewModelTest {

    @get:Rule
    var testDispatchersRule = TestDispatchersRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getUser: GetUser = mockk(relaxed = true)

    private val user = User("123", "First", "Last", "User123@gmail.com", "12345678")

    private lateinit var view: MockedViewObserver
    private lateinit var viewModel: UserProfileViewModel

    init {
        UniFlowLogger.init(DebugMessageLogger())
    }

    @Before
    fun before() {
        viewModel = UserProfileViewModel(getUser)
        view = viewModel.mockObservers()
    }

    @Test
    fun `empty state`() {
        verifySequence {
            view.hasState(UIState.Empty)
        }
    }

    @Test
    fun `should return user information`() {
        coEvery { getUser() } returns user

        viewModel.getUserData()

        verifySequence {
            view.hasState(UIState.Empty)
            view.hasEvent(UserProfileEvent.Loading)
            view.hasState(user.mapToUserState())
        }
    }

    @Test
    fun `should throw missing user exception when getting user`() {
        coEvery { getUser() } throws  MissingUserException("missing user")

        viewModel.getUserData()

        verifySequence {
            view.hasState(UIState.Empty)
            view.hasEvent(UserProfileEvent.Loading)
            view.hasEvent(UserProfileEvent.RetryView)
        }
    }

    @Test
    fun `should throw unknown exception when getting user`() {
        coEvery { getUser() } throws  UnknownHostException()

        viewModel.getUserData()

        verifySequence {
            view.hasState(UIState.Empty)
            view.hasEvent(UserProfileEvent.Loading)
            view.hasEvent(UserProfileEvent.RetryView)
        }
    }

    @Test
    fun `should update user information`() {
        coEvery { getUser() } returns user

        viewModel.getUserData()
        viewModel.updateUserInformation("name", "email", "123")

        verifySequence {
            view.hasState(UIState.Empty)
            view.hasEvent(UserProfileEvent.Loading)
            view.hasState(user.mapToUserState())
            view.hasState(UserProfileState("name", "email", "123"))
        }
    }

}