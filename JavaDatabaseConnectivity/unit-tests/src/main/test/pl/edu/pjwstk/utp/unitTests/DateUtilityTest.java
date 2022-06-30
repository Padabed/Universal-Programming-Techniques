package pl.edu.pjwstk.utp.unitTests;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Assert;
import org.junit.Test;

public final class DateUtilityTest {

	@Test
	public void differenceInYears() {
		LocalDateTime start = LocalDateTime.of(2011, 1, 1, 0, 0, 0);
		LocalDateTime end = LocalDateTime.of(2013, 1, 1, 0, 0, 0);
		Instant startInstant = start //
				.atZone(ZoneId.systemDefault()) //
				.toInstant();
		Instant endInstant = end //
				.atZone(ZoneId.systemDefault()) //
				.toInstant();
		//int difference = DateUtility.differenceInYears(Date.from(startInstant), Date.from(endInstant));
		Assert.assertEquals(2, DateUtility.differenceInYears(Date.from(startInstant), Date.from(endInstant)));
		Assert.assertNotEquals(1, DateUtility.differenceInYears(Date.from(startInstant), Date.from(endInstant)));
	}
}