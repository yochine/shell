
package me.zrxjava.system.support.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import me.zrxjava.system.support.util.SecurityUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatisplus自定义填充
 * @author void
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
		this.strictInsertFill(metaObject, "createBy", SecurityUtil::getCurrentUsername, String.class);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
		this.strictUpdateFill(metaObject, "updatedBy", SecurityUtil::getCurrentUsername, String.class);
	}

}
