package me.zrxjava.generator.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zrxjava.generator.constants.GenConstants;
import me.zrxjava.generator.entity.FormConf;
import me.zrxjava.generator.entity.TableColumn;
import me.zrxjava.generator.mapper.FormConfMapper;
import me.zrxjava.generator.mapper.TableColumnMapper;
import me.zrxjava.generator.service.IFormConfService;
import me.zrxjava.generator.util.VelocityInitializer;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.List;

/**
 * <p>
 * 表单配置 服务实现类
 * </p>
 *
 * @author void
 * @since 2021-02-05
 */
@Service
public class FormConfServiceImpl extends ServiceImpl<FormConfMapper, FormConf> implements IFormConfService {

    @Resource
    private TableColumnMapper tableColumnMapper;

    @Override
    public String detail(Long tableId, String dsName) {

        FormConf conf = getOne(Wrappers.<FormConf>lambdaQuery().eq(FormConf::getTableId, tableId));
        if (conf != null) {
            return conf.getFormInfo();
        }
        // 没有则自动生成
        List<TableColumn> tableColumns = tableColumnMapper.selectList(Wrappers.<TableColumn>lambdaQuery().eq(TableColumn::getTableId,tableId));
        // 设置velocity资源加载器
        VelocityInitializer.initVelocity();
        Template template = Velocity.getTemplate("vm/js/crud.js.vm", CharsetUtil.UTF_8);
        VelocityContext context = new VelocityContext();
        context.put("columns", tableColumns);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return StrUtil.trim(StrUtil.removePrefix(writer.toString(), GenConstants.CRUD_PREFIX));

    }
}
