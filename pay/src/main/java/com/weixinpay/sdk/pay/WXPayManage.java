package com.weixinpay.sdk.pay;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import com.tctogether.canvas.CommodityInfo;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by chb on 2016/11/8.
 */

public class WXPayManage {

    public static CommodityInfo commodityInfo;//商品详细信息类
    private PayReq req;
    private IWXAPI msgApi;
    private Map<String,String> resultunifiedorder;
    private StringBuffer sb;

    public WXPayManage(Context context)
    {
        msgApi = WXAPIFactory.createWXAPI(context, null);
        req = new PayReq();
        msgApi.registerApp(Constants.APP_ID);
    }
    /**
     * 发送支付请求
     * @param commodityName 商品名称
     * @param commodityPrice 商品价格（以分为单位的字符串）
     * @param payType 付款类型
     * @param drawid 作品id
     */
    public void sendPayRequest(String commodityName, String commodityPrice, int payType, String drawid,String title)
    {
        commodityInfo = new CommodityInfo();
        commodityInfo.setTitle(title);
        commodityInfo.setName(commodityName);
        commodityInfo.setPrice(commodityPrice);
        commodityInfo.setPayType(payType);
        commodityInfo.setDrawid(drawid);
        sb = new StringBuffer();
        GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
        getPrepayId.execute();
    }

    /**
     生成签名
     */
    private String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);
        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("orion",packageSign);
        return packageSign;
    }
    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

        this.sb.append("sign str\n"+sb.toString()+"\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("orion",appSign);
        return appSign;
    }
    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");
            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");

        Log.e("orion",sb.toString());
        return sb.toString();
    }

    private class GetPrepayIdTask extends AsyncTask<Void, Void, Map<String,String>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Map<String,String> result) {
            sb.append("prepay_id\n"+result.get("prepay_id")+"\n\n");
            resultunifiedorder = result;
            //获取支付参数
            genPayReq();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String,String> doInBackground(Void... params) {
            String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = genProductArgs();
            Log.e("orion",entity);
            byte[] buf = Util.httpPost(url, entity);
            String content = new String(buf);
            Log.e("orion", content);
            Map<String,String> xml=decodeXml(content);
            return xml;
        }
    }



    public Map<String,String> decodeXml(String content) {

        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName=parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if("xml".equals(nodeName)==false){
                            //实例化student对象
                            xml.put(nodeName,parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
            Log.e("orion",e.toString());
        }
        return null;

    }


    private String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String genOutTradNo() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    //
    private String genProductArgs() {
        StringBuffer xml = new StringBuffer();
        try {
            commodityInfo.setId(genOutTradNo());//设置订单号
            String nonceStr = genNonceStr();
            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));//app应用id
            packageParams.add(new BasicNameValuePair("body", commodityInfo.getName()));//产品名称
            packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));//商户号
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));//随机字符串
            packageParams.add(new BasicNameValuePair("notify_url", "http://121.40.35.3/test"));//接收财富通通知的URL
            packageParams.add(new BasicNameValuePair("out_trade_no", commodityInfo.getId()));//商品订单号
            packageParams.add(new BasicNameValuePair("spbill_create_ip", "127.0.0.1"));//用户的公网ip
            packageParams.add(new BasicNameValuePair("total_fee", commodityInfo.getPrice()));//商品金额，以分为单位
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));//交易类型
            String sign = genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));
            String xmlstring = toXml(packageParams);
            return new String(xmlstring.toString().getBytes(), "ISO8859-1");//xml中文不能支付转码
        } catch (Exception e) {
            return null;
        }
    }
    private void genPayReq() {
        req.appId = Constants.APP_ID;
        req.partnerId = Constants.MCH_ID;
        req.prepayId = resultunifiedorder.get("prepay_id");
        req.packageValue = "Sign=WXPay";
        req.nonceStr = genNonceStr();
        req.timeStamp = String.valueOf(genTimeStamp());
        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
        req.sign = genAppSign(signParams);
        sb.append("sign\n"+req.sign+"\n\n");
        //执行支付请求
        sendPayReq();
    }
    private void sendPayReq() {
        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
    }

}
