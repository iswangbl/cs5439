package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

@Controller
public class TestController {
    private static final Logger logger = LogManager.getLogger(TestController.class);

    @RequestMapping("/cve")
    @ResponseBody
    public String cve(@RequestHeader(value = "User-Agent", required = false) String userAgent) {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 在触发日志之前记录初始 CPU 占用率
        double initialCpuLoad = getProcessCpuLoad(osBean);
        System.out.printf("Initial CPU Load: %.2f%%%n", initialCpuLoad * 100);
        // 开启一个线程持续监控 CPU 占用率
        Thread monitorThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                double cpuLoad = getProcessCpuLoad(osBean);
                System.out.printf("Current CPU Load: %.2f%%%n", cpuLoad * 100);
                try {
                    Thread.sleep(500); // 每隔 500ms 采样一次
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        monitorThread.start();

        try {
            ThreadContext.put("loginId", userAgent);
            logger.info("Testing Log4j DoS with input: {}", userAgent);
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

        // 停止监控线程
        monitorThread.interrupt();

        // 在日志触发之后记录最终 CPU 占用率
        double finalCpuLoad = getProcessCpuLoad(osBean);
        System.out.printf("Final CPU Load: %.2f%%%n", finalCpuLoad * 100);
        System.out.println("Test completed.");

        return "";
    }

    // 获取当前进程的 CPU 使用率
    private static double getProcessCpuLoad(OperatingSystemMXBean osBean) {
        return osBean.getProcessCpuLoad(); // 返回值为 0~1 的浮点数
    }
}
