import java.time.LocalDate

tasks = [
        {
            id = "Task_1_1_1"
            title = "Heapsort"
            points = 1
        },
        {
            id = "Task_1_2_1"
            title = "Stack"
            points = 1
        }
]

allStudents = [
        {
            moniker = "MIZINCHIK"
            name = "Maxim C"
            repo = "https://github.com/MIZINCHIK/OOP/"
            assignments = [
                    {
                        info = tasks[0]
                        softDeadline = LocalDate.of(2022, 9, 13)
                        hardDeadline = LocalDate.of(2022, 9, 19)
                    },
                    {
                        info = tasks[1]
                        softDeadline = LocalDate.of(2022, 9, 26)
                        hardDeadline = LocalDate.of(2022, 10, 10)
                    }
            ]
        },
        {
            moniker = "Vashurik"
            name = "Maxim K"
            repo = "https://github.com/kapkekes/OOP/"
            assignments = [
                    {
                        info = tasks[0]
                        softDeadline = LocalDate.of(2022, 9, 13)
                        hardDeadline = LocalDate.of(2022, 9, 19)
                    },
                    {
                        info = tasks[1]
                        softDeadline = LocalDate.of(2022, 9, 26)
                        hardDeadline = LocalDate.of(2022, 10, 10)
                    }
            ]
        },
        {
            moniker = "Pelageech"
            name = "Artem B"
            repo = "https://github.com/pelageech/nsujava/"
            assignments = [
                    {
                        info = tasks[0]
                        softDeadline = LocalDate.of(2022, 9, 13)
                        hardDeadline = LocalDate.of(2022, 9, 19)
                    },
                    {
                        info = tasks[1]
                        softDeadline = LocalDate.of(2022, 9, 26)
                        hardDeadline = LocalDate.of(2022, 10, 10)
                    }
            ]
        },
        {
            moniker = "miqqra"
            name = "Mikhail K."
            repo = "https://github.com/miqqra/OOP/"
            assignments = [
                    {
                        info = tasks[0]
                        softDeadline = LocalDate.of(2022, 9, 13)
                        hardDeadline = LocalDate.of(2022, 9, 19)
                    },
                    {
                        info = tasks[1]
                        softDeadline = LocalDate.of(2022, 9, 26)
                        hardDeadline = LocalDate.of(2022, 10, 10)
                    }
            ]
        }
]

groups = [
        {
            name = "21213"
            students = [
                    allStudents[0],
                    allStudents[1],
                    allStudents[3]
            ]
        },
        {
            name = "21215"
            students = [
                    allStudents[2]
            ]
        }
]

classes = [
    {
        date = LocalDate.of(2022, 9, 5)
    },
    {
        date = LocalDate.of(2022, 9, 12)
    },
    {
        date = LocalDate.of(2022, 9, 19)
    }
]

settings {
    branch = "master"
    disableLongTests = false
}
