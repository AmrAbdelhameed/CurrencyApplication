package com.example.currencyapplication.presentation.utils

import android.os.Bundle
import androidx.navigation.NavController
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionFunctionsTest {
    @Test
    fun `roundOffDecimal should round off correctly`() {
        val input = 3.14159

        val result = input.roundOffDecimal()

        assertEquals(3.15, result, 0.0)
    }

    @Test
    fun `orEmpty should return the original value if not null`() {
        val input: Int? = 42

        val result = input.orEmpty()

        assertEquals(42, result)
    }

    @Test
    fun `orEmpty should return the default value if null`() {
        val input: Int? = null
        val default = 100

        val result = input.orEmpty(default)

        assertEquals(default, result)
    }

    @Test
    fun `navigateSafe should navigate if destinationId is not 0`() {
        val navController: NavController = mockk(relaxed = true)
        val args: Bundle = mockk(relaxed = true)
        val destinationId = 123
        val actionId = 456

        every { navController.currentDestination } returns mockk(relaxed = true)
        every { navController.currentDestination?.getAction(actionId)?.destinationId } returns destinationId

        navController.navigateSafe(actionId, args)

        verify { navController.navigate(actionId, args) }
    }

    @Test
    fun `navigateSafe should not navigate if destinationId is 0`() {
        val navController: NavController = mockk(relaxed = true)
        val args: Bundle = mockk(relaxed = true)
        val actionId = 456

        every { navController.currentDestination } returns mockk(relaxed = true)
        every { navController.currentDestination?.getAction(actionId)?.destinationId } returns 0

        navController.navigateSafe(actionId, args)

        verify(exactly = 0) { navController.navigate(actionId, args) }
    }
}
