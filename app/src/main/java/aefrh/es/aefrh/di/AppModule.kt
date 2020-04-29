package aefrh.es.aefrh.di

import aefrh.es.aefrh.presentation.epocas.EpocasViewModel
import aefrh.es.aefrh.presentation.fiestas.FiestaViewModel
import aefrh.es.aefrh.presentation.interno.InternoViewModel
import aefrh.es.aefrh.presentation.mapa.MapaViewModel
import aefrh.es.aefrh.presentation.noticias.NoticiasViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EpocasViewModel(epocaUseCase = get()) }
    viewModel { FiestaViewModel(fiestaUseCase = get()) }
    viewModel { MapaViewModel(fiestaUseCase = get()) }
    viewModel { InternoViewModel(internoUseCase = get()) }
    viewModel { NoticiasViewModel(noticiasUseCase = get()) }
}