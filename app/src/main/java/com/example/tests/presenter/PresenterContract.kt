package com.example.tests.presenter

import com.example.tests.view.ViewContract

internal interface PresenterContract {
    fun onAttach(viewContract: ViewContract)
    fun onDetach()
}