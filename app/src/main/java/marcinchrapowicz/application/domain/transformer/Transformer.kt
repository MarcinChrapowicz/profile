package marcinchrapowicz.application.domain.transformer

interface Transformer<V, T> {
    fun transform(from: V?): T
}

