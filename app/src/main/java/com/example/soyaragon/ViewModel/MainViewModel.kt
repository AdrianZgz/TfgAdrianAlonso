package com.example.soyaragon.ViewModel

import androidx.lifecycle.LiveData
import com.example.soyaragon.Domain.CampaignItemModel
import com.example.soyaragon.Domain.ShopItemModel
import com.example.soyaragon.Domain.Repository.MainRepository

class MainViewModel {
    private val repository=MainRepository()

    fun loadRestaurantesDestZgz():LiveData<MutableList<ShopItemModel>>{
        return repository.loadRestaurantesDestZgz()
    }

    fun loadComerciosDestZgz():LiveData<MutableList<ShopItemModel>>{
        return repository.loadComerciosDestZgz()
    }

    fun loadAlojamientosDestZgz():LiveData<MutableList<ShopItemModel>>{
        return repository.loadAlojamientosDestZgz()
    }
    fun loadTurismoDestZgz():LiveData<MutableList<ShopItemModel>>{
        return repository.loadTurismoDestZgz()
    }
    fun loadServiciosDestZgz():LiveData<MutableList<ShopItemModel>>{
        return repository.loadServiciosDestZgz()
    }

    ///HUESCA///
    fun loadRestaurantesDestHue():LiveData<MutableList<ShopItemModel>>{
        return repository.loadRestaurantesDestHue()
    }

    fun loadComerciosDestHue():LiveData<MutableList<ShopItemModel>>{
        return repository.loadComerciosDestHue()
    }

    fun loadAlojamientosDestHue():LiveData<MutableList<ShopItemModel>>{
        return repository.loadAlojamientosDestHue()
    }
    fun loadTurismoDestHue():LiveData<MutableList<ShopItemModel>>{
        return repository.loadTurismoDestHue()
    }
    fun loadServiciosDestHue():LiveData<MutableList<ShopItemModel>>{
        return repository.loadServiciosDestHue()
    }

    ///TERUEL///

    fun loadRestaurantesDestTer():LiveData<MutableList<ShopItemModel>>{
        return repository.loadRestaurantesDestTer()
    }

    fun loadComerciosDestTer():LiveData<MutableList<ShopItemModel>>{
        return repository.loadComerciosDestTer()
    }

    fun loadAlojamientosDestTer():LiveData<MutableList<ShopItemModel>>{
        return repository.loadAlojamientosDestTer()
    }
    fun loadTurismoDestTer():LiveData<MutableList<ShopItemModel>>{
        return repository.loadTurismoDestTer()
    }
    fun loadServiciosDestTer():LiveData<MutableList<ShopItemModel>>{
        return repository.loadServiciosDestTer()
    }

    fun loadCampaigns():LiveData<MutableList<CampaignItemModel>>{
        return repository.loadCampaigns()
    }


}