package marcinchrapowicz.application.user.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import kotlinx.android.synthetic.main.fragment_user_profile.*
import marcinchrapowicz.application.R
import marcinchrapowicz.application.user.state.UserProfileEvent
import marcinchrapowicz.application.user.state.UserProfileState
import marcinchrapowicz.application.user.viewmodel.UserProfileViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private val viewModel: UserProfileViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onState()
        onEvent()
        setListeners()
        viewModel.getUserData()
    }

    private fun onState() {
        onStates(viewModel) { state ->
            when (state) {
                is UserProfileState.Loaded -> {
                    hideLoading()
                    updateUserInformation(state)
                }
                is UserProfileState.Loading -> showLoading()
            }
        }
    }

    private fun onEvent() {
        onEvents(viewModel) { event ->
            when (event.take()) {
                is UserProfileEvent.RetryView -> navigateToRetryView()
            }
        }
    }

    private fun updateUserInformation(state: UserProfileState.Loaded) {
        user_profile_name.text = state.name
        user_profile_email.text = state.email
        user_profile_mobile.text = state.mobile
    }

    private fun navigateToRetryView() {
        val action = UserProfileFragmentDirections.actionBackupCardToBackupCardError()
        findNavController().navigate(action)
    }

    private fun showLoading() {
        user_profile_loading_view.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        user_profile_loading_view.visibility = View.GONE
    }

    private fun setListeners() {
        user_profile_update_user_information.setOnClickListener {
            viewModel.updateUserInformation(
                user_profile_update_name.text.toString(),
                user_profile_update_email.text.toString(),
                user_profile_update_mobile_number.text.toString()
            )
        }
    }
}
