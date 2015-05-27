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
                    <div class="custom">${not empty id_dep ? 'Edit Department' : 'Create Department' }</div>
                </div>
                <div class="container-fluid">
                    <form class="form" role="form"
                          action="${not empty id_dep ? '/Departments/EditValidator' : '/Departments/creator'}"
                          method="POST">
                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" id="title" name="title" value='${title}'/>
                            <input type="hidden" id="id" name="id_dep" value="${id_dep}">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                        <span class="alarma">${message}</span>
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

