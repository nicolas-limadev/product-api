resource "aws_vpc" "springreact-docker-at-169752714002" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    "Name" = "springreact-docker_vpc_1"
  }
}

resource "aws_subnet" "springreact_subnet" {
  vpc_id                  = aws_vpc.springreact-docker-at-169752714002.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "springreact_subnet_pub"
  }
}

resource "aws_internet_gateway" "springreact_igw" {
  vpc_id = aws_vpc.springreact-docker-at-169752714002.id

  tags = {
    "Name" = "springreact_igw"
  }
}

resource "aws_route_table" "springreact_rtb" {

  vpc_id = aws_vpc.springreact-docker-at-169752714002.id

  tags = {
    "Name" = "springreact_rtb"
  }
}

resource "aws_route" "springreact_route" {

  route_table_id         = aws_route_table.springreact_rtb.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.springreact_igw.id
}

resource "aws_route_table_association" "springreact_rtb_ass" {

  route_table_id = aws_route_table.springreact_rtb.id
  subnet_id      = aws_subnet.springreact_subnet.id

}

resource "aws_instance" "springreact_ec2" {

  instance_type          = "t2.micro"
  key_name               = aws_key_pair.springreact-key.id
  vpc_security_group_ids = [aws_security_group.springreact_sg.id]
  subnet_id              = aws_subnet.springreact_subnet.id

  ami = data.aws_ami.springreact_ami.id

  user_data = file("userdata.tpl")

  root_block_device {
    volume_size = 8
  }

  tags = {
    "Name" = "springreact_ec2"
  }
}