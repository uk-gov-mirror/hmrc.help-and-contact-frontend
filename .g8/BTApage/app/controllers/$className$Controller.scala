package controllers;format="decap"$

class $className;format="cap"$Controller @Inject()(appConfig: FrontendAppConfig,
                                          override val messagesApi: MessagesApi,
                                          authenticate: AuthAction,
                                          serviceInfo: ServiceInfoAction ) extends FrontendController with I18nSupport {

  def onPageLoad = (authenticate andThen serviceInfo) {
    implicit request =>
      Ok($className;format="decap"$(appConfig)(request.serviceInfoContent))
  }
}
