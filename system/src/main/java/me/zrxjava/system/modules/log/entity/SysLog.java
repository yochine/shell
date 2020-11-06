
package me.zrxjava.system.modules.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.zrxjava.common.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author void
 */
@Data
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_sys_log")
public class SysLog extends BaseEntity implements Serializable {


	/** 编号 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/** 操作IP */
	private String ip;

	/** 用户浏览器 */
	private String userAgent;

	/** 操作类别（0其它 1后台用户 2手机端用户） */
	private Integer operatorType;

	/** 操作模块 */
	private String title;

	/** 业务类型（0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据） */
	private Integer businessType;

	/** 请求URI */
	private String requestUri;

	/** 操作方式 */
	private String method;

	/** 操作提交的数据 */
	private String params;

	/** 操作状态（0正常 1异常） */
	private Integer status;

	/** 返回参数 */
	private String jsonResult;

	/** 执行时间 */
	private Long costTime;

	/** 异常信息 */
	private String errorMsg;

	/** 备注 */
	private String remark;

	/** 服务ID */
	private String serviceId;

	/** 版本号 */
	private String version;


}
