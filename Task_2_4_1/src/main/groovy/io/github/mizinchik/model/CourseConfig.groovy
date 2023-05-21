package io.github.mizinchik.model

class CourseConfig {
    List<TaskInfo> tasks = []
    List<ControlPoint> controlPoints = []
    Group group
    List<TaskAssignment> taskAssignments = []
    CourseSettings systemSettings
}
