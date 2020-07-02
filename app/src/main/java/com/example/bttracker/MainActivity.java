package com.example.bttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //　直前の行の、今回追加した"Implements View.onClickListener"以外の部分は、
    // MainActivity(サブクラス)が、ImportしたAppCompactActivity(スーパークラス)の機能(メソッド)を
    // それと同じメソッド名(と同じ引数)で以下に書くことで使用する（上書きする）という宣言(Overrideと言う)

    @Override
    //@Overrideは、「これはOverrideメソッドです。もしOverrideされていなければエラーを出力して」
    // というもの。もし、スペルミスなどで上手くOverride
    // できない場合、コンパイル時にここでOverrideできませんでした、と教えてくれるそうだ。
    // もしかしたら、android studioでは画面横の赤線でエラーを教えてくれるからそれほど重要ではないのかもしれない。
    // と思ったが、android studioの画面で知らせてくれるエラーは、文法に関するエラーであって、@Overrideで
    //　知らせてくれるのは、タイプミスが原因でOverrideできない、といったエラーメッセージ：文法ミスではないので
    // Android Studioにはわからないエラー
    // @Overrideはjava言語の話。
    protected void onCreate(Bundle savedInstanceState) {
        // onCreateはアプリが起動したとき、アクティビティが再起動したときに最初に動く。
        // そのアクティビティが他のアプリなどを開いているときにメモリの都合で破棄される場合、
        // onSaveInstanceStateメソッドが破棄時のインスタンスの状態をsavedInstanceState
        // に保存する。アクティビティ再起動時に、そのsavedInstanceState(Bundle型)を伴って
        // 復元することで、破棄時に入力されていたメンバ変数などが失われずに再度アクティビティを起動できる。
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        // onCreateはアプリが起動したとき、（アクティビティの起動時）に実行されるメソッドであるため、
        // createNotificationChannel()メソッドや、下記のOnClickListenerメソッドはonCreateメソッドの
        // 中、つまり、onCreateメソッドが呼び出されとときに”実行すること”にリストアップされていないといけない。

        TextView mainButton1 = findViewById(R.id.MainButton1);
        mainButton1.setOnClickListener(this);
        // ↑上記の意味について
        // まず、2行上のsetContentViewでresourceからactivity_main.xmlを呼び出してlayoutを置いていて、
        // 次に、そのactivity_main.xmlからidがMainButton1であるTextViewを呼び出し、
        // TextViewクラス型のインスタンスで定義する"mainButton1"に入力する。
        // そして、この"mainButton1"にOnClickListenerを設定する。
        // 引数thisは、今回アクティビティにonClickListenerインターフェースを実装しているので、
        // "このアクティビティ"という意味だと思われる。

        TextView mainButton2 = findViewById(R.id.MainButton2);
        mainButton2.setOnClickListener(this);

        TextView mainButton3 = findViewById(R.id.MainButton3);
        mainButton3.setOnClickListener(this);

        TextView mainButton4 = findViewById(R.id.MainButton4);
        mainButton4.setOnClickListener(this);

        Button setReminder = findViewById(R.id.setReminderButton);
        setReminder.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.MainButton1:
            // If MainButton1 is clicked, do something
                Intent toLog = new Intent(this, LogActivity.class);
                startActivity(toLog);
                break;
            case R.id.MainButton2:
                // If MainButton2 is clicked, do something
                Intent toNormal = new Intent(this, NormalActivity.class);
                startActivity(toNormal);
                break;
            case R.id.MainButton3:
                // If MainButton3 is clicked, do something
                Intent toMechanism = new Intent(this, MechanismActivity.class);
                startActivity(toMechanism);
                break;
            case R.id.MainButton4:
                // If MainButton4 is clicked, do something
                Intent toLink2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/health/how-to-break-a-fever"));
                startActivity(toLink2);
                break;
            case R.id.setReminderButton:
                Toast.makeText(this, "Reminder set!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
                PendingIntent pd = PendingIntent.getBroadcast(this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                long interval = 1000*6;
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis(), interval, pd);
        }
    }
/* ここから50行ほどは、上記のonClickListenメソッドではなく、onClickメソッドをつかってsetReminderを
　　　作っていたときのjavaコードである。本来は不要なコードは消すべきだと思うが、コードの意味をいろいろ調べて
　　　自分なりにコメントアウトして説明を加えたので、該当箇所をコードごとコメントアウトして残しておく。
    public void setReminder(View view) {
        // When user clicks "SET REMINDER" button, a toast message will pop up
        // to let user know that a reminder is set
        // setReminder含め、メソッドの括弧内には引数が入る。setReminder (View view)
        // の最初の"View"はViewという変数型であることを示し、次の"view"は変数名
        // toast: 操作に関する簡単なフィードバックをポップアップメッセージとして表示する
        // （例：sending message...） toastはタイムアウト後に自動で消える。
        Toast.makeText(this, "Reminder set!", Toast.LENGTH_SHORT).show();
        // ↓Intentを作成　Create an intent object to start the ReminderBroadcastReceiver class
        Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
                // ↓ペンディングインテントを作成　
                // Create a pending intent so that the intent object above
                // will only fire　when alarm triggers
        PendingIntent pd = PendingIntent.getBroadcast(this, 0, intent, 0);
        // ↑上記は、PendingIntentというクラス型で定義される"pd"という名前のインスタンス*を作成し、
        // 　　           *インスタンス（オブジェクトと同義。クラスをメモリ領域に実体として格納したもの。
        // 　　　　　　　　　　　クラス内のメソッドや変数（フィールドと呼ぶ？）を利用するためにはインスタンス化が必要。）
        // その"pd"に、PendingIntentクラスから呼び出したgetBroadcastメソッドにより取得（get）した
        // Broadcastのフィールド値を"pd"に入力する、
        // という意味だと思う。
        // (参考：　int a = 5 というコードは
        // 　　　　　　　　　　　　　　整数型で定義される"a"という変数を作成し、その"a"に5という値を入力する、という意味）
        //
        // ↓Create an AlarmManager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        // ↑上記は、AlarmManagerというクラス型で定義される"alarmManager"という名前のインスタンスを作成し、
        // その"alarmManager"に、getSystemServiceメソッドによりALARM_SERVICEを引数として入れて取得(get)した
        // SystemServiceをフィールド値として入力する、という意味だと思う。
        // ここで(AlarmManager)の括弧は、getSystemServiceの値はそのままでは（型が異なるため）
        // AlarmManagerに入力できないので、入力前にAlarmManager型に変換（キャストと呼ばれる？）する、
        // という意味。
        //
        // ↓Repeating interval for the alarmManager is set to 6 second for demonstration purpose
        // In real world application, users may want to get daily reminder
        // In that case, set the interval to 1000 * 60 * 60 * 24
        long interval = 1000*6;
        // Set up repeating alarm so that the notification reminder gets fired at the set interval
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), interval, pd);
        // ↑上記は、"alarmManager"という名前のインスタンスから呼び出したsetRepeatingメソッドに
        //   引数1： AlarmManagerのRTC_WAKEUPという定数
        //   引数2： SystemのcurrentTimeMillis()
        //   引数3: 直前のコードで設定したintervalの値
        //   引数4: 3行前のコードで作成したインスタンス"pd"
        // を入力しRepeatingのフィールド値として設定する、という意味だと思われる。
        // もっと端的に言うと、"alarmManager"という名前のインスタンスにRepeatingの値を設定する、という意味かな。
        // （ちなみに、フィールドの名前の最初の文字は大文字なのだろうか？
        // 　いや、これは"set"に続いているのでキャメルケースで表記するから最初の文字（RepeatingのR）
        // が大文字だな。きっと。）
        // ちなみに、setRepeatingは難しいところがあるようで、あまりオススメはされていないようだ。
    }

 */

    private void createNotificationChannel(){
        // Android 8 以降に導入されたNotification Channelにより、ユーザーはその通知に対して
        // 必要に応じてスイッチのON/OFF、通知の表示の有無、任意の効果音の割り当てなどができるそうだ。
        // First, check SDK version
        // Create notification channel only if SDK version > Android 8 Oreo
        // CAUTION: It's Oreo's O, not number 0!!!
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelID = "BT Tracker Channel";
            // ↑上記は、String型（文字列型）で定義する"channelID"という名前の変数を作成し、
            // そこに"BT Tracker Channel"という文字列を入力する、という意味。
            String channelName = "BTTrackerReminderChannel";
            String channelDescription = "Channel for BT Tracker reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            // ↑上記は、int型（整数型）で定義する"importance"という名前の変数を作成し、
            // そこに、NotificationManagerクラスのIMPORTANCE_DEFAULTという名前で示された定数
            // を入力する、という意味。
            NotificationChannel channel = new NotificationChannel(channelID, channelName, importance);
            // ↑上記は、NotificationChannelというクラス型で定義される"channel"という名前のインスタンスを
            // 作成し、NotificationChannelクラスに
            // 　　　　引数1: 上記で値を入力した"channelID" (つまり、"BT Tracker Channel)
            //      引数2: 上記で値を入力した"channelName" (つまり、"BTTrackerReminderChannel")
            //      引数3: 上記で値を入力した"importance" (つまり、NotificationManagerの
            //      　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　IMPORTANCE_DEFAULTの値)
            //  を入れたものを"channel"という名前のインスタンスに入力する、という意味。
            channel.setDescription(channelDescription);
            // ↑上記は、"channel"という名前のインスタンスのDescriptionというフィールド値に
            // 引数として、"channelDescription" (つまり、"Channel for BT Tracker reminder")
            // を設定する、という意味だと思われる。
            // もっと端的に言うと、"channel"という名前のインスタンスにDescriptionの値を設定する、という意味かな。
            // Create a notification manager
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            // ↑上記は、NotificationManagerというクラス型で定義される"notificationManager"という
            // 名前のインスタンスを作成し、getSystemServiceメソッドの引数に
            // NotificationManager.classを入力して取得したフィールド値を"notificationManager"
            // というインスタンスに入力する、という意味。
            // たぶん、もっと言うと、NotificationManagerクラス型で定義される"notificationManagerという
            // 名前のインスタンスを作成し、systemServiceのNotificationManagerクラスを取得して
            // "notificationManager"インスタンスに入力する、という意味だろう。
            //
            // ↓Create notification channel
            notificationManager.createNotificationChannel(channel);
            // ↑上記は、"notificationManager"インスタンスからcreateNotificationChannelメソッドを呼び出し、
            // 引数に"channel"インスタンスを入力する、という意味だと思う。
        }
    }
}
