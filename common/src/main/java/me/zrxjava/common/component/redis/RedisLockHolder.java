package me.zrxjava.common.component.redis;

import lombok.Data;

/**
 * @author void
 * @create 2021-01-15
 */
@Data
public class RedisLockHolder {

    /**
     * 业务唯一 key
     */
    private String businessKey;

    /**
     * 加锁时间 (秒 s)
     */
    private Long lockTime;

    /**
     * 上次更新时间（ms）
     */
    private Long lastModifyTime;

    /**
     * 保存当前线程
     */
    private Thread currentTread;

    /**
     * 更新的时间周期（毫秒）,公式 = 加锁时间（单位毫秒） / 3
     */
    private Long modifyPeriod;

    public RedisLockHolder(String businessKey, Long lockTime, Long lastModifyTime, Thread currentTread) {
        this.businessKey = businessKey;
        this.lockTime = lockTime;
        this.lastModifyTime = lastModifyTime;
        this.currentTread = currentTread;
        this.modifyPeriod = lockTime / 3;
    }
}
