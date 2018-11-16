package com.xiaojiu.studylibs.algorithm;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.socks.library.KLog;
import com.test.myselfview.R;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;
import com.xiaojiu.studylibs.manager.AlgorithmManager;

import butterknife.BindView;

public class AlgorithmAaddBActivity extends BaseAppCompatActivity {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.algorithm_tv_a_b)
    TextView mAlgorithmTvAB;
    private StringBuffer stringBuffer;

    @Override
    protected void initOnClick() {

    }

    @Override
    protected void initData() {
        stringBuffer = new StringBuffer();
        stringBuffer.append("Q:给出两个整数a和b, 求他们的和, 但不能使用 + 等数学运算符。\n");
        stringBuffer.append("A:不能使用数学运算符，第一时间想到使用其他的运算符，例如 位运算符\n");
        stringBuffer.append("位运算符复习\n");
        stringBuffer.append(" 与:\t\ta&b  ab都为1 结果为1\n");
        stringBuffer.append(" 或:\t\ta|b\t 只要有一个为1 就为1\n");
        stringBuffer.append(" 非:\t\t~\t 操作数相反\n");
        stringBuffer.append(" 异或：\ta^b\t 相同得0 不同得1\n");
        stringBuffer.append(" 情况1举例：\n");
        stringBuffer.append("       3 = 0011\n");
        stringBuffer.append("    +\t4 =\t0100\t\t^\t\t\t\t\t&\n");
        stringBuffer.append("    -----------------------------------------------------\n");
        stringBuffer.append("    7 = 0111 \t\t\t7 = 0111\t\t\t0 = 0000*/\n");
        stringBuffer.append(" 因为 两个数 与的特性是 必须都为真，结果才是真，导致的结果就是 异或 结果都为1\n");
        stringBuffer.append("  当两个数的与为0时,说明他已或的值  必为他俩的和\n");
        stringBuffer.append(" 情况2举例：\n");
        stringBuffer.append("       5 = 0101\n");
        stringBuffer.append("    +\t4 =\t0100\t\t^\t\t\t\t\t&\n");
        stringBuffer.append("    -----------------------------------------------------\n");
        stringBuffer.append("    9 = 1001 \t\t\t1 = 0001\t\t\t4 = 0100*/\n");
        stringBuffer.append("\n");
        stringBuffer.append(" public int addOperation(int a, int b) {\n");
        stringBuffer.append("    int yu = a & b;\n");
        stringBuffer.append("    int huo = a ^ b;\n");
        stringBuffer.append("    return yu == 0 ? huo : addOperation(yu << 1, huo)\n");
        stringBuffer.append(" }\n");
        KLog.e(AlgorithmManager.getInstance().addOperation(5, 5));
    }

    @Override
    protected void initView() {
        initToobar(myToolbar);
        mAlgorithmTvAB.setText(stringBuffer);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_algorithm_a_b;
    }

}
