-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典', '3', '1', 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', 'system:dict:list', '#', 'admin', sysdate(), '', null, '数据字典菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:dict:detail',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:dict:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:dict:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:dict:delete',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('数据字典导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:dict:export',       '#', 'admin', sysdate(), '', null, '');