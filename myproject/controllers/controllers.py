import hashlib
from pyramid.view import view_config
from pyramid.httpexceptions import HTTPFound
from auth import User
from ..models import (
    DBSession, User
    )

from ..forms import ContactForm


@view_config(route_name='home', renderer='home.mako')
def my_view(request):
    one = None
    return {'one': one, 'project': 'myproject'}

@view_config(route_name='user_register', renderer='user_register.mako')
def user_register(request):

    if "POST" == request.method:
        U = User() 
        U.user_id = request.POST['username']
        U.password = hashlib.sha1(request.POST['password']).hexdigest()
        U.first_name = request.POST['first_name']  
        U.last_name = request.POST['last_name']  
        U.email = request.POST['email'] 
        U.phone_no = request.POST['phone_no'] 
        DBSession.add(U)

        request.session.flash("User Added Successfully!")
        return HTTPFound(location=request.route_url('home'))

    return{}

        

@view_config(route_name='data_in', renderer='data_in.mako')
def data_in(request):
    
    request.session.flash("data recieved Successfully!")

    return{}




@view_config(route_name='contact', renderer="contact.mako")
def contact_form(request):

    f = ContactForm(request.POST)   # empty form initializes if not a POST request

    if 'POST' == request.method and 'form.submitted' in request.params:
        if f.validate():
            #TODO: Do email sending here.

            request.session.flash("Your message has been sent!")
            return HTTPFound(location=request.route_url('home'))

    return {'contact_form': f}
