//package com.example.demo.attach;
//
//import com.sun.tools.attach.VirtualMachine;
//import com.sun.tools.attach.VirtualMachineDescriptor;
//
//import java.util.List;
//
//public class AttachDemo {
//
//    public static void main(String[] args) throws Exception {
//        // 获取所有Java进程的描述符
//        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
//        for (VirtualMachineDescriptor vm : vms) {
//            System.out.println(vm.id() + " : " + vm.displayName());
//        }
//
//        // 选择一个Java进程，通过进程ID（PID）附加到该进程
//        String pid = "42765"; // 目标进程的PID
//        VirtualMachine vm = VirtualMachine.attach(pid);
//
//        // 启动调试会话，并连接到JDWP服务端口
//        String connectorAddress = vm.getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
//        if (connectorAddress == null) {
//            String agent = vm.getSystemProperties().getProperty("java.home") + "/lib/tools.jar";
//            vm.loadAgent(agent);
//            connectorAddress = vm.getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
//        }
//        System.out.println("Connector address: " + connectorAddress);
//    }
//}
//
