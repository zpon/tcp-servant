package dk.zpon.tcpservant.protocol.jsonrpc;

import dk.zpon.tcpservant.requesthandlers.reflector.IReflectorService;


/**
 * Class responsible for providing the handlers which handles connections.
 * 
 * @author sjuul
 */
public interface JsonRpcServiceFactoryInterface {

	IReflectorService getNewConnectionHandler();
}
