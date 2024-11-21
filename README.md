# SistemaPedidos

Codigo criado para implementação do gerenciamento dos pedidos.

Desenvolvido em: Java 21, SpringFrameWork + Apache Camel Framework, Docker, MariaDB, ActiveMQ.

## Instalação.

1 - No diretoriov /SistemaPedidos/docker-environment digite: docker-composse up -d
2 - Após a subida das instancias do Docker ( Docker mariadb; Docker activemq )
3 - Abra o projeto utilizando uma IDE Java (sugestão: IntelliJ )
4 - Execute a aplicação: SistemaPedidosApplication

## ActiveMQ

Ao rodar a aplicação spring serão criadas duas queues denominadas:

### Sistema-A
queue.com.example.SistemaA.orders (ENTRADA DE DADOS COM ORIGEM NO SISTEMA A)

### Sistema-B
queue.com.example.SistemaB.integration (SAIDA DE DADOS COM DESTINO A INTEGRAÇÂO COM O SISTEMA B)

### Payload (JSON) - MSG recebida através da queue ( queue.com.example.SistemaA.orders ) que será processada para o calculo do valor do pedido.

{
    "date": "22-10-2024 11:23:44",
    "code": "26",
    "orderStatus": "WAITING_VALUES",
    "subTotal": 0,
    "client": {
        "id": 3,
        "name": "Mercado Mix",
        "email": "comercial@mercadomix.com.br"
    },
    "itens": [
        {
            "amount": 48,
            "ean": "2321235212131",
            "products": {
                "id": 3
            }
        },
        {
            "amount": 24,
            "ean": "1112332232232",
            "products": {
                "id": 4
            }
        }
    ]
}
