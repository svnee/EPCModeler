package lu.sven.epcmodeler.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class HTTPClient {
	public String hostname = "localhost";
	public Integer port = 1337;
	private static Logger logger = Logger.getRootLogger();
	
	   public HTTPClient(String hostn, String target, final String POSTContent) throws Exception {
		   HttpClient httpclient = new DefaultHttpClient();
		   ContentProducer cp = new ContentProducer() {
			    public void writeTo(OutputStream outstream) throws IOException {
			        Writer writer = new OutputStreamWriter(outstream, "UTF-8");
			        writer.write(POSTContent);
			        writer.flush();
			    }
			};
			
			HttpEntity entity = new EntityTemplate(cp);
			HttpPost httppost = new HttpPost("http://"+hostn+":"+this.port+target);
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			logger.debug(response.toString());
	   }
	    
	}
