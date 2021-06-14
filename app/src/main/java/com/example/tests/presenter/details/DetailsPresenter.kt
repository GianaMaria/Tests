package com.example.tests.presenter.details

import com.example.tests.view.ViewContract
import com.example.tests.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private var count: Int = 0
) : PresenterDetailsContract {

    var viewContractTestNull: ViewDetailsContract? = null

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        viewContractTestNull?.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContractTestNull?.setCount(count)
    }

    override fun onAttach(viewContract: ViewContract) {
        viewContractTestNull = viewContract as ViewDetailsContract
    }

    override fun onDetach() {
        viewContractTestNull = null
        viewContractTestNull
    }
}