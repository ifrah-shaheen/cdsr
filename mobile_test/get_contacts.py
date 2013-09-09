import android
droid=android.Android()
atts = droid.contactsGetAttributes()
atts = atts.result
contacts = droid.contactsGet(atts)
contacts = contacts.result

#contacts[0]
#{u'display_name': u'jethro@verkkokauppazeus.fi', u'notes': u'', u'times_contacted': u'0', u'send_to_voicemail': u'0', u'primary_email': u'62', u'phonetic_name': u'', u'starred': u'0', u'_id': u'29'}
#contacts[5]
#{u'display_name': u'candidone79@yahoo.com', u'notes': u'', u'times_contacted': u'0', u'send_to_voicemail': u'0', u'primary_email': u'93', u'phonetic_name': u'', u'starred': u'0', u'_id': u'34'}

