
provider "aws" {
   region = "ap-south-1"
   access_key = var.access_key
   secret_key = var.secret_key
}

resource "aws_msk_cluster" "zensar_msk_cluster" {
  cluster_name           = "zensarcluster"
  kafka_version          = "3.7.x"
  number_of_broker_nodes = 3
  broker_node_group_info {
    client_subnets  = [aws_subnet.subset_az1.id, aws_subnet.subset_az2.id, aws_subnet.subset_az3.id]
    instance_type   = "kafka.t3.small"
    security_groups = [aws_security_group.kafka_sg.id]
    storage_info {
      ebs_storage_info {
         volume_size = 10
      }
    }
  }
  encryption_info {
    encryption_in_transit {
      client_broker = "PLAINTEXT"
    }
  }

  tags = {
    "kafka_cluser" : "zensar_msk_cluster"
  }

  lifecycle {
    create_before_destroy = false
  }
}

