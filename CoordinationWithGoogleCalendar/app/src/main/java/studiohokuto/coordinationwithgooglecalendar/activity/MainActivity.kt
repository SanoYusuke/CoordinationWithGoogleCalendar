package studiohokuto.coordinationwithgooglecalendar.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import studiohokuto.coordinationwithgooglecalendar.R
import studiohokuto.coordinationwithgooglecalendar.fragment.MainFragment
import studiohokuto.coordinationwithgooglecalendar.manager.FragmentManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val fragment = MainFragment()
        val fragmentName = MainFragment.FRAGMENT_NAME
        FragmentManager.addFragment(transaction, fragment, fragmentName)
    }
}
