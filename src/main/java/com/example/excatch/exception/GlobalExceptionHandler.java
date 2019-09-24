package com.example.excatch.exception;

import com.example.excatch.util.AlarmHelperBean;
import com.example.excatch.util.ExceptionMailBean;
import com.example.excatch.util.FileUtil;
import com.example.excatch.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author songqd
 * @Date 2019/9/3
 * Description:全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private AlarmHelperBean alarmHelperBean;

    /**
     * @description 实时异常捕捉
     * @parm [ex]
     * @author songqd
     * @date 2019/9/6
     * @reutun java.util.Map
     * @modifier
     */
/*    @ResponseBody
    @ExceptionHandler(value = Exception.class)*/
    public Map errorHandler(Exception ex) {
        ExceptionMailBean exceptionMailBean = new ExceptionMailBean();
        if(saveInFile(dataHandler(ex,exceptionMailBean),alarmHelperBean.getPath())){
            mailUtil.sendByThread(exceptionMailBean);
        }
        logger.info("测试捕捉异常(getSuppressed)：" + ex.getMessage());
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

    /**
     * @description 异常数据提取excDescribe以及ExceptionMailBean属性信息保存
     * @parm [exception ,exceptionMailBean]
     * @author songqd
     * @date 2019/9/5
     * @reutun java.lang.String
     * @modifier
     */
    public static String dataHandler(Exception exception,ExceptionMailBean exceptionMailBean){
        //初始化ExceptionBean
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String str = sw.toString();
        exceptionMailBean.setExceptionClassName(exception.getClass().getName());
        exceptionMailBean.setExceptionMessage(exception.getLocalizedMessage());
        exceptionMailBean.setExceptionDetail(str);

        //提取excDescribe
        String excClassName = exception.getStackTrace()[0].getClassName();
        String excMethodName = exception.getStackTrace()[0].getMethodName();
        Integer excLineNumber = exception.getStackTrace()[0].getLineNumber();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(excClassName);
        stringBuilder.append(excMethodName);
        stringBuilder.append(excLineNumber);

        return stringBuilder.toString();
    }

    /**
     * @description 存储异常信息至文件中
     * @parm [excDescribe, path]
     * @author songqd
     * @date 2019/9/5
     * @reutun boolean
     * @modifier
     */
    public static boolean saveInFile(String excDescribe,String path){
        String filePath = FileUtil.createFile(path);
        boolean flag = false;

        if(!FileUtil.isInFile(excDescribe,filePath)){
            FileUtil.writeFile(filePath,excDescribe);
            flag = true;
        }
        return flag;
    }

    public void realTimeSend(){

    }


}
