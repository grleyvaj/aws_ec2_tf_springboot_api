# Proyecto Spring Boot API Rest en AWS (EC2)

## DESCRIPCIÓN DEL PROYECTO
En este proyecto se desarrolla una API Rest / Microservicio de Spring Boot y se despliega en infraestructura de la nube de AWS en un EC2 empleando Terraform.

## ESTRUCTURA
- Crear API Rest con Spring Boot
- Desplegar infraestructura en AWS EC2 con Terraform

## REQUISITOS

### Instalación
1. **AWS CLI**  
   [Guía de instalación de AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
    - Configurar credenciales de AWS con el comando:
      ```bash
      aws configure
      ```
    - Probar con:
      ```bash
      aws --version
      ```

2. **Terraform**  
   [Guía de instalación de Terraform](https://developer.hashicorp.com/terraform/install?product_intent=terraform)
    - Probar con:
      ```bash
      terraform --version
      ```

### Configuraciones
- El archivo `generate_ssh_key.sh` se usa con Terraform para crear la SSH de conexión al EC2.
- La carpeta `iac` contiene el código para desplegar la infraestructura.
- El archivo `main.tf` es la plantilla de Terraform.
- En el archivo `main.tf` modificar las rutas `source` del recurso `aws_s3_bucket_object` por la de tu local

### Arranque
Ejecuta los siguientes comandos en la terminal desde la raíz del proyecto:
```bash
cd iac
terraform init
terraform validate
terraform plan
terraform apply
```

### OTROS COMANDOS DE INTERÉS

- **Comando para conectarse a la instancia de EC2 desde local**:  
  Ir a su `~/.ssh` y ejecutar:  
  ```bash
  ssh -i "aws_tf_springboot.pem" ec2-user@52.15.135.184
  ```
  
- **Verifica si tu aplicación está en ejecución:**
  ```bash
  ps aux | grep java
  ```
  
- **Iniciar jar manualmente**
  ```bash
  cd /home/ubuntu
  java -jar aws_tf_springboot-0.0.1-SNAPSHOT.jar
  ```

- **Probar solicitud a la API**
  ```bash
  curl http://localhost:8080/financials
  ```


- **Ver la arquitectura de un AMI**:
  
    Útil para que exista correspondencia entre el AMI y el `instance_type` seleccionado::
  ```bash
  aws ec2 describe-images --image-ids ami-00eb69d236edcfaf8 --query 'Images[0].[ImageId,Architecture]' --output table
  ```

### PROBAR LA API

Una vez desplegada la instancia, buscar la IP Publica 
![aws-ec2.png](imgs%2Faws-ec2.png)

Con esta IP realizar las peticiones
![postman.png](imgs%2Fpostman.png)