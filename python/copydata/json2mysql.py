#################################################################
#    1. create a table (name like "station" or "timeseriesList") in MySQL Database
#    2. create all fields according to the display in website
#    3. set this tablename = "table name in Database"
#    4. set urladdr = "website you want to copy data"
#    5. change mysql ip, user, password if need
#    6. all above steps by manual
#    7. execute this script, it will copy all data on website to MySQL Database
#
#################################################################
import requests
import urllib.request
import json
import pymysql

taburl = ["station", "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getStationList&datasource=1&format=objson",
          "timeseriesList", "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getTimeseriesList&datasource=1&format=objson&ts_name=*&parametertype_name=*"
         ];
tablename = taburl[2]
urladdr = taburl[3]

hostname = "192.168.1.119"
username = "root"
passwdname = "cics@unitec"
portnum = 3306
databasename = "AirData"

con = pymysql.connect(host = hostname,user = username,passwd = passwdname,db = databasename, port=portnum)
try:

    cursor = con.cursor()

    response = urllib.request.urlopen(urladdr).read()
    json_obj = json.loads(response.decode('utf-8'))

    keylist = json_obj[0].keys()
    fieldstr = ", ".join(keylist)

    formatlist = [];
    for index in range(len(keylist)):
        formatlist.append("\"%s\"")
    formatstr =", ".join(formatlist)

    for index in range(len(json_obj)):
        vallist = [];
        for item in keylist:
            if len(json_obj[index][item]) == 0:
                json_obj[index][item] = 'NULL'
            vallist.append('\"'+json_obj[index][item]+'\"')
            valstr =", ".join(vallist)
        cursor.execute('INSERT INTO ' + tablename + '('+fieldstr+') VALUES('+valstr+')')
    print("finished db insert")
    con.commit()

except Exception as e:
    print(e)
finally:
    con.close()
