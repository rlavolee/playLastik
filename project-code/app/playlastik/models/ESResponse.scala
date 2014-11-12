package playlastik.models

import org.elasticsearch.rest.RestStatus
import play.api.libs.json._

sealed trait ESResponse

case class ESFailure(error: String, status: Int) extends ESResponse

case class IndexResponse(_index: String, _type: String, _id: String, _version: Long, created: Boolean) extends ESResponse

case class ShardStatus(total:Int, successful:Int, failed:Int)

case class CountResponse(count: Long, _shards: ShardStatus, terminatedEarly:Option[Boolean] =Some(false))

// pattern => "_shards":{"total":5,"successful":5,"failed":0}

// pattern BroadcastOperationResponse

//case class ShardOperationFailure(index:String, shardId:Int,reason:String)
// {"_shards":{"total":10,"successful":5,"failed":0}}

case class RefreshIndicesResponse(_shards :ShardStatus)

object ShardStatus {
  implicit val shardStatusFormat = Json.format[ShardStatus]
}

object CountResponse {
  implicit val countResponseFormat = Json.format[CountResponse]
}

object IndexResponse {
  implicit val indexResponseFormat = Json.format[IndexResponse]
}

object ESFailure {
  implicit val esFailureFormat = Json.format[ESFailure]
}

object RefreshIndicesResponse {
  implicit val refreshIndicesResponseFormat = Json.format[RefreshIndicesResponse]
}


