/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.controller;

import com.orange.net.MulticastListener;
import com.orange.net.MulticastSender;
import java.util.TimerTask;

/**
 *
 * @author niuyunyun
 */
public class Controller implements IController {

    private static final String kIP = "192.168.0.101";
    private static final int kPort = 5000;
    private MulticastSender mMulticastSender;
    private MulticastListener mMulticastListener;

    public Controller() {
        mMulticastSender = new MulticastSender(kIP, kPort);
        java.util.Timer timer = new java.util.Timer(true);
        TimerTask sendTask = new TimerTask() {
            public void run() {
                mMulticastSender.send("hello");
            }
        };
        timer.schedule(sendTask, 1000, 1000 * 3);
        mMulticastListener = new MulticastListener("192.168.0.100", kPort);
        TimerTask listenTask = new TimerTask() {
            public void run() {
                mMulticastSender.send("hello");
            }
        };
        timer.schedule(listenTask, 1000, 1000 * 3);
        System.out.println("start controller ....");
    }

    @Override
    public void startMulticast() {
    }

    @Override
    public void startListenMulticast() {
    }
}
