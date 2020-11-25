package marcinchrapowicz.application.user.ui.error

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_error.*
import marcinchrapowicz.application.R

class ErrorFragment : Fragment(R.layout.fragment_error) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        error_action.setOnClickListener {
            val action = ErrorFragmentDirections.actionBackupCardErrorToBackupCard()
            findNavController().navigate(action)
        }
    }
}
