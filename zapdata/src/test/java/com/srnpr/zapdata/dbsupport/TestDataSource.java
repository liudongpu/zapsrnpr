package com.srnpr.zapdata.dbsupport;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopBase;
import com.srnpr.zapdata.dbdo.DbUp;

public class TestDataSource extends TestHelper {

	@Test
	public void testSource() {
		
	
		
		main();
		
		
		
		
	}
	public  void main() {  
		
	
		
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");  
        XADataSource xaDs1=getDataSource("jdbc:mysql://192.168.1.221:3306/zapdata", "zjwc", "ssssss");  
        XAConnection xaCon1=null;  
        XAResource xaRes1 = null;  
        Connection conn1=null;  
        Statement stmt1 =null;  
          
          
        XADataSource xaDs2=getDataSource("jdbc:mysql://192.168.1.221:3306/test", "zjwc", "ssssss");  
        XAConnection xaCon2=null;  
        XAResource xaRes2 = null;  
        Connection conn2=null;  
        Statement stmt2 =null;  
          
        int ret1=0;  
        int ret2=0;  
         
        Xid xid1=new MysqlXid(new byte[]{0x01}, new byte[]{0x02},100);  
        Xid xid2=new MysqlXid( new byte[]{0x01}, new byte[]{0x03},100);  
        try {             
            xaCon1 = getXAConnetion(xaDs1);  
            conn1= getConnection(xaCon1);  
            stmt1=conn1.createStatement();  
            xaRes1=xaCon1.getXAResource();  
              
            xaCon2 = getXAConnetion(xaDs2);  
            conn2= getConnection(xaCon2);  
            stmt2=conn2.createStatement();  
            xaRes2=xaCon2.getXAResource();  
              
            xaRes1.start(xid1, XAResource.TMNOFLAGS);   
            stmt1.execute("INSERT INTO test_call (zid,uid) VALUES (21,'aaabbb')");  
            xaRes1.end(xid1, XAResource.TMSUCCESS);  
              
            bLogTest(xaRes2.isSameRM(xaRes1));
            if (xaRes2.isSameRM(xaRes1)) {  
                xaRes2.start(xid1, XAResource.TMNOFLAGS);   
                stmt2.execute("INSERT INTO test_call (zid,uid) VALUES (21,'aaabbb')");  
                xaRes2.end(xid1, XAResource.TMSUCCESS);  
            }else{  
                  
                xaRes2.start(xid2, XAResource.TMNOFLAGS);   
                stmt2.execute("INSERT INTO test_call (zid,uid) VALUES (22,'aaabbb')");  
                xaRes2.end(xid2, XAResource.TMSUCCESS);  
                ret1=xaRes2.prepare(xid2);  
//              if (ret==XAResource.XA_OK) {  
//                  xaRes2.commit(xid2, false);  
//              }  
            }  
        
            ret2 = xaRes1.prepare(xid1);   
            if (ret1 == XAResource.XA_OK&&ret2==XAResource.XA_OK) {  
             //xaRes1.commit(xid1, false);             
             xaRes1.rollback(xid1);       
             if (xaRes2.isSameRM(xaRes1)) {  
                xaRes2.rollback(xid1);  
             }else{  
                 xaRes2.rollback(xid2);  
             }  
            }
            
        	
            
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (XAException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
      
  
    private  XADataSource getDataSource(String url,String user,String password) {  
        // TODO Auto-generated method stub  
        MysqlXADataSource dataSource = new MysqlXADataSource();  
        dataSource.setUrl(url);  
        dataSource.setUser(user);  
        dataSource.setPassword(password);  
        return dataSource;  
    }     
      
  
    public  XAConnection getXAConnetion(XADataSource dataSource) {  
        XAConnection XAConn = null;  
        try {  
            XAConn = dataSource.getXAConnection();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return XAConn;  
    }  
  
    public  Connection getConnection(XAConnection XAConn) {  
        Connection conn = null;  
        try {  
            conn = XAConn.getConnection();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
  
    public  void closeConnection(Connection conn) {  
        try {  
            conn.close();  
        } catch (SQLException e) {  
            System.out.println("连接关闭失败");  
        }  
    }  

}
