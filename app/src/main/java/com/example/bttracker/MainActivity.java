package com.example.bttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //　直前の行の、今回追加した"Implements View.onClickListener"以外の部分は、
    // MainActivity(サブクラス)が、ImportしたAppCompactActivity(スーパークラス)の機能(メソッド)を
    // それと同じメソッド名(と同じ引数)で以下に書くことで使用する（上書きする）という宣言(Overrideと言う)

    @Override
    //@Overrideは、「これはOverrideメソッドです。もしOverrideされていなければエラーを出力して」
    // というもの。もし、スペルミスなどで上手くOverride
    // できない場合、コンパイル時にここでOverrideできませんでした、と教えてくれるそうだ。
    // もしかしたら、androidでは画面横の赤線でエラーを教えてくれるからそれほど重要ではないのかもしれない。
    // @Overrideはjava言語の話。
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mainButton1 = findViewById(R.id.MainButton1);
        mainButton1.setOnClickListener(this);

        TextView mainButton2 = findViewById(R.id.MainButton2);
        mainButton2.setOnClickListener(this);

        TextView mainButton3 = findViewById(R.id.MainButton3);
        mainButton3.setOnClickListener(this);

        TextView mainButton4 = findViewById(R.id.MainButton4);
        mainButton4.setOnClickListener(this);

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
        }
    }


}
