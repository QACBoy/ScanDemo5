package com.example.scandemo5.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.scandemo5.Activity.Storage.MainActivity;
import com.example.scandemo5.Adapter.MsgShowScanAdapter;
import com.example.scandemo5.Data.ClintInfo;
import com.example.scandemo5.Data.UpLoad;
import com.example.scandemo5.MyApp;
import com.example.scandemo5.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.List;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBarUtils;
import fr.castorflex.android.smoothprogressbar.SmoothProgressDrawable;

/**
 * Created by Sam on 2017/9/17.
 */

public class Msg {

    private static long Sleep_time = 400; //ms

    public interface CallBack{  //弹窗回调
        void confirm(DialogPlus dialog);
    }

    public static void stopwait(){
        if(Global.dialog != null)Global.dialog.dismiss();
    }
    public static void wait(final Activity activity, final String title, final String msg){
        if(Global.dialog != null)Global.dialog.dismiss();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Global.dialog = DialogPlus.newDialog(activity)
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .setGravity(Gravity.CENTER)
                        .setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return 1;
                            }

                            @Override
                            public Object getItem(int position) {
                                return null;
                            }

                            @Override
                            public long getItemId(int position) {
                                return position;
                            }

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                convertView = activity.getLayoutInflater().inflate(R.layout.msg_wait,null);
                                ((TextView)convertView.findViewById(R.id.msg_wait_title)).setText(title);
                                ((TextView)convertView.findViewById(R.id.msg_wait_content)).setText(msg);
                                SmoothProgressBar mPocketBar = (SmoothProgressBar) convertView.findViewById(R.id.msg_bar);
                                mPocketBar.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(activity).interpolator(new AccelerateInterpolator()).build());
                                mPocketBar.setSmoothProgressDrawableColors(activity.getResources().getIntArray(R.array.wait_colors));
                                return convertView;
                            }
                        })
                        .setCancelable(false)
                        .create();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Global.dialog.show();
                    }
                });
            }
        }).start();
    }

    public static void showMsg(final Activity activity, final String title, final String msg, final CallBack callBack){
        if(Global.dialog != null)Global.dialog.dismiss();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Global.dialog = DialogPlus.newDialog(activity)
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .setGravity(Gravity.CENTER)
                        .setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return 1;
                            }

                            @Override
                            public Object getItem(int position) {
                                return null;
                            }

                            @Override
                            public long getItemId(int position) {
                                return position;
                            }

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                convertView = activity.getLayoutInflater().inflate(R.layout.show_msg,null);
                                ((TextView)convertView.findViewById(R.id.title_msg)).setText(title);
                                ((TextView)convertView.findViewById(R.id.content_msg)).setText(msg);
                                return convertView;
                            }
                        })
                        .setFooter(R.layout.msg_foot)
                        .setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(DialogPlus dialog, View view) {
                                switch (view.getId()){
                                    case R.id.msg_footer_close_button://点击关闭按钮
                                        dialog.dismiss();
                                        break;
                                    case R.id.msg_footer_confirm_button://点击保存按钮
                                        if(callBack != null)
                                            callBack.confirm(dialog);
                                        else
                                            dialog.dismiss();
                                        break;
                                }
                            }
                        })
                        .create();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Global.dialog.show();
                    }
                });
            }
        }).start();
    }

    public static void showMsg(final Activity activity,final String title, final String msg, final Bitmap image, final View.OnClickListener callBack){
        if(Global.dialog != null)Global.dialog.dismiss();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Global.dialog = DialogPlus.newDialog(activity)
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .setGravity(Gravity.TOP)
                        .setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return 1;
                            }

                            @Override
                            public Object getItem(int position) {
                                return null;
                            }

                            @Override
                            public long getItemId(int position) {
                                return position;
                            }

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                convertView = activity.getLayoutInflater().inflate(R.layout.show_msg,null);
                                ((TextView)convertView.findViewById(R.id.title_msg)).setText(title);
                                ((TextView)convertView.findViewById(R.id.content_msg)).setText(msg);
                                ((ImageView)convertView.findViewById(R.id.image_mag)).setImageBitmap(image);
                                return convertView;
                            }
                        })
                        .create();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Global.dialog.show();
                    }
                });
            }
        }).start();
    }

    public static void showFunciton(final Activity activity, final String title, final List dates, final OnItemClickListener callBack){
        if(Global.dialog != null)Global.dialog.dismiss();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                View header = LayoutInflater.from(activity).inflate(R.layout.msg_header,null,false);
                ((TextView)header.findViewById(R.id.msg_header_title)).setText(title);
                Global.dialog = DialogPlus.newDialog(activity)
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .setGravity(Gravity.CENTER)
                        .setHeader(header)
                        .setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return dates.size();
                            }

                            @Override
                            public Object getItem(int position) {
                                return dates.get(position);
                            }

                            @Override
                            public long getItemId(int position) {
                                return position;
                            }

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                convertView  = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.noimage_item_view, parent, false);
                                Log.d("12311", "getView: " + dates.get(position).getClass());;
                                if(ClintInfo.class == dates.get(position).getClass()) {
                                    ClintInfo data = (ClintInfo) dates.get(position);
                                    ((TextView) convertView.findViewById(R.id.title)).setText(data.client_no);
                                    ((TextView) convertView.findViewById(R.id.author)).setText(data.client_name);
                                }
                                else if(SQLite.Goods.class == dates.get(position).getClass()){
                                    SQLite.Goods data = (SQLite.Goods) dates.get(position);
                                    ((TextView) convertView.findViewById(R.id.title)).setText(data.goods_no);
                                    ((TextView) convertView.findViewById(R.id.author)).setText(data.goods_name);
                                }
                                return convertView;
                            }
                        })
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                if(callBack != null)
                                    callBack.onItemClick(dialog,item,view,position);
                            }
                        })
                        .create();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Global.dialog.show();
                    }
                });
            }
        }).start();
    }

    //连续扫描
    private static boolean ifSave;

    public static void showSacn(final Activity activity, final SQLite.Goods goods){
        if(Global.dialog != null)Global.dialog.dismiss();
        ifSave = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Global.dialog = DialogPlus.newDialog(activity)
                        .setGravity(Gravity.CENTER)
                        .setAdapter(new MsgShowScanAdapter(activity,goods))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .setFooter(R.layout.dialog_foot).setContentBackgroundResource(R.color.balck).setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(DialogPlus dialog, View view) {
                                switch (view.getId()){
                                    case R.id.footer_close_button://点击关闭按钮
                                        ifSave = false;
                                        dialog.dismiss();
                                        break;
                                    case R.id.footer_confirm_button://点击保存按钮
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        })
                        .setOnBackPressListener(new OnBackPressListener() {
                            @Override
                            public void onBackPressed(DialogPlus dialogPlus) {
                                dialogPlus.dismiss();
                            }
                        })
                        .setOnDismissListener(new OnDismissListener() {
                            @Override
                            public void onDismiss(DialogPlus dialog) {
                                if(ifSave)
                                    addtoMainactivity(dialog);
                                Global.setTYPE_SCA(Global.ScanType.rk_GoodsNo);
                            }
                        })
                        .create();
                MainActivity.mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Global.dialog.show();
                    }
                });
            }
        }).start();
    }

    private static void addtoMainactivity(DialogPlus dialog){
        String barcode = ((EditText) dialog.getHolderView().findViewById(new Integer(2)).findViewById(R.id.handle_item_value)).getText().toString();
        String goods_no = ((EditText) dialog.getHolderView().findViewById(new Integer(0)).findViewById(R.id.handle_item_value)).getText().toString();
        String goods_name = ((EditText) dialog.getHolderView().findViewById(new Integer(1)).findViewById(R.id.handle_item_value)).getText().toString();
        String sl = ((EditText)(dialog.getHolderView().findViewById(R.id.ids_quantity).findViewById(R.id.handle_item_value))).getText().toString();
        String pc = ((EditText)(dialog.getHolderView().findViewById(R.id.ids_LOT).findViewById(R.id.handle_item_value))).getText().toString();
        String kw = ((EditText)(dialog.getHolderView().findViewById(R.id.ids_location_no).findViewById(R.id.handle_item_value))).getText().toString();
        String sc = ((EditText)(dialog.getHolderView().findViewById(R.id.ids_MFG).findViewById(R.id.handle_item_value))).getText().toString();
        String dq = ((EditText)(dialog.getHolderView().findViewById(R.id.ids_EXP).findViewById(R.id.handle_item_value))).getText().toString();
        MainActivity.mainActivity.addScanDataEnd(new UpLoad.ScanData(barcode, goods_no, goods_name, sc, dq, pc,kw, sl));
    }
}
