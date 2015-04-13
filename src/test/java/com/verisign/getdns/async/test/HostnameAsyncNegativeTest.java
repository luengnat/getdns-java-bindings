package com.verisign.getdns.async.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.verisign.getdns.GetDNSException;
import com.verisign.getdns.GetDNSFactory;
import com.verisign.getdns.GetDNSFutureResult;
import com.verisign.getdns.IGetDNSContext;
import com.verisign.getdns.test.ErrorCodeMatcher;

public class HostnameAsyncNegativeTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGetHostnameNULL() throws Exception {
		final IGetDNSContext context = GetDNSFactory.create(1);
		try {
			thrown.expect(GetDNSException.class);
			thrown.expect(new ErrorCodeMatcher("GETDNS_RETURN_INVALID_PARAMETER"));
			GetDNSFutureResult futureResult = context.hostnameAsync(null, null);
			context.run();
			futureResult.get();
		} finally {
			context.close();
		}

	}
}
