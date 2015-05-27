<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/css/my.css"/>" type="text/css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class ="custom">${empty id_dep ? 'Edit Employee' : 'Create Employee'}</div>
                </div>
                <div class="container-fluid">
                    <form class="form" role="form"
                          action="${pageContext.request.contextPath}${empty id_dep ? '/Employees/edit' : '/Employees/employeeCreator'}"
                          method="POST">
                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" id="title" name="title" value='${title}'/>
                        </div>
                        <span class="alarma">${messages.job_title}</span>
                        <div class="form-group">
                            <label class="contact_form_label" for="firstName">First Name:</label>
                            <input type="text" class="form-control" id="firstName" name="firstName"
                                   value='${firstName}'/>
                        </div>
                        <span class="alarma">${messages.first_name}</span>
                        <div class="form-group">
                            <label class="contact_form_label" for="secondName">Second Name:</label>
                            <input type="text" class="form-control" id="secondName" name="secondName"
                                   value='${secondName}'/>
                        </div>
                        <span class="alarma">${messages.second_name}</span>
                        <div class="form-group">
                            <label class="contact_form_label" for="salary">Salary:</label>
                            <input type="text" class="form-control" id="salary" name="salary"
                                   value='${salary}'/>
                        </div>
                        <span class="alarma">${messages.salary}</span>
                        <div class="form-group">
                            <label class="contact_form_label" for="date">Date:</label>
                            <input type="date" class="form-control" id="date" name="date" value='${date}'/>
                            <input type="hidden" id="id" name="id" value='${id}'/>
                            <input type="hidden" name="id_dep" value="${id_dep}"/>
                        </div>
                       <span class="alarma">${messages.date} </span>
                        <button type="submit" class="btn btn-default">Submit</button>

                        <div class="alarma">
                            ${message}
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
</div>
</body>
</html>

