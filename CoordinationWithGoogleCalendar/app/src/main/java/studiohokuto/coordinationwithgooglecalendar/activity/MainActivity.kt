package studiohokuto.coordinationwithgooglecalendar.activity

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import studiohokuto.coordinationwithgooglecalendar.R
import studiohokuto.coordinationwithgooglecalendar.fragment.MainFragment
import studiohokuto.coordinationwithgooglecalendar.manager.FragmentManager

class MainActivity : AppCompatActivity() {
    /*-------------------- Constant --------------------*/
    private val TAG : String = MainActivity::class.java.name

    private val REQUEST_CODE : Int = 1

    /*-------------------- Method --------------------*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)
        val writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
        if (readPermission == PackageManager.PERMISSION_GRANTED
                && writePermission == PackageManager.PERMISSION_GRANTED) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = MainFragment()
            val fragmentName = MainFragment.FRAGMENT_NAME
            FragmentManager.addFragment(transaction, fragment, fragmentName)
        } else {
            // パーミッションの許可を求めるダイアログ表示
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR), REQUEST_CODE)
        }
    }



    /**
     * パーミッション許可ダイアログの結果ハンドリング
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        var readPermissionFlg = false
        var writePermissionFlg = false

        when (requestCode) {
            REQUEST_CODE -> {
                for (i in 0..permissions.size-1) {
                    when (permissions[i]) {
                        // READ
                        Manifest.permission.READ_CALENDAR -> {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                readPermissionFlg = true
                            }
                        }
                        // WRITE
                        Manifest.permission.WRITE_CALENDAR -> {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                writePermissionFlg = true
                            }
                        }
                    }
                }
            }
        }

        // パーミッションが全て許可されていれば処理を行なう
        if (readPermissionFlg && writePermissionFlg) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = MainFragment()
            val fragmentName = MainFragment.FRAGMENT_NAME
            FragmentManager.addFragment(transaction, fragment, fragmentName)
        } else {

        }

    }
}
