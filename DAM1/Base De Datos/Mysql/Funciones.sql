USE sakila;

DROP PROCEDURE saludo;

 -- DELIMITER $€
DELIMITER //
CREATE PROCEDURE saludo()
BEGIN
	SELECT "¡Hola clase de DAM!" AS mensaje;
    SELECT "¡Hola clase de DAM! (1)" AS mensaje;
    SELECT "¡Hola clase de DAM! (2)" AS mensaje;
END //
DELIMITER ;

CALL sakila.saludo()

DELIMITER //
CREATE PROCEDURE buscar_actor(IN p_apellido VARCHAR(50))
BEGIN
	SELECT first_name, last_name FROM actor
	WHERE last_name LIKE CONCAT(p_apellido, '%');
END //
DELIMITER ;

CALL buscar_actor('Jackman');

DELIMITER //
CREATE PROCEDURE contar_peliculas(OUT p_total INT)
BEGIN
	SELECT COUNT(*) INTO p_total FROM film;
END //
DELIMITER ;

CALL contar_peliculas();

DELIMITER //
CREATE FUNCTION calcular_iva(p_precio DECIMAL(10,2))
RETURNS DECIMAL(10,2) DETERMINISTIC
BEGIN
	RETURN p_precio * 1.21;
END //
DELIMITER ;

CALL calcular_iva(10); -- no existe

CALL `sakila`.`film_in_stock`(15,2, @resultado);
SELECT @resultado;
SELECT calcular_iva(@resultado);

SELECT calcular_iva(amount) FROM payment;

-- Cuantos inventories hay disponibles en cada tienda
SELECT store_id, COUNT(inventory_id) AS num_stock
FROM
	inventory
		JOIN
	store USING(store_id)
WHERE -- inventories que estén disponibles, es decir, que su fecha de devolución sea null
	inventory_in_stock(inventory_id)
GROUP BY store_id;

SELECT * FROM rental;
SELECT inventory_id,COUNT(rental_id) FROM rental GROUP BY inventory_id;

DELIMITER ;;
CREATE TRIGGER `ins_film` AFTER INSERT ON `film` FOR EACH ROW BEGIN
	INSERT INTO film_text (film_id, title, description)
		VALUES (new.film_id, new.title, new.description);
	END;;