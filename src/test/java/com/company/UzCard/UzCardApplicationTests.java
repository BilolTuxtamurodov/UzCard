package com.company.UzCard;

import com.company.UzCard.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UzCardApplicationTests {
@Autowired
private CardService cardService;
	@Test
	void contextLoads() {
//	String card = cardService.generateCardNumber();
//		System.out.println(card);
	}

}
