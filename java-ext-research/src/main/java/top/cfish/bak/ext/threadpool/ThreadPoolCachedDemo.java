package top.cfish.bak.ext.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: isisiwish
 * @date: 2019/1/22
 * @time: 19:16
 */
@Slf4j
public class ThreadPoolCachedDemo
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        for (int i = 0; i < 10; i++)
        {
            final int index = i;
            executorService.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    log.info("task:{}", index);
                }
            });
        }
        executorService.shutdown();
    }
}
