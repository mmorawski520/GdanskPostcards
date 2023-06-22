package com.zschie.gdanskpostcards.ui.gallery

class GdanskGalleryFragment : GalleryFragment("blured_gdansk_")
class SopotGalleryFragment : GalleryFragment("blured_sopot_")
class GdyniaGalleryFragment : GalleryFragment("blured_gdynia_")
class OtherGalleryFragment : GalleryFragment("blured_other_")

/*
this.photos = drawableFields.filter {
    it.name.startsWith("blured_gdansk_")
}.map { photo ->
    PhotoInfo(
        photo.getInt(null),
        R.string::class.java.fields.find {
            it.name.startsWith(photo.name, ignoreCase = true)
        }?.let { id -> resources.getString(id.getInt(null)) }
    )
}
 */