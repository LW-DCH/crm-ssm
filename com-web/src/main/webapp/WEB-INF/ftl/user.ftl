<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        table {
            width: 600px;
            text-align: center;
            border-collapse: collapse;
            margin-top: 50px;
        }

        th, td {
            border: 1px solid #ccc;
            line-height: 40px;
        }
    </style>
</head>
<body>

<table>
    <tr>
        <th>用户名id</th>
        <th>登录名</th>
        <th>邮箱</th>
        <th>手机号</th>
        <th>登录密码</th>
    </tr>
    <tr>
        <td>${userID}</td>
        <td>${userName}</td>
        <td>${email}</td>
        <td>${phone}</td>
        <td>${password}</td>
    </tr>
</table>

</body>
</html>