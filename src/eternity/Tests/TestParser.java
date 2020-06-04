package eternity.Tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import eternity.Tests.TestUtils;
import eternity.Parser;

class TestParser {
	@Test
	public void test_lex_single_number() {
			assertEquals(TestUtils.makeArrayList("1"), Parser.lex("1"));
	}
}
