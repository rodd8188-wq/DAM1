class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

class Ecommerce:
    def validarInventario(self, carrito):
        for producto, cantidad in carrito.items():
            if producto.stock < cantidad:
                print(f"Sin stock: {producto.nombre}")
                return False
        return True

    def aplicarCupon(self, total, descuento):
        print(f"Cupón aplicado: {descuento}%")
        return total - (total * descuento / 100)

    def finalizarCompra(self, carrito, usar_cupon=False):
        print("")
        print("Finalizando compra...")
        if not self.validarInventario(carrito):
            print("Compra cancelada")
            return
        total = 0
        for producto, cantidad in carrito.items():
            total += producto.precio * cantidad
        if usar_cupon:
            total = self.aplicarCupon(total, 10)
        print(f"Compra finalizada. Total: ${total}")
        for producto, cantidad in carrito.items():
            producto.stock -= cantidad



p1 = Producto("Laptop", 1000, 5)
p2 = Producto("Mouse", 50, 10)

carrito = {
    p1: 1,p2: 2
}

tienda = Ecommerce()

tienda.finalizarCompra(carrito, usar_cupon=False)
tienda.finalizarCompra(carrito, usar_cupon=True)
