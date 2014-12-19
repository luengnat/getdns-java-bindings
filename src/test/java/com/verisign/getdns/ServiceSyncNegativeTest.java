package com.verisign.getdns;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
 * 
 */
public class ServiceSyncNegativeTest implements IGetDNSTestConstants{
	
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	
	
	@Test
	public void testGetDNSSyncNonExistingDomain() {
	
		final IGetDNSContext context = GetDNSFactory.create(1);		
		try{
//			thrown.expect(GetDNSException.class);
//			thrown.expect(new ErrorCodeMatcher("GETDNS_RETURN_INVALID_PARAMETER"));
		//	context.addressSync(name, extensions)
			HashMap<String, Object> info =	context.serviceSync(UNREGDOMAIN,  null);
			assertNotNull(info);
		}finally {
			context.close();
		}

	}
	
	
	/*
	 *check for response for null domain  
	 */

	@Test
	public void testGetDNSSyncNULLDomain() {
	
		final IGetDNSContext context = GetDNSFactory.create(1);		
		try{
			thrown.expect(GetDNSException.class);
			thrown.expect(new ErrorCodeMatcher("GETDNS_RETURN_INVALID_PARAMETER"));
			context.serviceSync( null,  null);
		}finally {
			context.close();
		}

	}
	/*
	 * check unit test case against invalid domain (label too long)
	 */
	
	@Test
	public void testGetDNSSyncLongDomain(){
	
		final IGetDNSContext context = GetDNSFactory.create(1);		
		try{
			
			thrown.expect(GetDNSException.class);
			thrown.expect(new ErrorCodeMatcher("GETDNS_RETURN_BAD_DOMAIN_NAME"));
			context.serviceSync(TOOLONGDOMAINNAME,  null);
			 
		}finally {
			context.close();
		}
	}
	
	@Test
	public void testGetDNSSyncForTooManyOctets(){
		System.out.println("Junit 3");
		final IGetDNSContext context = GetDNSFactory.create(1);		
		try{
			
			thrown.expect(GetDNSException.class);
			thrown.expect(new ErrorCodeMatcher("GETDNS_RETURN_BAD_DOMAIN_NAME"));
			context.serviceSync(TOOMANYOCTETS, null);
			 
		}finally {
			context.close();
		}
	}

}