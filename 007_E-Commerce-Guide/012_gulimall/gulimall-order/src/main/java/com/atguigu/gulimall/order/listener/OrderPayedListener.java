package com.atguigu.gulimall.order.listener;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.atguigu.gulimall.order.config.AlipayTemplate;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.PayAsyncVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class OrderPayedListener {
    @Autowired
    AlipayTemplate alipayTemplate;
    @Autowired
    OrderService orderService;
    /**
     * 支付宝成功异步通知
     * @param request
     * @return
     */
    @PostMapping("/payed/notify")
    public String handleAlipayed(PayAsyncVo vo,HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        //只要我们收到了支付宝给我们异步的通知，告诉我们订单支付成功。返回success，支付宝就再也不通知
//        Map<String, String[]> map = request.getParameterMap();
//        for (String key : map.keySet()) {
//            String value = request.getParameter(key);
//            System.out.println("参数名："+key+"==>参数值："+value);
//        }
        //验签
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayTemplate.getAlipay_public_key(), alipayTemplate.getCharset(), alipayTemplate.getSign_type()); //调用SDK验证签名
        if(signVerified){
            System.out.println("签名验证成功...");
            String result = orderService.handlePayResult(vo);
            return result;
        }else {
            System.out.println("签名验证失败...");
            return "error";
        }

    }
}
