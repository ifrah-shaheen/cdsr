from pyck.forms import Form
from wtforms import TextField, TextAreaField, validators, BooleanField, PasswordField


class RegisterForm(Form):
    first_name = TextField('First Name', [validators.required("Name cannot be empty")])
    last_name = TextField('Last Name', [validators.required("Name cannot be empty")])
    username = TextField('Username', [validators.Length(min=4, max=25)])
    password = PasswordField('New Password', [
        validators.Required(),
        validators.EqualTo('confirm', message='Passwords must match')
    ])
    confirm = PasswordField('Repeat Password')
    email = TextField('Email Address', [validators.Length(min=6, max=35),
                                        validators.Email(message="Invalid email format")])
    message = TextAreaField("Message", [validators.required("Message cannot be empty")])
    accept_tos = BooleanField('I accept the TOS', [validators.Required()])
    
    
   
__all__ = ['ContactForm', 'RegisterForm' 'UserForm']