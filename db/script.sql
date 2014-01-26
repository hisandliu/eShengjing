/*
drop table if exists toc_tree;
create table toc_tree
(
    id integer primary key autoincrement not null,
    parent_id int(11) not null,
    depth int(11) not null,
    path_string varchar(255) not null,
    content_id int(11) not null,
    sort_order int(11) not null,
    is_hidden int(11)	not null
);

drop table if exists toc_content;
create table toc_content
(
    id integer primary key autoincrement not null,
    class_id	int(11)	not	null, -- toc_class.id,类编号
    ref_id	varchar(255)	null, -- 	参考id
    name	varchar(255)	not null, -- 	名称
    owner_id	int(11)	not null, -- 	toc_user.id,所属用户
    status	int(11)	not null -- 	状态
);

drop table if exists toc_class;
create table toc_class
(
    id integer primary key autoincrement not null,
    code	varchar(50)	not null, --	唯一标识
    name	varchar(255)	not null, --		类名称
    is_container	int(11)	not null, --		是否是容器
    is_content	int(11)	not null, --		是否是内容
    allow_parent_ids	varchar(1000)	not null, --		允许挂靠的父节点类型(可能是多个toc_class.id,用逗号分隔)
    modified	datetime null, --		修改日期
    modifier_id	int(11) null, --		toc_user.id,修改用户
    created	datetime null, --		新建日期
    creator_id	int(11) null --		toc_user.id,新建用户
);

drop table if exists toc_contentvalue;
create table toc_contentvalue
(
    id integer primary key autoincrement not null,
    content_id	int(11)	not null, -- 	toc_content.id,对应的内容编号
    translate_id	varchar(10)	not null, -- 	语言,(zh_TW,zh_CN,en_US)
    name	varchar(255)	null, -- 	名称
    title	varchar(255)	null, -- 	标题
    intro	varchar(255)	null, -- 	介绍
    content	text	null, -- 	内容
    data_float	float	null, -- 	浮点数
    data_int	int(11)	null, -- 	整数
    data_text	text	null, -- 	文本
    data_date	datetime	null, -- 	日期
    modified	timestamp	null, -- 	修改日期
    modifier_id	int(11)	null, -- 	toc_user.id,修改用户
    created	timestamp	null, -- 	新建日期
    creator_id	int(11)	null -- 	toc_user.id,新建用户
);


drop table if exists toc_user;
create table toc_user
(
    id integer primary key autoincrement not null,
    email	varchar(150)	not null, -- 	邮箱（唯一）
    login	varchar(150)	not null, -- 	登录用户（唯一）
    password_hash	varchar(50)	not null, -- 	密码
    password_hash_type	varchar(11)	not null -- 	加密类型
);

drop table if exists toc_activity_log;
create table toc_activity_log
(
    id integer primary key autoincrement not null,
    userid	int(11)	not null,	
    logtime	datetime	not null,	
    action	varchar(4000)	not null,	
    keycode	varchar(4000)	not null,	
    content	text	not null
);

drop table if exists resource;
create table resource
(
    id integer primary key autoincrement not null,
    code	int(11)	not null, --	
    parent_id	int(11)	not null, --	
    depth	int(11)	not null, --	
    type	int(11)	not null, --	
    child_count	int(11)	not null, --	
    sort_order	int(11)	not null, --	
    cnname	varchar(255)	null, --	名称
    enname	varchar(255)	null, --	名称
    twname	varchar(255)	null, --	名称
    cncontent	text	null, --	内容
    encontent	text	null, --	内容
    twcontent	text	null, --	内容
    cntitle	varchar(255)	null, --	
    entitle	varchar(255)	null, --	
    twtitle	varchar(255)	null, --	
    cnintro	varchar(4000)	null, --	
    enintro	varchar(4000)	null, --	
    twintro	varchar(4000)	null, --	
    tags	varchar(4000)	null
);
	
*/

select * from toc_tree 
select max(id) from toc_tree;
-- 
insert into toc_tree(parent_id, depth, path_string, content_id, sort_order, is_hidden)
values (1, 1, '/1/', 0, 0, 0);

select last_insert_rowid();

-- delete from toc_tree 
select * from toc_tree;
select * from toc_content where name = '第 1 章';
select * from toc_contentvalue order by id desc;
select * from toc_content  order by id desc;
select datetime(),datetime('now');
select sqlite_version(*) 
select datetime('now','localtime');
select datetime('localtime');
Genesis 9:29
/*
delete from toc_contentvalue where content_id in (select content_id from toc_tree where path_string like '%/1/2/%')
delete from toc_content where content_id in (select content_id from toc_tree where path_string like '%/1/2/%')
delete from toc_tree where path_string like '%/1/2/%'
*/
/1/3/48/
/1/2/26318/

select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where toc_tree.depth=2 


select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where toc_tree.path_string like '%/1/3/48/%' 

select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where toc_tree.path_string like '%/1/2/26318/%' 

select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where toc_content.ref_id='Romans'

select * from toc_tree where parent_id = 1 
and content_id in (select id from toc_content where ref_id = 'Old Testament')
第 6 章

select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where parent_id=2 

select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where parent_id=3

select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where parent_id=4

select * from toc_content 
Jeremiah 48
select * from toc_tree where dep

-- attach database book2 as book2;
-- select * from e1.toc_tree

select * from book2.toc_tree;


-- 1 

select toc_tree.id, max(toc_content.ref_id)
    from toc_tree inner join toc_content on toc_tree.content_id = toc_content.id 
    where toc_tree.parent_id in (
    select id from toc_tree where depth = 3
)
group by toc_tree.parent_id



 select toc_tree.parent_id,toc_tree.id tree_id,toc_tree.content_id,toc_content.ref_id 
    from toc_tree inner join toc_content on toc_tree.content_id = toc_content.id 
    where toc_tree.parent_id in (
    select id from toc_tree where depth = 3
)

select * from
(
    select toc_tree.id tree_id,toc_content.ref_id from toc_tree 
    inner join toc_content on toc_tree.content_id = toc_content.id where depth = 3
) t1
inner join 
(
    select toc_tree.parent_id,toc_tree.id tree_id,toc_tree.content_id,toc_content.ref_id 
    from toc_tree inner join toc_content on toc_tree.content_id = toc_content.id 
) t2 
on t1.tree_id = t2.parent_id



select t1.ref_id ref_id,max(t2.ref_id) maxname from
(
    select toc_tree.id,toc_content.ref_id from toc_tree 
    inner join toc_content on toc_tree.content_id = toc_content.id where depth = 3
) t1
inner join 
(
    select toc_tree.parent_id,toc_tree.id tree_id,toc_tree.content_id,toc_content.ref_id 
    from toc_tree inner join toc_content on toc_tree.content_id = toc_content.id where depth = 4
) t2 
on t1.id = t2.parent_id
group by t1.ref_id 
order by ref_id, maxname


select toc_tree.*,toc_content.ref_id from toc_tree 
inner join toc_content on toc_tree.content_id = toc_content.id where depth = 3
order by toc_tree.id


select toc_tree.*,toc_content.ref_id,toc_content.name from toc_tree 
inner join toc_content on toc_tree.content_id=toc_content.id
where depth = 3 and toc_tree.id not in (select parent_id from toc_tree)
order by toc_content.ref_id


select toc_tree.*,toc_content.ref_id,toc_content.name from toc_tree 
inner join toc_content on toc_tree.content_id=toc_content.id where toc_content.ref_id='Jeremiah 51'


select toc_tree.*,toc_content.ref_id,toc_content.name from toc_tree 
inner join toc_content on toc_tree.content_id=toc_content.id
where depth = 3 and toc_tree.id not in (select parent_id from toc_tree where depth = 4)
and toc_content.ref_id not in
(
select toc_content.ref_id from toc_tree 
inner join toc_content on toc_tree.content_id=toc_content.id 
where depth = 3 and toc_tree.id in (select parent_id from toc_tree where depth = 4)
)

-- alter table toc_tree add child_count int(11)
select * from toc_tree
update toc_tree set child_count

select * from toc_tree
inner join 
(
select toc_tree.id, count(*) count from toc_tree
inner join toc_tree child on toc_tree.id=child.parent_id
group by toc_tree.id
) t2
on toc_tree.id=t2.id

/*
update toc_tree set child_count=t2.count from toc_tree
inner join 
(
select toc_tree.id, count(*) count from toc_tree
inner join toc_tree child on toc_tree.id=child.parent_id
group by toc_tree.id
) t2
on toc_tree.id=t2.id
*/

select * from toc_tree;

/*
update toc_tree set child_count=(select count(*) from toc_tree where parent_id=2)
and toc_tree.id=2
*/

inner join 
(
select toc_tree.id, count(*) count from toc_tree
inner join toc_tree child on toc_tree.id=child.parent_id
group by toc_tree.id
) t2
on toc_tree.id=t2.id


select *  from toc_tree
inner join toc_tree child on toc_tree.id=child.parent_id
group by toc_tree.id




select cn.content cncontent, en.content encontent
from toc_content 
inner join toc_contentvalue cn on toc_content.id=cn.content_id and cn.translate_id='zh_CN'
inner join toc_contentvalue en on toc_content.id=en.content_id and en.translate_id='en_US'
where toc_content.ref_id='Genesis 1:1' 


select * from toc_content where ref_id = 'Genesis 1:1';


"select cn.content cncontent, en.content encontent " +
"from toc_content " + 
"inner join toc_contentvalue cn on toc_content.id=cn.content_id and cn.translate_id='zh_CN' " +
"inner join toc_contentvalue en on toc_content.id=en.content_id and en.translate_id='en_US' " +
"where toc_content.ref_id='Genesis 1:1' " + 

select * from toc_tree where id=843


E:\hisand\bible\src\Bible\bin\sqlitejdbc-v056.jar


select * from toc_contentvalue where content_id=17798

select * from toc_tree where parent_id in 
(select toc_tree.id from toc_tree inner join toc_content on 
    toc_tree.content_id=toc_content.id where ref_id like 'Jeremiah 29') order by sort_order
    
    
万军之耶和华以色列的神论到哥赖雅的儿子亚哈，并玛西雅的儿子西底家，如此说，他们是托我名向你们说假预言的，我必将他们交在巴比伦王尼布甲尼撒的手中。他要在你们眼前杀害他们。
Thus saith the LORD of hosts, the God of Israel, of Ahab the son of Kolaiah, and of Zedekiah the son of Maaseiah, which prophesy a lie unto you in my name; Behold, I will deliver them into the hand of Nebuchadrezzar king of Babylon; and he shall slay them before your eyes;

--17790	2	Jeremiah 29:20	29:20	1	0

----insert into toc_content (class_id,ref_id,name,owner_id,status)
--values (2,'Jeremiah 29:20','29:20',1,0)

32359

17791	843	4	/1/2/27/843/17791/	17790	20	0	

select * from toc_tree where content_id=32360

/1/2/27/843/32360/


2	Jeremiah 29:31	29:31	1	0
-- insert into toc_content(class_id,ref_id,name,owner_id,status) values (2,'Jeremiah 29:32','29:32',1,0)
select * from toc_tree where content_id=32359
-- insert into toc_tree(parent_id,depth,path_string,content_id,sort_order,is_hidden)
values (843,4,'/',32360,32,0)
-- update toc_tree set path_string = '/1/2/27/843/32361/' where id=32361

17793	zh_CN	29:24	29:24		论到尼希兰人示玛雅，你当说，

/*
insert into toc_contentvalue(content_id,translate_id,name,title,content)
values(32359,'zh_CN','29:21','29:21',
'万军之耶和华以色列的神论到哥赖雅的儿子亚哈，并玛西雅的儿子西底家，如此说，他们是托我名向你们说假预言的，我必将他们交在巴比伦王尼布甲尼撒的手中。他要在你们眼前杀害他们。'
)
insert into toc_contentvalue(content_id,translate_id,name,title,content)
values(32359,'en_US','29:21','29:21',
'Thus saith the LORD of hosts, the God of Israel, of Ahab the son of Kolaiah, and of Zedekiah the son of Maaseiah, which prophesy a lie unto you in my name; Behold, I will deliver them into the hand of Nebuchadrezzar king of Babylon; and he shall slay them before your eyes;'
)

insert into toc_contentvalue(content_id,translate_id,name,title,content)
values(32360,'zh_CN','29:21','29:32',
'所以耶和华如此说，我必刑罚尼希兰人示玛雅和他的后裔，他必无一人存留住在这民中，也不得见我所要赐与我百姓的福乐，因为他向耶和华说了叛逆的话。这是耶和华说的。'
)
insert into toc_contentvalue(content_id,translate_id,name,title,content)
values(32360,'en_US','29:21','29:32',
'Therefore thus saith the LORD; Behold, I will punish Shemaiah the Nehelamite, and his seed: he shall not have a man to dwell among this people; neither shall he behold the good that I will do for my people, saith the LORD; because he hath taught rebellion against the LORD.'
)
*/
select * from toc_content where ref_id like 'Jeremiah 29'
select * from toc_contentvalue where content like '%尼希兰%'
select * from toc_contentvalue where content_id=32359
select * from toc_contentvalue where content_id=32360
select * from toc_content where ref_id like 'Jeremiah 29:32'
select * from toc_content where ref_id like 'Jeremiah 29:21'

select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where ref_id like 'Jeremiah%'

select toc_content.*,toc_tree.* from toc_tree inner join toc_content 
on toc_tree.content_id=toc_content.id where parent_id = 817


select *  from toc_tree
inner join toc_tree child on toc_tree.id=child.parent_id

select toc_tree.id, count(toc_tree.id) count from toc_tree
inner join toc_tree child on toc_tree.id=child.parent_id
group by toc_tree.id order by count 



select * from toc_contentvalue order by id desc



select toc_tree.*,toc_content.* from toc_tree 
inner join toc_content on toc_tree.content_id=toc_content.id
where depth <= 3 and toc_tree.id not in (select parent_id from toc_tree)
order by toc_content.ref_id

select toc_tree.*,toc_content.* from toc_tree 
inner join toc_content on toc_tree.content_id=toc_content.id
where depth <= 3 order by depth, id, sort_order


select * from toc_content 
where id in (select content_id from toc_tree where depth=4)
and id not in (select content_id,count(*) from toc_contentvalue group by cone)

-- select content_id,count(*) count from toc_contentvalue group by content_id having count<>2
select * from toc_contentvalue where content_id not in (select id from toc_content)


select * from toc_content where ref_id like '%peter%'

select * from toc_contentvalue where name like '%peter%' or title like '%peter%'



select * from toc_content


select t.id tree_id, t.child_count,t.sort_order, c.* from toc_content c 
inner join toc_tree t on c.id=t.content_id  
where t.sort_order in (select sort_order - 1 from toc_tree where content_id=5) 
and t.parent_id in (select parent_id from toc_tree where content_id=5 )

select * from toc_content;
select * from resource where parent_id=0;
-- update resource set parent_id=0 where id=1 or id=24115


select * from toc_tree;
select * from toc_contentvalue


select * from resource where code like '1 Peter%';


select length(trim('   '));
select replace(' sdf vvv v', ' ', '_');

select replace(code, cnname, '') from resource where depth=4;

-- select like ('%[ ]%', '% %')

-- select * from resource where code like '_ %'
select substr('1 Peter', 1, 1)
select substr('1 Peter', 3, length('1 Peter') - 2);
select * from resource where code like '%Song of Songs%';

select substr(code, 1, 1),substr(code, 3, length(code) - 2) from resource where code like '_ %';

select substr(code, 1, 1) || '_' || substr(code, 3, length(code) - 2) from resource where code like '_ %' and depth=4;

-- update resource set code = substr(code, 1, 1) || '_' || substr(code, 3, length(code) - 2) where code like '_ %' and depth=4;

select replace(code, 'Song of Songs', 'Song_of_Songs') from resource where code like '%Song of Songs%';
-- 1-Thessalonians-2
select * from resource order by depth, sort_order;


-- update resource set code = substr(code, 1, 1) || '_' || substr(code, 3, length(code) - 2) where code like '_ %'
-- update resource set code = replace(code, 'Song of Songs', 'Song_of_Songs')  where code like '%Song of Songs%';
-- update resource set code = replace(code, ' ', '-') 
-- update resource set code = replace(code, ':', '-') 




delimiter $$
drop table if exists resource;
create table resource
(
    id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    code varchar(255)	not null,
    parent_code varchar(255) null,	
    parent_id	int(11)	not null,
    depth	int(11)	not null,
    type	int(11)	not null,
    child_count	int(11)	not null,
    sort_order	int(11)	not null, 
    cnname	varchar(255)	null,
    enname	varchar(255)	null,
    twname	varchar(255)	null,
    cncontent	text	null, 
    encontent	text	null, 
    twcontent	text	null, 
    cntitle	varchar(255)	null,
    entitle	varchar(255)	null, 
    twtitle	varchar(255)	null, 	
    cnintro	varchar(4000)	null, 
    enintro	varchar(4000)	null,
    twintro	varchar(4000)	null, 
    tags	varchar(4000)	null,
    PRIMARY KEY (id),
    KEY resouce_sort_order (sort_order)
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8$$








