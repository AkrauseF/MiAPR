package com.example.miapr;

import android.util.Log;

import java.io.IOException;

public class VerificaConexion {
    String url;

    public VerificaConexion(String url){
        this.url= url;
    }

    public boolean executeCommand(){
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 "+url);
            int mExitValue = mIpAddrProcess.waitFor();
            System.out.println(" mExitValue "+mExitValue);
            if(mExitValue==0){
                return true;
            }else{
                return false;
            }
        }
        catch (InterruptedException ignore)
        {
            ignore.printStackTrace();
            System.out.println(" Exception:"+ignore);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(" Exception:"+e);
        }
        return false;
    }

    public void testExecuteCommand() {

        String[] ips = {"192.168.0.5", "192.168.0.5", "192.169.0.3", "192.10.10.10", "192.179.0.9"};

        for ( int i=0; i<ips.length; i++) {

            Runtime runtime = Runtime.getRuntime();
            try {
                Process mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 " + ips[i]);
                int mExitValue = mIpAddrProcess.waitFor();
                System.out.println(" mExitValue " + mExitValue);
                if (mExitValue == 0) {
                    Log.i("Ver-Conect_IP: ", ips[i]);
                    Log.i("Ver-Conect: ", "Conexion ESTABLECIDA ");
                } else {
                    Log.i("Ver-Conect_IP: ", ips[i]);
                    Log.i("Ver-Conect: ", "Conexion FALLIDA");

                }
            } catch (InterruptedException ignore) {
                ignore.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
