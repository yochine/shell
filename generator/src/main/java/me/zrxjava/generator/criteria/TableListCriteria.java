package me.zrxjava.generator.criteria;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zrxjava.common.annotation.Query;
import me.zrxjava.common.base.BasePage;

/**
 * @author void
 * @create 2020-12-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableListCriteria extends BasePage {

    @Query(type = SqlKeyword.LIKE)
    private String tableName;

    private String tableComment;


}
