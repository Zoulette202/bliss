package com.bliss.agent;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.Math;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;




public class InfoPC {
//	private static final long MEGABYTE = 1024L * 1024L;	
	public long diskSize; 
	public String userName;  
	public long maxMemory; 
	public long memorySize;
	public long availableProcessors; 
	public long freePhysicalMemorySize; 
	public Runtime runtime; 
	public long freeMemory; 
	public long totalMemory; 
	public double memory; 
	public InetAddress ip;
	
	public InfoPC() {
		this.diskSize = (long) (new File("/").getTotalSpace() * (Math.pow(10, -9)));
		this.userName = System.getProperty("user.name"); 
		this.maxMemory = Runtime.getRuntime().maxMemory(); 
		this.memorySize = (long) (((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize() * (Math.pow(10, -9)));
		this.availableProcessors = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getAvailableProcessors();
		this.freePhysicalMemorySize = (long) (((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize() * (Math.pow(10, -9)));
		this.runtime = Runtime.getRuntime();
		this.freeMemory =  this.runtime.freeMemory();
		this.totalMemory =  this.runtime.totalMemory(); 
		this.memory =   ((runtime.totalMemory() - runtime.freeMemory()) * (Math.pow(10, -9))) ;
		try {
			this.ip = Inet4Address.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printUsage() {
		  OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		  for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
		    method.setAccessible(true);
		    if (method.getName().startsWith("get")
		        && Modifier.isPublic(method.getModifiers())) {
		            Object value;
		        try {
		            value = method.invoke(operatingSystemMXBean);
		        } catch (Exception e) {
		            value = e;
		        }
		        System.out.println(method.getName() + " = " + value);
		    } 
		  } 
	}
	
	public static void printRAMandDiskSize() {   
		InfoPC os = new InfoPC();
        System.out.println("Size of C: = "+ os.diskSize+" Go");
        System.out.println("User Name = "+ os.userName);

        System.out.println("RAM Size = "+ os.memorySize+" Go");

//        System.out.println("Total memory : "+totalMemory);
//        System.out.println("Free memory : "+freeMemory);
//        System.out.println("Used memory is bytes: " + memory);
//        System.out.println("Used memory is megabytes: "
//                + bytesToMegabytes(memory)); 
        
        System.out.println("Free physical memory size is : "+ os.freePhysicalMemorySize+" Go");
        System.out.println("AvailableProcessors : "+ os.availableProcessors); 
	}
	
	public static void main(String[] argrs) {
//		printUsage();
		printRAMandDiskSize();
		
//		printRAMUsage();
	}
	
}
