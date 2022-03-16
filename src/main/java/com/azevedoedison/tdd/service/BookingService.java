package com.azevedoedison.tdd.service;

import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azevedoedison.tdd.model.BookingModel;
import com.azevedoedison.tdd.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	public int daysCalculatorWithDatabase(String name) {
		Optional<BookingModel> bookingModelOptional = bookingRepository.findByReserveName(name);
		
		/*retorna o periodo em dias entre a data de checkin e a data de chekout*/
		return Period.between(bookingModelOptional.get().getCheckIn(), bookingModelOptional.get().getCheckOut()).getDays();
	}

}
