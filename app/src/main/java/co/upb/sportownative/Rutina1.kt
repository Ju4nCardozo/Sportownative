package co.upb.sportownative

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.textViewUser
import kotlinx.android.synthetic.main.activity_rutina1.*
import kotlinx.android.synthetic.main.list_item.*


private val db = FirebaseFirestore.getInstance()

class Rutina1 : AppCompatActivity() {
    //Rutina dia 1
    private lateinit var listViewAdapter1: ExpandableListAdapter
    private lateinit var listGroup1: List<String>
    private lateinit var listItem1: HashMap<String,List<String>>
    //Rutina dia 2
    private lateinit var listViewAdapter2: ExpandableListAdapter
    private lateinit var listGroup2: List<String>
    private lateinit var listItem2: HashMap<String,List<String>>
    //Rutina dia 3
    private lateinit var listViewAdapter3: ExpandableListAdapter
    private lateinit var listGroup3: List<String>
    private lateinit var listItem3: HashMap<String,List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina1)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?: "")

        showList()

        listViewAdapter1 = ExpandableListAdapter(this, listGroup1, listItem1)
        eListView1.setAdapter(listViewAdapter1)
        listViewAdapter2 = ExpandableListAdapter(this, listGroup2, listItem2)
        eListView2.setAdapter(listViewAdapter2)
        listViewAdapter3 = ExpandableListAdapter(this, listGroup3, listItem3)
        eListView3.setAdapter(listViewAdapter3)

        // initializing the listeners
        eListView1!!.setOnGroupClickListener { parent, v, groupPosition, id ->
            setListViewHeight(parent, groupPosition)
            false
        }
    }

    private fun showList(){

        //dia 1
        listGroup1 = ArrayList()
        listItem1 = HashMap()
        (listGroup1 as ArrayList<String>).add("Estiramiento")
        (listGroup1 as ArrayList<String>).add("Calentamiento de hombro")
        (listGroup1 as ArrayList<String>).add("Flexiones de pecho de agarre natural")
        (listGroup1 as ArrayList<String>).add("Flexiones de pecho de agarre abierto")
        (listGroup1 as ArrayList<String>).add("Flexiones de pecho inclinadas agarre natural")
        (listGroup1 as ArrayList<String>).add("Flexiones de declinadas en suelo")
        //dia 2
        listGroup2 = ArrayList()
        listItem2 = HashMap()
        (listGroup2 as ArrayList<String>).add("Estiramiento")
        (listGroup2 as ArrayList<String>).add("Calentamiento de hombro")
        (listGroup2 as ArrayList<String>).add("Flexiones de pecho de agarre natural")
        (listGroup2 as ArrayList<String>).add("Flexiones de pecho de agarre abierto")
        (listGroup2 as ArrayList<String>).add("Sentadillas posición natural")
        (listGroup2 as ArrayList<String>).add("Sentadillas laterales posición abierta")
        //dia 3
        listGroup3 = ArrayList()
        listItem3 = HashMap()
        (listGroup3 as ArrayList<String>).add("Estiramiento")
        (listGroup3 as ArrayList<String>).add("Calentamiento de hombro")
        (listGroup3 as ArrayList<String>).add("Flexiones de pecho de agarre natural")
        (listGroup3 as ArrayList<String>).add("Flexiones de pecho inclinadas agarre natural")
        (listGroup3 as ArrayList<String>).add("Flexiones de pecho inclinadas en silla agarre natural")
        (listGroup3 as ArrayList<String>).add("Flexiones de pecho laterales en silla agarre natural")

        val item1 : MutableList<String> = ArrayList()
        item1.add("Estiramiento de hombros, muñecas y cuello por 5 minutos.")
        val item2 : MutableList<String> = ArrayList()
        item2.add("Calentamiento de hombros, muñecas y cuello por 5 minutos.")
        val item3 : MutableList<String> = ArrayList()
        item3.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item4 : MutableList<String> = ArrayList()
        item4.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item5 : MutableList<String> = ArrayList()
        item5.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item6 : MutableList<String> = ArrayList()
        item6.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        //dia 2
        val item7 : MutableList<String> = ArrayList()
        item7.add("Estiramiento de hombros, muñecas, piernas y cuello por 5 minutos.")
        val item8 : MutableList<String> = ArrayList()
        item8.add("Calentamiento de hombros, muñecas, piernas y cuello por 5 minutos.")
        val item9 : MutableList<String> = ArrayList()
        item9.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item10 : MutableList<String> = ArrayList()
        item10.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item11 : MutableList<String> = ArrayList()
        item11.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item12 : MutableList<String> = ArrayList()
        item12.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item13 : MutableList<String> = ArrayList()
        //dia 3
        item13.add("Estiramiento de hombros, muñecas, y cuello por 5 minutos.")
        val item14 : MutableList<String> = ArrayList()
        item14.add("Calentamiento de hombros, muñecas, y cuello por 5 minutos.")
        val item15 : MutableList<String> = ArrayList()
        item15.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item16 : MutableList<String> = ArrayList()
        item16.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item17 : MutableList<String> = ArrayList()
        item17.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")
        val item18 : MutableList<String> = ArrayList()
        item18.add("5 sets de las repeticiones que le permita completar los 5 sets, de 30 segundo a 1 minuto de descanso.")

        listItem1[listGroup1[0]] = item1
        listItem1[listGroup1[1]] = item2
        listItem1[listGroup1[2]] = item3
        listItem1[listGroup1[3]] = item4
        listItem1[listGroup1[4]] = item5
        listItem1[listGroup1[5]] = item6
        // dia 2
        listItem2[listGroup2[0]] = item7
        listItem2[listGroup2[1]] = item8
        listItem2[listGroup2[2]] = item9
        listItem2[listGroup2[3]] = item10
        listItem2[listGroup2[4]] = item11
        listItem2[listGroup2[5]] = item12
        // dia 3
        listItem3[listGroup3[0]] = item13
        listItem3[listGroup3[1]] = item14
        listItem3[listGroup3[2]] = item15
        listItem3[listGroup3[3]] = item16
        listItem3[listGroup3[4]] = item17
        listItem3[listGroup3[5]] = item18
    }

    private fun setup(email: String) {

        title = "Rutina"


        db.collection("users").document(email).get().addOnSuccessListener {

            textViewUser.setText(it.get("nombre_completo") as String?)
        }
    }

    private fun setListViewHeight(
        listView: ExpandableListView,
        group: Int
    ) {
        val listAdapter = listView.expandableListAdapter as ExpandableListAdapter
        var totalHeight = 0
        val desiredWidth: Int = View.MeasureSpec.makeMeasureSpec(
            listView.width,
            View.MeasureSpec.EXACTLY
        )
        for (i in 0 until listAdapter.groupCount) {
            val groupItem: View = listAdapter.getGroupView(i, false, null, listView)
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += groupItem.measuredHeight
            if (listView.isGroupExpanded(i) && i != group
                || !listView.isGroupExpanded(i) && i == group
            ) {
                for (j in 0 until listAdapter.getChildrenCount(i)) {
                    val listItem: View = listAdapter.getChildView(
                        i, j, false, null,
                        listView
                    )
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
                    totalHeight += listItem.measuredHeight
                }
            }
        }
        val params = listView.layoutParams
        var height = (totalHeight
                + listView.dividerHeight * (listAdapter.groupCount - 1))
        if (height < 10) height = 200
        params.height = height+200
        listView.layoutParams = params
        listView.requestLayout()
    }
}