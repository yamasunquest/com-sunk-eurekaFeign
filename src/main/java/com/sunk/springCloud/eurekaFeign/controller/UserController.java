package com.sunk.springCloud.eurekaFeign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.client.ClientFactory;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;
import com.sunk.springCloud.eurekaFeign.feign.UserClient;

@RestController
public class UserController {

    @Autowired
    @Qualifier("userClient")
    UserClient client;

    @Autowired
    @Qualifier("hystrixUserClient")
    UserClient hytrixClient;

    @Autowired
    private DiscoveryClient discoveryClient;
    
    /**
     * 直接调用feign，feign会去调用eurekaService
     * */
    @RequestMapping("/")
    public String hello() {
/*    	List<ServiceInstance> list = discoveryClient.getInstances("eurekaService-user");
        if (list != null && list.size() > 0 ) {
            System.out.println(list.get(0).getUri());
        }
        
	DynamicServerListLoadBalancer lb = (DynamicServerListLoadBalancer) ClientFactory.getNamedLoadBalancer("eurekaService-user");  
	RandomRule randomRule = new RandomRule();  
	Server randomAlgorithmServer = randomRule.choose(lb, null);*/
	
        return client.hello();
    }

    @RequestMapping("/name")
    public String name() {
        return client.getName();
    }
    
    @RequestMapping("/age")
    public int age() {
        return client.getAge();
    }
    
    @RequestMapping("/sleep/{time}")
    public String sleep(@PathVariable("time") Long time) {
        return client.sleep(time);
    }
    
    /**
     * 1、调用hytrix
     * 2、hytrix继承并调用feign
     * 3、feign会去调用eurekaService
     * */
    @RequestMapping("/hytrix")
    public String hytrixHello() {
        return hytrixClient.hello();
    }

}
