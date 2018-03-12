package com.longke.shot;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.longke.shot.entity.Info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.attr.id;

/**
 * 作者：$ longke on 2018/2/9 08:42
 * 邮箱：373497847@qq.com
 */

public class MusicService extends Service implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{

    private static final String TAG = "MusicService";
    private List<Info.DataBean.ShootDetailListBean> mMusic_list = new ArrayList<>();
    private Messenger mMessenger;
    private static MediaPlayer mPlayer;
    private MusicBroadReceiver receiver;
    private int mCurrentPosition;
    private int mPosition;
    public static int playMode = 2;//1.单曲循环 2.列表循环 0.随机播放
    private Random mRandom;
    public static int prv_position;
    private Message mMessage;
    private static boolean isLoseFocus;
    private NotificationManager notificationManager;
    private boolean isFrist=true;

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: musicService");
        regFilter();
        initPlayer();

        //创建audioManger
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mRandom = new Random();
        super.onCreate();
    }

    private void initPlayer() {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnErrorListener(this);//资源出错
        mPlayer.setOnPreparedListener(this);//资源准备好的时候
        mPlayer.setOnCompletionListener(this);//播放完成的时候
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            mMusic_list = (List<Info.DataBean.ShootDetailListBean>) intent.getSerializableExtra("music_list");
            mMessenger = (Messenger) intent.getExtras().get("messenger");
            mPosition = SpTools.getInt(getApplicationContext(), "music_current_position", 0);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: musicService");
        cancelMusic();
        if (receiver != null) {
            getApplicationContext().unregisterReceiver(receiver);
        }
        //stopSelf();
    }

    private void cancelMusic() {
        notificationManager.cancel(Constants.NOTIFICATION_CEDE);
        mMessage = Message.obtain();
        mMessage.what = Constants.MSG_CANCEL;
       /* try {
            mMessenger.send(mMessage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        System.out.println("service : OnError");
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_NEXT);
        sendBroadcast(intent);
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

        mPlayer.start();//开始播放

        if (mMessenger != null) {
            sentPreparedMessageToMain();
            sentPositionToMainByTimer();
        }
    }

    private void sentPreparedMessageToMain() {
        Message mMessage = new Message();
        mMessage.what = Constants.MSG_PREPARED;
        mMessage.arg1 = mPosition;
        mMessage.obj = mPlayer.isPlaying();
        try {
            //发送播放位置
            mMessenger.send(mMessage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void sentPlayStateToMain() {
        mMessage = Message.obtain();
        mMessage.what = Constants.MSG_PLAY_STATE;
        mMessage.obj = mPlayer.isPlaying();
       /* try {
            //发送播放状态
            mMessenger.send(mMessage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }

    private void sentPositionToMainByTimer() {
        /*ThreadPoolUtil.getScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mPlayer.isPlaying()) {
                        //1.准备好的时候.告诉activity,当前歌曲的总时长
                        int currentPosition = mPlayer.getCurrentPosition();
                        int totalDuration = mPlayer.getDuration();
                        mMessage = Message.obtain();
                        mMessage.what = Constants.MSG_PROGRESS;
                        mMessage.arg1 = currentPosition;
                        mMessage.arg2 = totalDuration;
                        //2.发送消息
                        mMessenger.send(mMessage);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);*/
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_NEXT);
        sendBroadcast(intent);
    }

    /**
     * 播放
     */
    private void play(int position) {
        if (mPlayer != null && mMusic_list.size() > 0) {
            mPlayer.reset();
            try {
                if(position>=mMusic_list.size()-1){
                    return;
                }
                int id=mMusic_list.get(position).getScore();
                int fd = 0;
                switch (id){
                    case 0:
                        fd=R.raw.f0;
                        break;
                    case  1:
                        fd=R.raw.f1;
                       break;
                    case  2:
                        fd=R.raw.f2;
                        break;
                    case  3:
                        fd=R.raw.f3;
                        break;
                    case  4:
                        fd=R.raw.f4;
                        break;
                    case  5:
                        fd=R.raw.f5;
                        break;
                    case  6:
                        fd=R.raw.f6;
                        break;
                    case  7:
                        fd=R.raw.f7;
                        break;
                    case  8:
                        fd=R.raw.f8;
                        break;
                    case  9:
                        fd=R.raw.f9;
                        break;
                    case  10:
                        fd=R.raw.f10;
                        break;

                }
                AssetFileDescriptor file = getResources().openRawResourceFd(fd);

                mPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                            file.getLength());

                mPlayer.prepareAsync();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 暂停
     */
    private void pause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mCurrentPosition = mPlayer.getCurrentPosition();
            mPlayer.pause();
            sentPlayStateToMain();
        }
    }

    /**
     * 注册广播
     */
    private void regFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_LIST_ITEM);
        intentFilter.addAction(Constants.ACTION_PAUSE);
        intentFilter.addAction(Constants.ACTION_PLAY);
        intentFilter.addAction(Constants.ACTION_NEXT);
        intentFilter.addAction(Constants.ACTION_PRV);
        intentFilter.addAction(Constants.ACTION_CLOSE);
        intentFilter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        intentFilter.setPriority(1000);
        if (receiver == null) {
            receiver = new MusicBroadReceiver();
        }
        getApplicationContext().registerReceiver(receiver, intentFilter);
    }

    /**
     * 广播接收者
     */
    public class MusicBroadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Constants.ACTION_LIST_ITEM:
                    Log.i(TAG, "onReceive: ACTION_LIST_ITEM");
                    //点击左侧菜单
                    mPosition=0;
                    mMusic_list = (List<Info.DataBean.ShootDetailListBean>) intent.getSerializableExtra("music_list");

                    if (mPlayer != null) {
                        if(!mPlayer.isPlaying()){
                            mPlayer.start();
                        }
                    }
                    /*if (mPlayer != null) {
                        if(!mPlayer.isPlaying()){
                            mPlayer.start();
                        }
                    }else {
                        initPlayer();
                        play(mPosition);
                    }*/
                   // mPosition = intent.getIntExtra("position", 0);
                   // play(mPosition);
                    break;
                case Constants.ACTION_PAUSE:
                    Log.i(TAG, "onReceive: ACTION_PAUSE");
                    //暂停播放
                    pause();
                    break;
                case Constants.ACTION_PLAY:
                    Log.i(TAG, "onReceive: ACTION_PLAY");
                    mMusic_list = (List<Info.DataBean.ShootDetailListBean>) intent.getSerializableExtra("music_list");
                    //开始播放
                    if (mPlayer != null) {
                        Log.i(TAG, "mPlayer"+mMusic_list.size()+"mPosition"+mPosition);
                        //mPlayer.seekTo(mCurrentPosition);
                       // play(mPosition);
                        mPlayer.start();
                        //通知是否在播放
                        sentPlayStateToMain();
                    }else {
                        initPlayer();
                        Log.i(TAG, "mMusic_list.size"+mMusic_list.size()+"mPosition"+mPosition);
                        play(mPosition);
                    }
                    break;
                case Constants.ACTION_NEXT:
                    Log.i(TAG, "onReceive: ACTION_NEXT");
                    //下一首
                    prv_position = mPosition;
                    if (playMode % 3 == 1) {//1.单曲循环
                        play(mPosition);
                    } else if (playMode % 3 == 2) {//2.列表播放
                        mPosition++;
                        if (mPosition <= mMusic_list.size() - 1) {
                            play(mPosition);
                        } else {
                            //mPosition = 0;
                            play(mPosition);
                        }
                    } else if (playMode % 3 == 0) {// 0.随机播放
                        play(getRandom());
                    }
                    break;
                case Constants.ACTION_PRV:
                    Log.i(TAG, "onReceive: ACTION_PRV");
                    //上一首
                    prv_position = mPosition;
                    if (playMode % 3 == 1) {//1.单曲循环
                        play(mPosition);
                    } else if (playMode % 3 == 2) {//2.列表播放
                        mPosition--;
                        if (mPosition < 0) {
                            mPosition = mMusic_list.size() - 1;
                            play(mPosition);
                        } else {
                            if(isFrist){
                                play(mPosition);
                                isFrist=false;
                            }

                        }
                    } else if (playMode % 3 == 0) {// 0.随机播放
                        play(getRandom());
                    }
                    break;
                case Constants.ACTION_CLOSE:
                    Log.i(TAG, "onReceive: ACTION_CLOSE");
                    cancelMusic();
                    break;
                case AudioManager.ACTION_AUDIO_BECOMING_NOISY:
                    Log.i(TAG, "onReceive: ACTION_AUDIO_BECOMING_NOISY");
                    //如果耳机拨出时暂停播放
                    Intent intent_pause = new Intent();
                    intent_pause.setAction(Constants.ACTION_PAUSE);
                    sendBroadcast(intent_pause);
                    break;
            }
        }
    }

    private int getRandom() {
        mPosition = mRandom.nextInt(mMusic_list.size());
        return mPosition;
    }

    public static boolean isPlaying(){
        if(mPlayer != null){
            return mPlayer.isPlaying();
        }
        return false;
    }
}
