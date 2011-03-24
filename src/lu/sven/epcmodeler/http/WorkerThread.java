package lu.sven.epcmodeler.http;

import java.io.IOException;

import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpServerConnection;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpService;
import org.apache.log4j.Logger;

public class WorkerThread extends Thread {
	Logger logger = Logger.getRootLogger();
    private final HttpService httpservice;
    private final HttpServerConnection conn;
    
    public WorkerThread(
            final HttpService httpservice, 
            final HttpServerConnection conn) {
        super();
        this.httpservice = httpservice;
        this.conn = conn;
    }
    
    public void run() {
        logger.info("New connection thread");
        HttpContext context = new BasicHttpContext(null);
        try {
            while (!Thread.interrupted() && this.conn.isOpen()) {
                this.httpservice.handleRequest(this.conn, context);
            }
        } catch (ConnectionClosedException ex) {
            logger.error("Client closed connection");
        } catch (IOException ex) {
            logger.error("I/O error: " + ex.getMessage());
        } catch (HttpException ex) {
            logger.error("Unrecoverable HTTP protocol violation: " + ex.getMessage());
        } finally {
            try {
                this.conn.shutdown();
            } catch (IOException ignore) {}
        }
    }

}
