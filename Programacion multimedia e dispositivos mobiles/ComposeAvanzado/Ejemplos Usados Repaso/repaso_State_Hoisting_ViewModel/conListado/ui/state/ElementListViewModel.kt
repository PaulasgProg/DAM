package com.example.viewmodel.repaso.conlistado.ui.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.viewmodel.repaso.conlistado.model.getFakeElements

class ElementListViewModel: ViewModel() {
    var list = getFakeElements().toMutableStateList()
}