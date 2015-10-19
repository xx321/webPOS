package com.maxwell.pos.test;

import java.io.IOException;
import java.util.Enumeration;
import javax.comm.CommPortIdentifier;
 
public class Ghsdgfds {
 
    public static void main(String[] args) throws IOException, InterruptedException {
       Enumeration en = CommPortIdentifier.getPortIdentifiers();
       CommPortIdentifier portId;
       while(en.hasMoreElements()){
           portId = (CommPortIdentifier) en.nextElement();
           if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL)
           {
        	   System.out.println(portId.getName());
           }
       }
    }
}