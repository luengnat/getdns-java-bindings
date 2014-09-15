package com.verisign.getdns;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class GetDNSTest {
	
	


	


	@Test
	public void testGetDNSWithExtension() {
		System.out.println("---------Starting testGetDNSWithExtension");
		final IGetDNSContext context = GetDNSFactory.create(1);		
		try{
			HashMap<String, Object> extensions = new HashMap<String, Object>();
			extensions.put(GetDNSConstants.DNSSEC_RETURN_STATUS, GetDNSConstants.GETDNS_EXTENSION_TRUE);
			extensions.put(GetDNSConstants.DNSSEC_RETURN_ONLY_SECURE, GetDNSConstants.GETDNS_EXTENSION_TRUE);
			extensions.put(GetDNSConstants.DNSSEC_RETURN_VALIDATION_CHAIN, GetDNSConstants.GETDNS_EXTENSION_TRUE);
			
			HashMap<String, Object> info = context.generalSync("verisigninc.com", RRType.GETDNS_RRTYPE_A, extensions);
			System.out.println("Output: "+ info);
		}finally {
			context.close();
		}

	}

	@Test
	public void testGetDNSSimple()
	{
		System.out.println("---------Starting testGetDNSSimple");
		IGetDNSContext context = GetDNSFactory.create(1);
		try{
			HashMap<String, Object> info = context.generalSync("verisigninc.com", RRType.GETDNS_RRTYPE_A, null);
			System.out.println("Output: "+ info);
		}finally {
			context.close();
		}
	}
	
		
	@Test
	public void testGetDNSAsync() 
	{
		System.out.println("---------Starting testGetDNSAsync");
		final IGetDNSContext context = GetDNSFactory.create(1);		
	
		try{
			final CountDownLatch latch = new CountDownLatch(1);
			IGetDNSCallback callback = new IGetDNSCallback() {

				public void handleResponse(HashMap<String, Object> response) {
					System.out.println("Completed domain with index: "+(1-latch.getCount()));
					latch.countDown();
					System.out.println(response);
				}

				public void handleException(GetDNSException exception) {
					// TODO Auto-generated method stub

				}
			};
			
			context.generalASync("verisigninc.com", RRType.GETDNS_RRTYPE_A, null, callback);
			try {
				System.out.println("Sleeping..");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}finally {
			context.close();
		}
	}


	/*@Test
	public void testGetDNSBulk()
	{
		System.out.println("---------Starting testGetDNSBulk");
		GetDNS getDNS = new GetDNS();
		Object context = getDNS.contextCreate(1);  // invoke the native method
		try{
			String[] domains = {"google.com", "facebook.com", "youtube.com", "yahoo.com", "baidu.com", "wikipedia.org", "twitter.com", "qq.com", "amazon.com"};//, "live.com", "taobao.com", "linkedin.com", "google.co.in", "sina.com.cn", "hao123.com", "weibo.com", "blogspot.com", "tmall.com", "yahoo.co.jp", "sohu.com", "yandex.ru", "360.cn", "vk.com", "bing.com", "pinterest.com", "google.de", "wordpress.com", "ebay.com", "instagram.com", "soso.com", "google.co.uk", "google.co.jp", "paypal.com", "google.fr", "msn.com", "ask.com", "google.com.br", "163.com", "tumblr.com", "xvideos.com", "google.ru", "mail.ru", "microsoft.com", "imdb.com", "google.it", "stackoverflow.com", "google.es", "apple.com","imgur.com","reddit.com","adcash.com","craigslist.org","blogger.com","amazon.co.jp","t.co","aliexpress.com","google.com.mx","xhamster.com","fc2.com","google.ca","gmw.cn","wordpress.org","cnn.com","alibaba.com","bbc.co.uk","go.com","huffingtonpost.com","people.com.cn","godaddy.com","google.co.id","kickass.to","ifeng.com","chinadaily.com.cn","dropbox.com","vube.com","pornhub.com","google.com.tr","amazon.de","themeforest.net","xinhuanet.com","adobe.com","googleusercontent.com","google.com.au","netflix.com","odnoklassniki.ru","dailymotion.com","google.pl","thepiratebay.se","booking.com","ebay.de","xnxx.com","dailymail.co.uk","flipkart.com","about.com","uol.com.br","github.com","espn.go.com","google.com.hk","bp.blogspot.com","rakuten.co.jp","vimeo.com","akamaihd.net","onclickads.net","amazon.co.uk","indiatimes.com","flickr.com","blogspot.in","ebay.co.uk","redtube.com","tudou.com","alipay.com","fiverr.com","clkmon.com","salesforce.com","outbrain.com","nytimes.com","buzzfeed.com","google.com.ar","globo.com","pixnet.net","cnet.com","youporn.com","google.com.sa","aol.com","sogou.com","yelp.com","gigacircle.com","china.com","google.com.eg","google.com.tw","pconline.com.cn","mozilla.org","livejasmin.com","ameblo.jp","w3schools.com","secureserver.net","google.nl","so.com","amazonaws.com","slideshare.net","theguardian.com","wikia.com","mailchimp.com","directrev.com","hootsuite.com","google.com.pk","bbc.com","forbes.com","mama.cn","gmail.com","yaolan.com","adf.ly","aili.com","zol.com.cn","livejournal.com","weather.com","mmbang.com","soundcloud.com","wikihow.com","google.co.th","livedoor.com","google.co.za","skype.com","canadaalltax.com","naver.com","deviantart.com","chase.com","bankofamerica.com","ettoday.net","spiegel.de","baomihua.com","conduit.com","stackexchange.com","stumbleupon.com","xcar.com.cn","caijing.com.cn","torrentz.eu","9gag.com","loading-delivery1.com","foxnews.com","businessinsider.com","etsy.com","hostgator.com","walmart.com","blogfa.com","files.wordpress.com","indeed.com","mashable.com","zillow.com","badoo.com","tripadvisor.com","aweber.com","sourceforge.net","archive.org","39.net","reference.com","china.com.cn","moz.com","amazon.in","liveinternet.ru","mediafire.com","addthis.com","wellsfargo.com","shutterstock.com","answers.com","google.gr","inclk.com","statcounter.com","rt.com","gome.com.cn","codecanyon.net","onet.pl","naver.jp","pchome.net","google.be","jabong.com","allegro.pl","coccoc.com","google.com.co","google.com.my","java.com","4shared.com","nicovideo.jp","twitch.tv","google.com.ua","wikimedia.org","wix.com","doublepimp.com","telegraph.co.uk","google.com.ng","avg.com","bild.de","wordreference.com","dmm.co.jp","google.com.vn","ikea.com","bitly.com","avito.ru","huanqiu.com","yoka.com","lady8844.com","xgo.com.cn","techcrunch.com","bet365.com","espncricinfo.com","bitauto.com","tube8.com","popads.net","quora.com","jrj.com.cn","warriorforum.com","feedly.com","google.co.kr","force.com","ask.fm","leboncoin.fr","bleacherreport.com","v1.cn","google.se","php.net","amazon.fr","goo.ne.jp","acesse.com","google.at","wsj.com","enet.com.cn","google.ro","ci123.com","snapdeal.com","weebly.com","google.com.ph","zendesk.com","pandora.com","zeobit.com","chinaz.com","google.dz","ups.com","softonic.com","xywy.com","gogorithm.com","washingtonpost.com","google.cl","google.com.pe","wp.pl","github.io","photobucket.com","usatoday.com","mercadolivre.com.br","goodreads.com","hudong.com","hurriyet.com.tr","infusionsoft.com","zedo.com","google.com.sg","speedtest.net","gmx.net","reuters.com","pcbaby.com.cn","disqus.com","odesk.com","douban.com","daum.net","goal.com","usps.com","google.ch","meetup.com","ndtv.com","rev2pub.com","kaskus.co.id","media.tumblr.com","gameforge.com","rambler.ru","extratorrent.cc","google.pt","rediff.com","neobux.com","comcast.net","yesky.com","domaintools.com","google.cz","jqw.com","bluehost.com","ehow.com","ebay.in","ign.com","rutracker.org","rbc.ru","samsung.com","microsoftonline.com","gsmarena.com","b5m.com","fedex.com","tmz.com","time.com","hdfcbank.com","webmd.com","mystart.com","hp.com","elance.com","google.com.bd","americanexpress.com","marca.com","stockstar.com","bongacams.com","histats.com","scribd.com","goodgamestudios.com","varzesh3.com","google.co.ve","17ok.com","detik.com","icicibank.com","bestbuy.com","target.com","milliyet.com.tr","groupon.com","trello.com","tianya.cn","repubblica.it","cnzz.com","evernote.com","theladbible.com","constantcontact.com","probux.com","goo.gl","kompas.com","web.de","lifehacker.com","lenta.ru","nih.gov","taringa.net","olx.in","uploaded.net","fbcdn.net","4dsply.com","clickbank.com","onlylady.com","bloomberg.com","webssearches.com","myntra.com","onlinesbi.com","gfycat.com","mlb.com","turboloves.net","getresponse.com","abril.com.br","kakaku.com","thefreedictionary.com","xuite.net","free.fr","cj.com","youjizz.com","chaturbate.com","google.no","google.az","zippyshare.com","clicksvenue.com","elmundo.es","subscene.com","amazon.cn","elpais.com","capitalone.com","sahibinden.com","cpmterra.com","list-manage.com","google.ie","google.co.hu","iqiyi.com","udn.com","motherless.com","ebay.it","icmwebserv.com","google.ae","okcupid.com","steamcommunity.com","zeroredirect1.com","xing.com","rednet.cn","abcnews.go.com","ameba.jp","ria.ru","homedepot.com","accuweather.com","lemonde.fr","quikr.com","habrahabr.ru","gizmodo.com","youth.cn","chinabyte.com","voc.com.cn","hulu.com","libero.it","privatehomeclips.com","pof.com","semrush.com","likes.com","taboola.com","ebay.com.au","beeg.com","engadget.com","yandex.ua","dell.com","trovi.com","youm7.com","gazeta.pl","4399.com","drudgereport.com","amazon.it","blackhatworld.com","issuu.com","surveymonkey.com","trulia.com","lefigaro.fr","sabq.org","steampowered.com","lenovo.com","seznam.cz","expedia.com","intuit.com","ucoz.ru","orange.fr","buyma.com","hotels.com","xe.com","kickstarter.com","blogspot.co.uk","t-online.de","naukri.com","v9.com","doubleclick.com","google.co.il","cloudfront.net","liputan6.com","webmoney.ru","hubspot.com","9gag.tv","shareasale.com","retailmenot.com","chexun.com","vice.com","google.dk","intoday.in","52pk.net","asana.com","uimserv.net","ero-advertising.com","51fanli.com","xda-developers.com","ganji.com","istockphoto.com","w3.org","it168.com","basecamp.com","att.com","twimg.com","youboy.com","blogspot.mx","rottentomatoes.com","oracle.com","zoho.com"};

			long start = System.currentTimeMillis();
			for(String domain:domains){

				HashMap<String, Object> info = getDNS.generalSync(context, domain, 1, null);
				System.out.println("Output: "+ info);
			}
			System.out.println("Avg Time taken per domain: "+((System.currentTimeMillis()-start)/(1000.0*domains.length))+" seconds");

		}
		finally{
			getDNS.contextDestroy(context);
		}
	}*/
}
