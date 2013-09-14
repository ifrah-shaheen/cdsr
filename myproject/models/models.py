from sqlalchemy import (
    Column,
    Integer,
    Unicode,
    ForeignKey
    )

from . import DBSession, Base
from auth import User


# Create your models here.
class user_numbers(Base):
    __tablename__ = 'user_numbers'


    user_id = Column(Integer, ForeignKey(User.user_id))
    cell_number1 = Column(Integer, unique=True)
    cell_number2 = Column(Integer, unique=True)
    cell_number3 = Column(Integer, unique=True)
    id = Column(Integer, primary_key= True)

class location(Base):
    __tablename__ = 'location'
    id = Column(Integer, primary_key=True)
    user_id = Column(Integer, ForeignKey(User.user_id))
    longitude = Column(Integer)
    latitude = Column(Integer)
    cell_number = Column(Integer)
    



