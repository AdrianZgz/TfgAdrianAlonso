package com.example.soyaragon.Domain.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soyaragon.Domain.CampaignItemModel
import com.example.soyaragon.Domain.ShopItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase=FirebaseDatabase.getInstance("https://soyaragon-1f0dc-default-rtdb.europe-west1.firebasedatabase.app/")

    ///ZARAGOZA///
    fun loadRestaurantesDestZgz(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("RestaurantesDestZgz")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
            return listData

    }

    fun loadComerciosDestZgz(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("ComerciosDestZgz")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadAlojamientosDestZgz(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("AlojamientosDestZgz")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadTurismoDestZgz(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("TurismoDestZgz")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadServiciosDestZgz(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("ServiciosDestZgz")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    ///HUESCA///
    fun loadRestaurantesDestHue(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("RestaurantesDestHue")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadComerciosDestHue(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("ComerciosDestHue")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadAlojamientosDestHue(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("AlojamientosDestHue")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadTurismoDestHue(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("TurismoDestHue")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadServiciosDestHue(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("ServiciosDestHue")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    ///TERUEL///

    fun loadRestaurantesDestTer(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("RestaurantesDestTer")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadComerciosDestTer(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("ComerciosDestTer")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadAlojamientosDestTer(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("AlojamientosDestTer")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadTurismoDestTer(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("TurismoDestTer")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    fun loadServiciosDestTer(): LiveData<MutableList<ShopItemModel>>{
        val listData=MutableLiveData<MutableList<ShopItemModel>>()
        val ref=firebaseDatabase.getReference("ServiciosDestTer")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ShopItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ShopItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }

    ///CAMPAÑAS///
    fun loadCampaigns(): LiveData<MutableList<CampaignItemModel>>{
        val listData=MutableLiveData<MutableList<CampaignItemModel>>()
        val ref=firebaseDatabase.getReference("Campaigns")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<CampaignItemModel>()
                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CampaignItemModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listData

    }
}