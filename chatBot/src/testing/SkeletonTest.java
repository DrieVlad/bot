package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import chatBot.Skeleton;

public class SkeletonTest {

	@Test
	public void testHelp() {
		boolean check_help = Skeleton.help("help");
		AssertTrue(check_help);
	}

}
