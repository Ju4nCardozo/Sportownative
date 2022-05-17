package co.upb.sportownative

import android.content.Intent
import android.os.Bundle
import android.widget.ExpandableListAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.textViewUser
import kotlinx.android.synthetic.main.activity_rutina1.*
import kotlinx.android.synthetic.main.list_item.*


private val db = FirebaseFirestore.getInstance()

class Rutina1 : AppCompatActivity() {
    private lateinit var listViewAdapter: ExpandableListAdapter
    private lateinit var listGroup: List<String>
    private lateinit var listItem: HashMap<String,List<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina1)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?: "")

        showList()

        listViewAdapter = ExpandableListAdapter(this, listGroup, listItem)
        eListView.setAdapter(listViewAdapter)


    }

    private fun showList(){
        listGroup = ArrayList()
        listItem = HashMap()
        //dia 1
        (listGroup as ArrayList<String>).add("Estiramiento")
        (listGroup as ArrayList<String>).add("Calentamiento de hombro")
        (listGroup as ArrayList<String>).add("Flexiones de pecho de agarre natural")
        (listGroup as ArrayList<String>).add("Flexiones de pecho de agarre abierto")
        (listGroup as ArrayList<String>).add("Flexiones de pecho inclinadas agarre natural")
        (listGroup as ArrayList<String>).add("Flexiones de declinadas en suelo")
        //dia 2
        (listGroup as ArrayList<String>).add("Estiramiento")
        (listGroup as ArrayList<String>).add("Calentamiento de hombro")
        (listGroup as ArrayList<String>).add("Flexiones de pecho de agarre natural")
        (listGroup as ArrayList<String>).add("Flexiones de pecho de agarre abierto")
        (listGroup as ArrayList<String>).add("Sentadillas posición natural")
        (listGroup as ArrayList<String>).add("Sentadillas laterales posición abierta")
        //dia 3
        (listGroup as ArrayList<String>).add("Estiramiento")
        (listGroup as ArrayList<String>).add("Calentamiento de hombro")
        (listGroup as ArrayList<String>).add("Flexiones de pecho de agarre natural")
        (listGroup as ArrayList<String>).add("Flexiones de pecho inclinadas agarre natural")
        (listGroup as ArrayList<String>).add("Flexiones de pecho inclinadas en silla agarre natural")
        (listGroup as ArrayList<String>).add("Flexiones de pecho laterales en silla agarre natural")

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

        listItem[listGroup[0]] = item1
        listItem[listGroup[1]] = item2
        listItem[listGroup[2]] = item3
        listItem[listGroup[3]] = item4
        listItem[listGroup[4]] = item5
        listItem[listGroup[5]] = item6
        listItem[listGroup[6]] = item7
        listItem[listGroup[7]] = item8
        listItem[listGroup[8]] = item9
        listItem[listGroup[9]] = item10
        listItem[listGroup[10]] = item11
        listItem[listGroup[11]] = item12
        listItem[listGroup[12]] = item13
        listItem[listGroup[13]] = item14
        listItem[listGroup[14]] = item15
        listItem[listGroup[15]] = item16
        listItem[listGroup[16]] = item17
        listItem[listGroup[17]] = item18
    }

    private fun setup(email: String) {

        title = "Rutina"


        db.collection("users").document(email).get().addOnSuccessListener {

            textViewUser.setText(it.get("nombre_completo") as String?)
        }
    }
}