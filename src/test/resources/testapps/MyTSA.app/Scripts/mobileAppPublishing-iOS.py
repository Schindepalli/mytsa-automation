'''
    Created on Oct 22, 2015
    
    @author: rzavaleta
    '''
#  com.apperian.eas.user.authenticateuser
#  com.apperian.eas.apps.create
#  com.apperian.eas.apps.publish


import requests,os,urllib2
from requests.exceptions import ConnectionError
import json
import sys
import datetime
from subprocess import check_output

class appPublishManager:
    '''
        classdocs
        '''
    token = None
    xudid = None
    uploadUrl= None
    transactionID=None
    text=None
    appList= None
    id= 1
    apiVersion = '1.0'
    jsonRpc = '2.0'
    fileID=None
    created = False
    appID = None
    pskValue = None
    
    fileHeaders = {'content-type': 'application/octet-stream'}
    
    authenticateMethod='com.apperian.eas.user.authenticateuser'
    createMethod = 'com.apperian.eas.apps.create'
    publishMethod='com.apperian.eas.apps.publish'
    getApplistMethod='com.apperian.eas.apps.getlist'
    updateAppMethod='com.apperian.eas.apps.update'
    
    headers = {'content-type': 'application/js'}
    
    # TSA end point
    endpoint = 'https://easesvc.tsa.dhs.gov/ease.interface.php'
    authenticateToEnableURL='https://ws.tsa.dhs.gov/v1/users/authenticate/'
    getApplicationsURL='https://ws.tsa.dhs.gov/v1/applications/'
    
    
    
    def authenticate(self, user_id, password):
        param={'email': user_id, 'password': password}
        payload = {'id': self.id, 'apiVersion': self.apiVersion, 'method': self.authenticateMethod,
            'params':param ,
                'jsonrpc': self.jsonRpc}
        try:
            
            print 'Authenticating using ' + self.endpoint +'/'+ self.authenticateMethod + ', user ' + user_id
            r = requests.post(self.endpoint, data=json.dumps(payload), headers=self.headers)
        except ConnectionError as e:
            print "authenticate: Connection Error: "
            sys.exit(1)

        if r.status_code == requests.codes.ok:
            self.token = json.loads(r.text)['result']
            self.token = self.token['token']

        else:
            print 'authenticate: return status code NOT ok.'
            print r.status_code
            print r.text



    def getAppList(self,token):
        
        param={'token': token}
        
        payload = {
                    'id': self.id,
                    'apiVersion': self.apiVersion,
                    'method': self.getApplistMethod,
                    'params':param ,
                    'jsonrpc': self.jsonRpc
                }

        try:
            
            r = requests.post(self.endpoint, data=json.dumps(payload), headers=self.headers)
        
            if r.status_code == requests.codes.ok:
                self.appList = json.loads(r.text)['result']
                self.appList = self.appList['applications']

            else:
                print 'getlist: return status code NOT ok.'
                print r.status_code
                print r.text

            return self.appList
                
        except ConnectionError as e:
            print "authenticate: Connection Error: "
            print e
            sys.exit(1)



    def initCreate(self,token):
        
        param={'token': token}
        
        payload = {
                    'id': self.id,
                    'apiVersion': self.apiVersion,
                    'method': self.createMethod,
                    'params':param ,
                    'jsonrpc': self.jsonRpc
                }
                    
        try:
            
            r = requests.post(self.endpoint, data=json.dumps(payload), headers=self.headers,stream=True)
        
            if r.status_code == requests.codes.ok:
                print "^^^^^^^^^ Status Code OK"
                self.text = json.loads(r.text)['result']
                self.uploadUrl = self.text['fileUploadURL']
                self.transactionID =self.text['transactionID']
            
            else:
                print "initCreate: return status code NOT ok."
                print r.status_code
                print r.text
        
        
        except ConnectionError as e:
            print "initCreate: Connection Error: "
            print e
            sys.exit(1)



    def updateApp(self,token, appID):

        param={'appID': appID, 'token': token}
        
        payload = {
                    'id': self.id,
                    'apiVersion': self.apiVersion,
                    'method': self.updateAppMethod,
                    'params':param,
                    'jsonrpc': self.jsonRpc
                }

        try:
            
            r = requests.post(self.endpoint, data=json.dumps(payload), headers=self.headers,stream=True)

            if r.status_code == requests.codes.ok:
                json_data = json.loads(r.text)
                print json_data
                self.text = json.loads(r.text)['result']
                self.uploadUrl = self.text['fileUploadURL']
                self.transactionID =self.text['transactionID']
            
            else:
                print 'initCreate: return status code NOT ok.'
                print r.status_code
                print r.text
    
        except ConnectionError as e:
            print "initCreate: Connection Error: "
            print e
            sys.exit(1)



    def upload_file(self, upload_file, upload_url):
        
        print "Attempting to upload " + upload_file + " to " + upload_url

        try:
            with open(upload_file, 'rb') as f:
                f.close()
            
            ret = json.loads(check_output("curl --form LUuploadFile=@" + upload_file + " " + upload_url, shell=True))
            
            print ret
            
            file_id = ret['fileID']
        
        except IOError:
            print 'Missing file or no access rights (' + upload_file + ')'
            file_id = None
        
        return file_id



    def publish_transaction(self, meta, fileID):
        
        resource = self.endpoint
        
        headers = {'content-type': 'application/json'}
            
        payload = '{ "id" : '+str(self.id)+',"jsonrpc" : "'+self.jsonRpc+'", "apiVersion": "'+self.apiVersion+'", "method" :"'+ self.publishMethod+'" , "params" : {"EASEmetadata" :{"author" : "' + \
            meta['author'] + '","longdescription" : "' + meta['longdescription'] + '","name" : "' + \
            meta['name'] + '","shortdescription" : "' + meta['shortdescription'] + '","version" : "' + \
            meta['version'] + '","versionNotes" : "' + \
            meta['versionNotes'] + '"}, "files" :{"application" : "' + fileID + '"}, "token" : "' + self.token + '", "transactionID" : "' + self.transactionID + '"}}'
        
        print 'Attempting to publish app ' + meta['name']
        
        r = requests.post(resource, data=payload, headers=headers, stream=True)
        
        print r.status_code

        print json.dumps(r.text, indent=4, sort_keys=True)

        if r.status_code == 200:
            result = json.loads(r.text)['result']
            return result

        else:
            print r.status_code
            print r.text
            return None



    def authenticateToEnable(self, user_id, password):
        
        fileHeaders = {'content-type': 'application/json'}
        
        payload = {'user_id': user_id, 'password': password}
        
        try:
            
            r = requests.post(self.authenticateToEnableURL, data=json.dumps(payload), headers=fileHeaders)
        
            if r.status_code == requests.codes.ok:
                print "authenticateToEnable token"
                self.token = json.loads(r.text)
                self.token = self.token['token']
                print json.dumps(self.token)
            else:
                print 'authenticateToEnable: return status code NOT ok.'
                print r.status_code
                print r.text
    
        except ConnectionError as e:
            print "authenticate: Connection Error: "
            print e
            sys.exit(1)
    
    

    def getListForPSK(self, token):

        fileHeaders = {'X-TOKEN': token}
        
        try:
            print 'Authenticating using ' + self.getApplicationsURL

            r = requests.get(self.getApplicationsURL, headers=fileHeaders)
        
            if r.status_code == requests.codes.ok:
                applicationList = json.loads(r.text)['applications']

                for currentApp in applicationList:
                    if currentApp['name'] == appName:
                        print "Found " + appName + " for PSK"
                        self.pskValue = currentApp['psk']
                        print self.pskValue
                    
                    else:
                        print "Not " + appName + " app for PSK"

            else:
                print "authenticate: return status code NOT ok."
                print r.status_code
                print r.text

        except ConnectionError as e:
            print "authenticate: Connection Error: "
            print e
            sys.exit(1)



    def enableApp(self, psk, token):

        fileHeaders = {'X-TOKEN': token, 'Content-Type': 'application/json'}

        payload = {'enabled': True }

        try:
            url = self.getApplicationsURL + str(psk)
            
            print url
            
            print requests.put(url, data=json.dumps(payload), headers=fileHeaders)
            
            r = requests.put(url, data=json.dumps(payload), headers=fileHeaders)
        
            print json.dumps(r.text)
            
            if r.status_code == requests.codes.ok:
                print "Updated to enable"
            
            else:
                print r.status_code
                print "Could not enable"
    
            sys.exit(0)
    
    
        except ConnectionError as e:
            print "authenticate: Connection Error: "
            print e
            sys.exit(1)




filePath = sys.argv[1]

appName = sys.argv[2]

app=appPublishManager()

app.authenticate('mobile_deployer_dev','Mad2015#$')

app.getAppList(app.token)


for currentApp in app.appList:
    
    if currentApp['name'] == appName:
        print "Found " + appName
        app.created = True
        print currentApp['ID']
        app.appID = currentApp['ID']
    
    else:
        print "Not " + appName + " app"


if app.created == False:
    print "********* initCreate Run"
    app.initCreate(app.token)

else:
    print "********* updateApp Run"
    app.updateApp(app.token, app.appID)


app.fileID = app.upload_file(filePath, app.uploadUrl)


metaData = {
            "author" : "TSA",
            "longdescription" : "iOS CI publish to TEAC",
            "name" : appName,
            "shortdescription" : "iOS CI publish - " + str(datetime.datetime.now()),
            "version" : "1.0",
            "versionNotes" : "CI published this at: " + str(datetime.datetime.now())
            }


res = app.publish_transaction(metaData, app.fileID)

if res is None:
    print "Error publishing"
    sys.exit(1)

if res['status'] == 'complete':
    print "Published " + metaData['name']

else:
    print "Error publishing, error: " + res['status']
    sys.exit(1)

app.authenticateToEnable('mobile_deployer_dev','Mad2015#$')

app.getListForPSK(app.token)

print app.token

app.enableApp(app.pskValue, app.token)






