package com.verisign.getdns.example.asyncwithcallback;

import java.util.HashMap;

import com.verisign.getdns.GetDNSFactory;
import com.verisign.getdns.GetDNSUtil;
import com.verisign.getdns.IGetDNSCallback;
import com.verisign.getdns.IGetDNSContextAsyncWithCallback;
import com.verisign.getdns.example.sync.GetDNSAddressSync;

/*
 * 
 * Given a DNS name and type, return the records in the DNS answer section 
 * 
 * 
 * 
 */

public class GetDNSAddressAsyncCallback {

	public static void main(String[] args) {
		final IGetDNSContextAsyncWithCallback context = GetDNSFactory.createAsyncWithCallback(1, null);

		final String queryString = "verisigninc.com";

		try {
			IGetDNSCallback callback = new IGetDNSCallback() {

				@Override
				public void handleResponse(HashMap<String, Object> response, RuntimeException exception) {
					GetDNSAddressSync.printAnswer(response);
					System.out.println(GetDNSUtil.getDnsStatus(response));
				}
			};

			context.addressAsync(queryString, null, callback);
			context.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		System.exit(0);

	}
}
