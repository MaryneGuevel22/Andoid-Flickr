package maryne.isis.flickr.ui.liste;

import java.util.List;

import maryne.isis.flickr.model.Photo;

public class MyAdaptater (val photo :List<Photo>, val callback: (Int) -> Unit) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
}
