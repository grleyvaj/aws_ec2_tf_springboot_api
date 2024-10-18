provider "aws" {
  region = "us-east-2"
}

resource "aws_s3_bucket" "spring_boot_api_bucket" {
  bucket = "springboot-tf-aws-api"
}

resource "aws_s3_bucket_object" "spring_boot_api_jar" {
  bucket = aws_s3_bucket.spring_boot_api_bucket.bucket
  key    = "aws_tf_springboot-0.0.1-SNAPSHOT.jar"
  source = "/home/liam/worksapce/aws_tf_springboot/target/aws_tf_springboot-0.0.1-SNAPSHOT.jar"
  acl    = "private"
}

resource "aws_iam_role" "ec2_role" {
  name = "ec2_s3_access_role"
  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action    = "sts:AssumeRole",
      Effect    = "Allow",
      Principal = {
        Service = "ec2.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_role_policy" "ec2_role_policy" {
  name = "ec2_s3_access_policy"
  role = aws_iam_role.ec2_role.id
  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action   = ["s3:GetObject"],
      Effect   = "Allow",
      Resource = "${aws_s3_bucket.spring_boot_api_bucket.arn}/*"
    }]
  })
}

resource "aws_iam_instance_profile" "ec2_instance_profile" {
  name = "aws_api_tf_instance_profile"
  role = aws_iam_role.ec2_role.name
}

data "external" "ssh_key" {
  program = ["bash", "${path.module}/generate_ssh_key.sh"]
}

resource "aws_key_pair" "ec2_key_pair" {
  key_name   = "aws_tf_springboot_key"
  public_key = data.external.ssh_key.result["public_key"]
}

resource "aws_instance" "spring_boot_ec2" {
  ami           = "ami-00eb69d236edcfaf8"
  instance_type = "t3.micro"
  iam_instance_profile = aws_iam_instance_profile.ec2_instance_profile.name

  user_data = <<-EOF
              #!/bin/bash
              sudo apt-get update -y
              sudo apt-get install -y wget

              # Add repository for OpenJDK 21
              sudo add-apt-repository ppa:openjdk-r/ppa -y
              sudo apt-get update -y

              # Install OpenJDK 21
              sudo apt-get install openjdk-21-jdk -y

              # Configure environment variables
              echo "export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64" >> ~/.bashrc
              echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> ~/.bashrc
              source ~/.bashrc

              # Install aws cli
              sudo apt-get install -y awscli

              # Download and start the Spring Boot application
              cd /home/ubuntu
              if aws s3 cp s3://${aws_s3_bucket.spring_boot_api_bucket.bucket}/aws_tf_springboot-0.0.1-SNAPSHOT.jar .; then
                  echo "$(date) - Successfully downloaded JAR file." >> /home/ubuntu/spring_boot_app.log
              else
                  echo "$(date) - ERROR: Failed to download JAR file from S3." >> /home/ubuntu/spring_boot_app.log
                  exit 1
              fi

              # Ensure the JAR has execute permissions
              chmod +x aws_tf_springboot-0.0.1-SNAPSHOT.jar

              # Run the Spring Boot application
              if java -jar aws_tf_springboot-0.0.1-SNAPSHOT.jar >> /home/ubuntu/spring_boot_app.log 2>&1 &; then
                  echo "$(date) - Application started successfully." >> /home/ubuntu/spring_boot_app.log
              else
                  echo "$(date) - ERROR: Failed to start the Spring Boot application." >> /home/ubuntu/spring_boot_app.log
                  exit 1
              fi
              EOF

  tags = {
    Name = "SpringBootEC2Instance"
  }

  key_name = aws_key_pair.ec2_key_pair.key_name

  vpc_security_group_ids = [aws_security_group.allow_ssh_http.id]
}

resource "aws_security_group" "allow_ssh_http" {
  name        = "springboot-tf-aws-api-security-gropup"
  description =  "Allow HTTP, HTTPS, and SSH traffic"

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }


  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
