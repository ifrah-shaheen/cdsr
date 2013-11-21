import hashlib
import json
from pyramid.view import view_config
from pyramid.httpexceptions import HTTPFound
from auth import User
from ..models import (
    DBSession, User, registeredSims, location, contacts, phoneNumbers, address, sms
    )

@view_config(route_name='data', renderer="data.mako")
def data(request):
	print "-------------------------------------------------"
	str = request.body
	incomingData = json.loads(request.POST['JSONdata'])

	print incomingData
	
	for key, values in incomingData.iteritems():
	    dataType = key;
	    
	    if (key == "SMS"):
		print "---------------- SMS ----------------"
		
		
		#s.user_id = values['user_id']
		for item in values:
		    for inboxORsent, msgs in item.iteritems():
			if (inboxORsent == "inbox"):
			    
			    print"------its inbox data-----------"
			    for msg in msgs:
				s = sms()
				print "----------------- one msg --------------------"
				#for msgKey, msgValue in msg.iteritems():
				print "----------"
				s.smsType = "inbox"
				s.toRfrom = msg['phone']
				s.body = msg['body']
				DBSession.add(s)
				#print "key is " + msgKey
				#print "vale is " + msgValue
			elif(inboxORsent == "sent"):
			    
			    print"------its sent data-----------"
			    for msg in msgs:
				s = sms()
				print "----------------- one msg --------------------"
				s.smsType == "inbox"
				s.toRfrom = msg['phone']
				s.body = msg['body']
				DBSession.add(s)
		
			
	    elif (key == "Contacts"):
		print "-----------------------------------contacts -------------------"
		for contact in values:
		    c=contacts()
		    
		    #print "--------------------------------------------- contact 1 ---------------------------------------------------------------------"
		    for DetailsKey, DetailsValue in contact.iteritems():
			p=phoneNumbers()
			if(DetailsKey == "Phone"):
			    for phoneType, phoneNumber in DetailsValue.iteritems():
				if(phoneType == "Mobile"):
				    p.contactName=contact['name']
				    p.contacType= "Mobile"
				    p.number = phoneNumber
				    
				elif(phoneType == "Home"):
				  p.contactName=contact['name']
				  p.contacType= "Home"
				  p.number = phoneNumber
				    
				elif(phoneType == "Work"):
				  p.contactName=contact['name']
				  p.contacType= "Work"
				  p.number = phoneNumber
				  
				elif(phoneType == "Work Fax"):
				  p.contactName=contact['name']
				  p.contacType= "Work Fax "
				  p.number = phoneNumber
				  
				elif(phoneType == "Home Fax"):
				  p.contactName=contact['name']
				  p.contacType= "Home Fax"
				  p.number = phoneNumber
				    
				elif(phoneType == "Pager"):
				  p.contactName=contact['name']
				  p.contacType= "Pager"
				  p.number = phoneNumber
				  
				elif(phoneType == "other"):
				  p.contactName=contact['name']
				  p.contacType= "other"
				  p.number = phoneNumber
				  
				elif(phoneType == "Custom"):
				  p.contactName=contact['name']
				  p.contacType= "Custom"
				  p.number = phoneNumber
				  
				elif(phoneType == "Callback"):
				  p.contactName=contact['name']
				  p.contacType= "Callback"
				  p.number = phoneNumber
		    
				elif(phoneType == "Car"):
				  p.contactName=contact['name']
				  p.contacType= "Car"
				  p.number = phoneNumber
				  
				elif(phoneType == "Comapany Main"):
				  p.contactName=contact['name']
				  p.contacType= "Company Main"
				  p.number = phoneNumber
				  
				elif(phoneType == "ISDN"):
				  p.contactName=contact['name']
				  p.contacType= "ISDN"
				  p.number = phoneNumber
				  
				elif(phoneType == "Main"):
				  p.contactName=contact['name']
				  p.contacType= "Main"
				  p.number = phoneNumber
				  
				elif(phoneType == "Other Fax"):
				  p.contactName=contact['name']
				  p.contacType= "Other Fax"
				  p.number = phoneNumber
				  
				elif(phoneType == "Radio"):
				  p.contactName=contact['name']
				  p.contacType= "Radio"
				  p.number = phoneNumber
				  
				elif(phoneType == "Telex"):
				  p.contactName=contact['name']
				  p.contacType= "Telex"
				  p.number = phoneNumber
				  
				elif(phoneType == "TTY TDO"):
				  p.contactName=contact['name']
				  p.contacType= "TTY TDO"
				  p.number = phoneNumber
				  
				elif(phoneType == "Work Mobile"):
				  p.contactName=contact['name']
				  p.contacType= "Work Mobile"
				  p.number = phoneNumber
				  
				elif(phoneType == "Work Pager"):
				  p.contactName=contact['name']
				  p.contacType= "Work Pager"
				  p.number = phoneNumber
				  
				elif(phoneType == "Assistant"):
				  p.contactName=contact['name']
				  p.contacType= "Assistant"
				  p.number = phoneNumber
				  
				elif(phoneType == "MMS"):
				  p.contactName=contact['name']
				  p.contacType= "MMS"
				  p.number = phoneNumber
			    DBSession.add(p)	  
			if(DetailsKey == "Email"):
			    c.email=contact['Email']
			    for emailType, emailAddress in DetailsValue.iteritems():
				if(emailType == "Home"):
				    c.emailType="Home"
				elif(emailType == "Work"):
				    c.emailType="Work"
				elif(emailType == "Other"):
				    c.emailType="Other"
			if(DetailsKey == "name"):
			    c.emailType=DetailsValue
			if(DetailsKey == "Address"):
			    for address in DetailsValue:
				print "address"
				for addressField, fieldValue in address.iteritems():
				    print addressField + ": "+ fieldValue
			DBSession.add(c)
	    elif (values == 'location'):
		
		print "-------- location --------"
		l = location()
		l.longitude = float(incomingData['longitude'])
		l.latitude = float(incomingData['latitude'])
		l.user_id = 'ifrah'
		DBSession.add(l)
			    
	    elif (key == 'UserSettings'):		    
		print "------ setting ----------"
		U = User() 
		U.user_id = values['Email']
		U.password = hashlib.sha1(values['ServerPassword']).hexdigest()
		U.appPassword = values['AppPassword']  
		DBSession.add(U)
		sims = registeredSims()
		sims.user_id = values['Email']
		sims.sim1 = values['Sim1']
		sims.sim2 = values['Sim2']
		DBSession.add(sims)
		    
	    elif (key == 'adminSettings'):
		for adminKey, adminValue in values.iteritems():
		    print adminKey
		    if(adminValue == False):
			print "Admin Disabled"
		
		
	return{ }
