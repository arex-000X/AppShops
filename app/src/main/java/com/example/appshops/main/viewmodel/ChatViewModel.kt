package com.example.appshops.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appshops.model.MessageModel
import com.example.appshops.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatViewModel(currentUserId: String, currentOthersUserId: String) : ViewModel() {
    val message: MutableLiveData<List<MessageModel>> = MutableLiveData()
    val othersUser: MutableLiveData<User> = MutableLiveData()
    val messageSent: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    private val database = Firebase.database
    private val referenceFirebaseUsers = database.getReference("user")
    private val referenceFirebaseMessage = database.getReference("message")
    init {
        referenceFirebaseUsers.child(currentOthersUserId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user: User = snapshot.getValue(User::class.java) as User
                    othersUser.value = user
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
       referenceFirebaseMessage.child(currentUserId).child(currentOthersUserId).addValueEventListener(object:ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
              val messageList = ArrayList<MessageModel>()
               for (data:DataSnapshot in snapshot.children){
                   val messageModel  = data.getValue(MessageModel::class.java) as MessageModel
                   messageList.add(messageModel)
               }
               message.value = messageList
           }

           override fun onCancelled(error: DatabaseError) {
               TODO("Not yet implemented")
           }

       })

    }

    fun getMessage(): LiveData<List<MessageModel>> {
        return message
    }

    fun othersUser(): LiveData<User> {
        return othersUser
    }

    fun messageSent(): LiveData<Boolean> {
        return messageSent
    }

    fun error(): LiveData<String> {
        return error
    }
    fun sendMessage(message: MessageModel) {
        referenceFirebaseMessage
            .child(message.sendId)
            .child(message.reciverId)
            .push().setValue(message)
            .addOnSuccessListener {
                referenceFirebaseMessage
                    .child(message.reciverId)
                    .child(message.sendId)
                    .push().setValue(message)
                    .addOnSuccessListener {
                        messageSent.value = true
                    }.addOnFailureListener{
                        error.value = it.message
                    }
            }.addOnFailureListener{
                error.value = it.message
            }
    }

}