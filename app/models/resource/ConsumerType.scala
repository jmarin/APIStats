package models.resource

trait ConsumerType
case class HttpConsumerType() extends ConsumerType
case class TcpConsumerType() extends ConsumerType
