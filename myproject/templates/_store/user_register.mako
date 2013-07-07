<%inherit file="base.mako"/>

<div>
<h1>Register</h1>
<form action="${request.route_url('user_register')}" method="POST">
    <label for="username">User Name</label>
    <input type="text" name="username" id="username" />
    <br />
    <label for="password">password</label>
    <input type="text" name="password" id="password" />
    <br />
    <label for="first_name">first_name</label>
    <input type="text" name="first_name" id="first_name" />
    <br />
    <label for="last_name">last_name</label>
    <input type="text" name="last_name" id="last_name" />
    <br />
    <label for="email">email</label>
    <input type="text" name="email" id="email" />
    <br />
    <label for="phone_no">phone_no</label>
    <input type="text" name="phone_no" id="phone_no" />
    <br />
    <input type="submit" value="Add User" />
</form>

