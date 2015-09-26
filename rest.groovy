@Controller
class Rest{

 @RequestMapping("/")
 @ResponseBody
 String messageHi()
 {
   return "<h1>Hi groovy !</h1>";

 }

 @RequestMapping("/rest")
 @ResponseBody
 String message()
 {
   return "<h1>Hello rest:+ </h1>";

 }

}
