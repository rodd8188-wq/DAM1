# PROYECTO 3: Blog con SQLAlchemy ORM
# Nivel: Avanzado
# Concepto: ORM, relaciones complejas, consultas avanzadas

"""
REQUISITOS:
pip install sqlalchemy

SETUP:
Este proyecto usa SQLite para facilidad, pero SQLAlchemy soporta cualquier BD.
Para cambiar a MySQL:
  DATABASE_URL = 'mysql+pymysql://user:password@localhost/blog_db'
"""

from sqlalchemy import create_engine, Column, Integer, String, Text, DateTime, Float, ForeignKey, Table, Boolean
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, relationship, Session
from datetime import datetime
from typing import List, Optional

# ============ CONFIGURACIÓN ==========

DATABASE_URL = 'sqlite:///blog.db'
# Para MySQL: 'mysql+pymysql://root:password@localhost/blog_db'

engine = create_engine(DATABASE_URL, echo=False)
SessionLocal = sessionmaker(bind=engine)
Base = declarative_base()


# ============ MODELOS ==========

# Tabla de relación Many-to-Many para tags
post_tags = Table(
    'post_tags',
    Base.metadata,
    Column('post_id', Integer, ForeignKey('posts.id'), primary_key=True),
    Column('tag_id', Integer, ForeignKey('tags.id'), primary_key=True)
)


class Usuario(Base):
    """Modelo de Usuario (Autor)"""
    __tablename__ = 'usuarios'
    
    id = Column(Integer, primary_key=True)
    nombre = Column(String(100), nullable=False)
    email = Column(String(100), unique=True, nullable=False)
    contraseña = Column(String(255), nullable=False)
    bio = Column(Text)
    fecha_registro = Column(DateTime, default=datetime.now)
    activo = Column(Boolean, default=True)
    
    # Relaciones
    posts = relationship('Post', back_populates='autor', cascade='all, delete-orphan')
    comentarios = relationship('Comentario', back_populates='autor', cascade='all, delete-orphan')
    
    def __repr__(self):
        return f"<Usuario(id={self.id}, nombre='{self.nombre}')>"
    
    def cantidad_posts(self):
        """Retorna cantidad de posts del usuario"""
        return len(self.posts)


class Categoria(Base):
    """Modelo de Categoría"""
    __tablename__ = 'categorias'
    
    id = Column(Integer, primary_key=True)
    nombre = Column(String(100), unique=True, nullable=False)
    descripcion = Column(Text)
    
    # Relaciones
    posts = relationship('Post', back_populates='categoria', cascade='all, delete-orphan')
    
    def __repr__(self):
        return f"<Categoria(nombre='{self.nombre}')>"


class Tag(Base):
    """Modelo de Tag"""
    __tablename__ = 'tags'
    
    id = Column(Integer, primary_key=True)
    nombre = Column(String(50), unique=True, nullable=False)
    
    # Relaciones
    posts = relationship('Post', secondary=post_tags, back_populates='tags')
    
    def __repr__(self):
        return f"<Tag(nombre='{self.nombre}')>"


class Post(Base):
    """Modelo de Post/Artículo"""
    __tablename__ = 'posts'
    
    id = Column(Integer, primary_key=True)
    titulo = Column(String(200), nullable=False)
    contenido = Column(Text, nullable=False)
    resumen = Column(String(500))
    autor_id = Column(Integer, ForeignKey('usuarios.id'), nullable=False)
    categoria_id = Column(Integer, ForeignKey('categorias.id'), nullable=False)
    fecha_creacion = Column(DateTime, default=datetime.now)
    fecha_actualizacion = Column(DateTime, default=datetime.now, onupdate=datetime.now)
    publicado = Column(Boolean, default=False)
    vistas = Column(Integer, default=0)
    
    # Relaciones
    autor = relationship('Usuario', back_populates='posts')
    categoria = relationship('Categoria', back_populates='posts')
    comentarios = relationship('Comentario', back_populates='post', cascade='all, delete-orphan')
    tags = relationship('Tag', secondary=post_tags, back_populates='posts')
    
    def __repr__(self):
        return f"<Post(titulo='{self.titulo}')>"
    
    def cantidad_comentarios(self):
        """Retorna cantidad de comentarios"""
        return len(self.comentarios)
    
    def incrementar_vistas(self):
        """Incrementa el contador de vistas"""
        self.vistas += 1


class Comentario(Base):
    """Modelo de Comentario"""
    __tablename__ = 'comentarios'
    
    id = Column(Integer, primary_key=True)
    contenido = Column(Text, nullable=False)
    post_id = Column(Integer, ForeignKey('posts.id'), nullable=False)
    autor_id = Column(Integer, ForeignKey('usuarios.id'), nullable=False)
    fecha_creacion = Column(DateTime, default=datetime.now)
    aprobado = Column(Boolean, default=True)
    
    # Relaciones
    post = relationship('Post', back_populates='comentarios')
    autor = relationship('Usuario', back_populates='comentarios')
    
    def __repr__(self):
        return f"<Comentario(post_id={self.post_id}, autor_id={self.autor_id})>"


# ============ SERVICIO DE BD ==========

class BlogService:
    """Servicio para operaciones del blog"""
    
    def __init__(self):
        """Inicializa la BD"""
        Base.metadata.create_all(engine)
        self.session = SessionLocal()
    
    def cerrar(self):
        """Cierra la sesión"""
        self.session.close()
    
    # ========== USUARIOS ==========
    
    def crear_usuario(self, nombre: str, email: str, contraseña: str, bio: str = '') -> Usuario:
        """Crea un nuevo usuario"""
        usuario = Usuario(
            nombre=nombre,
            email=email,
            contraseña=contraseña,  # En producción: hashear
            bio=bio
        )
        self.session.add(usuario)
        self.session.commit()
        print(f"✓ Usuario '{nombre}' creado")
        return usuario
    
    def obtener_usuario(self, usuario_id: int) -> Optional[Usuario]:
        """Obtiene un usuario por ID"""
        return self.session.query(Usuario).filter_by(id=usuario_id).first()
    
    def obtener_usuario_por_email(self, email: str) -> Optional[Usuario]:
        """Obtiene un usuario por email"""
        return self.session.query(Usuario).filter_by(email=email).first()
    
    def listar_usuarios(self) -> List[Usuario]:
        """Lista todos los usuarios activos"""
        return self.session.query(Usuario).filter_by(activo=True).all()
    
    def eliminar_usuario(self, usuario_id: int) -> bool:
        """Elimina un usuario"""
        usuario = self.obtener_usuario(usuario_id)
        if usuario:
            self.session.delete(usuario)
            self.session.commit()
            print(f"✓ Usuario eliminado")
            return True
        return False
    
    # ========== CATEGORÍAS ==========
    
    def crear_categoria(self, nombre: str, descripcion: str = '') -> Categoria:
        """Crea una nueva categoría"""
        categoria = Categoria(nombre=nombre, descripcion=descripcion)
        self.session.add(categoria)
        self.session.commit()
        print(f"✓ Categoría '{nombre}' creada")
        return categoria
    
    def obtener_categoria(self, categoria_id: int) -> Optional[Categoria]:
        """Obtiene una categoría por ID"""
        return self.session.query(Categoria).filter_by(id=categoria_id).first()
    
    def listar_categorias(self) -> List[Categoria]:
        """Lista todas las categorías"""
        return self.session.query(Categoria).all()
    
    # ========== TAGS ==========
    
    def crear_o_obtener_tag(self, nombre: str) -> Tag:
        """Crea o obtiene un tag"""
        tag = self.session.query(Tag).filter_by(nombre=nombre).first()
        if not tag:
            tag = Tag(nombre=nombre)
            self.session.add(tag)
            self.session.commit()
        return tag
    
    def listar_tags(self) -> List[Tag]:
        """Lista todos los tags"""
        return self.session.query(Tag).all()
    
    # ========== POSTS ==========
    
    def crear_post(self, titulo: str, contenido: str, autor_id: int, categoria_id: int,
                   resumen: str = '', tags: List[str] = None) -> Post:
        """Crea un nuevo post"""
        post = Post(
            titulo=titulo,
            contenido=contenido,
            resumen=resumen or contenido[:500],
            autor_id=autor_id,
            categoria_id=categoria_id
        )
        
        # Agregar tags
        if tags:
            for tag_nombre in tags:
                tag = self.crear_o_obtener_tag(tag_nombre)
                post.tags.append(tag)
        
        self.session.add(post)
        self.session.commit()
        print(f"✓ Post '{titulo}' creado")
        return post
    
    def obtener_post(self, post_id: int) -> Optional[Post]:
        """Obtiene un post por ID"""
        post = self.session.query(Post).filter_by(id=post_id).first()
        if post:
            post.incrementar_vistas()
            self.session.commit()
        return post
    
    def listar_posts_publicados(self, categoria_id: int = None) -> List[Post]:
        """Lista posts publicados"""
        query = self.session.query(Post).filter_by(publicado=True)
        
        if categoria_id:
            query = query.filter_by(categoria_id=categoria_id)
        
        return query.order_by(Post.fecha_creacion.desc()).all()
    
    def listar_posts_por_autor(self, autor_id: int) -> List[Post]:
        """Lista posts de un autor"""
        return self.session.query(Post).filter_by(autor_id=autor_id).order_by(Post.fecha_creacion.desc()).all()
    
    def listar_posts_por_tag(self, tag_nombre: str) -> List[Post]:
        """Lista posts con un tag específico"""
        tag = self.session.query(Tag).filter_by(nombre=tag_nombre).first()
        if tag:
            return tag.posts
        return []
    
    def actualizar_post(self, post_id: int, **kwargs) -> Optional[Post]:
        """Actualiza un post"""
        post = self.obtener_post(post_id)
        if post:
            for key, value in kwargs.items():
                if hasattr(post, key):
                    setattr(post, key, value)
            self.session.commit()
            print(f"✓ Post actualizado")
        return post
    
    def publicar_post(self, post_id: int) -> bool:
        """Publica un post"""
        post = self.obtener_post(post_id)
        if post:
            post.publicado = True
            self.session.commit()
            print(f"✓ Post publicado")
            return True
        return False
    
    def eliminar_post(self, post_id: int) -> bool:
        """Elimina un post"""
        post = self.session.query(Post).filter_by(id=post_id).first()
        if post:
            self.session.delete(post)
            self.session.commit()
            print(f"✓ Post eliminado")
            return True
        return False
    
    # ========== COMENTARIOS ==========
    
    def crear_comentario(self, contenido: str, post_id: int, autor_id: int) -> Comentario:
        """Crea un nuevo comentario"""
        comentario = Comentario(
            contenido=contenido,
            post_id=post_id,
            autor_id=autor_id
        )
        self.session.add(comentario)
        self.session.commit()
        print(f"✓ Comentario agregado")
        return comentario
    
    def listar_comentarios_post(self, post_id: int) -> List[Comentario]:
        """Lista comentarios de un post"""
        return self.session.query(Comentario).filter_by(post_id=post_id, aprobado=True).all()
    
    def aprobar_comentario(self, comentario_id: int) -> bool:
        """Aprueba un comentario"""
        comentario = self.session.query(Comentario).filter_by(id=comentario_id).first()
        if comentario:
            comentario.aprobado = True
            self.session.commit()
            return True
        return False
    
    def eliminar_comentario(self, comentario_id: int) -> bool:
        """Elimina un comentario"""
        comentario = self.session.query(Comentario).filter_by(id=comentario_id).first()
        if comentario:
            self.session.delete(comentario)
            self.session.commit()
            return True
        return False
    
    # ========== REPORTES ==========
    
    def posts_mas_vistos(self, limit: int = 10) -> List[Post]:
        """Posts más visitados"""
        return self.session.query(Post).filter_by(publicado=True).order_by(Post.vistas.desc()).limit(limit).all()
    
    def posts_recientes(self, limit: int = 10) -> List[Post]:
        """Posts más recientes"""
        return self.session.query(Post).filter_by(publicado=True).order_by(Post.fecha_creacion.desc()).limit(limit).all()
    
    def usuario_mas_activo(self) -> Optional[Usuario]:
        """Usuario con más posts"""
        usuarios = self.session.query(Usuario).all()
        if usuarios:
            return max(usuarios, key=lambda u: len(u.posts))
        return None
    
    def categoria_mas_posts(self) -> Optional[Categoria]:
        """Categoría con más posts"""
        categorias = self.session.query(Categoria).all()
        if categorias:
            return max(categorias, key=lambda c: len(c.posts))
        return None
    
    def estadisticas(self):
        """Muestra estadísticas del blog"""
        total_usuarios = self.session.query(Usuario).count()
        total_posts = self.session.query(Post).filter_by(publicado=True).count()
        total_comentarios = self.session.query(Comentario).filter_by(aprobado=True).count()
        total_categorias = self.session.query(Categoria).count()
        
        print("\n" + "="*50)
        print("📊 ESTADÍSTICAS DEL BLOG")
        print("="*50)
        print(f"Usuarios activos: {total_usuarios}")
        print(f"Posts publicados: {total_posts}")
        print(f"Comentarios: {total_comentarios}")
        print(f"Categorías: {total_categorias}")
        
        usuario_activo = self.usuario_mas_activo()
        if usuario_activo:
            print(f"\n👤 Usuario más activo: {usuario_activo.nombre} ({usuario_activo.cantidad_posts()} posts)")
        
        posts_top = self.posts_mas_vistos(5)
        if posts_top:
            print(f"\n📈 Top 5 Posts:")
            for post in posts_top:
                print(f"  • {post.titulo} ({post.vistas} vistas)")
        
        print("="*50 + "\n")


# ============ MENÚ INTERACTIVO ==========

def menu_usuarios(service: BlogService):
    """Menú de gestión de usuarios"""
    while True:
        print("\n--- Gestión de Usuarios ---")
        print("1. Crear usuario")
        print("2. Listar usuarios")
        print("3. Ver perfil")
        print("4. Volver")
        
        opcion = input("Opción: ").strip()
        
        if opcion == '1':
            nombre = input("Nombre: ").strip()
            email = input("Email: ").strip()
            contraseña = input("Contraseña: ").strip()
            bio = input("Biografía (opcional): ").strip()
            service.crear_usuario(nombre, email, contraseña, bio)
        
        elif opcion == '2':
            usuarios = service.listar_usuarios()
            for u in usuarios:
                print(f"  [{u.id}] {u.nombre} ({u.email}) - {u.cantidad_posts()} posts")
        
        elif opcion == '3':
            try:
                uid = int(input("ID Usuario: ").strip())
                usuario = service.obtener_usuario(uid)
                if usuario:
                    print(f"\n{usuario.nombre}")
                    print(f"Email: {usuario.email}")
                    print(f"Bio: {usuario.bio}")
                    print(f"Posts: {usuario.cantidad_posts()}")
                    print(f"Registrado: {usuario.fecha_registro}")
            except ValueError:
                print("ID inválido")
        
        elif opcion == '4':
            break


def menu_posts(service: BlogService):
    """Menú de gestión de posts"""
    while True:
        print("\n--- Gestión de Posts ---")
        print("1. Crear post")
        print("2. Listar posts publicados")
        print("3. Ver post")
        print("4. Publicar post")
        print("5. Eliminar post")
        print("6. Volver")
        
        opcion = input("Opción: ").strip()
        
        if opcion == '1':
            try:
                titulo = input("Título: ").strip()
                contenido = input("Contenido: ").strip()
                
                usuarios = service.listar_usuarios()
                for u in usuarios:
                    print(f"  [{u.id}] {u.nombre}")
                autor_id = int(input("ID Autor: ").strip())
                
                categorias = service.listar_categorias()
                for c in categorias:
                    print(f"  [{c.id}] {c.nombre}")
                categoria_id = int(input("ID Categoría: ").strip())
                
                tags = input("Tags (separados por coma): ").strip().split(',')
                tags = [t.strip() for t in tags if t.strip()]
                
                service.crear_post(titulo, contenido, autor_id, categoria_id, tags=tags)
            except ValueError:
                print("Datos inválidos")
        
        elif opcion == '2':
            posts = service.listar_posts_publicados()
            for p in posts:
                print(f"  [{p.id}] {p.titulo} - {p.autor.nombre} ({p.vistas} vistas)")
        
        elif opcion == '3':
            try:
                pid = int(input("ID Post: ").strip())
                post = service.obtener_post(pid)
                if post:
                    print(f"\n{post.titulo}")
                    print(f"Autor: {post.autor.nombre}")
                    print(f"Categoría: {post.categoria.nombre}")
                    print(f"Vistas: {post.vistas}")
                    print(f"Comentarios: {post.cantidad_comentarios()}")
            except ValueError:
                print("ID inválido")
        
        elif opcion == '4':
            try:
                pid = int(input("ID Post: ").strip())
                service.publicar_post(pid)
            except ValueError:
                print("ID inválido")
        
        elif opcion == '5':
            try:
                pid = int(input("ID Post: ").strip())
                service.eliminar_post(pid)
            except ValueError:
                print("ID inválido")
        
        elif opcion == '6':
            break


def menu_principal():
    """Menú principal"""
    service = BlogService()
    
    # Crear datos de prueba si es necesario
    if service.listar_usuarios() == []:
        print("Creando datos de prueba...\n")
        
        # Usuarios
        u1 = service.crear_usuario("Juan", "juan@blog.com", "pass123", "Escritor de tecnología")
        u2 = service.crear_usuario("María", "maria@blog.com", "pass456", "Blogger de viajes")
        
        # Categorías
        c1 = service.crear_categoria("Tecnología", "Artículos sobre tecnología")
        c2 = service.crear_categoria("Viajes", "Guías y experiencias de viaje")
        
        # Posts
        p1 = service.crear_post(
            "Introducción a Python",
            "Python es un lenguaje de programación versátil y fácil de aprender...",
            u1.id, c1.id,
            tags=["python", "programación", "tutorial"]
        )
        service.publicar_post(p1.id)
        
        p2 = service.crear_post(
            "Mi viaje a Japón",
            "Hace poco visité Japón y fue una experiencia increíble...",
            u2.id, c2.id,
            tags=["viajes", "asia", "cultura"]
        )
        service.publicar_post(p2.id)
        
        # Comentarios
        service.crear_comentario("¡Excelente artículo!", p1.id, u2.id)
        service.crear_comentario("Gracias por compartir!", p2.id, u1.id)
        
        print("\n✓ Datos de prueba creados\n")
    
    while True:
        print("\n" + "="*50)
        print("📝 BLOG CON SQLAlchemy ORM")
        print("="*50)
        print("1. Gestión de Usuarios")
        print("2. Gestión de Posts")
        print("3. Gestión de Categorías")
        print("4. Reportes y Estadísticas")
        print("5. Salir")
        print("="*50)
        
        opcion = input("Opción (1-5): ").strip()
        
        if opcion == '1':
            menu_usuarios(service)
        elif opcion == '2':
            menu_posts(service)
        elif opcion == '3':
            while True:
                print("\n--- Gestión de Categorías ---")
                print("1. Crear categoría")
                print("2. Listar categorías")
                print("3. Volver")
                
                opt = input("Opción: ").strip()
                if opt == '1':
                    nombre = input("Nombre: ").strip()
                    desc = input("Descripción: ").strip()
                    service.crear_categoria(nombre, desc)
                elif opt == '2':
                    cats = service.listar_categorias()
                    for c in cats:
                        print(f"  [{c.id}] {c.nombre}")
                elif opt == '3':
                    break
        elif opcion == '4':
            service.estadisticas()
        elif opcion == '5':
            service.cerrar()
            print("¡Hasta luego! 👋")
            break
        else:
            print("Opción inválida")


if __name__ == '__main__':
    menu_principal()
