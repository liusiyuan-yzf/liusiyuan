package com.liusiyuan.controller;

import com.liusiyuan.util.SSLClient;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class JiameiController {

    @RequestMapping("/makeMenu")
    public void makeMenu(){
        Map<String,String> resultMap=null;
        String acessToken = "4_b7uXpM6ZtfTgYLThPvu8GtEVFSypAd9GCyRLZQ7FQC1aF6Tqqv18iKHpz82N-iTcGWdEWXVrqX6KPC2shXhwGb4zx-9VJL9ceNi-G3BV6J0pdjP0BfFfDVBT4Wb19fkKGZ6kbRJ7dXoQOWB1LVCcAAATDK";
        if (acessToken != null) {
            String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+acessToken;
            org.apache.http.client.HttpClient httpClient = null;
            try {
                httpClient = new SSLClient();
                HttpPost method=new HttpPost(url);
                //组装json参数
                JSONObject jsonButton = new JSONObject();

                JSONObject jsonSubButton = new JSONObject();
                jsonSubButton.put("type","view");
                jsonSubButton.put("name","喝吧商城");
                jsonSubButton.put("url","http://www.drinksba.com/mobile/loginWx.htm");

                JSONObject jsonSubButton1 = new JSONObject();
                jsonSubButton1.put("type","click");
                jsonSubButton1.put("name","满减优惠");
                jsonSubButton1.put("key","jiamei_youhui");

                JSONObject jsonSubButton2 = new JSONObject();
                jsonSubButton2.put("name","喝吧售后");

                JSONObject jsonSubButton5 = new JSONObject();
                jsonSubButton5.put("type","click");
                jsonSubButton5.put("name","客服热线");
                jsonSubButton5.put("key","jiamei_kefu");

                JSONObject jsonSubButton6 = new JSONObject();
                jsonSubButton6.put("type","view");
                jsonSubButton6.put("name","订单查询");
                jsonSubButton6.put("url","http://www.drinksba.com/mobile/loginWx.htm?redriectUrl=customer/myorder.html");

                JSONObject jsonSubButton7 = new JSONObject();
                jsonSubButton7.put("type","view");
                jsonSubButton7.put("name","会员中心");
                jsonSubButton7.put("url","http://www.drinksba.com/mobile/loginWx.htm?redriectUrl=customercenter.html");


                Object[] buttonSub2 = new Object[3];
                buttonSub2[0]=jsonSubButton5;
                buttonSub2[1]=jsonSubButton6;
                buttonSub2[2]=jsonSubButton7;
                jsonSubButton2.put("sub_button",buttonSub2);

                Object[] button = new Object[3];
                button[0]=jsonSubButton;
                button[1]=jsonSubButton1;
                button[2]=jsonSubButton2;
                jsonButton.put("button",button);
                //处理json参数
                StringEntity entity = new StringEntity(jsonButton.toString(),"utf-8");//解决中文乱码问题
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
                //调用第三方接口
                HttpResponse dataResult  = httpClient.execute(method);
                // 请求结束，返回结果
                if (dataResult.getStatusLine().getStatusCode() == 200) {
                    // 请求结束，返回结果
                    String resData = EntityUtils.toString(dataResult.getEntity());
                    System.out.println("请求返回结果集"+resData);

                    //结果集转为map返回
                    JSONObject  jasonObject = JSONObject.fromObject(resData);
                    resultMap= (Map<String,String>)jasonObject;
                    if("ok".equals(resultMap.get("errmsg"))){
                        System.out.println("创建自定义菜单成功");
                    }
                } else {
                    System.out.println("创建自定义菜单失败");
                }
            } catch (Exception e) {
                System.out.println("创建自定义菜单失败");
            }
        }
    }
}
