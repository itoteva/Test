import org.zeromq.ZMQ;


public class Server extends Thread{

	@Override
	public void run() {
		ZMQ.Context context = ZMQ.context(1);

		// Socket to talk to clients
		ZMQ.Socket responder = context.socket(ZMQ.REP);
		responder.bind("tcp://*:5555");

		while (!Thread.currentThread().isInterrupted()) {
			// Wait for next request from the client
			String request = responder.recvStr();
//			System.out.println("Server Received " + request);

			// Do some 'work'
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Send reply back to client
			responder.send(request, 0);
		}
		responder.close();
		context.term();	
	}	
}
