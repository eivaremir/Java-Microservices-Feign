# Prueba Microservicios en Spring/Feign

Microservicio alojado en Amazon EC2 (con SSL) y desarrollado en JAVA utilizando Spring Boot y Open Feign, base de datos MySQL alojada en Amazon RDS

Endpoints:

- https://springtest.eivaremir.com/deposit-account/current : listado de cuentas corrientes
- https://springtest.eivaremir.com/deposit-account/saving : listado de cuentas de ahorro

## Instrucciones

- Clone el repositorio en el directorio de su preferencia
- Código desarrollado en Intellij IDEA y Ubuntu por tanto se sugiere que se importen los módulos usando este IDE y este sistema operativo
- Para levantar los microservicios se deben ejecutar los modulos current (puerto 9001), saving (puerto 9002) y main (puerto 9000). No necesariamente en el órden establecido y tomándo en cuenta los scripts que ya tienen los classpath de las dependencias establecidos: /current/deploy, /saving/deploy y /main/deploy 
- No es necesario levantar la base de datos desde 0, puede utilizar la base de datos alojada en Amazon RDS con las credenciales en la sección **Extras**, sin embargo, se agregó el archivo export.sql a la raíz del proyecto en caso de necesitarlo.

## Deploy en AWS

Se levantaron los microservicios en una sóla instancia EC2 (por efectos de presupuesto 🤠) en un servidor Ubuntu 20 y creando los servicios con los archivos current.service, saving.service y main.service para una mejor gestión en el directorio /etc/systemd/system, luego se habilitan con `sudo systemctl enable main`, `sudo systemctl enable saving` y `sudo systemctl enable current` para iniciar automáticamente los procesos siempre al arranque del servidor.

Como servidor web por defecto HTTP y HTTPS se utilizó NGINX y Certbot para la gestión del certificado SSL para el nombre del subdominio. Se configuró para en enrutamiento del puerto 443 y 80 al 9000 (main proxy).

## Extras

Credenciales MySQL
 - Host: deposit-account.crltg6hajdp9.us-east-1.rds.amazonaws.com
 - User: admin
 - Pwd : 12345678


