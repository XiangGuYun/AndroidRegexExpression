package com.androidzhengzeexpression;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * https://www.cnblogs.com/shuqi/p/4427693.html
 * https://www.cnblogs.com/xyou/p/7427779.html
 */
public class MainActivity extends AppCompatActivity {

    EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etTest);

        //检查文字是否是邮箱
        String text1 = "850570138@qq.com";
        String pattern1 = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
        boolean isMail = Pattern.matches(pattern1, text1);
        Log.d("Test", "isMail? "+isMail);
        //检查文字是否是手机号码
        String text2 = "13916434237";
        String pattern2 = "(86)*0*13\\d{9}";
        boolean isPhoneNumber = Pattern.matches(pattern2, text2);
        Log.d("Test", "isPhoneNumber? "+isPhoneNumber);
        //检查文字是否是固话号码
        String text3 = "88836549";
        String pattern3 = "(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}";
        boolean isTelNumber = Pattern.matches(pattern3, text3);
        Log.d("Test", "isTelNumber? "+isTelNumber);
        //检查文字是否只有中文
        String text4 = "大叔大婶";
        String text4_1 = "大大666";
        String pattern4 = "^[\\u0391-\\uFFE5]+$";
        Log.d("Test", "isTelChineseOnly? "+Pattern.matches(pattern4,text4)
        +" "+Pattern.matches(pattern4,text4_1));
        //检查文字是否只有中英文数字和下划线
        String text5 = "大声道dsds2323_";
        String text5_1 = "dasd***";
        String pattern5 = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
        Log.d("Test", "isTelChineseNumberEnglishOnly? "+Pattern.matches(pattern5,text5)
                +" "+Pattern.matches(pattern5,text5_1));

        /*
        限制EditText的输入内容
        其他方法还有
        digit-只能穷举
        InputType
        maxLength
         */
        InputFilter filter = new InputFilter() {
            /**
             CharSequence source,  //输入的文字
             int start,  //开始位置
             int end,  //结束位置
             Spanned dest, //当前显示的内容
             int dstart,  //当前开始位置
             int dend //当前结束位置
             * @return
             */
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(dend>10){
                    Toast.makeText(MainActivity.this, "最多输入10个字符！",Toast.LENGTH_SHORT).show();
                    return "";
                }
                String pattern = "^[\\u4e00-\\u9fa5_a-zA-Z0-9。，.,]+$";
                if(Pattern.matches(pattern, source.toString())){
                    return source;
                }
                Toast.makeText(MainActivity.this, "只能输入中文、英文、数字和逗号句号",Toast.LENGTH_SHORT).show();
                return "";
            }
        };

        etText.setFilters(new InputFilter[]{filter});
    }
}
