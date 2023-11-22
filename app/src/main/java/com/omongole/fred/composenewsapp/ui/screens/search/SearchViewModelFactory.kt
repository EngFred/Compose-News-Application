package com.omongole.fred.composenewsapp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(
    private val searchQuery: String,
    private val assistedFactory: SearchAssistedFactory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(searchQuery) as T
    }
}

@AssistedFactory
interface SearchAssistedFactory{
    fun create( searchQuery: String ) : SearchScreenViewModel
}