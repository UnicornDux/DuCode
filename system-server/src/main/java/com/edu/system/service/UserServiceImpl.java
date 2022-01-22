package com.edu.system.service;

import com.edu.api.dubbo.UserService;
import com.edu.api.exception.ArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

@Slf4j
@DubboService
public class UserServiceImpl implements UserService {

    @Override
    public String getUserInfo(String userId) {

        String consumer = RpcContext.getContext().getAttachment("consumer");
        log.info("consumer ==> {} ", consumer);
        throw new RpcException("userId is null");
        //return "天不生我李淳罡，剑道万古如长夜";
    }

}
