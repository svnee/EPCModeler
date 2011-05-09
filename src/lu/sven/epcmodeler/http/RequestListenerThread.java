package lu.sven.epcmodeler.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;

import org.apache.http.HttpResponseInterceptor;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;
import org.apache.log4j.Logger;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;

public class RequestListenerThread extends Thread {
    private final ServerSocket serversocket;
    private final HttpParams params; 
    private final HttpService httpService;
    public Graph<Node,Edge> graph;
    public Layout<Node, Edge> layout;
    
    public RequestListenerThread(int port, Graph<Node, Edge> g, Layout<Node, Edge> l) throws IOException {
    	this.graph = g;
    	this.layout = l;
    	this.serversocket = new ServerSocket(port);
        this.params = new SyncBasicHttpParams();
        this.params
            .setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000)
            .setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8 * 1024)
            .setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false)
            .setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true)
            .setParameter(CoreProtocolPNames.ORIGIN_SERVER, "EPCModelerServer/1.0");

        // Set up the HTTP protocol processor
        HttpProcessor httpproc = new ImmutableHttpProcessor(new HttpResponseInterceptor[] {
                new ResponseDate(),
                new ResponseServer(),
                new ResponseContent(),
                new ResponseConnControl()
        });
        
        // Set up request handlers
        HttpRequestHandlerRegistry reqistry = new HttpRequestHandlerRegistry();
        reqistry.register("*", new HttpReqHandler(this.graph, this.layout));
        
        // Set up the HTTP service
        this.httpService = new HttpService(
                httpproc, 
                new DefaultConnectionReuseStrategy(), 
                new DefaultHttpResponseFactory(),
                reqistry,
                this.params);
    }
    
    public void run() {
    	Logger logger = Logger.getRootLogger();
    	logger.info("Listening on port " + this.serversocket.getLocalPort());
        while (!Thread.interrupted()) {
            try {
                // Set up HTTP connection
                Socket socket = this.serversocket.accept();
                DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
                logger.info("Incoming connection from " + socket.getInetAddress());
                conn.bind(socket, this.params);

                // Start worker thread
                Thread t = new WorkerThread(this.httpService, conn);
                t.setDaemon(true);
                t.start();
            } catch (InterruptedIOException ex) {
                break;
            } catch (IOException e) {
                logger.error("I/O error initialising connection thread: " 
                        + e.getMessage());
                break;
            }
        }
    }
}
