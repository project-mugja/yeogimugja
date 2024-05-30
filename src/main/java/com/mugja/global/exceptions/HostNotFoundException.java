package com.mugja.global.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HostNotFoundException extends Exception{
    public HostNotFoundException(HostNotFoundException e){
        super("Host does not exist");
    }
}
