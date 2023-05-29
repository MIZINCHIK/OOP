<html>
<head>
    <title>OOP performance table chart</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Group</th>
        <th>Name</th>
        <th>Build</th>
        <th>Tests total</th>
        <th>Tests passed</th>
    </tr>
    <#list groups as group>
    <#list group.students as student>
    <#list student.assignments as assignment>
    <tr>
        <td>${group.name}</td>
        <td>${student.name}</td>
        <td>${assignment.build}</td>
        <td>${assignment.testsTotal}</td>
        <td>${assignment.testsPassed}</td>
    </tr>
</#list>
</#list>
</#list>
</table>
</body>
</html>