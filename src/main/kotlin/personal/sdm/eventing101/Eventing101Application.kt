package personal.sdm.eventing101

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class Eventing101Application

fun main(args: Array<String>) {
	runApplication<Eventing101Application>(*args)
}
