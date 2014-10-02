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
import java.util.Date
import org.apache.commons.lang3.time.DateFormatUtils

class IdCard{

       // @BeanProperty
        //var carduser_id: Int = _

        @BeanProperty
        var card_id: Int = _

        @BeanProperty
        var card_name: String = _

        @BeanProperty
        var card_number: String = _

        @BeanProperty
        var expiration_date: String = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date())
}
