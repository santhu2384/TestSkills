package com.example.testskills

import com.example.testskills.domain.model.Users
import com.example.testskills.domain.usecase.LoginUsecase
import com.example.testskills.domain.usecase.UsersListUseCase
import com.example.testskills.presentation.LoginViewModel
import com.example.testskills.presentation.UIstateGen
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest
{
    private lateinit var loginUsecase: LoginUsecase
    private lateinit var loginuerlistusecase:UsersListUseCase
    private lateinit var viewModel: LoginViewModel

    private val dispatcher = StandardTestDispatcher()
    @Before
    fun setup()
    {
        loginUsecase = mockk()
        loginuerlistusecase = mockk()
        viewModel = LoginViewModel(loginUsecase,loginuerlistusecase)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `showUser-list_success`() = runTest(dispatcher)
    {
        val fakeuserlist = listOf(Users(1,"santhosh","batte.santhosh@gmail.com","dfdfdf")
                            ,Users(1,"santhosh","batte.santhosh@gmail.com","dfdfdf"))
        //when(loginuerlistusecase()).equals(fakeuserlist)
        viewModel.showUserlist()
        advanceUntilIdle()
        assert(true, viewModel._userlist.value as ()->Any)
        val state = viewModel._userlist.value as UIstateGen.Success
        assetE
    }

}