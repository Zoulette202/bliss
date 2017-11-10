package com.bliss.agent;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public class OsCheck {
	
	public static float ram () {
		float ram=0;
		float totalRam=0;
		float freeRame=0;

        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
                     method.setAccessible(true);
                     if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers())) {
                                  Object value;
                                  try {
                                                value = method.invoke(operatingSystemMXBean);
                                  } catch (Exception e) {
                                                value = e;
                                  }
                                  if (method.getName().equals("getTotalPhysicalMemorySize"))
                                                totalRam = Float.parseFloat(value.toString()) / 1024 / 1024 / 1024;
                                  else if (method.getName().equals("getFreePhysicalMemorySize"))
                                                freeRame = Float.parseFloat(value.toString()) / 1024 / 1024 / 1024;
                     }
        }
        ram=100 - ((freeRame / totalRam) * 100);
		
		return ram;
		
	}

	public  static float disque() {
		float disque=0;

        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        Map<String, Float> disk = new HashMap<>();
        for (File root : roots) {
                     try {
                                  disque =(100 - ((float) root.getFreeSpace() / (float) root.getTotalSpace()) * 100);
                     } catch (Exception e) {
                                  System.out.println("0");
                     }
        }
    	return disque;    
	}

	public static boolean error(float val,String name) {
		boolean statut = true;
		if(name.equals("ram"))
		{
			if(val>90)
			{
				statut=false;
			}
		}
		else if(name.equals("disque"))
		{
			if(val>70)
			{
				statut=false;
			}
			
		}
		return statut;
	}	
}