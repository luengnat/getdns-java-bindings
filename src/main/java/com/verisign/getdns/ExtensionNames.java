package com.verisign.getdns;

/**
 * <p>
 * Extensions are java HashMap, with the keys being the names of the extensions.
 * The definition of each extension describes the values that may be assigned to
 * that extension. For most extensions it is a Boolean, and since the default
 * value is “False” it will most often take the value
 * getdns.GETDNS_EXTENSION_TRUE.
 * </p>
 * <p>
 * <b>Note:</b> If a request is using a context in which stub resolution is set,
 * and that request also has any of the dnssec_return_status,
 * dnssec_return_only_secure, or dnssec_return_validation_chain extensions
 * specified, the API will not perform the request and will instead return an
 * error of getdns.GETDNS_RETURN_DNSSEC_WITH_STUB_DISALLOWED.
 * 
 * </p>
 * 
 * @author Vinay Soni
 */
public enum ExtensionNames {

	/**
	 * <p>
	 * Applications that want to do their own validation will want to have the
	 * DNSSEC-related records for a particular response. Use the
	 * dnssec_return_validation_chain extension. Set the extension’s value to
	 * getdns.GETDNS_EXTENSION_TRUE to cause a set of additional DNSSEC-related
	 * records needed for validation to be returned in the response object. This
	 * set comes as validation_chain (a list) at the top level of the response
	 * object. This list includes all resource record dicts for all the resource
	 * records (DS, DNSKEY and their RRSIGs) that are needed to perform the
	 * validation from the root up.
	 * </p>
	 */
	DNSSEC_RETURN_VALIDATION_CHAIN("dnssec_return_validation_chain"),

	/**
	 * <p>
	 * If instead of returning the status, you want to only see secure results,
	 * use the dnssec_return_only_secure extension. The extension’s value is set
	 * to getdns.GETDNS_EXTENSION_TRUE to cause only records that the API can
	 * validate as secure with DNSSEC to be returned in the replies_tree and
	 * replies_full lists. No additional names are added to the dict of the
	 * record; the change is that some records might not appear in the results.
	 * When this context option is set, if the API receives DNS replies but none
	 * are determined to be secure, the error code at the top level of the
	 * response object is getdns.GETDNS_RESPSTATUS_NO_SECURE_ANSWERS.
	 * </p>
	 */
	DNSSEC_RETURN_ONLY_SECURE("dnssec_return_only_secure"),

	/**
	 * <p>
	 * To return the DNSSEC status for each DNS record in the replies_tree list,
	 * use the dnssec_return_status extension. Set the extension’s value to
	 * getdns.GETDNS_EXTENSION_TRUE to cause the returned status to have the name
	 * dnssec_status added to the other names in the record’s dictionary
	 * (“header”, “question”, and so on). The potential values for that name are
	 * getdns.GETDNS_DNSSEC_SECURE, getdns.GETDNS_DNSSEC_BOGUS,
	 * getdns.GETDNS_DNSSEC_INDETERMINATE, and getdns.GETDNS_DNSSEC_INSECURE.
	 * </p>
	 */
	DNSSEC_RETURN_STATUS("dnssec_return_status"),

	/**
	 * <p>
	 * Many applications want to get both IPv4 and IPv6 addresses in a single call
	 * so that the results can be processed together. The address() method is able
	 * to do this automatically. If you are using the general() method, you can
	 * enable this with the return_both_v4_and_v6 extension. The extension’s value
	 * must be set to getdns.GETDNS_EXTENSION_TRUE to cause the results to be the
	 * lookup of either A or AAAA records to include any A and AAAA records for
	 * the queried name (otherwise, the extension does nothing). These results are
	 * expected to be usable with Happy Eyeballs systems that will find the best
	 * socket for an application.
	 * </p>
	 */
	RETURN_BOTH_V4_AND_V6("return_both_v4_and_v6"),

	/**
	 * <p>
	 * An application might want to see debugging information for queries such as
	 * the length of time it takes for each query to return to the API. Use the
	 * return_call_debugging extension. The extension's value (an int) is set
	 * toGETDNS_EXTENSION_TRUE to add the name call_debugging (a list) to the top
	 * level of the response object. Each member of the list is a dict that
	 * represents one call made for the call to the API.
	 * </p>
	 * <p>
	 * Each member has the following names:
	 * <ul>
	 * <li><b>query_name</b> (a bindata) is the name that was sent</li>
	 * <li><b> query_type</b> (an int) is the type that was queried for</li>
	 * <li><b> query_to</b> (a bindata) is the address to which the query was sent
	 * </li>
	 * <li><b> start_time</b> (a bindata) is the time the query started in
	 * milliseconds since the epoch, represented as a uint64_t</li>
	 * <li><b> end_time </b> (a bindata) is the time the query was received in
	 * milliseconds since the epoch, represented as a uint64_t</li>
	 * <li><b> entire_reply</b> (a bindata) is the entire response received</li>
	 * <li><b> dnssec_result </b> (an int) is the DNSSEC status</li>
	 * <li><b> GETDNS_DNSSEC_NOT_PERFORMED</b> if DNSSEC validation was not
	 * performed</li>
	 * </ul>
	 * </p>
	 */
	RETURN_CALL_DEBUGGING("return_call_debugging"),

	/**
	 * <p>
	 * The vast majority of DNS requests are made with the Internet (IN) class. To
	 * make a request in a different DNS class, use, the specify_class extension.
	 * The extension's value (an int) contains the class number. Few applications
	 * will ever use this extension.
	 * </p>
	 */
	SPECIFY_CLASS("specify_class"),

	/**
	 * <p>
	 * To receive a warning if a particular response violates some parts of the
	 * DNS standard, use theadd_warning_for_bad_dns extension. The extension's
	 * value (an int) is set to GETDNS_EXTENSION_TRUE to cause each reply in the
	 * replies_tree to contain an additional name, bad_dns (a list). The list is
	 * zero or more ints that indicate types of bad DNS found in that reply.
	 * </p>
	 * <p>
	 * The list of values is:
	 * <ul>
	 * <li><b>GETDNS_BAD_DNS_CNAME_IN_TARGET</b> A DNS query type that does not
	 * allow a target to be a CNAME pointed to a CNAME</li>
	 * <li><b> GETDNS_BAD_DNS_ALL_NUMERIC_LABEL</b> One or more labels in a
	 * returned domain name is all-numeric; this is not legal for a hostname</li>
	 * <li><b> GETDNS_BAD_DNS_CNAME_RETURNED_FOR_OTHER_TYPE</b> A DNS query for a
	 * type other than CNAME returned a CNAME response</li>
	 * </ul>
	 * </p>
	 */
	ADD_WARNING_FOR_BAD_DNS("add_warning_for_bad_dns"),

	/**
	 * <p>
	 * For lookups that need an OPT resource record in the Additional Data
	 * section, use theadd_opt_parameters extension. The extension’s value (a
	 * dict) contains the parameters; these are described in more detail in RFC
	 * 2671.
	 * </p>
	 * 
	 * <p>
	 * They are:
	 * 
	 * <ul>
	 * <li><b>maximum_udp_payload_size:</b>
	 * 
	 * an integer between 512 and 65535 inclusive. If not specified it defaults to
	 * the value in the getdns context.</li>
	 * 
	 * <li><b>
	 * 
	 * extended_rcode:</b>
	 * 
	 * an integer between 0 and 255 inclusive. If not specified it defaults to the
	 * value in the getdns context.</li>
	 * 
	 * <li><b>
	 * 
	 * version:</b>
	 * 
	 * an integer betwen 0 and 255 inclusive. If not specified it defaults to 0.</li>
	 * 
	 * <li><b>
	 * 
	 * do_bit:</b>
	 * 
	 * must be either 0 or 1. If not specified it defaults to the value in the
	 * getdns context.</li>
	 * 
	 * <li><b>
	 * 
	 * options:</b>
	 * 
	 * a list containing dictionaries for each option to be specified. Each
	 * dictionary contains two keys: option_code (an integer) and option_data (in
	 * the form appropriate for that option code).</li>
	 * </ul>
	 * 
	 * It is very important to note that the OPT resource record specified in
	 * theadd_opt_parameters extension might not be the same the one that the API
	 * sends in the query. For example, if the application also includes any of
	 * the DNSSEC extensions, the API will make sure that the OPT resource record
	 * sets the resource record appropriately, making the needed changes to the
	 * settings from the add_opt_parameters extension.
	 * 
	 * 
	 * </p>
	 * 
	 * <pre>
	 * {
	 * 	&#064;code
	 * 	HashMap&lt;String, Object&gt; optParams = new HashMap&lt;String, Object&gt;();
	 * 	optParams.put(ExtensionNames.ADD_OPT_PARAM_EXTENDED_RCODE.getName(), 128);
	 * 	HashMap&lt;ExtensionNames, Object&gt; extensions = new HashMap&lt;ExtensionNames, Object&gt;();
	 * 	extensions.put(ExtensionNames.ADD_OPT_PARAMETERS, optParams);
	 * }
	 * </pre>
	 */
	ADD_OPT_PARAMETERS("add_opt_parameters"),

	/**
	 * <p>
	 * an integer between 512 and 65535 inclusive. If not specified it defaults to
	 * the value in the getdns context.
	 * </p>
	 */
	ADD_OPT_PARAM_MAX_UDP_PAYLOAD("maximum_udp_payload_size"),

	/**
	 * <p>
	 * an integer between 0 and 255 inclusive. If not specified it defaults to the
	 * value in the getdns context.
	 * </p>
	 */
	ADD_OPT_PARAM_EXTENDED_RCODE("extended_rcode"),

	/**
	 * <p>
	 * an integer betwen 0 and 255 inclusive. If not specified it defaults to 0.
	 * </p>
	 */
	ADD_OPT_PARAM_VERSION("version"),

	/**
	 * <p>
	 * must be either 0 or 1. If not specified it defaults to the value in the
	 * getdns context.
	 * </p>
	 */
	ADD_OPT_PARAM_DO_BIT("do_bit"),

	/**
	 * <p>
	 * a list containing dictionaries for each option to be specified. Each
	 * dictionary contains two keys: option_code (an integer) and option_data (in
	 * the form appropriate for that option code).
	 * </p>
	 */
	ADD_OPT_PARAM_OPTIONS("options");

	private String name;

	private ExtensionNames(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

}
