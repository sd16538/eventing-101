package personal.sdm.eventing101

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ExperimentController(@Autowired val eventPublisher: ApplicationEventPublisher) {

    @PostMapping("/commence-chaos")
    @ResponseStatus(HttpStatus.OK)
    fun causeManyEvents(): String {

        eventPublisher.publishEvent(Chaos("YO YO YO"))
        println("event 1")
        eventPublisher.publishEvent(Chaos("HELLO"))
        println("event 2")
        eventPublisher.publishEvent(Chaos("HELLO AGAIN"))
        println("event 3")
        eventPublisher.publishEvent(Chaos("WHY AM I SHOUTING"))

        println("Okay done")

        return """{
            "TODO":"really todo"
            }""".trimMargin()
    }

    @EventListener
    @Async
    fun figureItOut(chaosCompletedEvent: ChaosCompletedEvent) {
        println("chaos $chaosCompletedEvent")
    }

    @EventListener
    @Async
    fun figureItOut1(lalaCompletedEvent: LalaCompletedEvent) {
        println("LALA $lalaCompletedEvent")
    }

    @EventListener
    @Async
    fun figureItOut2(eiFCompletedEvent: EiFCompletedEvent) {
        println("EIF $eiFCompletedEvent")
    }

    @EventListener
    @Async
    fun figureItOut3(diLCompletedEvent: DiLCompletedEvent) {
        println("DIL $diLCompletedEvent")
    }
}

@Component
class ChaosListeners(@Autowired val eventPublisher: ApplicationEventPublisher) {

    @EventListener(condition = "#chaos.someString == 'HELLO'")
    fun lalala(chaos: Chaos) {
        println("hum a tune")
        eventPublisher.publishEvent(ChaosCompletedEvent(
                someString = chaos.someString,
                status = "NOT_SO_COOL",
                details = emptyList(),
                errors = listOf(ChaosResult("PVQ", "Nope sorry", "Business Error"))
        ))
        eventPublisher.publishEvent(LalaCompletedEvent(
                someString = chaos.someString,
                status = "NOT_SO_COOL",
                details = emptyList(),
                errors = listOf(ChaosResult("PVQ", "Nope sorry", "Business Error"))
        ))
        println("la la la")
    }

    @EventListener
    fun everythingIsFine(chaos: Chaos) {
        println("is everything fine")
        eventPublisher.publishEvent(ChaosCompletedEvent(
                someString = chaos.someString,
                status = "COOL",
                details = listOf(ChaosResult("123456", "2020-01-25", "all good")),
                errors = emptyList()
        ))
        println("is everything fine still")
        eventPublisher.publishEvent(EiFCompletedEvent(
                someString = chaos.someString,
                status = "COOL",
                details = listOf(ChaosResult("123456", "2020-01-25", "all good")),
                errors = emptyList()
        ))
        println("everything really is fine")
    }

    @Async
    @EventListener
    fun doItLater(chaos: Chaos) {
        println("sometime in the near future")
        eventPublisher.publishEvent(ChaosCompletedEvent(
                someString = chaos.someString,
                status = "NOT_SO_COOL",
                details = emptyList(),
                errors = listOf(ChaosResult("PCQ", "Non non et non", "Funding error"))
        ))
        println("a little more into future")
        eventPublisher.publishEvent(DiLCompletedEvent(
                someString = chaos.someString,
                status = "NOT_SO_COOL",
                details = emptyList(),
                errors = listOf(ChaosResult("PCQ", "Non non et non", "Funding error"))
        ))
        println("sometime in the distant future")
    }
}