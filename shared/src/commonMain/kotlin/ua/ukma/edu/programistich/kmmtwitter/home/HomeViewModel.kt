package ua.ukma.edu.programistich.kmmtwitter.home

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ua.ukma.edu.programistich.kmmtwitter.common.viewmodel.ViewModel
import ua.ukma.edu.programistich.kmmtwitter.home.models.HomeState

class HomeViewModel : KoinComponent, ViewModel<HomeState, Unit, Unit>(
    initialState = HomeState.Loading
) {
    private val homeRepository: HomeRepository by inject()

    init {
        withViewModelScope {
            runCatching {
                return@runCatching homeRepository.getHomeTimeline()
            }.onSuccess {
                setViewState(HomeState.Success(it))
            }.onFailure {
                setViewState(HomeState.Error(it))
            }
        }
    }

    override fun obtainEvent(viewEvent: Unit) = Unit
}
