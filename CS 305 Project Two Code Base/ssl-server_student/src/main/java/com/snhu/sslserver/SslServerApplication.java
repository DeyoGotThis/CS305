package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";

@RestController
class ServerController{
	public static String createHash(String name) throws NoSuchAlgorithmException {
		MessageDigest mdObject = MessageDigest.getInstance("SHA-256");
		byte[] hashValue = mdObject.digest(name.getBytes());
		StringBuilder builder = new StringBuilder();
		for (byte b : hashValue) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
	
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "Daniel Leon";
    	String hash = createHash(data);
       
        return "<p>data: " + data + " : SHA-256 : " + hash;
    }
}