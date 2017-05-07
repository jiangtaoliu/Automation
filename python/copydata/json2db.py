import requests
import urllib.request
import json
import pymysql

taburl = ["station", "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getStationList&datasource=0&format=objson",
            "timeseriesList", "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getTimeseriesList&datasource=0&format=objson&ts_name=*&parametertype_name=*",
            #timeseriesValues: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getTimeseriesValues&datasource=0&format=objson",
            #timeseriesValuesLayer: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getTimeseriesValueLayer&datasource=0&format=objson",
            #sitesList: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getSiteList&datasource=0&format=objson",
            #parametersList: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getParameterList&datasource=0&format=objson",
            #parameterTypeList: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getParameterTypeList&datasource=0&format=objson",
            #timeseriesTypes: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getTimeseriesTypeList&datasource=0&format=objson",
            "groupsList", "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getGroupList&datasource=0&format=objson"
            #graph: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getGraph&datasource=0&format=objson",
            #graph templates: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getGraphTemplateList&datasource=0&format=objson",
            #atchmentsList: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getCatchmentList&datasource=0&format=objson",
            #riversList: "http://aklc.hydrotel.co.nz:8080/KiWIS/KiWIS?service=kisters&type=queryServices&request=getRiverList&datasource=0&format=objson"
            ];
tablename = taburl[2]
urladdr = taburl[3]

hostname = "127.0.0.1"
username = "root"
passwdname = "bamboo527"
portnum = 3306
databasename = "AirData"


con = pymysql.connect(host = hostname,user = username,passwd = passwdname,db = databasename, port=portnum)
try:

    cursor = con.cursor()

    url = urladdr
    response = urllib.request.urlopen(url).read()
    json_obj = json.loads(response.decode('utf-8'))

    keylist = json_obj[0].keys()
    fieldstr = ", ".join(keylist)

    formatlist = [];
    for index in range(len(keylist)):
        formatlist.append("\"%s\"")
    formatstr =", ".join(formatlist)

    #vallist = [];
    #for item in keylist:
    #    vallist.append("row[\""+item+"\"]")
    #valstr =", ".join(vallist)
    #for item in keylist:
    #    if len(json_obj[0][item]) == 0:
    #        json_obj[0][item] = 'NULL'
    #    vallist.append(json_obj[0][item])
    #    valstr =", ".join(vallist)
    #print(valstr)
    #print('INSERT INTO '+tablename+'('+fieldstr+') VALUES('+formatstr+'), ('+valstr+')')

    for index in range(len(json_obj)):
        vallist = [];
        for item in keylist:
            if len(json_obj[index][item]) == 0:
                json_obj[index][item] = 'NULL'
            vallist.append('\"'+json_obj[index][item]+'\"')
            valstr =", ".join(vallist)
        #print('INSERT INTO '+tablename+'('+fieldstr+') VALUES('+formatstr+'), ('+valstr+')')
        cursor.execute('INSERT INTO ' + tablename + '('+fieldstr+') VALUES('+valstr+')')


    #print(json_obj[2]["station_name"], json_obj[2]["station_no"], json_obj[2]["station_id"], float(json_obj[2]["station_latitude"]), json_obj[2]["station_longitude"])

    #cursor.execute('INSERT INTO ' + tablename + ' (station_name, \
    #      station_no, station_id, station_latitude, station_longitude) \
    #      VALUES("%s", "%s", "%s", "%s", "%s")', (json_obj[2]["station_name"], \
    #      json_obj[2]["station_no"], json_obj[2]["station_id"], \
    #      float(json_obj[2]["station_latitude"]), float(json_obj[2]["station_longitude"])))

    #cursor.execute('INSERT IGNORE INTO ' + tablename + ' (station_name, \
    #    station_no, station_id, station_latitude, station_longitude) \
    #    VALUES("%s", "%s", "%s", "%s", "%s")', ('925/1', '6456001', '6456001', float('-36.59414'), 174.652153))

    print("finished db")
    con.commit()

except Exception as e:
    print(e)
finally:
    con.close()
