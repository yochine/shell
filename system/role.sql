-- 菜单 SQL
insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('角色', '3', '1', 'role', 'system/role/index', 0, 0, 1, 0, 0, 'system:role:list', '#', 'admin', sysdate(), '', null, '角色菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('角色查询', @parentId, '1',  '#', '', 0, 0, 2, 0, 0, 'system:role:detail',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('角色新增', @parentId, '2',  '#', '', 0, 0, 2, 0, 0, 'system:role:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('角色修改', @parentId, '3',  '#', '', 0, 0, 2, 0, 0, 'system:role:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('角色删除', @parentId, '4',  '#', '', 0, 0, 2, 0, 0, 'system:role:delete',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('角色导出', @parentId, '5',  '#', '', 0, 0, 2, 0, 0, 'system:role:export',       '#', 'admin', sysdate(), '', null, '');