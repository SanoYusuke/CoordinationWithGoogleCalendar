package studiohokuto.coordinationwithgooglecalendar.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import studiohokuto.coordinationwithgooglecalendar.R

/**
 * トップページ用のFragment
 *
 * Created by Sano on 2018/03/22.
 */
class MainFragment : Fragment() {
    /*-------------------- Constant --------------------*/


    /*------------------ Layout,View -------------------*/


    /*-------------------- Variable --------------------*/


    /*---------------- companion object ----------------*/
    companion object {
        /** Fragmentの名称を保管 */
        val FRAGMENT_NAME: String = MainFragment::class.java.name
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_main, container, false)

        return view
    }
}