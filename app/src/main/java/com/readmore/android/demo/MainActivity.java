package com.readmore.android.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.readmore.ReadMoreTextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ReadMoreTextView readMoreTextView = (ReadMoreTextView) findViewById(R.id.txtReadMore);
    Button btnNext = (Button) findViewById(R.id.btnNext);
    readMoreTextView.setTrimMode(ReadMoreTextView.TRIM_MODE_LINES);
    readMoreTextView.setTrimCollapsedText("[+]");
    readMoreTextView.setTrimExpandedText("[-]");
    readMoreTextView.setColorClickableText(ContextCompat.getColor(this, R.color.loadMoreText));
    readMoreTextView.setTrimLines(3);
    readMoreTextView.setText(R.string.test_data);

    btnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, TextListActivity.class));
      }
    });
  }
}
