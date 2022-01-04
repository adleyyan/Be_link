package com.hq.bms.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.hq.bms.module.RabbitMQService;
import com.hq.bms.util.IllegalStrFilterUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class InterfaceRequestErrrorAndPerformanceLog {
    public static final Logger logger = LoggerFactory.getLogger(InterfaceRequestErrrorAndPerformanceLog.class);

    @Value("${db.log.bad.value:3000}")
    private int performanceBadValue;

    @Resource
    private RabbitTemplate rabbitTemplate;
//    @Resource
//    private InterfaceErrorService interfaceErrorService;

    @Pointcut("execution(* com.hq.bms.controller.*.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        Stopwatch stopwatch = Stopwatch.createStarted();

//        APIResponse apiResponse;
        try {
            logger.info("执行Controller开始: " + pjp.getSignature() + " 参数：" + Lists.newArrayList(pjp.getArgs()).toString());
            //处理入参特殊字符和sql注入攻击
//            checkRequestParam(pjp);
            //执行访问接口操作
//            apiResponse = (APIResponse) pjp.proceed(pjp.getArgs());
            try{
                logger.info("执行Controller结束: " + pjp.getSignature() + "， 返回值：" + JSONObject.toJSONString(pjp.proceed(pjp.getArgs())));
                //此处将日志打印放入try-catch是因为项目中有些对象实体bean过于复杂，导致序列化为json的时候报错，但是此处报错并不影响主要功能使用，只是返回结果日志没有打印，所以catch中也不做抛出异常处理
            }catch (Exception ex){
                logger.error(pjp.getSignature()+" 接口记录返回结果失败！，原因为：{}",ex.getMessage());
            }
            Long consumeTime = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
            logger.info("耗时：" + consumeTime + "(毫秒).");
            //当接口请求时间大于3秒时，标记为异常调用时间，并记录入库
            if(consumeTime > performanceBadValue){
//                DcPerformanceEntity dcPerformanceEntity = new DcPerformanceEntity();
//                dcPerformanceEntity.setInterfaceName(pjp.getSignature().toString());
//                dcPerformanceEntity.setRequestParam(Lists.newArrayList(pjp.getArgs()).toString());
//                dcPerformanceEntity.setConsumeTime(consumeTime + "毫秒");
//                RabbitMQMessageTarget mqTarget = RabbitMQMessageTarget.createFanoutTarget(ProjectConstants.DC_KEY_EXCHANGE_PERFORMANCE, new String[] { ProjectConstants.DC_KEY_QUEUE_PERFORMANCE});
//                rabbitTemplate.send(mqTarget, JSON.toJSONString(dcPerformanceEntity));
            }
        } catch (Exception throwable) {
//            apiResponse = handlerException(pjp, throwable);
        }

        return pjp.proceed(pjp.getArgs());
    }

    /**
     * @Author: gmy
     * @Description: 处理接口调用异常
     * @Date: 15:13 2018/10/25
     */
//    private APIResponse handlerException(ProceedingJoinPoint pjp, Throwable e) {
////        APIResponse apiResponse;
//        if(e.getClass().isAssignableFrom(ProjectException.class) ){
//            //ProjectException为自定义异常类，项目中Controller层会把所有的异常都catch掉，并手工封装成ProjectException抛出来，这样做的目的是ProjectException会记录抛出异常接口的路径，名称以及请求参数等等，有助于错误排查
//            ProjectException projectException = (ProjectException)e;
//            logger.error("捕获到ProjectException异常:",JSONObject.toJSONString(projectException.getDcErrorEntity()));
//            RabbitMQMessageTarget mqTarget = RabbitMQMessageTarget.createFanoutTarget(ProjectConstants.DC_KEY_EXCHANGE_INTERFACE_ERROR, new String[] { ProjectConstants.DC_KEY_QUEUE_INTERFACE_ERROR});
//            rabbitMQService.send(mqTarget, JSON.toJSONString(dataCenterException.getDcErrorEntity()));
//            apiResponse = new APIResponse(APIResponse.FAIL,null,projectException.getDcErrorEntity().getErrorMessage());
//        } else if (e instanceof RuntimeException) {
//            logger.error("RuntimeException{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
//            apiResponse = new APIResponse(APIResponse.FAIL,null,e.getMessage());
//        } else {
//            logger.error("异常{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);
//            apiResponse = new APIResponse(APIResponse.FAIL,null,e.getMessage());
//        }
//
//        return apiResponse;
//    }

    /**
     * @Author: gmy
     * @Description: 处理入参特殊字符和sql注入攻击
     * @Date: 15:37 2018/10/25
     */
//    private void checkRequestParam(ProceedingJoinPoint pjp){
//        String str = String.valueOf(pjp.getArgs());
//        if (!IllegalStrFilterUtil.sqlStrFilter(str)) {
//            logger.info("访问接口：" + pjp.getSignature() + "，输入参数存在SQL注入风险！参数为：" + Lists.newArrayList(pjp.getArgs()).toString());
//            DcErrorEntity dcErrorEntity = interfaceErrorService.processDcErrorEntity(pjp.getSignature() + "",Lists.newArrayList(pjp.getArgs()).toString(),"输入参数存在SQL注入风险!");
//            throw new DataCenterException(dcErrorEntity);
//        }
//        if (!IllegalStrFilterUtil.isIllegalStr(str)) {
//            logger.info("访问接口：" + pjp.getSignature() + ",输入参数含有非法字符!，参数为：" + Lists.newArrayList(pjp.getArgs()).toString());
//            DcErrorEntity dcErrorEntity = interfaceErrorService.processDcErrorEntity(pjp.getSignature() + "",Lists.newArrayList(pjp.getArgs()).toString(),"输入参数含有非法字符!");
//            throw new DataCenterException(dcErrorEntity);
//        }
//    }
}
