-- 菜单 SQL
insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('系统用户', '3', '1', 'user', 'system/user/index', 0, 0, 1, 0, 0, 'system:user:list', '#', 'admin', sysdate(), '', null, '系统用户菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('系统用户查询', @parentId, '1',  '#', '', 0, 0, 2, 0, 0, 'system:user:detail',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('系统用户新增', @parentId, '2',  '#', '', 0, 0, 2, 0, 0, 'system:user:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('系统用户修改', @parentId, '3',  '#', '', 0, 0, 2, 0, 0, 'system:user:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('系统用户删除', @parentId, '4',  '#', '', 0, 0, 2, 0, 0, 'system:user:delete',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (label, pid, sort, component_name, path, i_frame, cache, type, hidden, delete_flag, perms, icon, create_by, create_time, update_by, update_time)
values('系统用户导出', @parentId, '5',  '#', '', 0, 0, 2, 0, 0, 'system:user:export',       '#', 'admin', sysdate(), '', null, '');