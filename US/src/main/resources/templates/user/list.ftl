<html>
<body>
<div>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>用户名</td>
            <td>密码</td>
            <td>创建时间</td>
            <td>修改时间</td>
            <td>用户状态</td>
        </tr>
        </thead>
        <tbody>
        <#list userList as users>
        <tr>
            <td>${users.id}</td>
            <td>${users.username}</td>
            <td>${users.password}</td>
            <td>${users.ct?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td>${users.ut?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td>${users.getUserStatusEnum().status}</td>

        </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>