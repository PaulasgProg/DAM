package com.example.viewmodel.repaso.conlistado.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodel.repaso.conlistado.ui.screens.ListItem
import com.example.viewmodel.repaso.conlistado.model.Element
import com.example.viewmodel.repaso.conlistado.model.getFakeElements
import com.example.viewmodel.repaso.conlistado.ui.state.ElementListViewModel

@Preview(showSystemUi = true)
@Composable
fun MainScreenRepaso() {

    val list = remember {
        getFakeElements().toMutableStateList()
    }

    val vm: ElementListViewModel = viewModel()//con VM


//    ListSinVM(l = list)

    ListConVM(vm=vm)
}

@Composable
fun ListSinVM(l:MutableList<Element>){
    LazyColumn {
        items(
            items = l,//se recupera lista eltos Product
        ) { element ->//necesita asignar el nombre explícito
            ListItem(
                name=element.name,
                checked=element.checked,
                onCheckedChange={
//                    checkChanged->product.checked=checkChanged
                    element.checked=it },
                onRemove={l.remove(element)}// vm.list.remove()//borra el elto.//vm.list.removeAt()//borra el de la psiosicion 1, // list.remove//sin VM
            )
        }
    }
}


@Composable
fun ListConVM(vm: ElementListViewModel){

    Column() {
        AddBlock(addProduct = { vm.addProduct(it) })


        LazyColumn {
            items(
                items = vm.list,//se recupera lista eltos Product
            ) { product ->//necesita asignar el nombre explícito
                ListItem(
                    name = product.name,
                    checked = product.checked,
                    onCheckedChange = {
//                    checkChanged->product.checked=checkChanged
                        product.checked = it
                    },
                    onRemove = {
//                    vm.list.remove(product)//para no poder modificar directamente la lista desde fuera VM(val list = _list.toList()/)
                        vm.remove(product)//con el método de VM
                    }
                    // vm.list.remove()//borra el elto.
                    // vm.list.removeAt()//borra el de la psiosicion 1,
                    // list.remove//sin VM
                )
            }
        }
    }
}
