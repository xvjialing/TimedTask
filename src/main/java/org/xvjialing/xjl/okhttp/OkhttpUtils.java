package org.xvjialing.xjl.okhttp;

import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.xvjialing.xjl.common.CommonUtils;
import org.xvjialing.xjl.task.Task;

import java.util.List;

public class OkhttpUtils {

    private static OkHttpClient okHttpClient;
    private static Request request;

    public OkhttpUtils(){
        okHttpClient = new OkHttpClient();
        request=new Request.Builder()
                .get()
                .url(CommonUtils.REST_SERVICE_URL+"/api/tasks")
                .build();
    }

    public List<Task> getTasks(){
        try {
            Response execute = okHttpClient.newCall(request).execute();
            String taskStr=execute.body().string();
            return JSON.parseArray(taskStr,Task.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
