package marcinchrapowicz.application.domain.exception.user

data class MissingUserException(val information: String) : Exception(information)
