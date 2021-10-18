package maryne.isis.flickr.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import maryne.isis.flickr.R
import maryne.isis.flickr.model.Photo


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        val layout = inflater.inflate(R.layout.main_fragment, container, false)
        val boutonNext = layout.findViewById<Button>(R.id.Next)
        val boutonAll = layout.findViewById<Button>(R.id.All_Images)
        val titre = layout.findViewById<TextView>(R.id.Titre)
        val image = layout.findViewById<ImageView>(R.id.Image)

        val recycler = layout.findViewById<RecyclerView>(R.id.recyclerView)
        recycler.layoutManager = GridLayoutManager(requireActivity(),2)

        val observer = Observer<Photo> { photo ->
            val url =
                "https://live.staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"
            titre.text = photo.title
            Glide.with(layout).load(url).into(image)
        }

        boutonAll.setOnClickListener {
            Navigation.findNavController(layout).navigate(R.id.versListeFragment);
        }

        model.photo.observe(this, observer)
        boutonNext.setOnClickListener {
            model.nextPhoto()
        }

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}