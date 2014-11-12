import org.zeromq.ZMQ;

public class Client extends Thread {

	@Override
	public void run() {

		ZMQ.Context context = ZMQ.context(1);
		// Socket to talk to server
		System.out.println("Connecting to hello world server…");

		ZMQ.Socket requester = context.socket(ZMQ.REQ);
		requester.connect("tcp://localhost:5555");

		for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
			String request = "Hello " + requestNbr;
			System.out.println("Sending " + request);
			requester.send(request.getBytes(), 0);

			byte[] reply = requester.recv(0);
			System.out.println("Received " + new String(reply));
		}
		requester.close();
		context.term();

	}

}
