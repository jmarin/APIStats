package com.apistatsmodel.messages;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import scala.collection.mutable.LinkedHashMap;

public class APIStatsMessageTest {

	private APIStatsMessage message;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCensusURL() {
		LinkedHashMap<String, String> pathParams = new LinkedHashMap<String, String>();
		pathParams.update("geographyType", "block");
		LinkedHashMap<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.update("latitude", "42.456");
		queryParams.update("longitude", "-74.987");
		queryParams.update("format", "json");
		message = new APIStatsMessage(
				"Census - By Coordinates",
				"broadbandmap",
				"http://www.broadbandmap.gov/broadbandmap/census/block?latitude=42.456&longitude=-74.987&format=json",
				pathParams, queryParams, new DateTime(), 45, true, null, false);
		assertEquals(message.contextPath(), "broadbandmap");
		assertEquals(
				message.resourceURL(),
				"http://www.broadbandmap.gov/broadbandmap/census/block?latitude=42.456&longitude=-74.987&format=json");
	}
}
