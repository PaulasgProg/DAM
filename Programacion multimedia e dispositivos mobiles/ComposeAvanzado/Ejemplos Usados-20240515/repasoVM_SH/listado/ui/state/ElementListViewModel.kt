package com.example.viewmodel.repaso.conlistado.ui.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.viewmodel.repaso.conlistado.model.Element
import com.example.viewmodel.repaso.conlistado.model.getFakeElements

class ElementListViewModel: ViewModel() {
  //V1//var list = getFakeElements().toMutableStateList()
    //    private var _list = getFakeShoppingProducts().toMutableStateList()//lista no modificable desde fuera para que sólo lo haga el VM

    //    NUEVA VERSIÓN->partir de lista vacía
    private var _list= mutableStateListOf<Element>()
    val list:List<Element> get() = _list//para sólo tener acceso desde fuera al get para no poder modificarla

//    private var __list2 = mutableStateListOf<Product>()//Alt+Enter->para que permita introducir baking property automáticamente
//    private var _list2: SnapshotStateList<Product>
//        get() = __list2
//        set(value) {
//            __list2 = value
//        }



    fun remove(p: Element){
        _list.remove(p)
    }

    fun changeCheked(product: Element, it: Boolean) {
        product.checked=it
    }

    fun changeCheked(product: Element) {
        product.checked=!product.checked
    }

    private fun add(item: Element) =
        _list.add(item)

    fun addProduct(name: String) {
        add(Element(name))
    }
}