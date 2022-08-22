package dev.fabled.domain.model

abstract class FilterItem(
    val name: Int,
    val image: Int,
    val isSelected: Boolean = false,
    val tag: String
) {

    fun copy(
        name: Int = this.name,
        image: Int = this.image,
        isSelected: Boolean = this.isSelected,
        tag: String = this.tag
    ) = object : FilterItem(name, image, isSelected, tag) {

    }

}