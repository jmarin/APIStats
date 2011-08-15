package com.apistats.lift {
  package snippet {

    import com.apistats.lift.record.APIStatsMessageDoc
    import scala.xml.NodeSeq

    class InitStats {

      def numberOfTotalMessages(): NodeSeq = {
        <span>APIStatsMessageDoc.numberOfTotalMessages</span>
      }

    }
  }
}