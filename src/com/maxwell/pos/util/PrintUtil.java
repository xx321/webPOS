package com.maxwell.pos.util;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class PrintUtil {

	/**
	 * @param args
	 */
	static CommPortIdentifier portId;
	static Enumeration portList;
	static SerialPort serialPort;
	static OutputStream outputStream;
	
	private static Timer timer;

	public static void main(String[] args) throws IOException,
			UnsupportedCommOperationException, PortInUseException {

		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM3")) {
					try {
						serialPort = (SerialPort) portId.open("SimpleWriteApp",
								2000);

						outputStream = serialPort.getOutputStream();

						serialPort.setSerialPortParams(9600,
								SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
								SerialPort.PARITY_NONE);

						PrintWriter printWriter = new PrintWriter(
								serialPort.getOutputStream());

						printWriter.println("系統股份有限公司");
						printWriter.println("統編：123456789");
						printWriter.println("電話:(02)8227-1111");
						printWriter.println("發票地址XXXXXXXX");						
						printWriter.println("日:01/07/16 時:18-00");
						printWriter.println("機號:001 序號:01 頁:1");
						printWriter.println("可口可樂 2瓶 17元");
						printWriter.println("長壽香煙 1包 25元");
						printWriter.println("大享堡 1份 20元");
						printWriter.println("味全鮮奶 2瓶 17元");
						printWriter.println("總計             100元");
						printWriter.write(0x1d);
						printWriter.write(0x48);
						printWriter.write(0x50);
						
						printWriter.write(0x1d);
						printWriter.write(0x68);
						printWriter.write(0x50);
						
						printWriter.write(0x1b);
						printWriter.write(0x0c);
						printWriter.write(0x1b);
						printWriter.write(0x69);
						printWriter.flush();
						TimerTask timerTask = new TimerTask() {

							@Override
							public void run() {
								serialPort.close();
								serialPort.close();
								if (timer !=null)
									timer.cancel();
							}
						};
						
						timer = new Timer();
						timer.schedule(timerTask, 2000);
						
						

					} catch (IOException e) {

					}

				}

			}
		}

	}

}
