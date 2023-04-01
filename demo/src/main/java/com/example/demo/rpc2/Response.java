package com.example.demo.rpc2;

import lombok.Data;

/**
 * @author caozhixin
 * @date 2023/3/31 21:45
 */
@Data
public class Response {
    private String status;
    private String data;
    public Response(String data) {
        this.status = "200 OK";
        this.data = data;
    }
}
