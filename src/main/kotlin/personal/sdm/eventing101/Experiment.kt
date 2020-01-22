package personal.sdm.eventing101

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ExperimentController {

    @PostMapping("/commence-chaos")
    @ResponseStatus(HttpStatus.OK)
    fun causeManyEvents(): String {

        return """{
            "TODO":"really todo"
            }""".trimMargin()
    }
}