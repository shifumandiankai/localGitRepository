package com.wadejerry.scms.utils.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.wadejerry.scms.utils.Log.LogManager;

/**
 * 
 * <p></p>
 * @author jiangdahui Aug 24, 2013 3:34:37 PM
 * @version V1.0   
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} Aug 24, 2013
 * @modify by reason:{方法名}:{原因}
 */
public class StreamGobbler extends Thread {
	
	private InputStream		is;
	private String			type;
	private OutputStream	os;
	private StringBuilder	output;
	
	StreamGobbler(InputStream is, String type) {
		this(is, type, null);
	}
	
	StreamGobbler(InputStream is, String type, OutputStream redirect) {
		this.is = is;
		this.type = type;
		this.os = redirect;
		output = new StringBuilder();
	}
	
	public void run() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			if (os != null)
				pw = new PrintWriter(os);
			
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (pw != null)
					pw.println(line);
				output.append(line);
			}
			
			if (pw != null)
				pw.flush();
		} catch (IOException ioe) {
			LogManager.logException(ioe);
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					//LogUtils.logException(e);
					LogManager.logException(e);
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					//LogUtils.logException(e);
					LogManager.logException(e);
				}
			}
		}
	}
	
	public String getOutput() {
		return output.toString();
	}

	
    public String getType() {
    	return type;
    }
	
}
