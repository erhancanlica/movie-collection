<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <meta name="_csrf" th:content="${_csrf.token}"/>
    <meta name="_csrf_header" th:content="${_csrf.headerName}"/>

    <link rel="icon" type="image/png" sizes="16x16" th:href="@{/assets/favicon.png}">
    <link rel="stylesheet" th:href="@{/css/style.min.css}">
    <script th:src="@{https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js}"></script>
    <script th:src="@{https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js}"></script>
</head>

<body>
<div class="main-wrapper">
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>

    <div class="error-box">
        <div class="error-body text-center">
            <h1 class="error-title">403</h1>
            <h3 class="text-uppercase error-subtitle">FORBIDDON ERROR!</h3>
            <p class="text-muted m-t-30 m-b-30">YOU DON'T HAVE PERMISSION TO ACCESS ON THIS SERVER.</p>
            <a href="index.html" class="btn btn-info btn-rounded waves-effect waves-light m-b-40">Back to home</a></div>
    </div>
</div>

<script th:src="@{/js/jquery.min.js}"></script>
<script th:src="@{/js/popper.min.js}"></script>
<script th:src="@{/js/bootstrap.min.js}"></script>

<script>
    $('[data-toggle="tooltip"]').tooltip();
    $(".preloader").fadeOut();
</script>
</body>

</html>