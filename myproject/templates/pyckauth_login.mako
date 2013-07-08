<%inherit file="base_login.mako"/>

<%def name="title()">
CDSR | Login
</%def>

<%def name="header()">
  <div id="top" style="text-align: center">
    <br /><br />
    <img src="${request.static_url('myproject:static/logo-cdsr.png')}"  width="290px" height="126px" alt="pyck"/>
  </div>
</%def>
<%def name="title()"> CDSR | Login </%def>
<%def name="main_menu()"></%def>
<%def name="footer()"></%def>

<%def name="body_class()">login_page</%def>

<br />
<center>
    <div class="loginbox">
        <div class="loginbox_inner">
            <form action="${request.route_url('pyckauth_login')}" method="POST">
            <div class="loginbox_content">
                <img src="${request.static_url('myproject:static/icon-username.png')}" class="username-icons"/><input type="text" name="user_id" class="username" value="Username" onblur="if (this.value == '') {this.value = 'Username';}" onfocus="if (this.value == 'Username') {this.value = '';}"/><br />
                <img src="${request.static_url('myproject:static/icon-key.png')}" class="password-icons"/><input type="password" name="password" class="password spacer-left-35" value="Password" onblur="if (this.value == '') {this.value = 'Password';}" onfocus="if (this.value == 'Password') {this.value = '';}"/><br />


				<div class="button" >
					<div class="img-section dark-blue">
						<img src="${request.static_url('myproject:static/plane.png')}" class="" alt="icon-signin"/>
					</div>
					<div class="btn-section ">
						<button type="submit" name="form.submitted" class="btn-blue">SignIn</button>
					</div>				
				</div>			

				<div class="button" >
					<div class="img-section dark-yellow">
						<img src="${request.static_url('myproject:static/refresh.png')}"   class=" " alt="icon-reset" id=""/>
					</div>
					<div class="btn-section ">
						<button type="reset" name="reset" class="btn-yellow">Reset</button>
					</div>				
				</div>	


				<div class="button" >
					<div class="img-section dark-aqua">
						<img src="${request.static_url('myproject:static/plus.png')}"   alt="icon-signin" />
					</div>
					<div class="btn-section btn-aqua">
						<a name="SignUp" href="${request.route_url('user_register')}" class="">SignUp</a>
					</div>				
				</div>	


            </div>
            </form>
        </div>
    </div>
</center>
