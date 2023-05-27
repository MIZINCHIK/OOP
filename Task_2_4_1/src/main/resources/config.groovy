import java.time.LocalDate

group {
    name = "number"
}

students = [
        {
            moniker = "CHEL"
            name = "Chel C"
            repo = new URL("https://github.com/")
        }
]

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

assignments = [
    {
        assignee = students[0]
        info = tasks[0]
        softDeadline = LocalDate.of(2022, 9, 13)
        hardDeadline = LocalDate.of(2022, 9, 19)
    },
    {
        assignee = null
        info = tasks[1]
        softDeadline = LocalDate.of(2022, 9, 26)
        hardDeadline = LocalDate.of(2022, 10, 10)
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
    disableLongTests = true
}
