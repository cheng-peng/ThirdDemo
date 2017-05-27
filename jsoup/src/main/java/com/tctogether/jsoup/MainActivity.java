package com.tctogether.jsoup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * WebView 加载html
 */
public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.main_web);

        String html = "<p style=\"text-align:center\"><span style=\" font-family: 仿宋; font-size: 19px; \">——金融江湖使用说明</span><br/></p><p style=\"text-align:center\"><span style=\"font-size:14px;font-family:仿宋\"><img src=\"http://123.56.75.125:8082/ueditor/jsp/upload/image/20170113/1484306742282085177.jpg\" title=\"1484306742282085177.jpg\" alt=\"filehelper_1476424873081_45.jpg\"/>&nbsp;</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px; font-weight: bold;font-family:仿宋\">欢迎进入金融江湖！这是最大的财金社区。</span></p><p style=\"text-align:center;text-indent:37px\"><strong><span style=\"font-size:19px;font-family: 仿宋\">第一章 引子</span></strong></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">一、 江湖总则</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">金融是一个江湖，做业务就是交朋友。</span></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">二、 关于本服务</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">诚信待人，实名注册。</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">热心助人，实为助己。</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">&nbsp;</span></p><p style=\"text-align:center;text-indent:37px\"><strong><span style=\"font-size:19px;font-family: 仿宋\">第二章 闯“江湖”</span></strong></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">一、 推荐和新消息提醒</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">诚信待人，实名注册。</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">热心帮助，</span></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">二、 广而告之</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">金融江湖保留，您在行使这些权利时须另外取得金融江湖的书面许可。金融江湖如果未行使前述任何权利，并不构成对该权利的放弃。</span></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">三、 推荐和新消息提醒</span></strong></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">1</span></strong><strong><span style=\"font-size:19px;font-family: 仿宋\">、项目类业务</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">您从未经金融江湖授权的第三方获取本软件或与本软件名称相同的安装程序，金融江湖无法保证该软件能够正常使用，并对因此给您造成的损失不予负责。</span></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">2</span></strong><strong><span style=\"font-size:19px;font-family: 仿宋\">、交易类业务</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">意帮助金融江湖改进产品服务，请告知卸载原因。</span></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">3</span></strong><strong><span style=\"font-size:19px;font-family: 仿宋\">、交流分享</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">意帮助金融江湖改进产品服务，请告知卸载原因。</span></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">4</span></strong><strong><span style=\"font-size:19px;font-family: 仿宋\">、帮帮</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">意帮助金融江湖改进产品服务，请告知卸载原因。</span></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">5</span></strong><strong><span style=\"font-size:19px;font-family: 仿宋\">、查看门派</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">意帮助金融江湖改进产品服务，请告知卸载原因。</span></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">6</span></strong><strong><span style=\"font-size:19px;font-family: 仿宋\">、江湖寻人</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">意帮助金融江湖改进产品服务，请告知卸载原因。</span></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">7</span></strong><strong><span style=\"font-size:19px;font-family: 仿宋\">、招聘求职</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">意帮助金融江湖改进产品服务，请告知卸载原因。</span></p><p style=\"text-indent:37px\"><strong><span style=\"font-size:19px;font-family:仿宋\">8</span></strong><strong><span style=\"font-size:19px;font-family: 仿宋\">、官府</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">意帮助金融江湖改进产品服务，请告知卸载原因。</span></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">四、发布</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">意帮助金融江湖改进产品服务，请告知卸载原因。</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">&nbsp;</span></p><p style=\"text-align:center;text-indent:37px\"><strong><span style=\"font-size:19px;font-family: 仿宋\">第三章 经营“地盘”</span></strong></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">五、门派</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">后，金融江湖不保证旧版本软件的继续可用，请您随时核对并下载最新版本。</span></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">六、好友</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">各种安全技术和程序建立完善的管理制度来保护您的个人信息，以。</span></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">七、私聊</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">对本服务中的第三方软件自行加以判断，并承担因使用相关资源而引起的所有风险，金融江湖无法且不会对因前述风险导致的任何损失或损害承担责任。</span></p><p style=\"text-align:center;text-indent:37px\"><strong><span style=\"font-size:19px;font-family: 仿宋\">第四章 管理“我的”</span></strong></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">八、个人中心</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">方软件、插件、外挂、系统，登录或使用金融江湖软件及服务，或制作、发布、传播上述工具；</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">判断对违反有关法律法规或本协议规定的行为进行处罚，对违法违规的任何用户采取适当的法律行动，并依据法律法规保存有关信息向有关部门报告等，用户应独自承担由此而产生的一切法律责任。</span></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">九、我的帖子</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">或相关权利人书面同意，您不得为任何商业或非商业目的自行或许可任何第三方实施、利用、转让上述知识产权。</span></p><p><strong><span style=\"font-size:19px;font-family:仿宋\">十、其他相关</span></strong></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">请一定先核实对方身份，确认无误再进行相关操作，以免造成财产损失。</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">条款无论因何种原因部分无效或不可执行，其余条款仍有效，对双方具有约束力。</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">&nbsp;</span></p><p style=\"text-align:right;text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">金融江湖</span></p><p style=\"text-align:right;text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">2016-10-25</span></p><p style=\"text-align:right;text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">&nbsp;</span></p><p style=\"text-indent:37px\"><span style=\"font-size:19px;font-family:仿宋\">&nbsp;</span></p><p><br/></p><p><br/></p><p>\n" +
                "\t\t\t\t</p>";
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");// 避免中文乱码
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        WebSettings settings = mWebView.getSettings();
        //文本字体
        settings.setTextSize(WebSettings.TextSize.SMALLER);
        settings.setJavaScriptEnabled(true);

        settings.setNeedInitialFocus(false);

        settings.setSupportZoom(true);

        settings.setLoadWithOverviewMode(true);//适应屏幕

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        settings.setLoadsImagesAutomatically(true);//自动加载图片

        Document doc = Jsoup.parse(html);

        Elements ele_Img = doc.getElementsByTag("img");

        if (ele_Img.size() != 0) {
            for (Element element : ele_Img) {
                element.attr("width", "100%");
//一定要设置 auto 不要控制其高度，让其自己跟随宽度变化情况调整
                element.attr("height", "auto");
            }
        }
        mWebView.loadDataWithBaseURL(null, doc.toString(), "text/html", "utf-8", null);
    }
}
