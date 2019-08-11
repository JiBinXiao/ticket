package pri.xjb.ticket.security.encrypt.core;



import pri.xjb.ticket.security.encrypt.utils.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 *
 * Created by xjb on 2019/1/22
 */
public class EncryptionReqestWrapper extends HttpServletRequestWrapper {
	
	private byte[] requestBody = new byte[0];
	
	public EncryptionReqestWrapper(HttpServletRequest request) {
		super(request);
		try {
			requestBody = StreamUtils.copyToByteArray(request.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }
 
            @Override
            public boolean isFinished() {
                return false;
            }
 
            @Override
            public boolean isReady() {
                return true;
            }
 
            @Override
            public void setReadListener(ReadListener listener) {
 
            }
        };
	}

	public String getRequestData() {
		return new String(requestBody);
	}
	
	public void setRequestData(String requestData) {
		this.requestBody = requestData.getBytes();
	}
}
