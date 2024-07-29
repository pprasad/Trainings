terraform {
   required_providers {
      aws = {
        source = "hashicorp/aws"
        version = "~> 5.0"
      }
   }
}

resource "aws_vpc" "kafka_vpc" {
  cidr_block = "10.0.0.0/16"
  tags = {
     "description" : "kafka_vpc"
  }
}

resource "aws_internet_gateway" "default" {
  vpc_id = aws_vpc.kafka_vpc.id
}

resource "aws_eip" "default" {
  depends_on = [aws_internet_gateway.default]
  domain = "vpc"
}

resource "aws_route" "default" {
    route_table_id = aws_vpc.kafka_vpc.main_route_table_id
    destination_cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.default.id
}

resource "aws_route_table" "defult" {
  vpc_id = aws_vpc.kafka_vpc.id
}


data "aws_availability_zones" "current_azs" {
   state = "available"
}

resource "aws_subnet" "subset_az1" {
  vpc_id = aws_vpc.kafka_vpc.id
  availability_zone_id = data.aws_availability_zones.current_azs.zone_ids[0]
  cidr_block = "10.0.1.0/24"
}

resource "aws_subnet" "subset_az2" {
  vpc_id = aws_vpc.kafka_vpc.id
  availability_zone_id = data.aws_availability_zones.current_azs.zone_ids[1]
  cidr_block = "10.0.2.0/24"
}

resource "aws_subnet" "subset_az3" {
  vpc_id = aws_vpc.kafka_vpc.id
  availability_zone_id = data.aws_availability_zones.current_azs.zone_ids[2]
  cidr_block = "10.0.3.0/24"
}

resource "aws_security_group" "kafka_sg" {
   vpc_id = aws_vpc.kafka_vpc.id
   ingress {
      from_port = 0
      to_port = 9092
      protocol = "TCP"
      cidr_blocks = ["0.0.0.0/24"]
   }
   ingress {
    from_port = 0
    to_port = 9092
    protocol = "TCP"
    cidr_blocks = var.cidr_blocks_bastion_host
  }
   ingress {
     from_port = 22
     to_port = 22
     protocol = "tcp"
     cidr_blocks = ["0.0.0.0/0"]
   }
   egress {
      from_port = 0
      to_port = 0
      protocol = "-1"
      cidr_blocks = ["0.0.0.0/0"]
   }
}