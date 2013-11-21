from sqlalchemy import (
    Column,
    Integer,
    Unicode,
    ForeignKey
    )
from sqlalchemy import ForeignKey
from sqlalchemy.orm import relationships
from . import DBSession, Base
from auth import User


# Create your models here.
class registeredSims(Base):
    __tablename__ = 'registeredSims'
    user_id = Column(Integer, ForeignKey(User.user_id))
    id = Column(Integer, primary_key= True)
    sim1 = Column(Integer, primary_key= True)
    sim2 = Column(Integer, primary_key= True)

class location(Base):
    __tablename__ = 'location'
    id = Column(Integer, primary_key=True)
    user_id = Column(Integer, ForeignKey(User.user_id))
    longitude = Column(Integer)
    latitude = Column(Integer)
    
class contacts(Base):
    __tablename__ = 'contacts'
    contactName = Column(Unicode(50), primary_key=True)
    user_id = Column(Integer, ForeignKey(User.user_id))
    email = Column(Integer)
    emailType = Column(Unicode(200))
    
class phoneNumbers(Base):
    __tablename__ = 'phoneNumbers'
    id = Column(Integer, primary_key=True)
    contactName = Column(Unicode(50), ForeignKey(contacts.contactName))
    ContactType = Column(Unicode(15), ForeignKey(User.user_id))
    number = Column(Integer)
    
class address(Base):
    __tablename__ = 'address'
    id = Column(Integer, primary_key=True)
    contactName = Column(Unicode(50), ForeignKey(contacts.contactName))
    City = Column(Unicode(25))
    Country = Column(Unicode(25))
    region = Column(Unicode(25))
    postalCode = Column(Unicode(25))
    street = Column(Unicode(25))
    
    
class sms(Base):
    __tablename__ = 'sms'
    id = Column(Integer, primary_key=True)
    user_id = Column(Integer, ForeignKey(User.user_id))
    smsType = Column(Unicode(30))
    toRfrom = Column(Integer)
    body = Column(Unicode(2000))