package bootstrap.liftweb
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.BeforeAndAfterAll
import org.scalatest.WordSpec
import net.liftweb.util.Props
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MongoConfigTest extends WordSpec with BeforeAndAfterAll with ShouldMatchers {

  val initLift = new Boot

  override def beforeAll(): Unit = {
    initLift.boot

  }

  "Mongo configuration" should {
    val mongoport = Props.get("mongoport") openOr ("")
    val mongoserver = Props.get("mongoserver") openOr("")
    "use the default port for connection" in {
      assert(mongoport === "27017")

    }
    "use the localhost server" in {
      assert(mongoserver === "127.0.0.1")
    }
  }

}