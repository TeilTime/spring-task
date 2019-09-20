create table sale_customer (
     `id` int not null auto_increment comment '客户编号',
     `name` varchar(50) not null comment '客户名称',
     `mobilephone` varchar(12) comment '客户电话号码',
     `description` varchar(100) DEFAULT 'description' COMMENT '这是一个描述',
     PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';
