SELECT
pr.nombre AS producto,
dp.id_pedido AS pedido
FROM
detalle_pedido AS dp
JOIN
productos AS pr
ON dp.id_producto = pr.id_producto
ORDER BY
pr.nombre ASC,
dp.id_pedido ASC;
SELECT
c.nombre AS cliente,
p.id_pedido AS pedido,
p.coste_total AS coste_total
FROM
pedidos AS p
JOIN
clientes AS c
ON p.id_cliente = c.id_cliente
ORDER BY
p.coste_total DESC,
p.id_pedido ASC;
SELECT
p.id_pedido AS pedido,
c.nombre AS cliente,
p.fecha_pedido AS fecha_pedido
FROM
pedidos AS p
JOIN
clientes AS c
ON p.id_cliente = c.id_cliente
WHERE
p.fecha_pedido >= '2024-01-01 00:00:00'
ORDER BY
p.fecha_pedido ASC,
p.id_pedido ASC;
SELECT
p.id_pedido AS pedido,
c.nombre AS cliente,
p.estado AS estado,
p.coste_total AS coste_total
FROM pedidos AS p
INNER JOIN clientes AS c
ON p.id_cliente = c.id_cliente
WHERE p.estado IN ('cancelado', 'pendiente')
ORDER BY
p.estado ASC,
p.coste_total DESC;
SELECT
p.id_pedido AS pedido,
c.nombre AS cliente,
pa.metodo_pago AS metodo_pago
FROM pedidos AS p
INNER JOIN clientes AS c
ON p.id_cliente = c.id_cliente
INNER JOIN pagos AS pa
ON p.id_pedido = pa.id_pedido
ORDER BY p.id_pedido ASC;
SELECT
pr.nombre AS producto,
dp.cantidad AS cantidad,
dp.precio_unitario AS precio_unitario
FROM detalle_pedido AS dp
INNER JOIN productos AS pr
ON dp.id_producto = pr.id_producto
WHERE dp.id_pedido = 10
ORDER BY pr.nombre ASC;
SELECT
p.id_pedido AS pedido,
SUM(pa.total_pagado) AS total_pagado
FROM pedidos AS p
INNER JOIN pagos AS pa
ON p.id_pedido = pa.id_pedido
GROUP BY p.id_pedido
ORDER BY total_pagado DESC, p.id_pedido ASC;
SELECT
c.nombre AS cliente,
COUNT(p.id_pedido) AS total_pedidos
FROM clientes AS c
INNER JOIN pedidos AS p
ON c.id_cliente = p.id_cliente
GROUP BY c.id_cliente, c.nombre
ORDER BY total_pedidos DESC, c.nombre ASC;
SELECT
c.nombre AS cliente,
COUNT(p.id_pedido) AS total_pedidos
FROM clientes AS c
LEFT JOIN pedidos AS p
ON c.id_cliente = p.id_cliente
GROUP BY c.id_cliente, c.nombre
ORDER BY total_pedidos DESC, c.nombre ASC;

