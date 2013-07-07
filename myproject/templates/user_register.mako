<%inherit file="base_login.mako"/>
<%def name="title()"> CDSR | Register </%def>
<div id="register">
<h1>Register</h1>
<form action="${request.route_url('user_register')}" method="POST">


	<div class="half-form">
		<div class="label-part">	
    		<label for="first_name">first_name</label><br />
    		<label for="last_name">last_name</label><br />
   		 	<label for="email">email</label><br />
    		<label for="phone_no">phone_no</label><br />
		</div>
		<div class="input-part">
			<input type="text" name="first_name" id="first_name" value="First name"   onblur="if (this.value == '') {this.value = 'First name';}" onfocus="if (this.value == 'First name') {this.value = '';}"/><br />
			<input type="text" name="last_name" id="last_name"  value="Last name"  onblur="if (this.value == '') {this.value = 'Last name';}" onfocus="if (this.value == 'Last name') {this.value = '';}"/><br />
			<input type="text" name="email" id="email"  value="email" onblur="if (this.value == '') {this.value = 'email';}" onfocus="if (this.value == 'email') {this.value = '';}" /><br />
			<input type="text" name="phone_no" id="phone_no" value="Phone no"  onblur="if (this.value == '') {this.value = 'Phone no';}" onfocus="if (this.value == 'Phone no') {this.value = '';}" /><br />
		</div>
    
	</div>


	<div class="half-form">
		<div class="label-part">
			<label for="username">User Name</label><br />
    		<label for="password">password</label><br />
    		<label for="confirm-password">Confirm password</label><br />
		</div>
		<div class="input-part">
			<input type="text" name="username" id="username" value="Username" onblur="if (this.value == '') {this.value = 'Username';}" onfocus="if (this.value == 'Username') {this.value = '';}"/><br />
			<input type="password" name="password" id="password" /><br />
			<input type="password" name="confirm-password" id="confirm-password"   /><br />
		</div>

		
	</div>
	<div class="clear"></div><div class="spacer-height-8"></div>
	<input type="checkbox" name="agreement" value="agree" id="agreement" style="vertical-align:text-bottom;margin-left:15px; margin-right:15px;"/>Agree to our<a href="#"> Terms & Conditions</a> and <a href="#">Privacy Policy</a> 
	<div class="clear"></div>
		
		<div class="spacer-height-40"></div>
		<div class="spacer-width-26"></div>
				<div id="btn-box " class="btn-box-register">
                	<button type="submit" name="form.submitted" class="btn-aqua btn-aqua-register">Register</button>
					<button type="reset" name="reset" class="btn-yellow btn-yellow-register">Reset</button>
					<a name="cancel" href="${request.route_url('pyckauth_login')}" class="btn-red btn-red-register">Cancel</a>
				</div>
				<div id="btn-img-box " class="img-register">
					<img src="${request.static_url('myproject:static/plus.png')}"  class="spacer72 "  alt="icon-signin" id="icon-plus"/>
					<img src="${request.static_url('myproject:static/refresh.png')}"   class="spacer72 " alt="icon-signin" id="icon-refresh"/>
					<img src="${request.static_url('myproject:static/icon-cancel.png')}" class="spacer72 btn-red-position" alt="icon-cancel" id="icon-cancel"/>
				</div>
	
</form>

