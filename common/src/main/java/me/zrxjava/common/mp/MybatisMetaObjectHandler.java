
package me.zrxjava.common.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import me.zrxjava.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatisplus自定义填充
 * @author void
 */
@Slf4j
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
		this.setFieldValByName("createBy", SecurityUtils.getCurrentUsername(),metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
		this.setFieldValByName("updatedBy", SecurityUtils.getCurrentUsername(),metaObject);
	}

}
