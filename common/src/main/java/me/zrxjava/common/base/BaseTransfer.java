
package me.zrxjava.common.base;

import java.util.List;

/**
 * 基础转换器
 * @author void
 * @date 2020-09-16
 */
public interface BaseTransfer<D,E,V> {

    /**
     * DTO转Entity
     * @param dto /
     * @return /
     */
    E toEntity(D dto);

    /**
     * Entity转Vo
     * @param entity /
     * @return /
     */
    V toVo(E entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    List <E> toEntities(List<D> dtoList);

    /**
     * Entity集合转Vo集合
     * @param entityList /
     * @return /
     */
    List <V> toVos(List<E> entityList);
}
