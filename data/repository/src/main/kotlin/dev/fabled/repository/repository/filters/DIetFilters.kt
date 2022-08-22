package dev.fabled.repository.repository.filters

import dev.fabled.domain.model.FilterItem
import dev.fabled.repository.R

object Ketogenic : FilterItem(
    name = R.string.ketogenic,
    image = R.drawable.ic_ketogenic,
    tag = "Ketogenic"
)

object Vegetarian : FilterItem(
    name = R.string.vegetarian,
    image = R.drawable.ic_vegetarian,
    tag = "Vegetarian"
)

object LactoseVegetarian : FilterItem(
    name = R.string.lactose_vegetarian,
    image = R.drawable.ic_lacto_vegetarian,
    tag = "Lacto-Vegetarian"
)

object Perscetarian : FilterItem(
    name = R.string.perscetarian,
    image = R.drawable.ic_pescetarian,
    tag = "Pescetarian"
)