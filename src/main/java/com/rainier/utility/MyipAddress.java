package com.rainier.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyipAddress {
	public InetAddress myIp() {

		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			// System.out.println(ipAddr.getHostAddress());
			return ipAddr;

		} catch (UnknownHostException ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
