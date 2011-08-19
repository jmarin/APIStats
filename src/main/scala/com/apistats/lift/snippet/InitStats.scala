package com.apistats.lift {
  package snippet {

    import com.apistats.lift.record.APIStatsMessageDoc
    import scala.xml.NodeSeq
    import net.liftweb._
    import util.Helpers._
    import common._
    import org.joda.time.DateTime

    object InitStats {

      def totalCount(): NodeSeq = {
        <strong>{ APIStatsMessageDoc.numberOfTotalMessages }</strong>
      }

      def percentageGeospatial(): NodeSeq = {
        <span>{ APIStatsMessageDoc.percentageGeospatialMessages }</span>
      }

      def percentageFailed(): NodeSeq = {
        <span>{ APIStatsMessageDoc.percentageFailed }</span>
      }

      def avgResponseTimeLas100Messages(): NodeSeq = {
        <span>{ APIStatsMessageDoc.avgResponseTimeLast100Messages("") }</span>
      }

      def maxResponseTimeLas100Messages(): NodeSeq = {
        <span>{ APIStatsMessageDoc.maxResponseTimeLast100Messages("") }</span>
      }

      def minResponseTimeLas100Messages(): NodeSeq = {
        <span>{ APIStatsMessageDoc.minResponseTimeLast100Messages("") }</span>
      }

    }
  }
}