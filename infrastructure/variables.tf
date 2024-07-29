variable "key_pair_name" {
  type = string
  default = "kafka_key_pair"
}

variable "cidr_blocks_bastion_host" {
  type = list(string)
  default = ["10.0.4.0/24"]
}

variable "access_key" {
  type = string
  default = ""
  description = "AWS Access related to Access key"
}

variable "secret_key" {
  type = string
  default = ""
  description = "AWS Access related to Secret key"
}