package com.compose.template.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.compose.template.utills.ViewState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel()