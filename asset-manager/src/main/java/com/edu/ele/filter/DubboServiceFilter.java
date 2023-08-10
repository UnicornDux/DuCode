package com.edu.ele.filter;

import org.apache.dubbo.rpc.*;

public class DubboServiceFilter implements Filter {

  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    if (RpcContext.getContext().isConsumerSide()) {
      invocation.setAttachment("consumer", "true");
    }
    return invoker.invoke(invocation);
  }
}
