package maryne.isis.flickr.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import maryne.isis.flickr.model.Photo
import maryne.isis.flickr.model.SearchResult
import maryne.isis.flickr.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    public val photo = MutableLiveData<Photo>()
    private val listPhoto = ArrayList<Photo>()

    init{
        Repository().getPhotos(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val list = response.body()?.photos?.photo
                if (list != null ) {
                    photo.value = list[0]
                }
                list?.forEach { photo -> listPhoto.add(photo)}
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                print("Erreur lors du chargement !")
            }

        })
    }

    fun nextPhoto(){
        var IndexNext: Int = 0
        if(photo.value === null) {
            IndexNext = 0
        } else {
            val currentIndex = listPhoto.indexOf(photo.value)
            IndexNext = currentIndex + 1
            if(currentIndex === listPhoto.size - 1) {
                IndexNext = 0
            }
        }
        photo.value = listPhoto[IndexNext]
    }
}
