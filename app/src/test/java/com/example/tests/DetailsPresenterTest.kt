package com.example.tests

import com.example.tests.presenter.details.DetailsPresenter
import com.example.tests.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import junit.framework.TestCase.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter
    private var viewContractTestNull: ViewDetailsContract? = null

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter()
        presenter.onAttach(viewContract)
        viewContractTestNull = presenter.viewContractTestNull!!
    }

    //Проверка, что тест вызывается хотя бы раз, кладется значение инт (любое)
    @Test
    fun onIncrement_Test() {
        presenter.onIncrement()
        verify(viewContractTestNull, times(1))?.setCount(anyInt())
    }

    @Test
    fun onDecrement_Test() {
        presenter.onDecrement()
        verify(viewContractTestNull, times(1))?.setCount(anyInt())
    }

    //Проверка на нал и сравнивается 2 вью контракт
    @Test
    fun onAttach_Test() {
        presenter.onAttach(viewContract)
        assertNotNull(presenter.viewContractTestNull)
        assertEquals(viewContract,viewContractTestNull)
    }

    //Проверка на нал
    @Test
    fun onDetach_Test() {
        presenter.onDetach()
        assertNull(presenter.viewContractTestNull)
    }

    //Обнуление для дальнейших тестов или изменений
    @After
    fun close() {
        presenter.onDetach()
    }
}