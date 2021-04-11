-- 菜单 SQL
insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('部门', '3', '1', 'dept', 'system/dept/index', 0, 0, 1, 0, 0, 'system:dept:list', '#', 'admin', sysdate(), '', null, '部门菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('部门查询', @parentId, '1',  '#', '', 0, 0, 2, 0, 0, 'system:dept:detail',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('部门新增', @parentId, '2',  '#', '', 0, 0, 2, 0, 0, 'system:dept:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('部门修改', @parentId, '3',  '#', '', 0, 0, 2, 0, 0, 'system:dept:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('部门删除', @parentId, '4',  '#', '', 0, 0, 2, 0, 0, 'system:dept:delete',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('部门导出', @parentId, '5',  '#', '', 0, 0, 2, 0, 0, 'system:dept:export',       '#', 'admin', sysdate(), '', null, '');