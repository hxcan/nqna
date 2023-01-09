package com.stupidbeauty.nqna;

import com.stupidbeauty.shutdownat2100.helper.ShutDownAt2100Manager;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.protobuf.InvalidProtocolBufferException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.util.Log;
import com.stupidbeauty.upgrademanager.listener.PackageNameUrlMapDataListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.io.File;
import android.net.Uri;
import com.stupidbeauty.upgrademanager.UpgradeManager;

/**
 * @author Hxcan
 *
 */
public class MacroOperations
{
	private final Context context; //!<The context.
	private static final String TAG="MacroOperations"; //!<输出调试信息时使用的标记。


	private  InetAddress group; //!<广播组地址。
	private MulticastSocket multiSocket; //!<多播套接字。
	private static final int PORT = 11500; //!<多播组的端口号。

	/**
	 * 请求扫描照片。
	 * @param apkFilePath 照片文件的完整路径。
	 */
	public void requestScanPhoto(String apkFilePath)
	{

		Log.d(TAG, "requestInstallApk"); //Debug.

		scanFile(apkFilePath); //要求扫描 。

	} //public void requestScanPhoto(String apkFilePathJString)

	/**
	 * 要求扫描照片。
	 * @param path 照片文件的路径。
	 */
	private void scanFile(String path)
	{

		MediaScannerConnection.scanFile(context,
				new String[] { path }, null,
				new MediaScannerConnection.OnScanCompletedListener() {

					public void onScanCompleted(String path, Uri uri) {
						Log.i("TAG", "Finished scanning " + path);
					}
				});
	} //private void scanFile(String path)

	/**
	 * Request install apk.
	 * @param apkFilePath Apk file path.
	 */
	public void requestInstallApk(String apkFilePath)
	{
		String className= apkFilePath; //要提交的文字内容。

		String Result = ""; // 结果。

		String maskFileName=""; //获取掩码图片文件名。

		PackageManager packageManager=context.getPackageManager(); //获取软件包管理器。

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(apkFilePath)), "application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	} //private void requestInstallApk(String textContent, String transactionId)

	/**
	 * Request install apk.
	 * @param apkFilePath Apk file path.
	 */
	public void checkUpgrade()
	{
		UpgradeManager upgradeManager=new UpgradeManager(context); // Create upgrade manager.
      
		upgradeManager.checkUpgrade(); // Check upgrade.
	} //private void requestInstallApk(String textContent, String transactionId)

	/**
	 * Start friend shutdownat2100.
	 */
	public void startFriendShutDownAt2100()
	{
//     Chen xin
    
    ShutDownAt2100Manager shutDownAt2100Manager= null; // 管理与21点关机之间的事务。
    shutDownAt2100Manager=new ShutDownAt2100Manager(context);
    shutDownAt2100Manager.checkShutDownTime(); // Check shut down time.
	} //private void requestInstallApk(String textContent, String transactionId)

	/**
	 * 构造函数。
	 * @param context 服务上下文。
	 */
	public MacroOperations(Context context)
	{
		super();
		Log.d(TAG,"MacroOperations, 70."); //Debug.

		this.context=context; //Remember context.
		Log.d(TAG,"MacroOperations, 74."); //Debug.
	} //public MacroOperations()

	/**
	 * 加入多播组。
	 */
	private void joinMulticastGroup() 
	{
		
		try {
			//224.0.0.0~239.255.255.255

//Table 1 Multicast Address Range Assignments
//
//Description
//Range
//Reserved Link Local Addresses
//
//224.0.0.0/24
//
//Globally Scoped Addresses
//
//224.0.1.0 to 238.255.255.255
//
//Source Specific Multicast
//
//232.0.0.0/8
//
//GLOP Addresses
//
//233.0.0.0/8
//
//Limited Scope Addresses
//
//239.0.0.0/8
//			
			group = InetAddress.getByName("239.173.40.5");
			multiSocket=new MulticastSocket(PORT);
			multiSocket.joinGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} //private void joinMulticastGroup()




}
