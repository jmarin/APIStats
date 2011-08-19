package com.apistats.lift {
  package snippet {

    import com.apistats.lift.record.APIStatsMessageDoc
    import scala.xml.NodeSeq
    import net.liftweb._
    import util.Helpers._
    import common._
    import org.joda.time.DateTime

    object InitStats {
      
      def totalCount():NodeSeq = {
        <strong>{APIStatsMessageDoc.numberOfTotalMessages}</strong>
      }
      
      def percentageGeospatial():NodeSeq = {
        <span>{APIStatsMessageDoc.percentageGeospatialMessages}</span>
      }
      
      def percentageFailed():NodeSeq = {
        <span>{APIStatsMessageDoc.percentageFailed}</span>
      }
      
      def avgResponseTimeLas1000Messages():NodeSeq = {
        <span>{APIStatsMessageDoc.avgResponseTimeLas1000Messages("")}</span>
      }
      
      def maxResponseTimeLas1000Messages():NodeSeq = {
        <span>{APIStatsMessageDoc.maxResponseTimeLas1000Messages("")}</span>
      }      
      
      def minResponseTimeLas1000Messages():NodeSeq = {
        <span>{APIStatsMessageDoc.minResponseTimeLas1000Messages("")}</span>
      }
    
    }
  }
}