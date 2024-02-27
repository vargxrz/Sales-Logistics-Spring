create table cliente (
    id integer primary key auto_increment,
    nome varchar(100),
    cpf varchar(11)
);

create table produto (
    id integer primary key auto_increment,
    descricao varchar(100),
    precoUnitario numeric(20, 2)
);

create table pedido (
    id integer primary key auto_increment,
    idCliente integer references cliente(id),
    dataPedido timestamp,
    status varchar(20),
    total numeric(20, 2)
);

create table ItemPedido (
    id integer primary key auto_increment,
    IdPedido integer references pedido(id),
    IdProduto integer references produto(id),
    quantidade integer
);