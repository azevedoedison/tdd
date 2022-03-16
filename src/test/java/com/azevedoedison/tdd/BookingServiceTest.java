package com.azevedoedison.tdd;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.azevedoedison.tdd.model.BookingModel;
import com.azevedoedison.tdd.repository.BookingRepository;
import com.azevedoedison.tdd.service.BookingService;


@RunWith(SpringRunner.class)
public class BookingServiceTest {
	@Autowired
	BookingService bookingService = new BookingService();
	
	@MockBean	
	BookingRepository bookingRepository;
	
	@Test
	public void bookingTestServiceDaysCalculator() {
		String name = "Edison";
		int days = bookingService.daysCalculatorWithDatabase(name);
		Assertions.assertEquals(days, 10);
	}
	@Before
	public void setup() {
		
		/*O Before é utilizando para que o método seja executado antes do teste. Ele servirá para mockarmos o Repository 
		 * para não ter a necessidade de acessarmos bancos de dados. É uma forma de criar um objeto bookingModel
		 * para testarmos a consulta sem ter necessariamente consulta a banco.*/	
		LocalDate checkIn = LocalDate.parse("2020-11-10");
		LocalDate checkOut = LocalDate.parse("2020-11-20");
		BookingModel bookingModel = new BookingModel("1", "Michelli",checkIn, checkOut, 2);		
		Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName()))
			.thenReturn(java.util.Optional.of(bookingModel));
	}
}
