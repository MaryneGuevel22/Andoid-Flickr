package maryne.isis.flickr.ui.liste

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import maryne.isis.flickr.model.Photo
import maryne.isis.flickr.model.SearchResult
import maryne.isis.flickr.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ListViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    public val photo = MutableLiveData<List<Photo>>()
    private val listPhoto = ArrayList<Photo>()

    init {
        Repository().getPhotos(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val list = response.body()?.photos?.photo
                if (list != null) {

                    photo.value = list
                }
                list?.forEach { photo -> listPhoto.add(photo) }
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                print("Erreur lors du chargement !")
            }

        })
    }
}