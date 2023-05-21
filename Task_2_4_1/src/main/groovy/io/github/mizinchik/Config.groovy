package io.github.mizinchik

import io.github.mizinchik.model.CourseConfig
import io.github.mizinchik.model.CourseSettings
import io.github.mizinchik.model.Group
import io.github.mizinchik.model.Student
import io.github.mizinchik.model.TaskAssignment
import io.github.mizinchik.model.TaskInfo

import java.time.LocalDate

def config = new CourseConfig()

config.tasks = [
        new TaskInfo(id: "task1", title: "Task 1", points: 10),
        new TaskInfo(id: "task2", title: "Task 2", points: 15),
        new TaskInfo(id: "task3", title: "Task 3", points: 20)
]

config.group = new Group(name: "Group 1")
config.group.students = [
        new Student(moniker: "MIZINCHIK", name: "Maxim C", repo: new URL("https://github.com/MIZINCHIK/OOP"))
]

config.tasks = [
        new TaskInfo(id: 1, title: "Task_1_1_1", points: 0)
]

config.taskAssignments = [
        new TaskAssignment(assignee: config.group.students[0], info: config.tasks[0], deadline: LocalDate.of(2023, 6, 1))
]

config.controlPoints = [
]

config.systemSettings = new CourseSettings()

println config
