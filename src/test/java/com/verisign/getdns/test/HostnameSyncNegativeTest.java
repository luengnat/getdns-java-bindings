package com.verisign.getdns.test;

import java.net.UnknownHostException;
import java.util.HashMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.verisign.getdns.GetDNSException;
import com.verisign.getdns.GetDNSFactory;
import com.verisign.getdns.IGetDNSContext;

public class HostnameSyncNegativeTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	 @Test
		public void testGetHostnameNUll() throws UnknownHostException{
			final IGetDNSContext context = GetDNSFactory.create(1);		
			try{
				thrown.expect(GetDNSException.class);				
				thrown.expect(new ErrorCodeMatcher("GETDNS_RETURN_INVALID_PARAMETER"));
				HashMap<String, Object> info = context.hostnameSync(null , null);
				System.out.println(info);
				
			
				 
			}finally {
				context.close();
			}
		}

}
