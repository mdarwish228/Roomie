package com.darwish.roomie.presentation.group.currentgroups

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.darwish.roomie.R
import com.darwish.roomie.presentation.group.common.GroupViewHolder
import com.darwish.roomie.presentation.login.LoginActivity
import com.darwish.roomie.data.group.Group
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_current_groups.*


class CurrentGroupsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var groupAdapter: FirestoreRecyclerAdapter<Group, GroupViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null || auth.currentUser?.reload() == null) {
            navigateToLoginActivity()
        }

        val query = FirebaseFirestore.getInstance()
            .collection("groups")
            .whereArrayContains("group_members", user!!.uid)
            .orderBy("last_modified", Query.Direction.DESCENDING)

        val options: FirestoreRecyclerOptions<Group> = FirestoreRecyclerOptions.Builder<Group>()
            .setQuery(query, Group::class.java)
                .build();

        groupAdapter = object : FirestoreRecyclerAdapter<Group, GroupViewHolder>(options) {
            override fun onBindViewHolder(groupViewHolder: GroupViewHolder, position: Int, group: Group) {
                groupViewHolder.groupName.text = group.group_name
            }

            override fun onCreateViewHolder(group: ViewGroup, i: Int): GroupViewHolder {
                val v = LayoutInflater.from(group.context)
                    .inflate(R.layout.group, group, false)

                return GroupViewHolder(v)
            }
        }

        groupRecyclerView.adapter = groupAdapter
        groupRecyclerView.layoutManager = LinearLayoutManager(activity);
    }

    override fun onResume() {
        super.onResume()
        groupAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        groupAdapter.stopListening()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
