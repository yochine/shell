-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典详情', '3', '1', 'detail', 'system/detail/index', 1, 0, 'C', '0', '0', 'system:detail:list', '#', 'admin', sysdate(), '', null, '数据字典详情菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典详情查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:detail',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典详情新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典详情修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典详情删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:delete',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典详情导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:detail:export',       '#', 'admin', sysdate(), '', null, '');