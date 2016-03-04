package com.sunk.springCloud.eurekaFeign.hytrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sunk.springCloud.eurekaFeign.feign.UserClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("hystrixUserClient")
public class HystrixWrappedUserClient implements UserClient {

    @Autowired
    @Qualifier("userClient")
    private UserClient feignUserClient;

    @Override
    @HystrixCommand(groupKey = "UserGroup", fallbackMethod = "fallBackCall")
    public String hello() {
        return this.feignUserClient.hello();
    }

	@Override
    @HystrixCommand(groupKey = "UserGroup", fallbackMethod = "fallBackCall")
	public String getName() {
		return this.feignUserClient.getName();
	}

	@Override
    @HystrixCommand(groupKey = "UserGroup", fallbackMethod = "fallBackCall")
	public int getAge() {
		return this.feignUserClient.getAge();
	}

	@Override
    @HystrixCommand(groupKey = "UserGroup", fallbackMethod = "fallBackCall")
	public String sleep(Long time) {
		return this.feignUserClient.sleep(time);
	}
	
    public String fallBackCall() {
        String fallback = ("FAILED SERVICE CALL! - FALLING BACK");
        return fallback;
    }
}
