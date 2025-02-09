import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.homeworks.R

class NavigationManager {
    companion object {
        private val fragmentContainer = R.id.main_fragment_container

        fun navigateToFragment(
            fragmentManager: FragmentManager,
            fragment: Fragment,
            addToBackStack: Boolean = true
        ) {
            val transaction = fragmentManager.beginTransaction()
                .replace(fragmentContainer, fragment)

            if (addToBackStack) {
                transaction.addToBackStack(null)
            }

            transaction.commit()
        }
    }
}
