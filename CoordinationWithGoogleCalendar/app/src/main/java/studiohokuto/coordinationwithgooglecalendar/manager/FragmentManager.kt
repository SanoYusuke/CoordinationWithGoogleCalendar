package studiohokuto.coordinationwithgooglecalendar.manager

import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import studiohokuto.coordinationwithgooglecalendar.R
import java.util.*

/**
 * Created by Sano on 2018/03/22.
 */
object FragmentManager {
    /** フラグメントの Queue. */
    val mQueueFragment : LinkedList<Fragment> = LinkedList<Fragment>()
    
    /**
     * 初期化
     */
    fun initialize() {
        mQueueFragment.clear()
    }


    /**
     * Fragment遷移時に呼ぶメソッド
     * ※画面を追加する
     *
     * @param transaction  Fragment接続用コンテナ
     * @param fragment 追加されるFragment
     * @param fragmentName Fragmentのタグ名
     */
    fun addFragment(transaction: FragmentTransaction, fragment : Fragment, fragmentName: String?) {
        // コンテナにFragmentを格納
        transaction.add(R.id.main_fragment_container, fragment, fragmentName)

        // 現在のfragmentをhideする
        if(mQueueFragment.size > 0){
            val currentFragment = mQueueFragment.peek()
            transaction.hide(currentFragment)
        }

        transaction.commit()

        //キューに追加
        mQueueFragment.push(fragment)
    }


    /**
     * Dialog を呼ぶメソッド
     *
     * @param transaction  Fragment接続用コンテナ
     * @param fragment 追加されるFragment
     * @param fragmentName Fragmentのタグ名
     */
    fun addFragmentDialog(transaction: FragmentTransaction, fragment : Fragment, fragmentName: String?) {
        // コンテナにFragmentを格納
        transaction.add(R.id.main_fragment_container, fragment, fragmentName)
        transaction.commit()

        //キューに追加
        mQueueFragment.push(fragment)
    }


    /**
     * Fragment遷移時に呼ぶメソッド
     * 現在表示されているfragmentは削除
     * ※画面を追加する
     *
     * @param transaction  Fragment接続用コンテナ
     * @param fragment 追加されるFragment
     * @param fragmentName Fragmentのタグ名
     */
    fun replaceFragment(transaction: FragmentTransaction, fragment : Fragment, fragmentName: String?) {
        // コンテナにFragmentを格納
        transaction.add(R.id.main_fragment_container, fragment, fragmentName)

        //フラグメントを削除する
        if(mQueueFragment.size > 0) {
            val currentFragment = mQueueFragment.pop()
            transaction.remove(currentFragment)
        }

        transaction.commit()

        //キューに追加
        mQueueFragment.push(fragment)
    }


    /**
     * Fragment遷移時に呼ぶメソッド
     * 現在表示されているfragmentは削除
     * ※画面を追加する
     *
     * @param transaction  Fragment接続用コンテナ
     */
    fun removeFragment(transaction: FragmentTransaction) {
        //表示されているフラグメントを消す
        if(mQueueFragment.size > 0) {
            val currentFragment = mQueueFragment.pop()
            transaction.remove(currentFragment)
        }
        //次に表示されるフラグメントを表示する。
        if(mQueueFragment.size > 0) {
            val beforeFragment = mQueueFragment.peek()
            transaction.show(beforeFragment)
        }
        // 画面に表示
        transaction.commit()
    }


    /**
     * 保存しているフラグメントをすべて削除する
     */
    fun deleteAllFragment(transaction: FragmentTransaction){
        for(fragment in mQueueFragment){
            transaction.remove(fragment)
        }
        mQueueFragment.clear()
    }


    /**
     * 保存しているターゲット以外のフラグメントをすべて削除する
     */
    fun deleteAllFragmentTargetExcept(transaction: FragmentTransaction, targetFragmentName : String){
        val replaceFragment : LinkedList<Fragment> = LinkedList<Fragment>()
        for(fragment in mQueueFragment){
            //ターゲットのフラグメント以外だったら
            if(!fragment.tag.equals(targetFragmentName)) {
                transaction.remove(fragment)
            }else{
                replaceFragment.push(fragment)
            }
        }

        //一度履歴を削除して、ターゲットのフラグメントのみを保存しなおす。
        mQueueFragment.clear()
        for(fragment in replaceFragment) {
            mQueueFragment.push(fragment)
        }
    }


    /**
     * 現在のフラグメントを取得
     */
    fun getCurrentFragment() : Fragment {
        return mQueueFragment.peek()
    }

}