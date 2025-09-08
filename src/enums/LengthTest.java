package enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LengthTest {

		Length l1 = new Length(10, LengthUnit.M);
		Length l2 = new Length(1000, LengthUnit.CM);
		Length l3 = new Length(20, LengthUnit.M);
		@Test
		void testToString()
		{
			assertEquals("10M", l1.toString());
			assertEquals("1000CM", l2.toString());
		}
		@Test
		void testPlus()
		{
			Length res = new Length(20, LengthUnit.M);
			assertEquals(res, l1.plus(l2));

			res = new Length(2000, LengthUnit.CM);
			assertEquals(res, l2.plus(l1));
		}
		@Test
		void testMinus()
		{
			Length res = new Length(0, LengthUnit.M);
			assertEquals(res, l1.minus(l2));

			res = new Length(0, LengthUnit.CM);
			assertEquals(res, l2.minus(l1));
		}
		@Test
		void testConvert()
		{
			assertEquals(l2, l1.convert(LengthUnit.CM));
			assertEquals(l1, l2.convert(LengthUnit.M));
		}
		@Test
		void testBetween()
		{
			assertEquals(-10000, LengthUnit.MM.between(l3, l2), 0.00001);
			assertEquals(10, LengthUnit.M.between(l2, l3), 0.00001);
			assertEquals(-1000, LengthUnit.CM.between(l3, l2), 0.00001);
		}
		@Test
		void testEquals()
		{
			Length res = new Length(0, LengthUnit.CM);
			assertTrue(res.equals(l2.minus(l1)));
		}
	}

