package maryne.isis.flickr.model

class Photo (
    val id: String,
    val owner : String,
    val secret: String,
    val server : String,
    val farm: String,
    val title: String,
    val ispublic: Integer,
    val isfirend: Integer,
    val isfamily: Integer
    ) {
}