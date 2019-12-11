package support

import play.api.Application

import scala.reflect.ClassTag

//TODO to be replaced by the one supplied by play once this service is upgraded to 2.6", deprecated since "0.88.0"
trait Injecting {
  this: {def app: Application} =>
  def inject[T: ClassTag]: T = app.injector.instanceOf[T]
}
