package com.edu.api.filter;

import com.edu.api.exception.ArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;

@Slf4j
@Activate(group = CommonConstants.PROVIDER)
public class DubboExceptionFilter extends ListenableFilter {

    public DubboExceptionFilter() {
        super.listener = new DubboExceptionFilter.ExceptionListener();
    }


    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    static class ExceptionListener implements Listener {

        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation)         {

            if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {

                try {

                    Throwable exception = appResponse.getException();

                    if (exception instanceof RpcException) {
                        appResponse.setException(
                                new ArgumentException(exception.getMessage(), exception)
                        );
                    }else if(exception instanceof ArgumentException){
                        appResponse.setException(
                                new ArgumentException(exception.getMessage(), exception)
                        );
                    }

                } catch (Throwable e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

        @Override
        public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {
            log.error("dubbo invoke error", t);
        }
    }
}
