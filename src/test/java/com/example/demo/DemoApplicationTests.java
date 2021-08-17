package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void givenStaticMethodWithNoArgs_whenMocked_thenReturnsMockSuccessfully() {
		assertEquals(StaticUtils.name(), "Baeldung");

		try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
			utilities.when(StaticUtils::name).thenReturn("Eugen");
			assertEquals(StaticUtils.name(), "Eugen");
		}

		assertEquals(StaticUtils.name(), "Baeldung");
	}

	@Test
	void givenStaticMethodWithArgs_whenMocked_thenReturnsMockSuccessfully() {
		assertThat(StaticUtils.range(2, 6)).containsExactly(2, 3, 4, 5);

		try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
			utilities.when(() -> StaticUtils.range(2, 6))
					.thenReturn(Arrays.asList(10, 11, 12));

			assertThat(StaticUtils.range(2, 6)).containsExactly(10, 11, 12);
		}

		assertThat(StaticUtils.range(2, 6)).containsExactly(2, 3, 4, 5);
	}



}
