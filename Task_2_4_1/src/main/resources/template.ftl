<html lang="en">
<head>
    <title>Students Table Chart</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Group</th>
        <th>Name</th>
        <th>Task</th>
        <th>Build</th>
        <th>Documentation</th>
        <th>Tests total</th>
        <th>Tests passed</th>
    </tr>
    <#list groups as group>
        <#if group.students?size != 0>
            <#list group.students as student>
                <tr>
                    <#if student?is_first>
                        <td rowspan="${group.tasks}">${group.name}</td>
                    </#if>
                <#if student.assignments?size != 0>
                    <#list student.assignments as assignment>
                        <#if assignment?is_first>
                            <td rowspan="${student.assignments?size}">${student.name}</td>
                        </#if>
                        <td>${assignment.info.id}</td>
                        <td>${assignment.build}</td>
                        <td>${assignment.docs}</td>
                        <td>${assignment.testsTotal}</td>
                        <td>${assignment.testsPassed}</td>
                        </tr>
                    </#list>
                <#else>
                        <td>${student.name}</td>
                        <td colspan="5">Student isn't assigned any tasks</td>
                    </tr>
                </#if>
            </#list>
        <#else>
            <tr>
                <td>${group.name}</td>
                <td colspan="6">The group is empty</td>
            </tr>
        </#if>
    </#list>
</table>
</body>
</html>
