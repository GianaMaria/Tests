package com.example.tests.presenter.search

import com.example.tests.model.SearchResponse
import com.example.tests.repository.GitHubRepository
import com.example.tests.view.ViewContract
import com.example.tests.view.search.ViewSearchContract
import retrofit2.Response

internal class SearchPresenter internal constructor(
    private val repository: GitHubRepository
) : PresenterSearchContract, GitHubRepository.GitHubRepositoryCallback {

    var viewContractTestNull: ViewSearchContract? = null

    override fun searchGitHub(searchQuery: String) {
        viewContractTestNull?.displayLoading(true)
        repository.searchGithub(searchQuery, this)
    }

    override fun onAttach(viewContract: ViewContract) {
        viewContractTestNull = viewContract as ViewSearchContract
    }

    override fun handleGitHubResponse(response: Response<SearchResponse?>?) {
        if (response != null && response.isSuccessful) {
            val searchResponse = response.body()
            val searchResults = searchResponse?.searchResults
            val totalCount = searchResponse?.totalCount
            if (searchResults != null && totalCount != null) {
                viewContractTestNull?.displaySearchResults(
                    searchResults,
                    totalCount
                )
            } else {
                viewContractTestNull?.displayError("Search results or total count are null")
            }
        } else {
            viewContractTestNull?.displayError("Response is null or unsuccessful")
        }
    }

    override fun handleGitHubError() {
        viewContractTestNull?.displayLoading(false)
        viewContractTestNull?.displayError()
    }

    override fun onDetach() {
        viewContractTestNull = null
    }
}
