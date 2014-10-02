package wallet

import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import scala.util.Random
//import org.springframework.util.Assert
//import scala.util.DateTime
//import org.scala_tools.time.Imports._
//import org.joda.time.DateTime
//import com.github.nscala_time.time.Imports._
import java.util.Date
import java.text.DateFormat
import java.text.DateFormat._
import scala.collection.mutable.Map
import java.util.ArrayList
import scala.collection.JavaConversions._
//import collection.mutable.{Set, Map, HashMap, MultiMap}
import scala.collection.mutable.Set
import scala.collection.mutable.HashMap
import scala.collection.mutable.MultiMap

//import java.util.concurrent.atomic

//import java.text._
//import java.util._


/**
 * This config class will trigger Spring @annotation scanning and auto configure Spring context.
 *
 * @author saung
 * @since 1.0
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
@RequestMapping(value=Array("/api/v1"))
class WalletConfig {

val usermap = Map[Int,CreateUser]();
var newuser = new CreateUser();

val newcardobj = new IdCard();
val idcardmap = new HashMap[Int,Set[IdCard]] with MultiMap[Int,IdCard]

//var IDCounter: java.util.concurrent.atomic.AtomicInteger = 11213323;
//val idcardmap = Map[Int, List[IdCard]]();
val newloginobj = new WebLogin();
val loginmap = new HashMap[Int,Set[WebLogin]] with MultiMap[Int,WebLogin]

val newbankobj = new BankAccount();
val bankmap = new HashMap[Int,Set[BankAccount]] with MultiMap[Int,BankAccount]


val array : ArrayList[IdCard] = new ArrayList()
val webarray: ArrayList[WebLogin] = new ArrayList()
val bankarray: ArrayList[BankAccount] = new ArrayList()

@RequestMapping(value = Array(""), method = Array(RequestMethod.GET))
@ResponseBody
def welcomerequest(): String= {

return "Welcome to the wallet application";

}

@RequestMapping(value = Array("/users"), method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
@ResponseBody
def respondtorequest(@RequestBody userObj : CreateUser): CreateUser = {

var num = new Random();
userObj.id = num.nextInt(2000);

//val dt = new Date();
//val df = getDateInstance(LONG);
//userObj.created_at = df.format(dt);
//var id ="u-" + userObj.id
usermap.put(userObj.id,userObj);
return usermap(userObj.id);

}

@RequestMapping(value = Array("/users/{id}"), method = Array(RequestMethod.GET),produces = Array("application/json"))
@ResponseBody
def getresponsetorequest(@PathVariable("id") user_id: Int): CreateUser = {

		return (usermap(user_id));

}

@RequestMapping(value = Array("/users/{id}"), method = Array(RequestMethod.PUT),produces = Array("application/json"))
@ResponseBody
def updateresponsetorequest(@RequestBody userObj: CreateUser,@PathVariable("id") user_id : Int): CreateUser = {

		//val dt = new Date();
		//val df = getDateInstance(LONG);

        userObj.id = user_id;
        //userObj.created_at = df.format(dt); 

		usermap.update(userObj.id,userObj);

		return usermap(userObj.id); 
	
}

//******************** ID CARD ***************************************************//

@RequestMapping(value = Array("/users/{id}/idcards"), method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
@ResponseBody
def idcardrespondtorequest(@RequestBody idcardObj : IdCard, @PathVariable("id") user_id : Int): IdCard = {
	
		var cardid = new Random();

	idcardObj.card_id = cardid.nextInt(100);

	
	//idcardObj.setcard_id(IDCounter.incrementAndGet());

	idcardmap.addBinding(user_id,idcardObj);

	//array(idcardObj.card_id) = idcardObj;
	
	 array.add(idcardObj); 

	 // newcardobj = idcardObj;


		return idcardObj;

}

@RequestMapping(value = Array("/users/{id}/idcards"), method = Array(RequestMethod.GET),produces = Array("application/json"))
@ResponseBody
def idcardgetresponsetorequest(@PathVariable("id") user_id: Int): ArrayList[IdCard] = {
		return array;

}

@RequestMapping(value = Array("/users/{id}/idcards/{card_id}"), method = Array(RequestMethod.DELETE) ,produces = Array("application/json"))
@ResponseBody
def idcardgetresponsetorequest(@PathVariable("id") user_id: Int, @PathVariable("card_id") card_id: Int ): String = {

			
			for ( idcard : IdCard <- array
				if idcard.card_id.equals(card_id))
			        array.remove(idcard)
			

			return "Deleted"
			 

			}

//*********************************** WEB LOGIN *************************************************//

@RequestMapping(value = Array("/users/{id}/weblogins"), method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
@ResponseBody
def createloginrespondtorequest(@RequestBody loginObj : WebLogin, @PathVariable("id") user_id: Int): WebLogin = {

var num = new Random();
loginObj.login_id = num.nextInt(500);
	
	//idcardObj.setcard_id(IDCounter.incrementAndGet());

	loginmap.addBinding(user_id,loginObj);
	
	webarray.add(loginObj); 
				
	return loginObj;



}

@RequestMapping(value = Array("/users/{id}/weblogins"), method = Array(RequestMethod.GET),produces = Array("application/json"))
@ResponseBody
def logingetresponsetorequest(@PathVariable("id") user_id: Int): ArrayList[WebLogin] = {

		return webarray;

}

@RequestMapping(value = Array("/users/{id}/weblogins/{login_id}"), method = Array(RequestMethod.DELETE),produces = Array("application/json"))
@ResponseBody
def logindeletetorequest(@PathVariable("id") useridObj: Int, @PathVariable("login_id") login_id: Int ): String = {

	for ( weblogin : WebLogin <- webarray
				if weblogin.login_id.equals(login_id))
			        array.remove(weblogin)

	return "Deleted";
	
}

//************************************* BANK ACCOUNT ***************************************************************//

@RequestMapping(value = Array("/users/{id}/bankaccounts"), method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
@ResponseBody
def createbankaccrespondtorequest(@RequestBody bankObj : BankAccount,@PathVariable("id") user_id: Int): BankAccount = {

var num = new Random();
bankObj.ba_id = num.nextInt(100);

bankmap.addBinding(user_id,bankObj);
	
	bankarray.add(bankObj); 
				
	return bankObj;

}

@RequestMapping(value = Array("/users/{id}/bankaccounts"), method = Array(RequestMethod.GET),produces = Array("application/json"))
@ResponseBody
def listbankresponsetorequest(@PathVariable("id") user_id: Int): ArrayList[BankAccount] = {

		return bankarray;
	}

	
@RequestMapping(value = Array("/users/{id}/bankaccounts/{ba_id}"), method = Array(RequestMethod.DELETE),produces = Array("application/json"))
@ResponseBody
def bankdeletetorequest(@PathVariable("id") useridObj: Int,@PathVariable("ba_id") ba_id: Int): String = {

	
	for ( bankacc : BankAccount <- bankarray
				if bankacc.ba_id.equals(ba_id))
			        array.remove(bankacc)

		return "Deleted";

	}



}
 