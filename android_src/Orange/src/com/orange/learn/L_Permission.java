package com.orange.learn;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class L_Permission
{
    class BCReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {

        }
    }

    class IntentFilterImpl extends IntentFilter
    {
    }
    

    @SuppressLint("NewApi")
    public static void test(Context context)
    {
    
        PackageManager pm = context.getPackageManager();
        CharSequence csPermissionGroupLabel;
        CharSequence csPermissionLabel;
        String[] permissions = new String[1];
        permissions[0] = "test";
        List<PackageInfo> packageInfos = pm.getPackagesHoldingPermissions(permissions, 0);
        {
        }
        List<PermissionGroupInfo> lstGroups = pm.getAllPermissionGroups(0);
        for (PermissionGroupInfo pgi : lstGroups)
        {
            csPermissionGroupLabel = pgi.loadLabel(pm);
            Log.e("perm", pgi.name + ": " + csPermissionGroupLabel.toString());

            try
            {
                List<PermissionInfo> lstPermissions = pm.queryPermissionsByGroup(pgi.name, 0);
                for (PermissionInfo pi : lstPermissions)
                {
                    csPermissionLabel = pi.loadLabel(pm);
                    Log.e("perm", "   " + pi.name + ": " + csPermissionLabel.toString());
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    private static TextView tv1;
    private static Context mContext; 
    public static View makeView(Context context)
    {
        mContext = context;
        ScrollView sView = new ScrollView(context);
        LinearLayout llLayout = new LinearLayout(context);
        llLayout.setOrientation(LinearLayout.VERTICAL);
        tv1 = new TextView(context);
        Button btn = new Button(context);
        btn.setText("打开gps");
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Settings.Secure.setLocationProviderEnabled(mContext.getContentResolver(), LocationManager.GPS_PROVIDER, true); 
            }
        });
        TextView tv = new TextView(context);
        
        LocationManager locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
      //定义
        LocationListener locationListener=new LocationListener(){
                //位置信息变化时触发
                public void onLocationChanged(Location location) {
                    String text = "";
                    text += ("时间："+location.getTime());
                    text += "\n";
                    text += ("经度："+location.getLongitude());
                    text += "\n";
                    text += ("纬度："+location.getLatitude());
                    text += "\n";
                    text += ("海拔："+location.getAltitude());
                    text += "\n";
                    tv1.setText(text);
                }
                //gps禁用时触发
                public void onProviderDisabled(String provider) {
 
                    String text =("当前GPS状态：禁用\n");
                    tv1.setText(text);
                }
                //gps开启时触发
                public void onProviderEnabled(String provider) {
                    String text =("当前GPS状态：开启\n");
                    tv1.setText(text);
                }
                //gps状态变化时触发
                public void onStatusChanged(String provider, int status,Bundle extras) {
                    String text = "";
                        if(status==LocationProvider.AVAILABLE){
                             text =("当前GPS状态：可见的\n");
                        }else if(status==LocationProvider.OUT_OF_SERVICE){
                             text =("当前GPS状态：服务区外\n");
                        }else if(status==LocationProvider.TEMPORARILY_UNAVAILABLE){
                             text =("当前GPS状态：暂停服务\n");
                        }
                        tv1.setText(text);
                }
        };
        //绑定监听，有4个参数
        //参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种，我们选用GPS，网络在此不做讨论
        //参数2，位置信息更新周期：
        //参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
        //参数4，监听
        //备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener); 
        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            // 得到自己的包名
            String pkgName = pi.packageName;

            PackageInfo pkgInfo = pm.getPackageInfo(pkgName, PackageManager.GET_PERMISSIONS);// 通过包名，返回包信息
            String sharedPkgList[] = pkgInfo.requestedPermissions;// 得到权限列表
            if (null != sharedPkgList)
            {
                for (int i = 0; i < sharedPkgList.length; i++)
                {
                    String permName = sharedPkgList[i];

                    PermissionInfo tmpPermInfo = pm.getPermissionInfo(permName, 0);// 通过permName得到该权限的详细信息
                    PermissionGroupInfo pgi = pm.getPermissionGroupInfo(tmpPermInfo.group, 0);// 权限分为不同的群组，通过权限名，我们得到该权限属于什么类型的权限。

                    tv.append(i + "-" + permName + "\n");
                    tv.append(i + "-" + pgi.loadLabel(pm).toString() + "\n");
                    tv.append(i + "-" + tmpPermInfo.loadLabel(pm).toString() + "\n");
                    tv.append(i + "-" + tmpPermInfo.loadDescription(pm).toString() + "\n");
                    tv.append("\n");

                }
            }
        }
        catch (NameNotFoundException e)
        {
            Log.e("##ddd", "Could'nt retrieve permissions for package");

        }
        llLayout.addView(tv1);
        llLayout.addView(btn);
        llLayout.addView(tv);
        sView.addView(llLayout);
        return sView;
    }

}
