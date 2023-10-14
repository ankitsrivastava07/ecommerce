package com.apigateway.exceptionHandler;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class Test {

    public void display(AtomicReference<Set<String>> atomicReference){
        System.out.println("-------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> >>>>>>>>>>>>>>>>  "+atomicReference.get());
    }
}
