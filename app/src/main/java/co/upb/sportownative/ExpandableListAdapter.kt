package co.upb.sportownative

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExpandableListAdapter internal constructor(private val context: Context, private val listGroup: List<String>, private val listItem: HashMap<String, List<String>>):
    BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return listGroup.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return this.listItem[this.listGroup[p0]]!!.size
    }

    override fun getGroup(p0: Int): Any {
        return listGroup[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return this.listItem[this.listGroup[p0]]!![p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convertView = p2
        val groupTitle = getGroup(p0) as String
        if (convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.list_group, null)
        }
        val listTitle = convertView!!.findViewById<TextView>(R.id.listTitle)
        listTitle.setText(groupTitle)
        return convertView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var convertView = p3
        val itemTitle = getChild(p0,p1) as String
        if (convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.list_item,null)
        }
        val expandedListItem = convertView!!.findViewById<TextView>(R.id.expandedListItem)
        expandedListItem.setText(itemTitle)
        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}