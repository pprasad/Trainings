output "zookeeper_url" {
  depends_on = [aws_msk_cluster.zensar_msk_cluster]
  value = aws_msk_cluster.zensar_msk_cluster.zookeeper_connect_string
}

output "bootstrap-servers" {
  depends_on = [aws_msk_cluster.zensar_msk_cluster]
  value = aws_msk_cluster.zensar_msk_cluster.bootstrap_brokers
}

output "boostrap_brokers_tls" {
  depends_on = [aws_msk_cluster.zensar_msk_cluster]
  value = aws_msk_cluster.zensar_msk_cluster.bootstrap_brokers_tls
}

output "lunch_bastion" {
  value = "ssh ec2-user@${aws_instance.bastion_host.public_ip} -i cert.pem"
}