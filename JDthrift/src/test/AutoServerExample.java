package test;

import org.apache.athrift.AutoProcessorGenerator;
import org.apache.athrift.AutoThriftGenerator;
import org.apache.athrift.service.ThriftServicesParser;
import org.apache.thrift.TBaseProcessor;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class AutoServerExample {
	public static void main(String[] args)
	{
		try {
		    // ��һ��: �û��Լ���д��ҵ�����
		    AutoTestHandler handler = new AutoTestHandler();
		    
		    // �ڶ���: ���ɶ�Ӧthrift�����ļ��������棬�������Զ�TProcessorʵ��
		    AutoThriftGenerator tmpAutoThriftGenerator = new AutoThriftGenerator();
		    ThriftServicesParser tmpThriftServicesParser =
		        tmpAutoThriftGenerator.generateAutoThrift("D:/����/thrift", "firstTest.thrift");
		    AutoProcessorGenerator tmpAutoProcessorGenerator = new AutoProcessorGenerator();
		    TBaseProcessor tmpProcessor = tmpAutoProcessorGenerator.generate("UserStorage", handler, tmpThriftServicesParser);
			
		    // ������: ��������
		    TServerTransport serverTransport = new TServerSocket(9090);
	        TServer server = new TSimpleServer(new Args(serverTransport).processor(tmpProcessor));
	        System.out.println("Starting the simple server...");
	        server.serve();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
