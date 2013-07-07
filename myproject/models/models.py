from sqlalchemy import (
    Column,
    Integer,
    Unicode,
    )

from . import DBSession, Base


# Create your models here.
class user(Base): 
    __tablename__ = 'user' 

    id=Column(Integer, primary_key=True, autoincrement=True)
    username=Column(Unicode(200), unique=True) 
    password=Column(Unicode(200),unique=False)
    first_name= Column(Unicode(200)) 
    last_name= Column(Unicode(200)) 
    email=Column(Unicode(200),unique=True) 
    phone_no=Column(Integer,unique=False)
