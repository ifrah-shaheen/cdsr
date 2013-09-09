'''
Created on Sep 2, 2013

@author: ifrah
'''
import android
import sys
import time
from time import strftime
import json
import string


class cdsr:

    def app_display(self):
        droid = android.Android()
        opt_list = ["Retrieve Contacts" , "Retrieve Location" ,  "Retrieve SMS" , "Retrieve  Photos" , "Retrieve Videos"]
        d=droid.dialogCreateAlert("select file")
        droid.dialogSetSingleChoiceItems(opt_list)
        droid.dialogSetPositiveButtonText('ok')
        droid.dialogSetNegativeButtonText('cancel')
        droid.dialogShow()
        r=droid.dialogGetResponse().result
        result = droid.dialogGetSelectedItems().result[0]
        opt_chosen = opt_list [result] 
        if r['which']== "negative":
            return
        else:
            if opt_chosen == "Retrieve Contacts":
                cdsr().get_contacts()
                cdsr().app_display()
            elif opt_chosen == "Retrieve Location":
                cdsr().get_location()
                cdsr().app_display()
	    elif opt_chosen == "Retrieve SMS":
                cdsr().get_sms()
                cdsr().app_display()

    def get_sms(self):
	droid = android.Android()
	msgs=droid.smsGetMessages(False)
	print msgs
	msg_list = []
	for msg in msgs.result:
		"""writing SMS in JSON file"""
	    	saved = sys.stdout
		f = file ('sms.json', 'wb')
		sys.stdout = f
		msg_list.append(msg)
		print json.dumps(msg_list) #Print it out at the end.
	sys.stdout = saved
	f.close()

    
    def get_contacts(self):
        droid = android.Android()
        c = droid.contactsGet()
	list_out = [] #Create one output dict
        for con in c.result:
		print con
	for con in c.result:
		"""writing contacts in JSON file"""
	    	saved = sys.stdout
		f = file ('contacts.json', 'wb')
		sys.stdout = f
		list_out.append(con)
		print json.dumps(list_out) #Print it out at the end.
	sys.stdout = saved
	f.close()
        """
        droid = android.Android() 
        contacts = droid.getConstants("android.provider.ContactsContract$CommonDataKinds$Phone").result
        count = droid.contactsGetCount
        for c in contacts: 
          print c 
        """
        print"-------------"
        droid.makeToast( "Retrieved" )

	


    def get_location(self):
	droid = android.Android()
	if not droid.locationProviderEnabled(u'gps').result:
    		droid.dialogCreateAlert("Enable GPS", "Please enable GPS before using this app")
    		droid.dialogSetPositiveButtonText("OK")
    		droid.dialogShow()
    		result = droid.dialogGetResponse().result
    		droid.dialogDismiss()
    		droid.startActivity('android.settings.LOCATION_SOURCE_SETTINGS')
    		sys.exit()

	droid.startLocating()
	loc = droid.readLocation().result
	while loc == {}:
    		loc = droid.readLocation().result
    		if loc == {}:
 			droid.makeToast("No lcation data yet!")
    		if loc != {}:
			try:
            			l = loc['gps']
			except KeyError:
	    			l = loc['network']
        		outstr = 'longitude:' + str(l['longitude']) + ',' + 'Latitude' + str(l['latitude'])
        		print(outstr)
			lo=l['longitude']
			la=l['latitude']
			address = droid.geocode(la, lo).result
			temp = address[0]
			area=temp['locality']
			city=temp['admin_area']
			street=temp['feature_name']
			country=temp['country_name']
			new_address='locality:'+area+',city:'+city+',street:'+street +',country:'+country
        		droid.dialogCreateAlert("Found Location", outstr)
        		droid.dialogSetPositiveButtonText("OK")
        		droid.dialogShow()
        		result = droid.dialogGetResponse().result
        		droid.dialogDismiss()
			droid.dialogCreateAlert("Found Location", new_address)
        		droid.dialogSetPositiveButtonText("OK")
        		droid.dialogShow()
        		result = droid.dialogGetResponse().result
        		droid.dialogDismiss()
    		else:
			time.sleep(10)

	droid.stopLocating()
	"""writing coordinate in JSON file"""
	saved = sys.stdout
	f = file ('location.json', 'wb')
	sys.stdout = f
	loc_dict = {}
	loc_dict = {"longitude" : lo , "latitude" : la}
	print json.dumps(loc_dict) #Print it out at the end.
	sys.stdout = saved
	f.close()


                
if __name__ == '__main__':
    
    #cdsr().get_contacts()
    #cdsr().get_location()
    cdsr().app_display()
    
    """'''
Created on Sep 2, 2013

@author: ifrah
'''
import android
import kivy
from kivy.lang import Builder
from kivy.uix.gridlayout import GridLayout
from kivy.app import App

class cdsr(App):
    def get_contacts(self):
        c = droid.contactsGet()
        for con in c.result:
            print con
        droid = android.Android() 
        contacts = droid.getConstants("android.provider.ContactsContract$CommonDataKinds$Phone").result
        count = droid.contactsGetCount
        for c in contacts: 
          print c 
       
        print"-------------"
        droid.makeToast( "Retrieved" )
        
    
if __name__ == '__main__':"""    
    
    
    
    
