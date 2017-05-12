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
import traceback

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

    vallist = [];
    for index in range(len(json_obj)):
        vallist.append(json_obj[index]["ts_id"])
    #print(vallist)
    vallist.sort()

    urldict = {}
    for tsid in vallist:
        urldict[tsid] = "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getTimeseriesValues&datasource=1&format=json&ts_id="+tsid+"&period=complete"
    #print(urldict)

    for (k,v) in urldict.items():
        ts_resp = urllib.request.urlopen(v).read()
        ts_json_obj = json.loads(ts_resp.decode('utf-8'))

        tablename = "tsid"+k+"tbl"
        print("CREATE TABLE IF NOT EXISTS "  + tablename + "(TSTimestamp varchar(255), TSValue varchar(255))")
        cursor.execute("CREATE TABLE IF NOT EXISTS "  + tablename + "(TSTimestamp varchar(255), TSValue varchar(255))")
        con.commit()


        objlist = ts_json_obj[0]['data']
        i = 0
        for eachts in objlist:
            #print(eachts[0])
            tsp = eachts[0]
            va = '{}'.format(eachts[1])
            if va is None:
                va = 'NULL'
            #print('INSERT INTO ' + tablename + ' (TSTimestamp, TSValue) VALUES("' + tsp +'","' + va + '")')
            cursor.execute('INSERT INTO ' + tablename + ' (TSTimestamp, TSValue) VALUES("' + tsp +'","' + va + '")')
            i = i+1
            if (i%5000 == 0):
                con.commit()
        con.commit()
        #break

    print("finished db insert")
    con.commit()

except Exception as e:
        print("=== STEP ERROR INFO START")
        traceback.print_exc()
        print("=== STEP ERROR INFO END")
finally:
    con.close()
