package com.solomyuri.email_sender.model;

import lombok.Data;

@Data
public class EmailAccount {
    private String from;
    private Integer port;
    private String host;
    private String username;
    private String password;
}
