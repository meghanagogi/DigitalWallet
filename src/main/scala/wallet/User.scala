package wallet

import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import scala.collection.JavaConversions._
import scala.reflect.{BeanProperty, BooleanBeanProperty}
import scala.util.Random
//import scala.util.DateTime
//import java.util.Calendar
//import org.scala_tools.time.Imports._
//import org.joda.time.DateTime
import java.util.Date
import org.apache.commons.lang3.time.DateFormatUtils

class CreateUser{
        @BeanProperty
        var id: Int = _

        @BeanProperty
        var name: String = _

        @BeanProperty
        var email: String = _

        @BeanProperty
        var password: String = _

        @BeanProperty
        var created_at: String = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date())

        @BeanProperty
        var updated_at: String = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date())


//      val num = new Random();
//       return
//      println(num.nextInt());

}
