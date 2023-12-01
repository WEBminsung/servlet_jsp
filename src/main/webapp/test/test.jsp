<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ES6 Template Literal in HTML</title>
</head>
<body>
<script>
    <%
//    request.setAttribute("JSESSIONID", "hello");
    %>
    var a = <%=request.getAttribute("JSESSIONID") == null%>;
    if(a){
        console.log("true임");
    }else{
        console.log(a);
    }
</script>

</body>
</html>
