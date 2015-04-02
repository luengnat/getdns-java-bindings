package com.verisign.getdns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class HostnameAsyncPositiveTest {

	@Test
	public void testGetHostnameIPV4() throws UnknownHostException, ExecutionException, TimeoutException {
		HashMap<ContextOptionNames, Object> options = new HashMap<ContextOptionNames, Object>();
		options.put(ContextOptionNames.STUB,true);
		final IGetDNSContext context = GetDNSFactory.create(1,options);
		try {
			GetDNSFutureResult futureResult = context.hostnameAsync("8.8.8.8", null);
			HashMap<String, Object> info = null;
			try {
				info = futureResult.get(5000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(info);
			assertNotNull(info);
			assertEquals("Time out error" + info.get("status"), 900, Integer.parseInt(info.get("status").toString()));
			assertEquals(RRType.PTR.getValue(),GetDNSUtil.getinfovalues(info, "type"));
		} finally {
			context.close();
		}

	}

	 @Test
	public void testGetHostnameIPV6() throws UnknownHostException, ExecutionException, TimeoutException {
		 HashMap<ContextOptionNames, Object> options = new HashMap<ContextOptionNames, Object>();
			options.put(ContextOptionNames.STUB,true);
		final IGetDNSContext context = GetDNSFactory.create(1,options);
		try {
			GetDNSFutureResult futureResult = context.hostnameAsync("2001:4860:4860::8888", null);
			HashMap<String, Object> info = null;
			try {
				info = futureResult.get(5000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			assertNotNull(info);
			assertEquals("Time out error" + info.get("status"), 900, Integer.parseInt(info.get("status").toString()));
			assertEquals(RRType.PTR.getValue(), GetDNSUtil.getinfovalues(info, "type"));
		} finally {
			context.close();
		}

	}
}
