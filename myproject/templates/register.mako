

<%inherit file="base.mako"/>

<%def name="title()">
PyCK Project - Register :)
</%def>

<div>
<h1>Register :)</h1>

<form action="${request.route_url('user_register')}" method="POST">
    ${register_form.as_p() | n}
    <input type="submit" name="form.submitted" value="Send Email" />
</form>
<br /><br /><br /><br /><br /><br />
</div>