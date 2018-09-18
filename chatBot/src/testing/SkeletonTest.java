package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import chatBot.Skeleton;

public class SkeletonTest {

	@Test
	public void testHelp() {
		boolean check_help = Skeleton.help("help");
<<<<<<< HEAD
		assertEquals(check_help, true);
=======
		assertTrue(check_help);
>>>>>>> 84a0ff709626e5a1643aeab96e4477344929b40a
	}

}
