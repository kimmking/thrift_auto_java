package test;

import java.util.ArrayList;

import org.apache.athrift.AutoClient;
import org.apache.athrift.AutoThriftGenerator;
import org.apache.athrift.CommonArgs;
import org.apache.athrift.CommonException;
import org.apache.athrift.CommonStruct;
import org.apache.athrift.service.ThriftServicesParser;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


public class AutoClientExample {
    public static void main(String[] args) {
        try {
            // ��һ��: ����thrift��ͨѶ��·
            TTransport transport = null;
            transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);

            // �ڶ���: ���ɶ�Ӧthrift�����ļ��������棬�������Զ�Clientʵ��
            AutoThriftGenerator tmpAutoThriftGenerator = new AutoThriftGenerator();
            ThriftServicesParser tmpThriftServicesParser =
                    tmpAutoThriftGenerator.generateAutoThrift("D:/����/thrift", "firstTest.thrift");
            AutoClient client = new AutoClient(protocol, "UserStorage", tmpThriftServicesParser);

            // ������: ʹ���Զ�Clientʵ��,���þ����Զ�˷���(�û��Լ���д��ҵ�����)
            CommonArgs tmpCommonArgs = new CommonArgs();
            tmpCommonArgs.addOneValue("uid", 10);
            tmpCommonArgs.addOneValue("test", "test123");
            ArrayList tmpList = (ArrayList) client.sendRequest("retrieve", tmpCommonArgs);
            System.out.println("�������˷���:"+tmpList.size());
            
            // ���Ĳ�: �ر�ͨѶ��·
            transport.close();
        }
        catch (TException x) {
            x.printStackTrace();
        }
        catch (CommonException ae) {
            ae.printStackTrace();
        }
    }
}