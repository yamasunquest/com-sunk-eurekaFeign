package com.sunk.springCloud.eurekaFeign.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 映射到service中的hello rest,在controller中直接调用helloClient即可
 */

@FeignClient("eurekaService-user")
public interface UserClient {
    @RequestMapping(value = "/user/hello", method = GET)
    String hello();
    
	@RequestMapping(value="/user/name",method =GET)
	String getName();
	
	@RequestMapping(value="/user/age",method =GET)
	int getAge();
	
    @RequestMapping(value = "/user/sleep/{time}",method = GET)
	String sleep(@RequestParam("time") Long time);
}