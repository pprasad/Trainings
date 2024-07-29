resource "aws_subnet" "bastion_subnet" {
  vpc_id = aws_vpc.kafka_vpc.id
  cidr_block = var.cidr_blocks_bastion_host[0]
  map_public_ip_on_launch = true
  availability_zone = data.aws_availability_zones.current_azs.names[0]
}


resource "aws_instance" "bastion_host" {
  ami           = "ami-068e0f1a600cd311c"
  instance_type = "t2.micro"
  vpc_security_group_ids = [aws_security_group.kafka_sg.id]
  key_name = aws_key_pair.generate_key_pair.key_name
  subnet_id = aws_subnet.bastion_subnet.id
  user_data = templatefile("userscript.tftpl", {
    bootstrap_server_1 = split(",", aws_msk_cluster.zensar_msk_cluster.bootstrap_brokers)[0]
    bootstrap_server_2 = split(",", aws_msk_cluster.zensar_msk_cluster.bootstrap_brokers)[1]
    bootstrap_server_3 = split(",", aws_msk_cluster.zensar_msk_cluster.bootstrap_brokers)[2]
  })
  root_block_device {
    volume_type = "gp2"
    volume_size = 100
  }
}