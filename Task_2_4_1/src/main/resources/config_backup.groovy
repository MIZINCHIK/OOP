package io.github.mizinchik.dsl

import java.time.LocalDate

config {

    group {
        name = "number"
        students = [
                student {
                    moniker = "CHEL"
                    name = "Chel C"
                    repo = new URL("https://github.com/")
                }
        ]
    }


    tasks = [
        task {
            id = "Task_1_1_1"
            title = "Heapsort"
            points = 1
        },
        task {
            id = "Task_1_2_1"
            title = "Stack"
            points = 1
        }
    ]

    assignments {
        assignment {
            student = "Chel C"
            task = "Heapsort"
            softDeadline = LocalDate.of(2022, 9, 13)
            hardDeadline = LocalDate.of(2022, 9, 19)
        }
        assignment {
            student = "Chel C"
            task = "Heapsort"
            softDeadline = LocalDate.of(2022, 9, 26)
            hardDeadline = LocalDate.of(2022, 10, 10)
        }
    }

    classes {
        hour {
            date = LocalDate.of(2022, 9, 5)
        }
        hour {
            date = LocalDate.of(2022, 9, 12)
        }
        hour {
            date = LocalDate.of(2022, 9, 19)
        }
    }

    settings {
        branch = "master"
        disableLongTests = true
    }
}
