resource "aws_security_group" "springreact_sg" {

  name        = "springreact_sg"
  description = "springreact security group"
  vpc_id      = aws_vpc.springreact-docker-at-169752714002.id

}

resource "aws_security_group_rule" "sgr_pub_out" {

  from_port         = 0
  protocol          = "-1"
  security_group_id = aws_security_group.springreact_sg.id
  to_port           = 0
  type              = "egress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "srg_http_in" {

  from_port         = 80
  protocol          = "tcp"
  security_group_id = aws_security_group.springreact_sg.id
  to_port           = 80
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]

}

resource "aws_security_group_rule" "srg_ssh_input" {

  from_port         = 22
  protocol          = "tcp"
  security_group_id = aws_security_group.springreact_sg.id
  to_port           = 22
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]

}

resource "aws_key_pair" "springreact-key" {

    key_name = "springreact-key"
    public_key = file("~/.ssh/springreact-key.pub")
  
}
